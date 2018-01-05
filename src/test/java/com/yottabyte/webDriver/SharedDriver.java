package com.yottabyte.webDriver;

import com.yottabyte.config.ConfigManager;
import com.yottabyte.utils.TakeScreenShot;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.UnreachableBrowserException;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;


public class SharedDriver extends EventFiringWebDriver {
    private static WebDriver REAL_DRIVER = null;
    private static Scenario scenario;
    private static TakeScreenShot screenShot = new TakeScreenShot();
    private static ChromeDriverService service;
    private static final Thread CLOSE_THREAD = new Thread() {
        @Override
        public void run() {
            REAL_DRIVER.close();
            if (service != null && service.isRunning()){
                service.stop();
            }
        }
    };

    static {
        Runtime.getRuntime().addShutdownHook(CLOSE_THREAD);
        ConfigManager config = new ConfigManager();

        DesiredCapabilities browser = null;
        if ("chrome".equalsIgnoreCase(config.get("browser"))){
            browser = ChromeDes();
        }else if ("firefox".equalsIgnoreCase(config.get("browser"))){
            browser = FirefoxDes();
        }else if ("internet Explorer".equalsIgnoreCase(config.get("browser"))){
            browser = IEDes();
        }else {
            System.out.println("没有找到对应浏览器类型");
        }
        EventListener eventListener = new EventListener();

        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.BROWSER, Level.ALL);

        browser.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
        String ServerHOst;
        try {
            ServerHOst = config.get("selenium_server_host");
            URL url = new URL("http://" + ServerHOst + ":4444/wd/hub");
            REAL_DRIVER = new RemoteWebDriver(url, browser);
            REAL_DRIVER = new EventFiringWebDriver(REAL_DRIVER).register(eventListener);
        } catch (MalformedURLException exceptions) {

        } catch (UnreachableBrowserException e){
            System.out.println("Can not find remote server. Start local service");
            LocalChromeDriverService.createAndStartService();
            service = LocalChromeDriverService.getService();
            REAL_DRIVER = new RemoteWebDriver(service.getUrl(),browser);
        }
    }

    public SharedDriver() {
        super(REAL_DRIVER);
//        REAL_DRIVER.manage().window().maximize();
        REAL_DRIVER.manage().timeouts().implicitlyWait(5, TimeUnit.MINUTES);
        REAL_DRIVER.manage().timeouts().pageLoadTimeout(5,TimeUnit.MINUTES);
    }

    @Override
    public void close() {
        if (Thread.currentThread() != CLOSE_THREAD) {
            throw new UnsupportedOperationException("You shouldn't close this WebDriver. It's shared and will close when the JVM exits.");
        }
        super.close();
        super.quit();
    }

    @Before
    public void deleteAllCookies(Scenario scenario) throws InterruptedException {
        this.scenario = scenario;
        System.out.println("===========开始测试   Feature: " + scenario.getName() + " Tags: " + scenario.getSourceTagNames().toString() + " ==========");
        manage().deleteAllCookies();
    }

    @After
    public void embedScreenshot(Scenario scenario) {
        try {
            LogEntries logEntries = REAL_DRIVER.manage().logs().get(LogType.BROWSER);
            System.out.println("=============================浏览器控制台日志================================");
            for (LogEntry entry : logEntries) {
                if (entry.getLevel().toString().contains("INFO") || entry.getLevel().toString().contains("WARNING"))
                    break;
                System.out.println(entry);
            }
            System.out.println("=============================测试结束================================");
            System.out.println("进行截图时页面当前的url：" + REAL_DRIVER.getCurrentUrl());

            byte[] screenshot = getScreenshotAs(OutputType.BYTES);
            scenario.embed(screenshot, "image/png");

        } catch (WebDriverException somePlatformsDontSupportScreenshots) {
            System.err.println(somePlatformsDontSupportScreenshots.getMessage());
        }
    }

    /**
     * 配置远程chrome浏览器设置
     * @return
     */
    private static DesiredCapabilities ChromeDes(){
        try {
            ChromeOptions options = new ChromeOptions();
            LoggingPreferences loggingPreferences = new LoggingPreferences();

            loggingPreferences.enable(LogType.BROWSER, Level.ALL);
            options.addArguments("test-type", "start-maximized");
            DesiredCapabilities desiredCapabilities = DesiredCapabilities.chrome();
            desiredCapabilities.setBrowserName("chrome");
            desiredCapabilities.setJavascriptEnabled(true);
            desiredCapabilities.setCapability(CapabilityType.LOGGING_PREFS,loggingPreferences);
            desiredCapabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS,true);
            desiredCapabilities.setCapability(ChromeOptions.CAPABILITY, options);
            return desiredCapabilities;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 配置远程IE浏览器设置
     * @return
     */
    private static DesiredCapabilities IEDes(){
        try {
            DesiredCapabilities desiredCapabilities = DesiredCapabilities.internetExplorer();
            desiredCapabilities.setBrowserName("internet Explorer");
            desiredCapabilities.setVersion("11");
            desiredCapabilities.setPlatform(Platform.WINDOWS);
            desiredCapabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
            desiredCapabilities.setCapability("ignoreProtectedModeSettings", true);
            return desiredCapabilities;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 配置远程firefox浏览器设置
     * @return
     */
    private static DesiredCapabilities FirefoxDes(){
        try {
            DesiredCapabilities desiredCapabilities = DesiredCapabilities.firefox();
            desiredCapabilities.setBrowserName("firefox");
            return desiredCapabilities;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static Scenario getScenario() {
        return scenario;
    }

    public static TakeScreenShot getScreenShot() {
        return screenShot;
    }
}