package com.yottabyte.pages.topology;

import com.yottabyte.hooks.LoginBeforeAllTests;
import com.yottabyte.pages.DateEditorPage;
import com.yottabyte.pages.PageTemplate;
import com.yottabyte.utils.WaitForElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * @author sunxj
 */
public class DetailPage extends PageTemplate {
    public DetailPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(className = "icon-tianjiatubiaoxuanting_icon")
    private WebElement addButton;

    public WebElement getAddButton() {
        return addButton;
    }

    @FindBy(className = "icon-gengxinxuanting_icon")
    private WebElement refreshButton;

    public WebElement getRefreshButton() {
        return refreshButton;
    }

    @FindBy(className = "el-switch__label--right")
    private WebElement switchButton;

    public WebElement getSwitchButton() {
        return switchButton;
    }

    @FindBy(xpath = "//input[@placeholder='请输入节点名称']")
    private WebElement nodeName;

    public WebElement getNodeName() {
        return nodeName;
    }

    @FindBy(xpath = "//input[@placeholder='请输入节点分组']")
    private WebElement nodeGroup;

    public WebElement getNodeGroup() {
        return nodeGroup;
    }

    @FindBy(xpath = "//span[text()='添加节点']")
    private WebElement addNodeButton;

    public WebElement getAddNodeButton() {
        return addNodeButton;
    }

    @FindBy(className = "el-message-box__message")
    private WebElement alert;

    public WebElement getAlert() {
        return alert;
    }

    @FindBy(className = "leftright")
    private WebElement leftRightButton;

    public WebElement getLeftRightButton() {
        return leftRightButton;
    }

    @FindBy(xpath = "//span[text()='左值']")
    private WebElement leftValueButton;

    public WebElement getLeftValueButton() {
        return leftValueButton;
    }

    @FindBy(xpath = "//span[text()='右值']")
    private WebElement rightValueButton;

    public WebElement getRightValueButton() {
        return rightValueButton;
    }

    @FindBy(xpath = "//span[text()='上值']")
    private WebElement topValueButton;

    public WebElement getTopValueButton() {
        return topValueButton;
    }

    @FindBy(xpath = "//span[text()='下值']")
    private WebElement lowerValueButton;

    public WebElement getLowerValueButton() {
        return lowerValueButton;
    }

    @FindBy(xpath = "//span[text()='左下值']")
    private WebElement lowerLeft;

    @FindBy(xpath = "//span[text()='左上值']")
    private WebElement leftTop;

    @FindBy(xpath = "//span[text()='右下值']")
    private WebElement lowerRight;

    @FindBy(xpath = "//span[text()='右上值']")
    private WebElement rightTop;

    @FindBy(className = "icon-yejianxuanting_icon")
    private WebElement nightMode;

    @FindBy(className = "icon-tianjiatubiaoxuanting_icon")
    private WebElement addInputButton;

    // 添加输入项
    public WebElement getAddInputButton() {
        return addInputButton;
    }

    public WebElement getNightMode() {
        return nightMode;
    }

    public WebElement getRightTop() {
        return rightTop;
    }

    public WebElement getLeftTop() {
        return leftTop;
    }

    public WebElement getLowerLeft() {
        return lowerLeft;
    }

    public WebElement getLowerRight() {
        return lowerRight;
    }

    @FindBy(xpath = "//textarea[@placeholder='请输入搜索内容']")
    private WebElement textArea;

    public WebElement getTextArea() {
        return textArea;
    }

    @FindBy(xpath = "//input[@placeholder='请输入搜索时间范围']")
    private WebElement dateEditor;

    public WebElement getDateEditor() {
        return dateEditor;
    }

    // 获取今天按钮
    public WebElement getToday() {
        return this.getTime("Today");
    }

    // 获取昨天按钮
    public WebElement getYesterday() {
        return this.getTime("Yesterday");
    }

    // 获取本周按钮
    public WebElement getThisWeek() {
        return this.getTime("ThisWeek");
    }

    // 获取上周按钮
    public WebElement getLastWeek() {
        return this.getTime("LastWeek");
    }

    // 获取本月按钮
    public WebElement getThisMonth() {
        return this.getTime("ThisMonth");
    }

