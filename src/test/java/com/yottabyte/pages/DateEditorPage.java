package com.yottabyte.pages;

import com.yottabyte.utils.ConstructPageFactoryWithName;
import com.yottabyte.utils.WaitForElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * 时间控件页面元素
 */

public class DateEditorPage extends PageTemplate {

    public DateEditorPage(WebDriver driver) {
        super(driver);
    }


    @FindBy( className = "el-date-editor")
    private WebElement publicDateEditor;

    @FindBy ( xpath = "//*[text()='实时/最近']")   // "//div[contains(text(),'最近') and contains(text(),'/')] "
    private WebElement dateMenuRecent;

    @FindBy ( xpath = "//*[text()='快捷选项']")
    private WebElement dateMenuFastChoose;

    @FindBy ( xpath = "//*[text()='快捷选项']/following-sibling::div//button[text()='最近7天']")
    private WebElement recentSevenDay;

    public WebElement getPublicDateEditor(){
        ExpectedCondition expectedCondition = ExpectedConditions.elementToBeClickable(publicDateEditor);
        WaitForElement.waitForElementWithExpectedCondition(webDriver,expectedCondition);
        return publicDateEditor;
    }

    public WebElement getRecentSevenDay() {
        ExpectedCondition expectedCondition = ExpectedConditions.elementToBeClickable(recentSevenDay);
        WaitForElement.waitForElementWithExpectedCondition(webDriver,expectedCondition);
        WebElement e = recentSevenDay;
        ConstructPageFactoryWithName c = new ConstructPageFactoryWithName();
        c.constructPageFactoryWithName(parentPageName);
        return e;
    }

}
