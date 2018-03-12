package com.yottabyte.pages.userGroups;

import com.yottabyte.pages.PageTemplate;
import com.yottabyte.utils.WaitForElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class CreatePage extends PageTemplate{

    public CreatePage(WebDriver driver) {
        super(driver);
    }

    @FindBy (xpath = "//input[@placeholder='请输入分组名']")
    private WebElement userGroupName;

    @FindBy (xpath = "//input[@placeholder='请输入描述']")
    private WebElement userGroupDes;

    @FindBy (className = "yw-select-owner")
    private WebElement ownerButton;

    @FindBy (className = "yw-select-info")
    private WebElement roleButton;

    @FindBy (className = "el-select-dropdown")
    private List<WebElement> selectors;

    @FindBy(className = "btn-submit")
    private WebElement createButton;

    @FindBy(className = "el-message-box__message")
    private WebElement message;

    public WebElement getUserGroupName() {
        return userGroupName;
    }

    public WebElement getUserGroupDes() {
        return userGroupDes;
    }

    public List<WebElement> getUserGroupOwner(){
        return getSelectorElements(ownerButton);
    }

    public List<WebElement> getUserGroupRole(){
        return getSelectorElements(roleButton);
    }

    public WebElement getCreateButton() {
        ExpectedCondition expectedCondition = ExpectedConditions.elementToBeClickable(createButton);
        WaitForElement.waitForElementWithExpectedCondition(webDriver,expectedCondition);
        return createButton;
    }

    public WebElement getSuccessMessage(){
        return message;
    }

    public WebElement getErrorMessage(){
        return message;
    }

    private List<WebElement> getSelectorElements(WebElement e){
        e.click();
        ExpectedCondition expectedCondition = ExpectedConditions.visibilityOf(selectors.get(1));
        WaitForElement.waitForElementWithExpectedCondition(webDriver,expectedCondition);
        List<WebElement> list = selectors.get(1).findElements(By.tagName("li"));
        return list;
    }


}

