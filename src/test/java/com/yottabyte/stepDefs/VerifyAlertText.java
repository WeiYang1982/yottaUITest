package com.yottabyte.stepDefs;

import com.yottabyte.utils.GetElementFromPage;
import cucumber.api.java.en.Then;
import org.openqa.selenium.WebElement;

import static org.junit.Assert.*;

/**
 * Created by A on 2017/4/11.
 */
public class VerifyAlertText {

    @Then("^I will see the alertErrorMessage \"([^\"]*)\"$")
    public void iWillSeeErrorMessage(String errorMessage){
        WebElement element = GetElementFromPage.getWebElementWithName("Alert");
        String realResult = element.getText();
        assertEquals(errorMessage,realResult);
    }
}
