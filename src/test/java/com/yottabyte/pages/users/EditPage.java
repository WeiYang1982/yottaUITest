package com.yottabyte.pages.users;

import com.yottabyte.pages.PageTemplate;
import com.yottabyte.utils.WaitForElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class EditPage extends PageTemplate {
    public EditPage(WebDriver driver) {
        super(driver);
    }

    @FindBy (xpath = "//input[@placeholder='请输入用户名']")
    private WebElement userName;

    @FindBy (xpath = "//input[@placeholder='请输入全名']")
    private WebElement fullName;

    @FindBy (xpath = "//input[@placeholder='请输入邮箱地址']")
    private WebElement email;

    @FindBy (xpath = "//input[@placeholder='请输入电话号码']")
    private WebElement telephone;

    @FindBy (className = "yw-select")
    private WebElement groupButton;

    @FindBy (className = "el-select-dropdown")
    private WebElement selectors;

    @FindBy (className = "el-checkbox__inner")
    private WebElement modifyPassword;

    @FindBy (xpath = "//label[text()='新密码']//following-sibling::div//input[@type='password']")
    private WebElement newPassword;

    @FindBy (xpath = "//label[text()='重复密码']//following-sibling::div//input[@type='password']")
    private WebElement repeatPassword;

    @FindBy (className = "btn-submit")
    private WebElement saveButton;

    @FindBy (className = "el-message-box__message")
    private WebElement message;

    public WebElement getUserName() {
        return userName;
    }

    public WebElement getFullName() {
        return fullName;
    }

    public WebElement getEmail() {
        return email;
    }

    public WebElement getTelephone() {
        return telephone;
    }

    public List<WebElement> getUserGroups() {
        groupButton.click();
        ExpectedCondition expectedCondition = ExpectedConditions.visibilityOf(selectors);
        WaitForElement.waitForElementWithExpectedCondition(webDriver,expectedCondition);
        return selectors.findElements(By.tagName("li"));
    }

    public WebElement getModifyPassword() {
        return modifyPassword;
    }

    public WebElement getNewPassword() {
        return newPassword;
    }

    public WebElement getRepeatPassword() {
        return repeatPassword;
    }

    public WebElement getSaveButton() {
        return saveButton;
    }

    public WebElement getSuccessMessage(){
        return message;
    }

    public WebElement getErrorMessage(){
        return message;
    }
}
