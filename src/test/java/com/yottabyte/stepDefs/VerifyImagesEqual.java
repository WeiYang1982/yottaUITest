package com.yottabyte.stepDefs;

import com.yottabyte.utils.AssertImagesEqual;
import com.yottabyte.webDriver.SharedDriver;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;

public class VerifyImagesEqual {
    @And("^The expected image \"([^\"]*)\" will be equal to the actual image$")
    public void theExpectedImageWillBeEqualToTheActualImage(String expectImageName){
        if (expectImageName.equalsIgnoreCase("--")){
            System.out.println("Do not need compare images!");
        }else {
            AssertImagesEqual
                    .assertThat(SharedDriver.getScreenShot())
                    .isEqual(expectImageName,SharedDriver.getScreenShot().getActualImageName());
        }
    }
}
