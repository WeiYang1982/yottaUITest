package com.yottabyte.stepDefs;

import com.yottabyte.utils.GetElementFromPage;
import cucumber.api.java.en.Then;
import org.openqa.selenium.WebElement;

import static org.junit.Assert.*;

/**
 * Created by A on 2017/4/13.
 */
public class VerifyElementTextWillBe {

    @Then("^I will see the \"([^\"]*)\" result will be \"([^\"]*)\"$")
    public void iWillSeeTheResultWillBe(String elementName, String excpText){
        WebElement element = GetElementFromPage.getWebElementWithName(elementName);
        String realText = element.getText();
        assertEquals(realText,excpText);
    }
}
