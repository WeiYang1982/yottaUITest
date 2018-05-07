package com.yottabyte.pages.alert;

import com.yottabyte.pages.PageTemplate;
import com.yottabyte.utils.WaitForElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class AlertsCreatePage extends PageTemplate {

    public AlertsCreatePage(WebDriver driver) {
        super(driver);
    }

    @FindBy (xpath = "//label[@class='el-form-item__label'][contains(text(),'名称')]/following-sibling::div//input")
    private WebElement alertName;

    @FindBy (xpath = "//label[@class='el-form-item__label'][contains(text(),'描述')]/following-sibling::div//input")
    private WebElement alertDes;

    @FindBy (xpath = "//div[@class='el-select']//input[@placeholder='请选择分组']")
    private WebElement alertGroupButton;

    @FindBy (className = "group-popper")
    private WebElement alertGroupSelectors;

    @FindBy (className = "runner-select")
    private WebElement alertUserButton;

    @FindBy (xpath = "//div[@class='el-select']//input[@placeholder='请选择日志来源']")
    private WebElement alertSourceButton;

    @FindBy (className = "el-textarea__inner")
    private WebElement searchContent;

    @FindBy (className = "el-switch")
    private WebElement alertEnable;

    @FindBy (xpath = "//div[@class='el-select']//input[@placeholder='请选择']")
    private WebElement alertTypeButton;

    @FindBy (xpath = "//span[@class='el-radio-button__inner'][text()='定时']")
    private WebElement alertPlanTimeButton;

    @FindBy (xpath = "//div[@class='el-form-item__content']/div[@class='el-row']//input")
    private List<WebElement> alertPlanInputs;

    @FindBy (xpath = "//span[@class='el-radio-button__inner'][text()='crontab']")
    private WebElement alertPlanCrontabButton;

    public WebElement getAlertName() {
        return alertName;
    }

    public WebElement getAlertDes() {
        return alertDes;
    }

    public List<WebElement> getAlertGroups() {
        WaitForElement.waitForElementWithExpectedCondition(webDriver, ExpectedConditions.elementToBeClickable(alertGroupButton));
        alertGroupButton.click();
        WaitForElement.waitForElementWithExpectedCondition(webDriver,ExpectedConditions.visibilityOf(alertGroupSelectors));
        return alertGroupSelectors.findElements(By.tagName("li"));
    }

    public List<WebElement> getAlertUsers() {
        return getSelectors(alertUserButton).findElements(By.tagName("li"));
    }

    public List<WebElement> getAlertSources() {return getSelectors(alertSourceButton).findElements(By.tagName("li"));}

    public WebElement getSearchContent() {
        return searchContent;
    }

    public WebElement getAlertEnable() {
        WaitForElement.waitForElementWithExpectedCondition(webDriver,ExpectedConditions.elementToBeClickable(alertEnable));
        return alertEnable;
    }

    public List<WebElement> getAlertTypes() {return getSelectors(alertTypeButton).findElements(By.tagName("li")); }

    public WebElement getAlertPlanTimeButton() {

        return alertPlanTimeButton;
    }



    private WebElement getSelectors(WebElement element) {
        WaitForElement.waitForElementWithExpectedCondition(webDriver,ExpectedConditions.elementToBeClickable(element));
        element.click();
        List<WebElement> list = webDriver.findElements(By.className("el-select-dropdown__list"));
        WaitForElement.waitForElementWithExpectedCondition(webDriver,ExpectedConditions.visibilityOf(list.get(list.size() - 1 ) ));
        return list.get(list.size() - 1 );
    }
}
