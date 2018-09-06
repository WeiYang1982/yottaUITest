package com.yottabyte.stepDefs;

import com.yottabyte.hooks.LoginBeforeAllTests;
import cucumber.api.java.en.And;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;

public class LouOut {

    public Cookie cookie = LoginBeforeAllTests.getCookie();

    @And("^I logout current user$")
    public void iLogoutCurrentUser() {
        WebDriver webDriver = LoginBeforeAllTests.getWebDriver();
        webDriver.manage().deleteAllCookies();
        webDriver.navigate().refresh();
    }

}
