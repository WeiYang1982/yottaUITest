package com.yottabyte.stepDefs;

import com.yottabyte.utils.TakeScreenShot;
import cucumber.api.java.en.Then;

/**
 * @author sunxj
 */
public class Screenshot {
    @Then("^take a screenshot$")
    public void takeScreenshot() {
        TakeScreenShot screenShot = new TakeScreenShot();
        screenShot.screenShot();
    }
}
