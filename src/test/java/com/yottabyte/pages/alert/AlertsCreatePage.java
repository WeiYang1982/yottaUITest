package com.yottabyte.pages.alert;

import com.yottabyte.pages.PageTemplate;
import com.yottabyte.utils.CheckSelectedFromDropdownList;
import com.yottabyte.utils.WaitForElement;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

public class AlertsCreatePage extends PageTemplate {

    public AlertsCreatePage(WebDriver driver) {
        super(driver);
    }

    CheckSelectedFromDropdownList check = new CheckSelectedFromDropdownList();
    List<String> list;
    List<String> tmpAlertTypes = new ArrayList<>();
    // 监控名称
    @FindBy (xpath = "//label[@class='el-form-item__label'][contains(text(),'名称')]/following-sibling::div//input")
    private WebElement alertName;
    // 监控描述
    @FindBy (xpath = "//label[@class='el-form-item__label'][contains(text(),'描述')]/following-sibling::div//input")
    private WebElement alertDes;
    // 监控分组的按钮
    @FindBy (xpath = "//div[@class='el-select']//input[@placeholder='请选择分组']")
    private WebElement alertGroupButton;
    // 监控分组的下拉选框
    @FindBy (className = "group-popper")
    private WebElement alertGroupSelectors;
    // 运行用户的按钮
    @FindBy (className = "runner-select")
    private WebElement alertUserButton;
    // 日志来源的按钮
    @FindBy (xpath = "//div[@class='el-select']//input[@placeholder='请选择日志来源']")
    private WebElement alertSourceButton;
    // 搜索内容
    @FindBy (className = "el-textarea__inner")
    private WebElement searchContent;
    // 已存搜索按钮
    @FindBy (className = "saved-search-dropdown")
    private WebElement savedSearchButton;
    // 启用/禁用按钮
    @FindBy (className = "el-switch")
    private WebElement alertEnable;
    // 监控类型的按钮
    @FindBy (xpath = "//div[@class='el-select']//input[@placeholder='请选择']")
    private WebElement alertTypeButton;
    // 执行计划-定时按钮
    @FindBy (xpath = "//span[@class='el-radio-button__inner'][text()='定时']")
    private WebElement alertPlanTimeButton;
    // 执行计划输入框 定时激活时为输入框和单位下拉框;crontab激活时为输入框
    @FindBy (xpath = "//div[@class='el-form-item__content']/div[@class='el-row']//input")
    private List<WebElement> alertPlanInputs;
    // 执行计划-crontab按钮
    @FindBy (xpath = "//span[@class='el-radio-button__inner'][text()='crontab']")
    private WebElement alertPlanCrontabButton;
    // 触发条件的父级元素
    @FindBy (className = "alert-trigger")
    private WebElement alertTrigger;
    // 触发条件下拉选框
    @FindBy (className = "condition_select")
    private List<WebElement> conditionSelects;
    // 告警级别父元素
    @FindBy (className = "levels")
    private WebElement alertLevel;
    // 添加阈值按钮
    @FindBy (className = "add-threshold")
    private WebElement addThresholdButton;
    // 保存按钮
    @FindBy (className = "update-save")
    private WebElement saveButton;
    // 提示信息弹框
    @FindBy(className = "el-message-box__message")
    private WebElement message;

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

    public List<WebElement> getSavedSearch() {
        WaitForElement.waitForElementWithExpectedCondition(webDriver,ExpectedConditions.elementToBeClickable(savedSearchButton));
        savedSearchButton.click();
        List<WebElement> list = webDriver.findElements(By.className("saved-search-dropdown-menu"));
        WaitForElement.waitForElementWithExpectedCondition(webDriver,ExpectedConditions.visibilityOf(list.get(list.size() - 1 ) ));
        return list.get(list.size() - 1).findElements(By.tagName("li"));
    }
    public WebElement getAlertEnable() {
        WaitForElement.waitForElementWithExpectedCondition(webDriver,ExpectedConditions.elementToBeClickable(alertEnable));
        return alertEnable;
    }

    public List<WebElement> getAlertTypes() {return getSelectors(alertTypeButton).findElements(By.tagName("li")); }

