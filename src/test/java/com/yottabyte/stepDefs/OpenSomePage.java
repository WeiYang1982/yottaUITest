package com.yottabyte.stepDefs;

import com.yottabyte.hooks.LoginBeforeAllTests;
import com.yottabyte.utils.ConstructPageFactoryWithName;
import cucumber.api.java.en.Given;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import java.net.URI;

public class OpenSomePage {
    private WebDriver webDriver = LoginBeforeAllTests.getWebDriver();
    private String baseURL = LoginBeforeAllTests.getBaseURL();

    /**
     * 打开指定页面  参数由feature提供
     * @param pageName
     */
    @Given("^open the \"([^\"]*)\" page for uri \"([^\"]*)\"$")
    public void openThePageForURI(String pageName, URI uri){
        Cookie cookie = LoginBeforeAllTests.getCookie();
        if (webDriver.manage().getCookies() == null) {
            webDriver.manage().addCookie(cookie);
        }
        webDriver.get(baseURL + uri);
        ConstructPageFactoryWithName c = new ConstructPageFactoryWithName();
        c.constructPageFactoryWithName(pageName);
    }

    /**
     * 打开某页面，且不需要指定URI
     * @param pageName
     */
    @Given("^open the \"([^\"]*)\" page$")
    public void openThePage(String pageName){
        webDriver.get(baseURL);
        ConstructPageFactoryWithName c = new ConstructPageFactoryWithName();
        c.constructPageFactoryWithName(pageName);
    }


}