    // 获取上月按钮
    public WebElement getLastMonth() {
        return this.getTime("LastMonth");
    }

    // 获取十分钟按钮
    public WebElement getTenMinutes() {
        return this.getTime("TenMinutes");
    }

    // 获取30分钟按钮
    public WebElement getHalfHour() {
        return this.getTime("HalfHour");
    }

    // 获取1小时按钮
    public WebElement getOneHour() {
        return this.getTime("OneHour");
    }

    // 获取1天按钮
    public WebElement getOneDay() {
        return this.getTime("OneDay");
    }

    // 获取2天按钮
    public WebElement getTwoDays() {
        return this.getTime("TwoDays");
    }

    // 获取7天按钮
    public WebElement getSevenDays() {
        return this.getTime("SevenDays");
    }

    // 获取全部时间
    public WebElement getWholeTime() {
        return this.getTime("WholeTime");
    }

    public void getSecondAgo() {
        DateEditorPage dateEditorPage = new DateEditorPage(webDriver);
        LoginBeforeAllTests.setPageFactory(dateEditorPage);
        dateEditorPage.getRecently("3", "");
        LoginBeforeAllTests.setPageFactory(this);
    }

    public void getMinuteAgo() {
        DateEditorPage dateEditorPage = new DateEditorPage(webDriver);
        LoginBeforeAllTests.setPageFactory(dateEditorPage);
        dateEditorPage.getRecently("3", "分钟前");
        LoginBeforeAllTests.setPageFactory(this);
    }

    public void getHourAgo() {
        DateEditorPage dateEditorPage = new DateEditorPage(webDriver);
        LoginBeforeAllTests.setPageFactory(dateEditorPage);
        dateEditorPage.getRecently("3", "小时前");
        LoginBeforeAllTests.setPageFactory(this);
    }

    public void getDayAgo() {
        DateEditorPage dateEditorPage = new DateEditorPage(webDriver);
        LoginBeforeAllTests.setPageFactory(dateEditorPage);
        dateEditorPage.getRecently("3", "天前");
        LoginBeforeAllTests.setPageFactory(this);
    }

    public void getCustomTime() {
        DateEditorPage dateEditorPage = new DateEditorPage(webDriver);
        LoginBeforeAllTests.setPageFactory(dateEditorPage);
        dateEditorPage.getCustomTime("00:00:00", "00:00:00", "2018-08-01", "2018-08-03");
        LoginBeforeAllTests.setPageFactory(this);
    }

    // 获取对应的时间按钮
    public WebElement getTime(String time) {
        WebElement webElement = null;
        try {
            DateEditorPage dateEditorPage = new DateEditorPage(webDriver);
            LoginBeforeAllTests.setPageFactory(dateEditorPage);
            Class<DateEditorPage> dateEditorPageClass = DateEditorPage.class;
            String methodName = "get" + time;
            Method method = dateEditorPageClass.getDeclaredMethod(methodName);
            webElement = (WebElement) method.invoke(dateEditorPageClass.getDeclaredConstructor(WebDriver.class).newInstance(webDriver));
            LoginBeforeAllTests.setPageFactory(this);
        } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return webElement;
    }

    @FindBy(xpath = "//span[text()='搜索']")
    private WebElement searchButton;

    public WebElement getSearchButton() {
        WaitForElement.waitForElementWithExpectedCondition(webDriver, ExpectedConditions.elementToBeClickable(searchButton));
        return searchButton;
    }

    @FindBy(xpath = "//label[contains(text(),'标题')]/following-sibling::div//input")
    private WebElement title;

    public WebElement getToken() {
        return token;
    }

    @FindBy(xpath = "//label[contains(text(),'标识')]/following-sibling::div//input")
    private WebElement token;

    @FindBy(xpath = "//label[contains(text(),'默认值')]/following-sibling::div//input")
    private WebElement defaultValue;

    public WebElement getDefaultValue() {
        return defaultValue;
    }

    @FindBy(xpath = "//label[contains(text(),'输入类型')]/following-sibling::div//button")
    private WebElement inputType;

    public WebElement getInputType() {
        inputType.click();
        return webDriver.findElement(By.className("filter-type-selection-menu"));
    }

    public WebElement getTitle() {
        return title;
    }

