package com.yottabyte.pages.alert;

import com.yottabyte.config.ConfigManager;
import com.yottabyte.hooks.LoginBeforeAllTests;
import com.yottabyte.pages.PageTemplate;
import com.yottabyte.stepDefs.IChooseValueFromSelectList;
import com.yottabyte.stepDefs.SetKeyWithValue;
import com.yottabyte.utils.CheckSelectedFromDropdownList;
import com.yottabyte.utils.WaitForElement;
import com.yottabyte.webDriver.SharedDriver;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AlertsCreatePage extends PageTemplate {

    public AlertsCreatePage(WebDriver driver) {
        super(driver);
    }

    CheckSelectedFromDropdownList check = new CheckSelectedFromDropdownList();
    List<String> list;
    List<String> tmpAlertTypes = new ArrayList<>();
    // tab页按钮
    @FindBy (className = "el-tabs__item")
    private List<WebElement> tabs;
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
    // 高级配置tab的基础路径
    final private String ADVANCEDCONFIGPATH = "//form//*[text()='扩展搜索']/parent::*";
    // 扩展搜索搜索内容
    @FindBy(xpath = ADVANCEDCONFIGPATH + "//textarea")
    private WebElement exSearchContent;
    // 扩展搜索日志来源
    @FindBy(xpath = ADVANCEDCONFIGPATH + "//input[@placeholder='请选择日志来源']")
    private WebElement exAlertSourceButton;
    // 启用效果图
    @FindBy(xpath = "//span[@class='graph-switch-text']//following::label[1]")
    private WebElement graphEnable;
    // 告警抑制
    @FindBy(className = "suppression-radio-group")
    private WebElement suppressionAlertGroup;
    // 恢复提示
    @FindBy(xpath = "//label[contains(text(),'恢复提示')]/parent::*//label[@class='el-switch']")
    private WebElement recoverNote;
    // 抑制告警输入框
    @FindBy(xpath = ADVANCEDCONFIGPATH + "//div[@class='unit-input']")
    private List<WebElement> unitInputs;
    // 抑制告警下的复选框
    @FindBy(className = "el-checkbox__input")
    private WebElement checkBox;
    // 添加告警类型下拉框按钮
    @FindBy(className = "add-config-dropdown-button")
    private WebElement addAlertNoteTypeButton;
    // 添加告警类型下拉框
    @FindBy(className = "add-config-dropdown-menu")
    private WebElement addAlertNoteTypeGroup;
    // 告警提醒类型父元素
    @FindBy(className = "el-collapse")
    private WebElement alertNoteFrame;

    // 基本配置tab
    public WebElement getBasicConfigTab() {
        return tabs.get(0);
    }
    // 高级配置tab
    public WebElement getAdvancedConfigTab() {
        return tabs.get(1);
    }
    // 告警方式tab
    public WebElement getAlertNoteTypeTab() {
        return tabs.get(2);
    }

    public WebElement getAlertName() {
        return alertName;
    }

    public WebElement getAlertDes() {
        return alertDes;
    }

    public WebElement getAlertGroups() {
        WaitForElement.waitForElementWithExpectedCondition(webDriver, ExpectedConditions.elementToBeClickable(alertGroupButton));
        alertGroupButton.click();
        WaitForElement.waitForElementWithExpectedCondition(webDriver,ExpectedConditions.visibilityOf(alertGroupSelectors));
        return alertGroupSelectors;
    }

    public WebElement getAlertUsers() {
        return getSelectors(alertUserButton);
    }

    public WebElement getAlertSources() {return getSelectors(alertSourceButton);}

    public WebElement getSearchContent() {
        return searchContent;
    }

    public WebElement getSavedSearch() {
        WaitForElement.waitForElementWithExpectedCondition(webDriver,ExpectedConditions.elementToBeClickable(savedSearchButton));
        savedSearchButton.click();
        List<WebElement> list = webDriver.findElements(By.className("saved-search-dropdown-menu"));
        WaitForElement.waitForElementWithExpectedCondition(webDriver,ExpectedConditions.visibilityOf(list.get(list.size() - 1 ) ));
        return list.get(list.size() - 1);
    }
    public WebElement getAlertEnable() {
        WaitForElement.waitForElementWithExpectedCondition(webDriver,ExpectedConditions.elementToBeClickable(alertEnable));
        return alertEnable;
    }

    public WebElement getAlertTypes() {return getSelectors(alertTypeButton); }

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
    public WebElement getAlertTriggerHourOrMinute() {
        return getSelectors(alertTrigger.findElement(By.className("el-select")));
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

    // 选择基线对比监控时搜索结果选择在区间内/外时，右侧输入框
    public WebElement getBaseLineRightInput() {
        if (tmpAlertTypes.size()==0){
            list = check.checkSelected(webDriver, alertTypeButton);
            tmpAlertTypes.addAll(list);
        }
        String tmp = tmpAlertTypes.get(0);
        if ("基线对比监控".equalsIgnoreCase(tmp)) {
            String searchResult = check.checkSelected(webDriver,conditionSelects.get(0)).get(0);
            if (searchResult.contains("区间")) {
                return webDriver.findElement(By.className("baseline-right")).findElement(By.tagName("input"));
            }else {
                throw new NoSuchElementException("没有找到输入框，请检查");
            }
        }else {
            throw new NoSuchElementException("监控类型不正确，请检查！当前选择为：" + tmp);
        }
    }

    // 告警级别单位下拉框
    public List<WebElement> getAlertLevelUnit() {
        WaitForElement.waitForElementWithExpectedCondition(webDriver,ExpectedConditions.visibilityOfElementLocated(By.className("levels")));
        WebElement element = webDriver.findElement(By.className("levels")).findElement(By.className("input-with-unit")).findElement(By.className("level-select"));
        WebElement tmpElement = getSelectors(element);
        List<WebElement> elementList = tmpElement.findElements(By.tagName("li"));
        return elementList;
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

    public WebElement getExSearchContent() {
        return exSearchContent;
    }

    public List<WebElement> getExAlertSources() {
        return getSelectors(exAlertSourceButton).findElements(By.tagName("li"));
    }

    public WebElement getGraphEnable() {
        return graphEnable;
    }

    public WebElement getNotSuppressButton() {
        WaitForElement.waitForElementWithExpectedCondition(webDriver,ExpectedConditions.elementToBeClickable(suppressionAlertGroup.findElements(By.className("el-radio__input")).get(0)));
        return suppressionAlertGroup.findElements(By.className("el-radio__input")).get(0);
    }

    public WebElement getSuppressButton() {
        WaitForElement.waitForElementWithExpectedCondition(webDriver,ExpectedConditions.elementToBeClickable(suppressionAlertGroup.findElements(By.className("el-radio__input")).get(1)));
        return suppressionAlertGroup.findElements(By.className("el-radio__input")).get(1);
    }
    // 发送第一次告警选项中的输入框
    public WebElement getFixedPeriodInput() {
        String tmp = getSuppressButton().getAttribute("class");
        if (tmp.contains("is-checked")){
            return unitInputs.get(0).findElement(By.tagName("input"));
        }else {
            throw new ElementNotInteractableException("抑制告警选项未激活");
        }
    }

    // 发送第一次告警选项中的单位下拉框
    public List<WebElement> getFixedPeriodUnits() {
        WebElement tmp = unitInputs.get(0).findElement(By.className("el-select")).findElement(By.tagName("input"));
        return getSelectors(tmp).findElements(By.tagName("li"));
    }
    // 抑制告警中的复选框
    public WebElement getCheckBox() {
        return checkBox;
    }
    // 取消抑制的输入框
    public WebElement getCancelSuppressInput() {
        String tmp = getSuppressButton().getAttribute("class");
        String tmp1 = checkBox.getAttribute("class");
        if (tmp.contains("is-checked")) {
            if (tmp1.contains("is-checked")) {
                return unitInputs.get(1).findElement(By.tagName("input"));
            }else {
                throw new ElementNotInteractableException("取消抑制复选框选项未激活");
            }
        }else {
            throw new ElementNotInteractableException("抑制告警选项未激活");
        }
    }
    // 取消抑制的单位
    public List<WebElement> getCancelSuppressUnits() {
        WebElement tmp = unitInputs.get(1).findElement(By.className("el-select")).findElement(By.tagName("input"));
        return getSelectors(tmp).findElements(By.tagName("li"));
    }
    // 添加rsyslog告警方式
    public void rsyslogType(String address, List<String> protocol, List<String> level, String facility, List<String> condition, String content) {
        SetKeyWithValue set = new SetKeyWithValue();
        IChooseValueFromSelectList choose = new IChooseValueFromSelectList();
        try {
            choose.iChooseTheFromThe(new ArrayList<String>(Arrays.asList("rsyslog告警")),getAlertNoteTypes());
            List<String> titles = getAlertNoteTitle();
            for (int i=0; i<titles.size(); i++){
                if ("rsyslog告警".equals(titles.get(i))){
                    WebElement fatherElement = alertNoteFrame.findElements(By.className("el-collapse-item")).get(i);
                    if (fatherElement.getAttribute("class").contains("is-active")){
                        WebElement rsysAddress = fatherElement.findElements(By.className("el-input__inner")).get(0);
                        WebElement rsysProtocol = fatherElement.findElements(By.className("el-input__inner")).get(1);
                        WebElement rsysLevel = fatherElement.findElements(By.className("el-input__inner")).get(2);
                        WebElement rsysFacility  = fatherElement.findElements(By.className("el-input__inner")).get(3);
                        WebElement rsysCondition = fatherElement.findElements(By.className("el-input__inner")).get(4);
                        WebElement rsysContent = fatherElement.findElement(By.className("el-textarea__inner"));
                        if (address != null && address.trim().length() != 0){
                            set.iSetTheParameterWithValue(rsysAddress, address);
                        }
                        if (protocol != null && protocol.size() != 0 && !protocol.contains("")){
                            choose.iChooseTheFromThe(protocol, getSelectors(rsysProtocol));
                        }
                        if (level != null && level.size() != 0 && !level.contains("")){
                            choose.iChooseTheFromThe(level, getSelectors(rsysLevel));
                        }
                        if (facility != null && facility.trim().length() != 0){
                            set.iSetTheParameterWithValue(rsysFacility,facility);
                        }
                        choose.iCancelAllSelectionExcept(getSelectors(rsysCondition), condition);
                        if (content != null && content.trim().length() != 0){
                            set.iSetTheParameterWithValue(rsysContent, content);
                        }
                    }
                }
            }
        }catch (NoSuchElementException e){
            throw new NoSuchElementException("没有选择告警方式");
        }
    }

    // 添加邮件告警方式
    public void emailType(String title, List<String> emails, List<String> condition, String content){
        SetKeyWithValue set = new SetKeyWithValue();
        IChooseValueFromSelectList choose = new IChooseValueFromSelectList();
        try {
            choose.iChooseTheFromThe(new ArrayList<String>(Arrays.asList("邮件告警")),getAlertNoteTypes().findElements(By.tagName("li")));
            List<String> titles = getAlertNoteTitle();
            for (int i=0; i<titles.size(); i++){
                if ("邮件告警".equals(titles.get(i))){
                    WebElement fatherElement = alertNoteFrame.findElements(By.className("el-collapse-item")).get(i);
                    if (fatherElement.getAttribute("class").contains("is-active")){
                        WebElement emailTitle = fatherElement.findElements(By.className("el-input__inner")).get(0);
                        WebElement email = fatherElement.findElements(By.tagName("input")).get(1);
                        WebElement emailCondition = fatherElement.findElements(By.className("el-input__inner")).get(2);
                        WebElement emailContent = fatherElement.findElement(By.className("el-textarea__inner"));
                        if (title != null && title.trim().length() != 0){
                            set.iSetTheParameterWithValue(emailTitle, title);
                        }
                        if (emails != null && emails.size() != 0 && !emails.contains("")){
                            WaitForElement.waitForElementWithExpectedCondition(webDriver,ExpectedConditions.elementToBeClickable(email));
                            email.click();
                            set.iSetTheParameterWithValue(email,emails.get(0));
                            List<WebElement> list = webDriver.findElements(By.className("el-select-dropdown__list"));
                            WaitForElement.waitForElementWithExpectedCondition(webDriver,ExpectedConditions.visibilityOf(list.get(list.size() - 1 ) ));
                            WebElement e = list.get(list.size()-1);
                            choose.iChooseTheFromThe(emails, e.findElements(By.tagName("li")));
                        }
                        choose.iCancelAllSelectionExcept(getSelectors(emailCondition), condition);
                        if (content != null && content.trim().length() != 0){
                            set.iSetTheParameterWithValue(emailContent, content);
                        }
                    }
                }
            }
        }catch (NoSuchElementException e){
            throw new NoSuchElementException("没有选择告警方式");
        }
    }
    // 添加告警转发方式
    public void forwardType(String address, List<String> condition) {
        SetKeyWithValue set = new SetKeyWithValue();
        IChooseValueFromSelectList choose = new IChooseValueFromSelectList();
        try {
            choose.iChooseTheFromThe(new ArrayList<String>(Arrays.asList("告警转发")), getAlertNoteTypes().findElements(By.tagName("li")));
            List<String> titles = getAlertNoteTitle();
            for (int i = 0; i < titles.size(); i++) {
                if ("告警转发".equals(titles.get(i))) {
                    WebElement fatherElement = alertNoteFrame.findElements(By.className("el-collapse-item")).get(i);
                    if (fatherElement.getAttribute("class").contains("is-active")) {
                        WebElement forwardAddress = fatherElement.findElements(By.className("el-input__inner")).get(0);
                        WebElement forwardCondition = fatherElement.findElements(By.tagName("input")).get(1);
                        if (address != null && address.trim().length() != 0){
                            set.iSetTheParameterWithValue(forwardAddress, address);
                        }
                        choose.iCancelAllSelectionExcept(getSelectors(forwardCondition),condition);
                    }
                }
            }
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("没有选择告警方式");
        }
    }
    // 添加ping主机方式
    public void pingHostType(String address, List<String> condition) {
        SetKeyWithValue set = new SetKeyWithValue();
        IChooseValueFromSelectList choose = new IChooseValueFromSelectList();
        try {
            choose.iChooseTheFromThe(new ArrayList<String>(Arrays.asList("ping主机")), getAlertNoteTypes().findElements(By.tagName("li")));
            List<String> titles = getAlertNoteTitle();
            for (int i = 0; i < titles.size(); i++) {
                if ("ping主机".equals(titles.get(i))) {
                    WebElement fatherElement = alertNoteFrame.findElements(By.className("el-collapse-item")).get(i);
                    if (fatherElement.getAttribute("class").contains("is-active")) {
                        WebElement hostAddress = fatherElement.findElements(By.className("el-input__inner")).get(0);
                        WebElement conditions = fatherElement.findElements(By.tagName("input")).get(1);
                        if (address != null && address.trim().length() != 0){
                            set.iSetTheParameterWithValue(hostAddress, address);
                        }
                        choose.iCancelAllSelectionExcept(getSelectors(conditions),condition);
                    }
                }
            }
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("没有选择告警方式");
        }
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

    private List<String> getAlertNoteTitle() {
        List<String> list = new ArrayList<>();
        List<WebElement> webElements = alertNoteFrame.findElements(By.className("method-title"));
        int l = webElements.size();
        if (l == 0){
            throw new NoSuchElementException("没有选择告警方式");
        }else {
            for (int i=0; i<l; i++){
                list.add(webElements.get(i).getText());
            }
            return list;
        }
    }

    private WebElement getAlertNoteTypes() {
        WaitForElement.waitForElementWithExpectedCondition(webDriver,ExpectedConditions.elementToBeClickable(addAlertNoteTypeButton));
        addAlertNoteTypeButton.click();
        WaitForElement.waitForElementWithExpectedCondition(webDriver,ExpectedConditions.visibilityOf(webDriver.findElement(By.className("add-config-dropdown-menu"))));
        return webDriver.findElement(By.className("add-config-dropdown-menu"));
    }

    public void createAlert(String alertName, List<String> alertGroup, List<String> alertSource, List<String> alertLevel ) {
        SetKeyWithValue setKey = new SetKeyWithValue();
        IChooseValueFromSelectList choose = new IChooseValueFromSelectList();
        setKey.iSetTheParameterWithValue(getAlertName(), alertName);
        choose.iChooseTheFromThe(alertGroup, getAlertGroups());
        choose.iChooseTheFromThe(alertSource, getAlertSources());
        setKey.iSetTheParameterWithValue(getSearchContent(), "*");
        setKey.iSetTheParameterWithValue(getAlertTriggerInput(), "5");
        switch (alertLevel.size()) {
            case 1 :
                setKey.iSetTheParameterWithValue(getAlertLevelInput(), alertLevel.get(0));
                break;
            case 2 :
                setKey.iSetTheParameterWithValue(getAlertLevelInput(), alertLevel.get(0));
                getAddThresholdButton().click();
                setKey.iSetTheParameterWithValue(getMiddleLevelInput(),alertLevel.get(1));
                break;
            case 3 :
                setKey.iSetTheParameterWithValue(getAlertLevelInput(), alertLevel.get(0));
                getAddThresholdButton().click();
                setKey.iSetTheParameterWithValue(getMiddleLevelInput(), alertLevel.get(1));
                getAddThresholdButton().click();
                setKey.iSetTheParameterWithValue(getHighLevelInput(), alertLevel.get(2));
        }
        getSaveButton().click();
    }

    public static void main(String args[]) throws InterruptedException {
        SharedDriver driver = new SharedDriver();
        ConfigManager c = new ConfigManager();
        LoginBeforeAllTests login = new LoginBeforeAllTests(driver,c);
        login.beforeScenario();
        Thread.sleep(10000);
        driver.get("http://alltest.rizhiyi.com/alerts/new/");
        AlertsListPage p = new AlertsListPage(driver);
        Thread.sleep(2000);
//        p.getCreateAlert().click();
//        Thread.sleep(10000);
//        new AlertsCreatePage(driver).tabs.get(2).click();
//        new AlertsCreatePage(driver).rsyslogType("192.168.1.82:514",
//                new ArrayList<String>(Arrays.asList("UDP")),
//                new ArrayList<String>(Arrays.asList("INFO")),"local0",
//                new ArrayList<String>(Arrays.asList("")),"{{ alert.name }}|{{ alert.strategy.trigger.start_time|date:\"Y-m-d H:i:s\" }}|{{ alert.strategy.trigger.end_time|date:\"Y-m-d H:i:s\" }}|{{ alert.search.query }}");
        List list = new ArrayList<>();
        List list1 = new ArrayList<>();
        List list2 = new ArrayList<>();
        list.add("default_Alert");
        list1.add("所有日志");
        list2.add("1");
        list2.add("3");
        list2.add("10");

        new AlertsCreatePage(driver).createAlert("autotest", list, list1, list2);
        driver.quit();
    }


}
