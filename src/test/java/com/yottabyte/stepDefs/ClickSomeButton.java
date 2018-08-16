package com.yottabyte.stepDefs;

import com.yottabyte.hooks.LoginBeforeAllTests;
import com.yottabyte.utils.GetElementFromPage;
import com.yottabyte.utils.WaitForElement;
import cucumber.api.java.en.And;
import cucumber.api.java.en.When;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * Created by A on 2017/4/7.
 */
public class ClickSomeButton {
    WebDriver webDriver = LoginBeforeAllTests.getWebDriver();

    @When("^I click the \"([^\"]*)\" button$")
    public void iClickTheButton(String buttonName){
        if (buttonName != null && buttonName.trim().length() != 0){
            WebElement button = GetElementFromPage.getWebElementWithName(buttonName);
            ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView();", button);
            iClickTheButton(button);
        }else {
            System.out.println("skip this step!");
        }
    }

    public void iClickTheButton(WebElement button){
        WaitForElement.waitForElementWithExpectedCondition(
                LoginBeforeAllTests.getWebDriver(), ExpectedConditions.elementToBeClickable(button));
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView();", button);
        button.click();
    }

    @And("^I click the table \"([^\"]*)\" button$")
    public void iClickTheTableButton(String tableAddress){
        if (tableAddress.contains("-")) {
            String buttonName = tableAddress.split("-")[0];
            int row = Integer.parseInt(tableAddress.split("-")[1]);
            WebElement button = GetElementFromPage.getWebElementWithName(buttonName, row);
            ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView();", button);
            button.click();
        }else {
            System.out.println("Table Address is wrong!!!");
        }
    }
}
