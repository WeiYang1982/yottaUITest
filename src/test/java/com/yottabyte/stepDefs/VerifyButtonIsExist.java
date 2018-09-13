package com.yottabyte.stepDefs;

import com.yottabyte.utils.GetElementFromPage;
import cucumber.api.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.WebElement;

/**
 * @author sunxj
 */
public class VerifyButtonIsExist {
    @Then("^I will see the \"([^\"]*)\" is not exist$")
    public void iWillSeeTheIsNotExist(String buttonName) {
        WebElement webElement = GetElementFromPage.getWebElementWithName(buttonName);
        Assert.assertTrue(webElement == null);
    }
}