    public WebElement getAlertPlanTimeButton() {
        return alertPlanTimeButton;
    }
    // 执行计划-定时-输入框
    public WebElement getAlertPlanTimeInput() {
        if (alertPlanTimeButton.findElement(By.xpath("./parent::label")).getAttribute("class").contains("is-active")) {
            return alertPlanInputs.get(0);
        }else {
           throw new NoSuchElementException("定时按钮未激活");
        }
    }
    // 执行计划-定时-单位
    public List<WebElement> getAlertPlanTimeUnits() {
        if (alertPlanTimeButton.findElement(By.xpath("./parent::label")).getAttribute("class").contains("is-active")) {
            return getSelectors(alertPlanInputs.get(1)).findElements(By.tagName("li"));
        }else {
            throw new NoSuchElementException("定时按钮未激活");
        }
    }

    public WebElement getAlertPlanCrontabButton() {
        WaitForElement.waitForElementWithExpectedCondition(webDriver,ExpectedConditions.elementToBeClickable(alertPlanCrontabButton));
        return alertPlanCrontabButton;
    }

    // 执行计划-crontab-输入框
    public WebElement getAlertPlanCrontabInput() {
        if (alertPlanCrontabButton.findElement(By.xpath("./parent::label")).getAttribute("class").contains("is-active")) {
            return alertPlanInputs.get(0);
        }else {
            throw new NoSuchElementException("crontab按钮未激活");
        }
    }
    // 通用-触发条件输入框，后面带时间单位
    public WebElement getAlertTriggerInput() {
        return alertTrigger.findElement(By.className("input-with-unit")).findElement(By.tagName("input"));
    }
    // 通用-触发条件时间单位下拉框
    public List<WebElement> getAlertTriggerHourOrMinute() {
        return getSelectors(alertTrigger.findElement(By.className("el-select"))).findElements(By.tagName("li"));
    }

    // 监控类型选择事件数监控、字段统计监控、连续统计监控时，条件类型下拉框，如：计数、独立数等
    public List<WebElement> getConditionTypes() {
        if (tmpAlertTypes.size()==0){
            list = check.checkSelected(webDriver, alertTypeButton);
            tmpAlertTypes.addAll(list);
        }
        String tmp = tmpAlertTypes.get(0);
        if (tmp.equalsIgnoreCase("事件数监控") || tmp.equalsIgnoreCase("字段统计监控") || tmp.equalsIgnoreCase("连续统计监控")) {
            if (conditionSelects.size() == 2) {
                return getSelectors(conditionSelects.get(0)).findElements(By.tagName("li"));
            }else if (conditionSelects.size() == 1) {
                throw new NoSuchElementException("监控类型不正确，请检查，当前选择监控类型为： " + list.get(0));
            }else {
                throw new NoSuchElementException("存在多于2个下拉框或者没有下拉框，请检查，当前选择监控类型为： " + list.get(0));
            }
        }else if (tmp.equalsIgnoreCase("基线对比监控") || tmp.equalsIgnoreCase("Spl统计监控")) {
            throw new NoSuchElementException("监控类型不正确，当前选择监控类型为： " + list.get(0));
        }else {
            throw new NoSuchElementException("没有找到条件下拉框，当前选择监控类型为： " + list.get(0));
        }
    }

    // 监控类型选择字段统计监控、连续统计监控、基线对比监控时 左侧第一个输入框
    public WebElement getAlertTriggerFieldsInput() {
        if (tmpAlertTypes.size()==0){
            list = check.checkSelected(webDriver, alertTypeButton);
            tmpAlertTypes.addAll(list);
        }
        String tmp = tmpAlertTypes.get(0);
        if (tmp.equalsIgnoreCase("字段统计监控") || tmp.equalsIgnoreCase("连续统计监控") || tmp.equalsIgnoreCase("基线对比监控")) {
            return alertTrigger.findElement(By.className("condition-input")).findElement(By.tagName("input"));
        }else {
            throw new NoSuchElementException("监控类型不正确，当前选择监控类型为： " + list.get(0));
        }
    }

    // 监控类型选择连续统计监控、Spl统计监控时，左侧第二个输入框
    public WebElement getAlertTriggerRightInput() {
        if (tmpAlertTypes.size()==0){
            list = check.checkSelected(webDriver, alertTypeButton);
            tmpAlertTypes.addAll(list);
        }
        String tmp = tmpAlertTypes.get(0);
        if (tmp.equalsIgnoreCase("连续统计监控") || tmp.equalsIgnoreCase("Spl统计监控")) {
            return alertTrigger.findElement(By.className("condition-input-left")).findElement(By.tagName("input"));
        }else {
            throw new NoSuchElementException("监控类型不正确，当前选择监控类型为： " + list.get(0));
        }
    }

