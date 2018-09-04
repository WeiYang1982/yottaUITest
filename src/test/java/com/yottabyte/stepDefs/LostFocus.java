package com.yottabyte.stepDefs;

import com.yottabyte.hooks.LoginBeforeAllTests;
import com.yottabyte.utils.GetElementFromPage;
import cucumber.api.java.en.And;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LostFocus {
    /**
     * //让元素失去焦点
     * @param elementName 需要失去焦点的元素名称
     */
    @And("^I let element \"([^\"]*)\" lose focus$")
    public void iLetElementLoseFocus(String elementName) {
        WebDriver webDriver = LoginBeforeAllTests.getWebDriver();
        WebElement element = GetElementFromPage.getWebElementWithName(elementName);
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].blur();", element);
    }
}
