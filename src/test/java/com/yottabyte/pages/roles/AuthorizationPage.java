package com.yottabyte.pages.roles;

import com.yottabyte.hooks.LoginBeforeAllTests;
import com.yottabyte.pages.PageTemplate;
import com.yottabyte.utils.WaitForElement;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class AuthorizationPage extends PageTemplate {

    public AuthorizationPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(className = "el-loading-mask")
    private WebElement loading;

    @FindBy(xpath = "//div[not(contains(@style,'display: none;'))][@class='assign-tab']")
    private WebElement tab;

    @FindBy(className = "function-checkbox")
    private List<WebElement> functionCheckboxes;

    @FindBy(className = "url-checkbox")
    private List<WebElement> urlCheckboxes;

    @FindBy(xpath = "//div[@class='operation-btn-block']/button/span[text()='保存']")
    private WebElement saveButton;

//    public WebElement getUserGroupTab() {
//        return userGroupTab;
//    }

    @FindBy(className = "el-message__group")
    private WebElement successMessage;

//    @FindBy(className = "el-message-box__message")
//    private WebElement message;

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
        return tab.findElement(By.xpath("//span[contains(text(),'" + value + "')]/preceding-sibling::span"));
    }

    public WebElement getCreateUnitButton(String value) {
        return tab.findElement(By.xpath("//span[text()='" + value + "']/preceding-sibling::span"));
    }

    public WebElement getChooseAllCheckBoxes() {
        return tab.findElement(By.className("el-checkbox__input"));
    }

    public List<WebElement> getFunctionCheckboxes() {
        return functionCheckboxes;
    }

    public List<WebElement> getUrlCheckboxes() {
        return urlCheckboxes;
    }

    public WebElement getSearchTime() {
        return tab.findElement(By.tagName("input"));
    }

    public WebElement getSuccessMessage() {
        return super.getSuccessMessage();
    }

    public WebElement getErrorMessage() {
        return super.getErrorMessage();
    }


    @Override
    protected void load() {
        webDriver.get("http://" + config.get("rizhiyi_server_host") + "/account/roles/");
        LoginBeforeAllTests.login();
        webDriver.get("http://" + config.get("rizhiyi_server_host") + "/account/roles/");
    }
    @Override
    protected void isLoaded() throws Error {
        try {
            WaitForElement.waitForElementWithExpectedCondition(webDriver, ExpectedConditions.invisibilityOf(loading));
            assertTrue(webDriver.getCurrentUrl().contains("/account/roles/"));
        } catch (Exception e) {
            throw new Error("Cannot locate account roles page");
        }
    }

}
