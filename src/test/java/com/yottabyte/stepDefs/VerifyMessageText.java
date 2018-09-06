package com.yottabyte.stepDefs;

import com.yottabyte.utils.GetElementFromPage;
import cucumber.api.PendingException;
import cucumber.api.java.en.Then;
import org.openqa.selenium.WebElement;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class VerifyMessageText {
    @Then("^I will see the success message \"([^\"]*)\"$")
    public void iWillSeeTheSuccessMessage(String messageText) {
        WebElement element = GetElementFromPage.getWebElementWithName("getSuccessMessage");
        String realText = element.getText();
        assertEquals(messageText, realText);
    }

    @Then("^I will see the error message \"([^\"]*)\"$")
    public void iWillSeeErrorMessage(String errorMessage){
        WebElement element = GetElementFromPage.getWebElementWithName("ErrorMessage");
        String realResult = element.getText();
        assertEquals(errorMessage, realResult);
    }

    @Then("^I will see the error message contains \"([^\"]*)\"$")
    public void iWillSeeTheErrorMessageContains(String errorMessage) {
        WebElement element = GetElementFromPage.getWebElementWithName("ErrorMessage");
        String realResult = element.getText();
        assertTrue(realResult.contains(errorMessage));
    }
}
