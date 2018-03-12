package com.yottabyte.stepDefs;

import com.yottabyte.utils.GetElementFromPage;
import cucumber.api.java.en.And;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebElement;

/**
 * Created by A on 2017/4/7.
 */
public class ClickSomeButton {

    @When("^I click the \"([^\"]*)\" button$")
    public void iClickTheButton(String buttonName){
        if (buttonName != null && buttonName.trim().length() != 0){
            WebElement button = GetElementFromPage.getWebElementWithName(buttonName);
            button.click();
        }else {
            System.out.println("skip this step!");
        }
    }

    @And("^I click the table \"([^\"]*)\" button$")
    public void iClickTheTableButton(String tableAddress){
        if (tableAddress.contains("-")) {
            String buttonName = tableAddress.split("-")[0];
            int row = Integer.parseInt(tableAddress.split("-")[1]);
            Object[] o = new Object[1];
            o[0] = row;
            WebElement button = GetElementFromPage.getWebElementWithName(buttonName, o);
            button.click();
        }else {
            System.out.println("Table Address is wrong!!!");
        }
    }
}
