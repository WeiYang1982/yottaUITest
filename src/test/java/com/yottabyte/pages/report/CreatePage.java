package com.yottabyte.pages.report;

import com.yottabyte.pages.PageTemplate;
import com.yottabyte.utils.WaitForElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class CreatePage extends PageTemplate {

    public CreatePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(className = "el-message-box__message")
    private WebElement successMessage;

    public WebElement getSuccessMessage() {
        return successMessage;
    }

    // 下拉列表
    @FindBy(className = "el-select-dropdown__list")
    private List<WebElement> dropdownLists;

    public WebElement getDropdownList() {
        return dropdownLists.get(dropdownLists.size() - 1);
    }

    @FindBy(xpath = "//label[text()='名称']/following-sibling::div/input")
    private WebElement name;

    public WebElement getName() {
        return name;
    }

    @FindBy(xpath = "//label[text()='描述']/following-sibling::div/input")
    private WebElement describe;

    public WebElement getDescribe() {
        return describe;
    }

    @FindBy(xpath = "//label[text()='运行用户']/following-sibling::div//input")
    private WebElement runningUser;

    public WebElement getRunningUser() {
        runningUser.click();
        return this.getDropdownList();
    }

    @FindBy(xpath = "//label[text()='报表分组']/following-sibling::div//input[@class='el-input__inner']")
    private WebElement reportGroup;

    public WebElement getReportGroup() {
        reportGroup.click();
        return this.getDropdownList();
    }

    @FindBy(xpath = "//label[text()='报表类型']/following-sibling::div//input[@class='el-input__inner']")
    private WebElement reportType;

    public WebElement getReportType() {
        reportType.click();
        return this.getDropdownList();
    }

    @FindBy(xpath = "//label[text()='接收邮箱']/following-sibling::div//input[@class='el-input__inner']")
    private WebElement email;

    public WebElement getEmail() {
        email.click();
        return this.getDropdownList();
    }

    @FindBy(xpath = "//label[text()='邮件主题']/following-sibling::div//input")
    private WebElement subject;

    public WebElement getSubject() {
        return subject;
    }

    @FindBy(xpath = "//input[@placeholder='时']")
    private WebElement hour;

    public WebElement getHour() {
        return hour;
    }

    @FindBy(xpath = "//input[@placeholder='分']")
    private WebElement minute;

    public WebElement getMinute() {
        return minute;
    }

    // 下一步
    @FindBy(className = "yw-extract-primary-btn")
    private WebElement nextButton;

    public WebElement getNextButton() {
        return nextButton;
    }

    @FindBy(xpath = "//label[text()='趋势图列表']/following-sibling::div/button")
    private WebElement chartList;

    @FindBy(className = "el-dropdown-menu")
    private WebElement chartDropdownList;

    public WebElement getChartList() {
        chartList.click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return chartDropdownList;
    }

    @FindBy(id = "layout1")
    private WebElement layout1;

    public WebElement getLayout1() {
        return layout1;
    }

    @FindBy(xpath = "//span[text()='保存']")
    private WebElement save;

    public WebElement getSave() {
        return save;
    }

}
