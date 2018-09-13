package com.yottabyte.stepDefs;

import com.yottabyte.hooks.LoginBeforeAllTests;
import cucumber.api.java.en.Then;
import org.openqa.selenium.WebDriver;

/**
 * @author sunxj
 */
public class SwitchWindow {
    private WebDriver webDriver = LoginBeforeAllTests.getWebDriver();

    @Then("switch to another window")
    public void switchWindow() {
        String handle = webDriver.getWindowHandle();
        for (String handles : webDriver.getWindowHandles()) {
            if (handles.equals(handle))
                continue;
            webDriver.switchTo().window(handles);
        }
    }
}
