package com.yottabyte.stepDefs;

import com.yottabyte.utils.GetElementFromPage;
import cucumber.api.java.en.And;
import org.openqa.selenium.WebElement;

import java.util.List;

public class IChooseValueFromSelectList {
    @And("^I choose the \"([^\"]*)\" from the \"([^\"]*)\"$")
    public void iChooseTheFromThe(String value, String selectListName){
        List<WebElement> fatherSelectList = GetElementFromPage.getWebElementsWithName(selectListName);
        for (WebElement e : fatherSelectList){
            if (value.equalsIgnoreCase(e.getText())){
                e.click();
            }
        }
    }
}
