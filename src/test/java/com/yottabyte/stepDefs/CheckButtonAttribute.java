package com.yottabyte.stepDefs;

import com.yottabyte.utils.GetElementFromPage;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

/**
 * @author sunxj
 */
public class CheckButtonAttribute {

    /**
     * 检查元素是否有某属性
     *
     * @param buttonNameList
     * @param attribute
     */
    @Then("^I will see the \"([^\"]*)\" is \"([^\"]*)\"$")
    public void checkClass(List<String> buttonNameList, String attribute) {
        for (String buttonName : buttonNameList) {
            WebElement element = GetElementFromPage.getWebElementWithName(buttonName);
            Assert.assertTrue(element.getAttribute("class").contains(attribute));
        }
    }


    @And("^I will see the \"([^\"]*)\" is clickable$")
    public void iWillSeeTheIsClickable(List<String> buttonNameList) {
        for (String buttonName : buttonNameList) {
            WebElement element = GetElementFromPage.getWebElementWithName(buttonName);
            Assert.assertFalse(element.getAttribute("class").contains("disabled"));
        }
    }
}
