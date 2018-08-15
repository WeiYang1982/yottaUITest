package com.yottabyte.pages.timedTask;

import com.yottabyte.hooks.LoginBeforeAllTests;
import com.yottabyte.pages.PageTemplate;
import com.yottabyte.utils.WaitForElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class ListPage extends PageTemplate {

    public ListPage(WebDriver driver) {
        super(driver);
        if (driver.manage().getCookieNamed("sessionid") == null) {
            LoginBeforeAllTests.login();
        }
    }

    @FindBy(xpath = "//*[contains(text(),'编辑')]")
    private List<WebElement> edit;

    @FindBy(xpath = "//*[contains(text(),'复制')]")
    private List<WebElement> copy;

    @FindBy(xpath = "//*[contains(text(),'分组')]")
    private List<WebElement> changeGroup;

    @FindBy(xpath = "//*[contains(text(),'删除')]")
    private List<WebElement> delete;

    @FindBy(className = "number")
    private List<WebElement> number;

    @FindBy(className = "el-message__group")
    private WebElement successMessage;

    @FindBy(className = "el-input__inner")
    private List<WebElement> groups;

    @FindBy(className = "el-select-dropdown__list")
    private List<WebElement> dropdownLists;

    @FindBy(className = "el-button--primary")
    private List<WebElement> ensureButton;

    // 表体
    @FindBy(className = "el-table__body")
    private WebElement tableBody;

    @FindBy(className = "el-switch")
    private WebElement switchButton;

    @FindBy(className = "el-button--default")
    private List<WebElement> selectLists;

    @FindBy(className = "el-dropdown-menu__item")
    private List<WebElement> selectOptions;

    public List<WebElement> getGroupList() {
        selectLists.get(0).click();
        return selectOptions;
    }

    public WebElement getSwitchButton() {
        return switchButton;
    }

    public WebElement getDelete() {
        this.clickLastPage();
        return delete.get(delete.size() - 1);
    }

    public WebElement getTableBody() {
        return tableBody;
    }

    public WebElement getEnsureChangeGroup() {
        return ensureButton.get(0);
    }

    public WebElement getEnsureDelete() {
        return ensureButton.get(1);
    }

    public List<WebElement> getGroup() {
        groups.get(groups.size() - 1).click();
        return dropdownLists.get(dropdownLists.size() - 1).findElements(By.tagName("li"));
    }

    public WebElement getSuccessMessage() {
        return successMessage;
    }

    public WebElement getChangeGroup() {
        this.clickLastPage();
        return changeGroup.get(changeGroup.size() - 2);
    }

    // 最后一条记录的编辑按钮
    public WebElement getEdit() {
        this.clickLastPage();
        return edit.get(edit.size() - 1);
    }

    // 最后一条记录的复制按钮
    public WebElement getCopy() {
        this.clickLastPage();
        return copy.get(copy.size() - 1);
    }

    //点击最后一页
    public void clickLastPage() {
        WaitForElement.waitForElementWithExpectedCondition(LoginBeforeAllTests.getWebDriver(), ExpectedConditions.visibilityOf(number.get(0)));
        number.get(number.size() - 1).click();
    }
}
