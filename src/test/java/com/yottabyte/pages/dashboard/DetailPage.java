package com.yottabyte.pages.dashboard;

import com.yottabyte.pages.PageTemplate;
import com.yottabyte.utils.GetTime;
import com.yottabyte.utils.WaitForElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

/**
 * @author sunxj
 */
public class DetailPage extends PageTemplate {
    public DetailPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(className = "el-loading-mask")
    private WebElement loading;

    @FindBy(xpath = "//span[contains(text(),'确定')]")
    private List<WebElement> ensureList;

    @FindBy(className = "icon-guolvxuanting_icon")
    private WebElement showFilter;

    @FindBy(className = "control-panel-content")
    private WebElement filter;

    @FindBy(className = "el-switch__label--left")
    private WebElement clickableButton;

    @FindBy(className = "icon-tianjiatubiaoxuanting_icon")
    private WebElement addButton;

    @FindBy(className = "icon-yichuyiruxuanting_icon")
    private WebElement moveButton;

    @FindBy(className = "icon-gengxinxuanting_icon")
    private WebElement refreshButton;

    @FindBy(className = "icon-cunweibaobiao_icon")
    private WebElement saveAsReportButton;

    @FindBy(className = "icon-yejianxuanting_icon")
    private WebElement nightModeButton;

    @FindBy(className = "icon-quanpingxuanting_icon")
    private WebElement fullScreenButton;

    @FindBy(className = "el-checkbox")
    private List<WebElement> checkBox;

    @FindBy(className = "el-tabs__item")
    private List<WebElement> tabList;

    @FindBy(className = "icon-qiehuan1")
    private WebElement switchButton;

    @FindBy(className = "dash-switch-ul")
    private WebElement dropdownList;

    @FindBy(className = "icon-guanbianniu")
    private WebElement closeTag;

    @FindBy(className = "dropdown-link-btn")
    private WebElement dropDownLinkButton;

    @FindBy(className = "recover")
    private WebElement recoverTag;

    @FindBy(className = "moveout")
    private WebElement moveoutTag;

    @FindBy(className = "delete")
    private WebElement deleteTag;

    @FindBy(className = "el-message__group")
    private List<WebElement> successMessage;

    @FindBy(className = "icon-tianjiatubiaoxuanting_icon")
    private WebElement addEventButton;

    @FindBy(className = "yw-dropdown-menu")
    private List<WebElement> eventList;

    @FindBy(xpath = "//label[contains(text(),'输入类型')]/following-sibling::div//button")
    private WebElement inputType;

    @FindBy(xpath = "//span[text()='添加']")
    private WebElement addChoiceValueButton;

    @FindBy(xpath = "//div[@class='el-form-item dynamic-search-btn']//span")
    private WebElement searchInputButton;

    @FindBy(xpath = "//label[contains(text(),'搜索内容')]/following-sibling::div//textarea")
    private WebElement searchInput;

    @FindBy(xpath = "//label[contains(text(),'标题')]/following-sibling::div//input[@class='el-input__inner']")
    private List<WebElement> titleList;

    public WebElement getInputTitle() {
        return titleList.get(titleList.size() - 1);
    }

    public WebElement getSearchInputButton() {
        return searchInputButton;
    }

    // 获取本月按钮
    public WebElement getThisMonth() {
        return GetTime.getTime(webDriver, "ThisMonth");
    }

    public WebElement getTimeRange() {
        return super.getInputElement("搜索时间");
    }

    public WebElement getDefaultDropdownList() {
        return super.getDropdownList("默认值");
    }

    public WebElement getDynamicField() {
        return super.getInputElement("动态字段");
    }

    public WebElement getSearchInput() {
        return searchInput;
    }

    public WebElement getAddChoiceValueButton() {
        return addChoiceValueButton;
    }

    public WebElement getInputType() {
        inputType.click();
        return eventList.get(eventList.size() - 1);
    }

    public WebElement getFilterTitle() {
        return super.getInputElement("标题");
    }

    public WebElement getChoiceValue() {
        return super.getInputElement("可选值");
    }

    public WebElement getFilterToken() {
        return super.getInputElement("标识");
    }

    public WebElement getFilterField() {
        return super.getInputElement("过滤字段");
    }

    public WebElement getFilterDefaultValue() {
        return super.getInputElement("默认值");
    }


    public WebElement getEventList() {
        return eventList.get(eventList.size() - 1);
    }

    public WebElement getAddEventButton() {
        return addEventButton;
    }

    public WebElement getSuccessMessage() {
        return successMessage.get(successMessage.size() - 1);
    }

    public WebElement getDeleteTag() {
        return deleteTag;
    }

    public WebElement getRecoverTag() {
        return recoverTag;
    }

    public WebElement getMoveoutTag() {
        return moveoutTag;
    }

    public WebElement getDropDownLinkButton() {
        return dropDownLinkButton;
    }

    public WebElement getCloseTag() {
        return closeTag;
    }

    public WebElement getDropdownList() {
        WaitForElement.waitForElementWithExpectedCondition(webDriver, ExpectedConditions.invisibilityOf(ensureList.get(0)));
        isLoaded();
        switchButton.click();
        return dropdownList;
    }

    public WebElement getTab() {
        return tabList.get(tabList.size() - 1);
    }

    public List<WebElement> getCheckBox() {
        return checkBox;
    }

    public WebElement getSaveAsReportButton() {
        return saveAsReportButton;
    }

    public WebElement getNightModeButton() {
        return nightModeButton;
    }

    public WebElement getFullScreenButton() {
        return fullScreenButton;
    }

    public WebElement getAddButton() {
        return addButton;
    }

    public WebElement getMoveButton() {
        return moveButton;
    }

    public WebElement getRefreshButton() {
        return refreshButton;
    }

    public WebElement getClickableButton() {
        return clickableButton;
    }

    public WebElement getFilter() {
        return filter;
    }

    public WebElement getShowFilter() {
        return showFilter;
    }

    public WebElement getTagName() {
        return super.getInputElement("名称");
    }

    public WebElement getEnsureCreateTagButton() {
        return ensureList.get(0);
    }

    public WebElement getEnsureMoveTagButton() {
        return ensureList.get(4);
    }

    public WebElement getEnsureDeleteTagButton() {
        return ensureList.get(6);
    }

    public WebElement getEnsureCreateFilter() {
        return ensureList.get(8);
    }

    public WebElement getEnsureCreateInput() {
        return ensureList.get(9);
    }

    @Override
    protected void isLoaded() throws Error {
        WaitForElement.waitForElementWithExpectedCondition(webDriver, ExpectedConditions.invisibilityOf(loading));
    }
}