    // 监控类型选择基线对比监控时，基线时间下拉框
    public List<WebElement> getBaseLineTimeSelectors() {
        if (tmpAlertTypes.size()==0){
            list = check.checkSelected(webDriver, alertTypeButton);
            tmpAlertTypes.addAll(list);
        }
        String tmp = tmpAlertTypes.get(0);
        if (tmp.equalsIgnoreCase("基线对比监控")) {
            WebElement element = webDriver.findElement(By.xpath("//label[contains(text(),'基线时间')]/following-sibling::div//input"));
            WaitForElement.waitForElementWithExpectedCondition(webDriver,ExpectedConditions.elementToBeClickable(element));
            return getSelectors(element).findElements(By.tagName("li"));
        }else {
            throw new NoSuchElementException("监控类型不正确，当前选择监控类型为： " + list.get(0));
        }
    }

    // 触发条件下拉框
    public List<WebElement> getConditions() {
        if (tmpAlertTypes.size()==0){
            list = check.checkSelected(webDriver, alertTypeButton);
            tmpAlertTypes.addAll(list);
        }
        String tmp = tmpAlertTypes.get(0);
        if (tmp.equalsIgnoreCase("事件数监控") || tmp.equalsIgnoreCase("字段统计监控") || tmp.equalsIgnoreCase("连续统计监控")) {
            if (conditionSelects.size() == 2) {
                return getSelectors(conditionSelects.get(1)).findElements(By.tagName("li"));
            }else {
                throw new NoSuchElementException("监控类型不正确或者没有下拉框，请检查，当前选择监控类型为： " + list.get(0));
            }
        }else if (tmp.equalsIgnoreCase("基线对比监控") || tmp.equalsIgnoreCase("Spl统计监控")) {
            if (conditionSelects.size() == 1) {
                return getSelectors(conditionSelects.get(0)).findElements(By.tagName("li"));
            }else {
                throw new NoSuchElementException("监控类型不正确或者没有下拉框，请检查，当前选择监控类型为： " + list.get(0));
            }
        }else {
            throw new NoSuchElementException("没有找到条件下拉框，当前选择监控类型为： " + list.get(0));
        }
    }
    // 告警级别输入框
    public WebElement getAlertLevelInput() {
        WaitForElement.waitForElementWithExpectedCondition(webDriver,ExpectedConditions.visibilityOf(alertLevel));
        return alertLevel.findElement(By.className("input-with-unit")).findElement(By.className("el-input__inner"));
    }
    // 告警级别单位下拉框
    public List<WebElement> getAlertLevelUnit() {
        WaitForElement.waitForElementWithExpectedCondition(webDriver,ExpectedConditions.visibilityOf(alertLevel));
        WebElement element = alertLevel.findElement(By.className("input-with-unit")).findElement(By.className("level-select"));
        return getSelectors(element).findElements(By.tagName("li"));
    }

    public WebElement getAddThresholdButton() {
        return addThresholdButton;
    }

    public WebElement getMiddleLevelInput() {
        if (alertLevel.findElements(By.className("input-with-unit")).size() >= 2){
            return alertLevel.findElements(By.className("input-with-unit")).get(1).findElement(By.className("el-input__inner"));
        }else {
            throw new NoSuchElementException("请添加阈值");
        }
    }

    public WebElement getHighLevelInput() {
        if (alertLevel.findElements(By.className("input-with-unit")).size() >= 3){
            return alertLevel.findElements(By.className("input-with-unit")).get(2).findElement(By.className("el-input__inner"));
        }else {
            throw new NoSuchElementException("请添加阈值");
        }
    }

    public WebElement getSaveButton() {
        WaitForElement.waitForElementWithExpectedCondition(webDriver,ExpectedConditions.elementToBeClickable(saveButton));
        return saveButton;
    }

    public WebElement getSuccessMessage() {
        WaitForElement.waitForElementWithExpectedCondition(webDriver,ExpectedConditions.visibilityOf(message));
        return message;
    }

    public WebElement getErrorMessage() {
        WaitForElement.waitForElementWithExpectedCondition(webDriver,ExpectedConditions.visibilityOf(message));
        return message;
    }

    private WebElement getSelectors(WebElement element) {
        WaitForElement.waitForElementWithExpectedCondition(webDriver,ExpectedConditions.elementToBeClickable(element));
        element.click();
        List<WebElement> list = webDriver.findElements(By.className("el-select-dropdown__list"));
        WaitForElement.waitForElementWithExpectedCondition(webDriver,ExpectedConditions.visibilityOf(list.get(list.size() - 1 ) ));
        return list.get(list.size() - 1 );
    }


}
