package com.yottabyte.pages.roles;

import com.yottabyte.pages.PageTemplate;
import com.yottabyte.utils.WaitForElement;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AuthorizationPage extends PageTemplate {

    public AuthorizationPage(WebDriver driver) {
        super(driver);
        isLoaded();
    }

    @FindBy(className = "el-loading-mask")
    private WebElement loading;
//
//    @FindBy(xpath = "//span[text()='用户分组']/parent::button")
//    private WebElement userGroupTab;
//
//    @FindBy(xpath = "//span[text()='Agent 管理']/parent::button")
//    private WebElement agentManagerTab;
//
//    @FindBy(xpath = "//span[text()='监控']/parent::button")
//    private WebElement alertTab;

    @FindBy(xpath = "//div[@class='operation-btn-block']/button/span[text()='保存']")
    private WebElement saveButton;

//    public WebElement getUserGroupTab() {
//        return userGroupTab;
//    }

    @FindBy(className = "el-message__group")
    private WebElement successMessage;

//    public WebElement getAgentManagerTab() {
//        return agentManagerTab;
//    }
//
//    public WebElement getAlertTab() {
//        return alertTab;
//    }

    public WebElement getSaveButton() {
        return saveButton;
    }

    public WebElement getTabButton(String tabName) {
        return webDriver.findElement(By.xpath("//span[text()='" + tabName + "']/parent::button"));
    }

    public WebElement getCreateGroupButton(String value) {
        return webDriver.findElement(By.xpath("//span[contains(text(),'" + value + "')]/preceding-sibling::span"));
    }

    public WebElement getCreateUnitButton(String value) {
        return webDriver.findElement(By.xpath("//span[text()='" + value + "']/preceding-sibling::span"));
    }

    public WebElement getSuccessMessage() {
        return successMessage;
    }




    @Override
    protected void isLoaded() throws Error {
        WaitForElement.waitForElementWithExpectedCondition(webDriver, ExpectedConditions.invisibilityOf(loading));
    }

}
