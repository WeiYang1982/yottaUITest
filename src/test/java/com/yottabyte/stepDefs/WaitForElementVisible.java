package com.yottabyte.stepDefs;

import com.yottabyte.hooks.LoginBeforeAllTests;
import com.yottabyte.utils.GetElementFromPage;
import com.yottabyte.utils.WaitForElement;
import cucumber.api.java.en.And;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * Created by A on 2017/4/14.
 */
public class WaitForElementVisible {

    @When("^I wait for \"([^\"]*)\" will be visible")
    public void iWaitForWillBeVisible(String elementName){
        WebDriver webDriver = LoginBeforeAllTests.getWebDriver();
        WebElement element = GetElementFromPage.getWebElementWithName(elementName);
        ExpectedCondition expectedCondition = ExpectedConditions.visibilityOf(element);
        WaitForElement.waitForElementWithExpectedCondition(webDriver,expectedCondition);
    }

    @And("^I wait for \"([^\"]*)\" will be invisible")
    public void iWaitForWillBeInvisible(String elementName){
        WebDriver webDriver = LoginBeforeAllTests.getWebDriver();
        WebElement element = GetElementFromPage.getWebElementWithName(elementName);
        ExpectedCondition expectedCondition = ExpectedConditions.invisibilityOf(element);
        WaitForElement.waitForElementWithExpectedCondition(webDriver,expectedCondition);
    }
}
