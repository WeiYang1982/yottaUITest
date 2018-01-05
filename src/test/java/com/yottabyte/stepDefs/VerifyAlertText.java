package com.yottabyte.stepDefs;

import com.yottabyte.utils.GetElementFromPage;
import cucumber.api.java.en.Then;

import static org.junit.Assert.*;

/**
 * Created by A on 2017/4/11.
 */
public class VerifyAlertText {

    @Then("^I will see the alertErrorMessage \"([^\"]*)\"$")
    public void iWillSeeErrorMessage(String errorMessage){
        String realResult = GetElementFromPage.getSomeString("AlertText");
        assertEquals(errorMessage,realResult);
    }
}
