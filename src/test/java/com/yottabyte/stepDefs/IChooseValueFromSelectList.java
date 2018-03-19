package com.yottabyte.stepDefs;

import com.yottabyte.hooks.LoginBeforeAllTests;
import com.yottabyte.utils.GetElementFromPage;
import com.yottabyte.utils.WaitForElement;
import cucumber.api.java.en.And;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class IChooseValueFromSelectList {
    @And("^I choose the \"([^\"]*)\" from the \"([^\"]*)\"$")
    public void iChooseTheFromThe(List<String> values, String selectListName){
        WebDriver webDriver = LoginBeforeAllTests.getWebDriver();
        if (values.size() == 1){
            String value = values.get(0);
            if (value != null && value.trim().length() != 0){
                List<WebElement> fatherSelectList = GetElementFromPage.getWebElementsWithName(selectListName);
                for (WebElement e : fatherSelectList){
                    ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView();", e);
                    if (value.equalsIgnoreCase(e.getText())){
                        e.click();
                    }
                }
                WebElement e = fatherSelectList.get(0).findElement(By.xpath("./parent::*"));
                if (e.isDisplayed()){
                    ((JavascriptExecutor) webDriver).executeScript("arguments[0].style.display='none';", e);
                    ExpectedCondition expectedCondition = ExpectedConditions.invisibilityOf(e);
                    WaitForElement.waitForElementWithExpectedCondition(webDriver,expectedCondition);
                }
            }
        }else {
            List<WebElement> fatherSelectList = GetElementFromPage.getWebElementsWithName(selectListName);
            for (String s : values){
                for (WebElement e : fatherSelectList){
                    if (s.equalsIgnoreCase(e.getText())){
                        ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView();", e);
                        e.click();
                    }
                }
            }
            WebElement e = fatherSelectList.get(0).findElement(By.xpath("./parent::*"));
            if (e.isDisplayed()){
                ((JavascriptExecutor) webDriver).executeScript("arguments[0].style.display='none';", e);
                ExpectedCondition expectedCondition = ExpectedConditions.invisibilityOf(e);
                WaitForElement.waitForElementWithExpectedCondition(webDriver,expectedCondition);
            }
        }
    }

    @And("^I cancel selection \"([^\"]*)\" from the \"([^\"]*)\"$")
    public void iCancelSelectionFromThe(List<String> values, String selectListName){
        WebDriver webDriver = LoginBeforeAllTests.getWebDriver();
        ////TODO 等待bug修复后继续完善 http://pha.yottabyte.cn/T3484
        if (values.size() == 1){
            String value = values.get(0);
            if (value != null && value.trim().length() != 0){
                List<WebElement> fatherSelectList = GetElementFromPage.getWebElementsWithName(selectListName);
                for (WebElement e : fatherSelectList){
                    ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView();", e);
                    if (value.equalsIgnoreCase(e.getText())){
                        e.click();
                    }else {
                        System.out.println("Wrong text! element text is: "  + e.getText());
                    }
                }
            }
        }else {
            List<WebElement> fatherSelectList = GetElementFromPage.getWebElementsWithName(selectListName);
            for (String s : values){
                for (WebElement e : fatherSelectList){
                    if (s.equalsIgnoreCase(e.getText())){
                        ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView();", e);
                        e.click();
                    }
                }
            }
            WebElement e = fatherSelectList.get(0).findElement(By.xpath("./parent::*/parent::*/parent::*/parent::*/parent::*"));
            if (e.isDisplayed()){
                ((JavascriptExecutor) webDriver).executeScript("arguments[0].style.display='none';", e);
                ExpectedCondition expectedCondition = ExpectedConditions.invisibilityOf(e);
                WaitForElement.waitForElementWithExpectedCondition(webDriver,expectedCondition);
            }
        }
    }
}
