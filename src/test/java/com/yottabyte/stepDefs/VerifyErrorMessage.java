package com.yottabyte.stepDefs;

import com.yottabyte.utils.GetElementFromPage;
import cucumber.api.java.en.Then;

import static org.junit.Assert.assertEquals;

public class VerifyErrorMessage {

    @Then("^I will see the errorMessage \"([^\"]*)\"$")
    public void iWillSeeErrorMessage(String errorMessage){
        String realResult = GetElementFromPage.getSomeString("ErrorMessageText");
        assertEquals(errorMessage,realResult);
    }
}
