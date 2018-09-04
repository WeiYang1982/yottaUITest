package com.yottabyte.pages.topology;

import com.yottabyte.hooks.LoginBeforeAllTests;
import com.yottabyte.pages.DateEditorPage;
import com.yottabyte.pages.PageTemplate;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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

    @FindBy(xpath = "//span[text()='右下值']")
    private WebElement lowerRight;

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
//        DateEditorPage dateEditorPage = new DateEditorPage(webDriver);
//        LoginBeforeAllTests.setPageFactory(dateEditorPage);
//        WebElement webElement = dateEditorPage.getToday();
//        LoginBeforeAllTests.setPageFactory(this);
//        return webElement;
        return this.getTime("Today");
    }

    // 获取昨天按钮
    public WebElement getYesterday() {
        DateEditorPage dateEditorPage = new DateEditorPage(webDriver);
        LoginBeforeAllTests.setPageFactory(dateEditorPage);
        WebElement webElement = dateEditorPage.getYesterday();
        LoginBeforeAllTests.setPageFactory(this);
        return webElement;
    }

    // 获取本周按钮
    public WebElement getThisWeek() {
        DateEditorPage dateEditorPage = new DateEditorPage(webDriver);
        LoginBeforeAllTests.setPageFactory(dateEditorPage);
        WebElement webElement = dateEditorPage.getThisWeek();
        LoginBeforeAllTests.setPageFactory(this);
        return webElement;
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
            webElement = (WebElement) method.invoke(dateEditorPageClass.getDeclaredConstructor().newInstance());
            LoginBeforeAllTests.setPageFactory(this);
        } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return webElement;
    }

    public static void main(String[] args) {
        new DetailPage(LoginBeforeAllTests.getWebDriver()).getTime("Today");
    }

    @FindBy(xpath = "//span[text()='搜索']")
    private WebElement searchButton;

    public WebElement getSearchButton() {
        return searchButton;
    }

    @FindBy(xpath = "//input[@placeholder='请选择展示字段']")
    private WebElement filedInput;

    @FindBy(className = "el-select-dropdown__list")
    private List<WebElement> dropdownList;

    public WebElement getFiledInput() {
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
}