    @FindBy(xpath = "//input[@placeholder='请选择展示字段']")
    private WebElement filedInput;

    @FindBy(className = "el-select-dropdown__list")
    private List<WebElement> dropdownList;

    public WebElement getFiledInput() {
        WaitForElement.waitForElementWithExpectedCondition(webDriver, ExpectedConditions.visibilityOf(filedInput));
        filedInput.click();
        return dropdownList.get(dropdownList.size() - 1);
    }

    @FindBy(xpath = "//input[@placeholder='请输入']")
    private WebElement unit;

    public WebElement getUnit() {
        return unit;
    }

    @FindBy(xpath = "//span[text()='应用']")
    private WebElement apply;

    public WebElement getApply() {
        return apply;
    }

    @FindBy(className = "el-message__group")
    private WebElement successMessage;

    public WebElement getSuccessMessage() {
        return successMessage;
    }

    @FindBy(xpath = "//p[text()='+ 添加']")
    private WebElement addColourButton;

    public WebElement getAddColourButton() {
        return addColourButton;
    }

    @FindBy(xpath = "//div[@class='color-value from el-input']/input")
    private WebElement fromInput;

    public WebElement getFromInput() {
        return fromInput;
    }

    @FindBy(xpath = "//div[@class='color-value to el-input']/input")
    private WebElement toInput;

    public WebElement getToInput() {
        return toInput;
    }

    @FindBy(className = "el-color-picker__color-inner")
    private WebElement setColourButton;

    public WebElement getSetColourButton() {
        return setColourButton;
    }

    @FindBy(className = "el-color-dropdown__value")
    private WebElement setColourInput;

    public WebElement getSetColourInput() {
        return setColourInput;
    }

    @FindBy(className = "el-color-dropdown__btn")
    private WebElement ensureSetColourButton;

    public WebElement getEnsureSetColourButton() {
        return ensureSetColourButton;
    }

    @FindBy(xpath = "//span[text()='单值']")
    private WebElement singleValueButton;

    public WebElement getSingleValueButton() {
        return singleValueButton;
    }

    @FindBy(className = "topbottom")
    private WebElement topBottom;

    // 第三种布局方式
    public WebElement getTopBottom() {
        return topBottom;
    }

    @FindBy(className = "topleftright")
    private WebElement topLeftRight;

    // 第四种布局方式
    public WebElement getTopLeftRight() {
        return topLeftRight;
    }

    @FindBy(className = "leftrightbottom")
    private WebElement leftRightBottom;

    // 第五种布局方式
    public WebElement getLeftRightBottom() {
        return leftRightBottom;
    }

    @FindBy(className = "lefttopbottom")
    private WebElement lefttopbottom;

    // 第六种布局方式
    public WebElement getLefttopbottom() {
        return lefttopbottom;
    }

    @FindBy(className = "topbottomright")
    private WebElement topbottomright;

    // 第七种布局方式
    public WebElement getTopbottomright() {
        return topbottomright;
    }

    @FindBy(className = "all")
    private WebElement all;

    // 第八种布局方式
    public WebElement getAll() {
        return all;
    }

    @FindBy(className = "yw-modal-btn-primary")
    private List<WebElement> ensureInputButton;

    public WebElement getEnsureInputButton() {
        return ensureInputButton.get(0);
    }

    @FindBy(xpath = "//label[contains(text(),'动态字段')]/following-sibling::div//input")
    private WebElement dynamicFields;

    public WebElement getDynamicFields() {
        return dynamicFields;
    }

    @FindBy(xpath = "//label[contains(text(),'搜索内容')]/following-sibling::div//textarea")
    private WebElement searchInput;

    public WebElement getSearchInput() {
        return searchInput;
    }

    @FindBy(xpath = "//div[@class='el-form-item dynamic-search-btn']//span")
    private WebElement searchInputButton;

    public WebElement getSearchInputButton() {
        return searchInputButton;
    }

    @FindBy(xpath = "//label[contains(text(),'时间范围')]/following-sibling::div//input")
    private WebElement timeRange;

    public WebElement getTimeRange() {
        return timeRange;
    }

    // 动态菜单下的默认值
    public WebElement getDynamicDefault() {
        defaultValue.click();
        return dropdownList.get(dropdownList.size() - 1);
    }
}
