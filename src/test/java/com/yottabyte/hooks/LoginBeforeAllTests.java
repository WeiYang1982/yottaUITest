package com.yottabyte.hooks;

import com.yottabyte.config.ConfigManager;
import com.yottabyte.entity.Account;
import com.yottabyte.pages.LoginPage;
import com.yottabyte.utils.JdbcUtils;
import com.yottabyte.utils.WaitForElement;
import com.yottabyte.webDriver.SharedDriver;
import cucumber.api.java.Before;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class LoginBeforeAllTests {
    private static WebDriver webDriver;
    private static String baseURL;
    private static Object pageFactory;
    private static ConfigManager config;
    private static Cookie cookie;

    public LoginBeforeAllTests(SharedDriver driver, ConfigManager manager) {
        webDriver = driver;
        config = manager;
        baseURL = "http://" + manager.get("rizhiyi_server_host");
    }

    @Before
    public void beforeScenario() {
        System.out.println("Login Before Test!");
        webDriver.manage().deleteAllCookies();
        webDriver.get(baseURL + "/auth/login/");
        if (cookie == null) {
            login();
        } else {
            webDriver.get(baseURL);
            Date exDate = cookie.getExpiry();
            if (exDate.before(new Date())) {
                Calendar calendar = new GregorianCalendar();
                calendar.setTime(exDate);
                calendar.add(Calendar.DATE, 7);
                exDate = calendar.getTime();
                cookie = new Cookie(cookie.getName(), cookie.getValue(), cookie.getDomain(), cookie.getPath(), exDate);
            }
            webDriver.manage().addCookie(cookie);
            webDriver.get(baseURL);
        }
        setPageFactory("PublicNavBarPage");
    }

    public static void login() {
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.getUsername().clear();
        String username = config.get("username");
        loginPage.getUsername().sendKeys(username);
        loginPage.getPassword().clear();
        loginPage.getPassword().sendKeys(config.get("password"));
        loginPage.getLoginButton().click();
//        WaitForElement.waitForElementWithExpectedCondition(webDriver,ExpectedConditions.stalenessOf(webDriver.findElement(By.xpath("/html"))));
        WebDriverWait wait = new WebDriverWait(webDriver, 10, 1000);
        wait.until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                while (driver.manage().getCookieNamed("sessionid") == null) {
                    return false;
                }
                return true;
            }
        });
        cookie = webDriver.manage().getCookieNamed("sessionid");
        setAccount(username);
    }

    private static void setAccount(String username) {
        StringBuffer sql = new StringBuffer("SELECT d.name, a.id FROM Account a ");
        sql.append("LEFT JOIN Domain d ON d.id = a.domain_id WHERE a.name = '");
        sql.append(username).append("'");

        List<String> list = JdbcUtils.query(sql.toString());
        if (list.size() != 0) {
            Account account = new Account();
            account.setAccountName(list.get(0));
            account.setAccountId(list.get(1));
        }
    }

    public static WebDriver getWebDriver() {
        return webDriver;
    }

    public static void setWebDriver(WebDriver webDriver) {
        LoginBeforeAllTests.webDriver = webDriver;
    }

    public static Cookie getCookie() {
        return cookie;
    }

    public static String getBaseURL() {
        return baseURL;
    }

    public static Object getPageFactory() {
        return pageFactory;
    }

    public static void setPageFactory(Object pageFactory) {
        LoginBeforeAllTests.pageFactory = pageFactory;
    }

    public static void setPageFactory(String pageFactoryName) {
        if (!pageFactoryName.startsWith("com.yottabyte.pages.")) {
            pageFactoryName = "com.yottabyte.pages." + pageFactoryName;
        }
        Constructor c = null;
        try {
            c = Class.forName(pageFactoryName).getDeclaredConstructor(WebDriver.class);
            c.setAccessible(true);
            pageFactory = c.newInstance(webDriver);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public static ConfigManager getConfig() {
        return config;
    }
}
