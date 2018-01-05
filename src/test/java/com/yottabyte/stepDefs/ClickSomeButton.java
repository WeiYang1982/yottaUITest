package com.yottabyte.stepDefs;

import com.yottabyte.utils.GetElementFromPage;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebElement;

/**
 * Created by A on 2017/4/7.
 */
public class ClickSomeButton {

    @When("^I click the \"([^\"]*)\" button$")
    public void iClickTheButton(String buttonName){
        WebElement button = GetElementFromPage.getWebElementWithName(buttonName);
        button.click();
    }
}
