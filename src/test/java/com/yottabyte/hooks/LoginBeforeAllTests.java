package com.yottabyte.hooks;

import com.yottabyte.config.ConfigManager;
import com.yottabyte.pages.LoginPage;
import com.yottabyte.pages.PublicNavBarPage;
import com.yottabyte.webDriver.SharedDriver;
import cucumber.api.Scenario;
import cucumber.api.java.Before;
import org.openqa.selenium.WebDriver;

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
        webDriver.get(baseURL + "/auth/login/");
        System.out.println(webDriver + "login driver");
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.getUsername().clear();
        loginPage.getUsername().sendKeys(config.get("username"));
        loginPage.getPassword().clear();
        loginPage.getPassword().sendKeys(config.get("password"));
        loginPage.getLoginButton().click();
        PublicNavBarPage publicNavBarPage = new PublicNavBarPage(webDriver);
        setPageFactory(publicNavBarPage);
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

    public static ConfigManager getConfig() {
        return config;
    }
}
