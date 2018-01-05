package com.yottabyte.stepDefs;

import cucumber.api.java.en.And;

/**
 * Created by A on 2017/4/13.
 */
public class WaitForSomeSecond {

    @And("^I wait for \"([^\"]*)\" millsecond")
    public void iWaitForSecond(int millseconds) throws InterruptedException {
        Thread.sleep(millseconds);
    }

}
