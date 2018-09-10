package com.yottabyte.pages.users;

import com.yottabyte.pages.PageTemplate;
import com.yottabyte.stepDefs.ClickSomeButton;
import com.yottabyte.stepDefs.IChooseValueFromSelectList;
import com.yottabyte.stepDefs.SetKeyWithValue;
import com.yottabyte.utils.WaitForElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class CreatePage extends PageTemplate{

    public CreatePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//input[@placeholder='请输入用户名']")
    private WebElement userName;

    @FindBy(xpath = "//input[@placeholder='请输入全名']")
    private WebElement fullName;

    @FindBy(xpath = "//input[@placeholder='请输入邮箱地址']")
    private WebElement email;

    @FindBy(xpath = "//input[@placeholder='请输入电话号码']")
    private WebElement telephone;

    @FindBy(xpath = "//input[@placeholder='长度8到16个字符，至少包含数字和字母']")
    private WebElement password;

    @FindBy(className = "yw-select")
    private WebElement userGroupButton;

    @FindBy(className = "el-select-dropdown")
    private List<WebElement> selectors;

    @FindBy(className = "btn-submit")
    private WebElement createButton;

    @FindBy(xpath = "//*[@class='el-message-box__btns']//span[contains(text(),'确定')]")
    private WebElement OKButton;

    @FindBy(className = "el-message-box__message")
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

    public WebElement getPassword() {
        return password;
    }

    public List<WebElement> getUserGroups() {
        userGroupButton.click();
        ExpectedCondition expectedCondition = ExpectedConditions.visibilityOf(selectors.get(0));
        WaitForElement.waitForElementWithExpectedCondition(webDriver,expectedCondition);
        List<WebElement> list = selectors.get(0).findElements(By.tagName("li"));
        return list;
    }

    public WebElement getCreateButton() {
        ExpectedCondition expectedCondition = ExpectedConditions.elementToBeClickable(createButton);
        WaitForElement.waitForElementWithExpectedCondition(webDriver,expectedCondition);
        return createButton;
    }

    public WebElement getOKButton() {
        return OKButton;
    }

    public WebElement getSuccessMessage(){
        return super.getSuccessMessage();
    }

    public WebElement getErrorMessage(){
        return message;
    }

    public void createAUser(String userName, String fullName, String email, String telephone, String password, List<String> userGroup) {
        SetKeyWithValue setKey = new SetKeyWithValue();
        ClickSomeButton click = new ClickSomeButton();
        IChooseValueFromSelectList choose = new IChooseValueFromSelectList();
        setKey.iSetTheParameterWithValue("UserName",userName);
        setKey.iSetTheParameterWithValue("FullName",fullName);
        setKey.iSetTheParameterWithValue("Email",email);
        setKey.iSetTheParameterWithValue("Telephone",telephone);
        setKey.iSetTheParameterWithValue("Password",password);
        choose.iChooseTheFromThe(userGroup,"UserGroups");
        click.iClickTheButton("CreateButton");
        click.iClickTheButton("OKButton");

    }


}
