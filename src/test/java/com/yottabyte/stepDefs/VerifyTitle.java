package com.yottabyte.stepDefs;

import com.yottabyte.utils.GetElementFromPage;
import cucumber.api.java.en.Then;

import static org.junit.Assert.*;

/**
 * 断言title的内容是指定内容
 * Created by A on 2017/4/7.
 */
public class VerifyTitle {

    @Then("^the page's title will be \"([^\"]*)\"$")
    public void theTitleWillBe(String titleName){
        String realResult = GetElementFromPage.getCurrentPageTitle();
        assertEquals(titleName,realResult);
    }

}
