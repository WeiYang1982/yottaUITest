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


    @FindBy(xpath = "//input[@placeholder='请选择快捷时间或时间范围']")
    private WebElement publicDateEditor;

    @FindBy(xpath = "//*[text()='实时/最近']")   // "//div[contains(text(),'最近') and contains(text(),'/')] "
    private WebElement dateMenuRecent;

    @FindBy(xpath = "//*[text()='快捷选项']")
    private WebElement dateMenuFastChoose;


    @FindBy(xpath = "//button[text()='最近7天']")

    private WebElement recentSevenDay;

    @FindBy(xpath = "//button[text()='今天']")
    private WebElement today;

    @FindBy(xpath = "//button[text()='昨天']")
    private WebElement yesterday;

    public WebElement getYesterday() {
        return this.getDateButton(yesterday);
    }

    public WebElement getToday() {
        return this.getDateButton(today);
    }

    public WebElement getRecentSevenDay() {
        return this.getDateButton(recentSevenDay);
    }

    public WebElement getPublicDateEditor() {
        ExpectedCondition expectedCondition = ExpectedConditions.elementToBeClickable(publicDateEditor);
        WaitForElement.waitForElementWithExpectedCondition(webDriver, expectedCondition);
        return publicDateEditor;
    }

    /**
     * 获取时间控件上的按钮
     *
     * @param webElement 按钮元素
     * @return
     */
    private WebElement getDateButton(WebElement webElement) {
        ExpectedCondition expectedCondition = ExpectedConditions.elementToBeClickable(webElement);
        WaitForElement.waitForElementWithExpectedCondition(webDriver, expectedCondition);
        WebElement e = webElement;
        ConstructPageFactoryWithName c = new ConstructPageFactoryWithName();
        c.constructPageFactoryWithName("splSearch." + parentPageName);
        return e;
    }

}
