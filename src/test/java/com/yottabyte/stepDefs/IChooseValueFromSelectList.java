package com.yottabyte.stepDefs;

import com.sun.media.jfxmedia.logging.Logger;
import com.yottabyte.config.ConfigManager;
import com.yottabyte.hooks.LoginBeforeAllTests;
import com.yottabyte.utils.GetElementFromPage;
import com.yottabyte.utils.WaitForElement;
import com.yottabyte.webDriver.SharedDriver;
import cucumber.api.java.en.And;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

public class IChooseValueFromSelectList {
    WebDriver webDriver = LoginBeforeAllTests.getWebDriver();

    @And("^I choose the \"([^\"]*)\" from the \"([^\"]*)\"$")
    public void iChooseTheFromThe(List<String> values, String selectListName){
        List<WebElement> fatherSelectList = GetElementFromPage.getWebElementsWithName(selectListName);
        iChooseTheFromThe(values,fatherSelectList);
    }

    public void iChooseTheFromThe(List<String> values, List<WebElement> elements){
        if (values.size() == 1){
            String value = values.get(0);
            if (value != null && value.trim().length() != 0){
                WebElement parentElement = elements.get(0).findElement(By.xpath("./parent::*"));
                for (WebElement e : elements) {
                    ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView();", e);
                    if (value.equalsIgnoreCase(e.getText())){
                        e.click();
                        break;
                    }
                }
                try {
                    if (parentElement.isDisplayed()){
                        ((JavascriptExecutor) webDriver).executeScript("arguments[0].style.display='none';", parentElement);
                        ExpectedCondition expectedCondition = ExpectedConditions.invisibilityOf(parentElement);
                        WaitForElement.waitForElementWithExpectedCondition(webDriver,expectedCondition);
                    }
                }catch (StaleElementReferenceException e){
                    Logger.logMsg(Logger.WARNING,"父元素消失！尝试继续");
                }

            }
        }else {
            for (String s : values){
                for (WebElement e : elements){
                    if (s.equalsIgnoreCase(e.getText())){
                        ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView();", e);
                        e.click();
                    }
                }
            }
            WebElement e = elements.get(0).findElement(By.xpath("./parent::*"));
            if (e.isDisplayed()){
                ((JavascriptExecutor) webDriver).executeScript("arguments[0].style.display='none';", e);
                ExpectedCondition expectedCondition = ExpectedConditions.invisibilityOf(e);
                WaitForElement.waitForElementWithExpectedCondition(webDriver,expectedCondition);
            }
        }
    }

    @And("^I cancel selection \"([^\"]*)\" from the \"([^\"]*)\"$")
    public void iCancelSelectionFromThe(List<String> values, String selectListName){
        WebElement parentElement = GetElementFromPage.getWebElementWithName(selectListName);
        iCancelSelectionFromThe(values, parentElement);
    }

    public void iCancelSelectionFromThe(List<String> values, WebElement parentElement){
        List<WebElement> selectLists = parentElement.findElements(By.xpath("./li[contains(@class,'selected')]"));
        for (String value: values){
            if (value != null && value.trim().length() != 0){
                for (WebElement e : selectLists){
                    ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView();", e);
                    if (e.getAttribute("class").contains("selected")){
                        if (e.getAttribute("style").contains("display: none;")){
                            ((JavascriptExecutor) webDriver).executeScript("arguments[0].style.display='block';", e);
                            if (value.equalsIgnoreCase(e.getText())){
                                e.click();
                                continue;
                            }
                        }else {
                            if (value.equalsIgnoreCase(e.getText())){
                                e.click();
                                continue;
                            }
                        }
                    }
                }
            }
        }
        if (parentElement.isDisplayed()){
            ((JavascriptExecutor) webDriver).executeScript("arguments[0].style.display='none';", parentElement);
            ExpectedCondition expectedCondition = ExpectedConditions.invisibilityOf(parentElement);
            WaitForElement.waitForElementWithExpectedCondition(webDriver,expectedCondition);
        }

    }

    public static void main(String args[]) throws InterruptedException {
        SharedDriver driver = new SharedDriver();
        ConfigManager c = new ConfigManager();
        LoginBeforeAllTests login = new LoginBeforeAllTests(driver,c);
        login.beforeScenario();
        Thread.sleep(10000);
        driver.get("http://alltest.rizhiyi.com/account/usergroups/1/");
        driver.findElements(By.className("el-input__inner")).get(2).click();
        WebElement e = driver.findElement(By.className("el-select-dropdown__list"));
        List list = new ArrayList<>();
        list.add("admin");
        list.add("sunxc");
        new IChooseValueFromSelectList().iCancelSelectionFromThe(list,e);
    }

    private void te(List<String> list){
        if (list.size() == 1){
            System.out.println("0:" + list.get(0));
        }else {

        }
    }
}
