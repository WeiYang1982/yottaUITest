package com.yottabyte.pages;

import com.yottabyte.stepDefs.IChooseValueFromSelectList;
import com.yottabyte.utils.ConstructPageFactoryWithName;
import com.yottabyte.utils.WaitForElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

/**
 * 时间控件页面元素
 */

public class DateEditorPage extends PageTemplate {

    public DateEditorPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//i[@class='el-input__icon el-icon-time']/following-sibling::input")
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

    @FindBy(xpath = "//button[text()='本周']")
    private WebElement thisWeek;

    @FindBy(xpath = "//button[text()='上周']")
    private WebElement lastWeek;

    @FindBy(xpath = "//button[text()='本月']")
    private WebElement thisMonth;

    @FindBy(xpath = "//button[text()='上月']")
    private WebElement lastMonth;

    @FindBy(xpath = "//button[text()='10分钟']")
    private WebElement tenMinutes;

    @FindBy(xpath = "//button[text()='30分钟']")
    private WebElement halfHour;

    @FindBy(xpath = "//button[text()='1小时']")
    private WebElement oneHour;

    @FindBy(xpath = "//button[text()='1天']")
    private WebElement oneDay;

    @FindBy(xpath = "//button[text()='2天']")
    private WebElement twoDays;

    @FindBy(xpath = "//button[text()='7天']")
    private WebElement sevenDays;

    @FindBy(xpath = "//button[text()='全部时间']")
    private WebElement wholeTime;

    @FindBy(xpath = "//div[@class='el-tabs__item'][text()='最近']")
    private WebElement recently;

    @FindBy(xpath = "//div[@class='el-tabs__item'][text()='自定义时间范围']")
    private WebElement customTime;

    @FindBy(xpath = "//div[@class='el-input el-input-group el-input-group--append']/input")
    private WebElement timeInput;

    @FindBy(xpath = "//div[@class='custom-col-block']/button/span")
    private WebElement applyButton;

    @FindBy(className = "el-input-group__append")
    private WebElement groupAppend;

    @FindBy(className = "el-select-dropdown__list")
    private List<WebElement> dropdownList;

    @FindBy(xpath = "//input[@placeholder='开始时间']")
    private WebElement startTimeInput;

    @FindBy(xpath = "//input[@placeholder='结束时间']")
    private WebElement endTimeInput;

    @FindBy(xpath = "//input[@placeholder='开始日期']")
    private WebElement startDateInput;

    @FindBy(xpath = "//input[@placeholder='结束日期']")
    private WebElement endDateInput;

    @FindBy(xpath = "//div[@class='title-btn']//span[text()='应用']")
    private WebElement applyCustomTime;

    public void getCustomTime(String startTime, String endTime, String startDate, String endDate) {
        customTime.click();
        startTimeInput.sendKeys(startTime);
        endTimeInput.sendKeys(endTime);
        startDateInput.sendKeys(startDate);
        endDateInput.sendKeys(endDate);
        applyCustomTime.click();
    }

    public void getRecently(String time, String timeUnit) {
        recently.click();
        timeInput.sendKeys(time);
        if (!"".equals(timeUnit)) {
            groupAppend.click();
            List<String> list = new ArrayList<>();
            list.add(timeUnit);
            new IChooseValueFromSelectList().iChooseTheFromThe(list, dropdownList.get(dropdownList.size() - 1));
        }
        applyButton.click();
    }

    public WebElement getWholeTime() {
        return wholeTime;
    }

    public WebElement getTenMinutes() {
        return tenMinutes;
    }

    public WebElement getHalfHour() {
        return halfHour;
    }

    public WebElement getOneHour() {
        return oneHour;
    }

    public WebElement getOneDay() {
        return oneDay;
    }

    public WebElement getTwoDays() {
        return twoDays;
    }

    public WebElement getSevenDays() {
        return sevenDays;
    }

    public WebElement getThisWeek() {
        return thisWeek;
    }

    public WebElement getLastWeek() {
        return lastWeek;
    }

    public WebElement getThisMonth() {
        return thisMonth;
    }

    public WebElement getLastMonth() {
        return lastMonth;
    }

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
