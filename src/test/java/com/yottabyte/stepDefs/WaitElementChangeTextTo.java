package com.yottabyte.stepDefs;

import com.yottabyte.hooks.LoginBeforeAllTests;
import com.yottabyte.utils.GetElementFromPage;
import com.yottabyte.utils.WaitForElement;
import cucumber.api.java.en.And;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;

/**
 * 等待指定元素的文本变成指定内容
 * Created by A on 2017/4/13.
 */
public class WaitElementChangeTextTo {

    /**
     * 等待指定元素的文本变成指定内容
     * @param elementName
     * @param text
     */
    @And("^I wait element \"([^\"]*)\" change text to \"([^\"]*)\"$")
    public void waitElementChangeTextTo(String elementName, String text){
        WebDriver webDriver = LoginBeforeAllTests.getWebDriver();
        WebElement element = GetElementFromPage.getWebElementWithName(elementName);
        ExpectedCondition expectedCondition = new ExpectedCondition<Boolean>(){
            @Override
            public Boolean apply(WebDriver driver) {
                Boolean flag = element.getText().contains(text);
                return flag;
            }
        };
        WaitForElement.waitForElementWithExpectedCondition(webDriver,expectedCondition);
    }

    //TODO 元素变化导致的文本内容变化
    @And("^I wait element \"([^\"]*)\" change and text will be \"([^\"]*)\"$")
    public void waitElementReloadCauseTextChange(String elementName,String text){

    }
}
