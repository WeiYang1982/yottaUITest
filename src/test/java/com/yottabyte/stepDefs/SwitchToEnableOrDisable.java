package com.yottabyte.stepDefs;

import com.yottabyte.utils.GetElementFromPage;
import cucumber.api.java.en.And;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class SwitchToEnableOrDisable {
    @And("^I switch the \"([^\"]*)\" button to \"([^\"]*)\"$")
    public void iSwitchTheButtonTo(String buttonName, String value) {
        WebElement element = GetElementFromPage.getWebElementWithName(buttonName);
        WebElement enable = element.findElement(By.className("el-switch__label--left"));
        WebElement disable = element.findElement(By.className("el-switch__label--right"));
        if (value.equalsIgnoreCase("enable")) {
            if(enable.getAttribute("style").contains("display: none")) {
                element.click();
            }else {
                System.out.println("already enable");
            }
        }else if (value.equalsIgnoreCase("disable")) {
            if(disable.getAttribute("style").contains("display: none")) {
                element.click();
            }else {
                System.out.println("already disable");
            }
        }else {
            System.out.println("error value :" + value);
        }
    }
}
