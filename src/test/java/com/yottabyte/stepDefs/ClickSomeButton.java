package com.yottabyte.stepDefs;

import com.yottabyte.hooks.LoginBeforeAllTests;
import com.yottabyte.utils.*;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.When;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.Map;

/**
 * Created by A on 2017/4/7.
 */
public class ClickSomeButton {
    WebDriver webDriver = LoginBeforeAllTests.getWebDriver();

    @When("^I click the \"([^\"]*)\" button$")
    public void iClickTheButton(String buttonName) {
        if (buttonName != null && buttonName.trim().length() != 0) {
            String parameters = "";
            WebElement button = null;
            if (JsonStringPaser.isJson(buttonName)) {
                Map<String, Object> map = JsonStringPaser.json2Stirng(buttonName);
                for (Map.Entry<String, Object> entry : map.entrySet()) {
                    buttonName = entry.getKey();
                    parameters = (String) entry.getValue();
                }
                button = GetElementFromPage.getWebElementWithName(buttonName, parameters);
            }else {
                button = GetElementFromPage.getWebElementWithName(buttonName);
            }
            ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView();", button);
            iClickTheButton(button);
        } else {
            System.out.println("skip this step!");
        }
    }

    public void iClickTheButton(WebElement button) {
        WaitForElement.waitForElementWithExpectedCondition(
                LoginBeforeAllTests.getWebDriver(), ExpectedConditions.elementToBeClickable(button));
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView();", button);
        button.click();
    }

    @And("^I click the table \"([^\"]*)\" button$")
    public void iClickTheTableButton(String tableAddress) {
        if (tableAddress.contains("-")) {
            String buttonName = tableAddress.split("-")[0];
            int row = Integer.parseInt(tableAddress.split("-")[1]);
            WebElement button = GetElementFromPage.getWebElementWithName(buttonName, row);
            ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView();", button);
            button.click();
        } else {
            System.out.println("Table Address is wrong!!!");
        }
    }

    @And("^I trigger the button \"([^\"]*)\"$")
    public void trigger(String buttonName) {
        GetElementFromPage.getWebElementWithName(buttonName);
    }
}
