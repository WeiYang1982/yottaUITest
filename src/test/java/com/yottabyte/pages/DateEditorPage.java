package com.yottabyte.pages;

import com.yottabyte.stepDefs.IChooseValueFromSelectList;
import com.yottabyte.stepDefs.SetKeyWithValue;
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

    @FindBy(xpath = "//button[text()='所有时间']")
    private WebElement wholeTime;

    @FindBy(xpath = "//button[text()='全部时间']")
    private WebElement topoWholeTime;

    @FindBy(xpath = "//button[text()='30秒窗口']")
    private WebElement thirtySeconds;

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

    @FindBy(xpath = "//span[text()='最近']/preceding-sibling::span")
    private WebElement recentlyRadioButton;

    @FindBy(xpath = "//span[text()='实时']/preceding-sibling::span")
    private WebElement realTimeButton;

    @FindBy(xpath = "//i[@class='el-input__icon el-icon-arrow-up']/following-sibling::input[@placeholder='请输入']")
    private WebElement dropDownButton;

    @FindBy(className = "el-select-dropdown__list")
    private List<WebElement> daysDropDown;

    public WebElement getDaysDropDown() {
        dropDownButton.click();
        return this.getDateButton(daysDropDown.get(daysDropDown.size() - 1));
    }

    public WebElement getTimeInput() {
        return this.getDateButton(timeInput);
    }

    public WebElement getApplyButton() {
        return this.getDateButton(applyButton);
    }

    public WebElement getRealTimeButton() {
        WaitForElement.waitForElementWithExpectedCondition(webDriver, ExpectedConditions.visibilityOf(dateMenuRecent));
        dateMenuRecent.click();
        return this.getDateButton(realTimeButton);
    }

    public WebElement getRecentlyRadioButton() {
        WaitForElement.waitForElementWithExpectedCondition(webDriver, ExpectedConditions.visibilityOf(dateMenuRecent));
        dateMenuRecent.click();
        return this.getDateButton(recentlyRadioButton);
    }

    public void getCustomTime(String startTime, String endTime, String startDate, String endDate) {
        WaitForElement.waitForElementWithExpectedCondition(webDriver, ExpectedConditions.visibilityOf(customTime));
        customTime.click();
        startTimeInput.sendKeys(startTime);
        endTimeInput.sendKeys(endTime);
        startDateInput.sendKeys(startDate);
        endDateInput.sendKeys(endDate);
        applyCustomTime.click();
    }

    public void getCustomTime() {
        WaitForElement.waitForElementWithExpectedCondition(webDriver, ExpectedConditions.visibilityOf(customTime));
        customTime.click();
        SetKeyWithValue setValue = new SetKeyWithValue();
        setValue.iSetTheParameterWithValue(startTimeInput, "00:00:00");
        setValue.iSetTheParameterWithValue(endTimeInput, "00:00:00");
        setValue.iSetTheParameterWithValue(startDateInput, "2016-08-01");
        setValue.iSetTheParameterWithValue(endDateInput, "2018-08-03");
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

    public WebElement getTopoWholeTime() {
        return this.getDateButton(topoWholeTime);
    }

    public WebElement getThirtySeconds() {
        return this.getDateButton(thirtySeconds);
    }

    public WebElement getWholeTime() {
        return this.getDateButton(wholeTime);
    }

    public WebElement getTenMinutes() {
        return this.getDateButton(tenMinutes);
    }

    public WebElement getHalfHour() {
        return this.getDateButton(halfHour);
    }

    public WebElement getOneHour() {
        return this.getDateButton(oneHour);
    }

    public WebElement getOneDay() {
        return this.getDateButton(oneDay);
    }

    public WebElement getTwoDays() {
        return this.getDateButton(twoDays);
    }

    public WebElement getSevenDays() {
        return this.getDateButton(sevenDays);
    }

    public WebElement getThisWeek() {
        return this.getDateButton(thisWeek);
    }

    public WebElement getLastWeek() {
        return this.getDateButton(lastWeek);
    }

    public WebElement getThisMonth() {
        return this.getDateButton(thisMonth);
    }

    public WebElement getLastMonth() {
        return this.getDateButton(lastMonth);
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
