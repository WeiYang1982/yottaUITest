package com.yottabyte.hooks;

import com.yottabyte.config.ConfigManager;
import com.yottabyte.pages.LoginPage;
import com.yottabyte.webDriver.SharedDriver;
import cucumber.api.java.Before;
import org.openqa.selenium.WebDriver;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class LoginBeforeAllTests {
    private static WebDriver webDriver;
    private static String baseURL;
    private static Object pageFactory;
    private static ConfigManager config;

    public LoginBeforeAllTests(SharedDriver driver,ConfigManager manager){
        webDriver = driver;
        config = manager;
        baseURL = manager.get("yottaweb");
    }

    @Before
    public void beforeScenario() {
        System.out.println("Login Before Test!");
        webDriver.manage().deleteAllCookies();
        webDriver.get(baseURL + "/auth/login/");
        System.out.println(webDriver + "login driver");
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.getUsername().clear();
        loginPage.getUsername().sendKeys(config.get("username"));
        loginPage.getPassword().clear();
        loginPage.getPassword().sendKeys(config.get("password"));
        loginPage.getLoginButton().click();
        setPageFactory("PublicNavBarPage");
    }

    public static WebDriver getWebDriver() {
        return webDriver;
    }

    public static void setWebDriver(WebDriver webDriver) {
        LoginBeforeAllTests.webDriver = webDriver;
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

    public static void setPageFactory(String pageFactoryName){
        if (!pageFactoryName.startsWith("com.yottabyte.pages.")){
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
