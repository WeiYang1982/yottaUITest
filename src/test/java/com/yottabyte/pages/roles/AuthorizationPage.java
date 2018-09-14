package com.yottabyte.pages.roles;

import com.yottabyte.config.ConfigManager;
import com.yottabyte.hooks.LoginBeforeAllTests;
import com.yottabyte.pages.PageTemplate;
import com.yottabyte.stepDefs.ICheckValuesFromCheckBox;
import com.yottabyte.stepDefs.Pagination;
import com.yottabyte.utils.ElementExist;
import com.yottabyte.utils.WaitForElement;
import com.yottabyte.webDriver.SharedDriver;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class AuthorizationPage extends PageTemplate {

    public AuthorizationPage(WebDriver driver) {
        super(driver);
    }

    private WebElement webElement;

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

    public List<WebElement> getGroupManagement(String targetName) {
        webElement = getElementFromTable(targetName);
        return webElement.findElements(By.tagName("td")).get(2).findElements(By.className("el-checkbox"));
    }

    public List<WebElement> getIntraGroupManagement(String targetName) {
        if (webElement != null) {
            return webElement.findElements(By.tagName("td")).get(3).findElements(By.className("el-checkbox"));
        }else {
            return getElementFromTable(targetName).findElements(By.tagName("td")).get(3).findElements(By.className("el-checkbox"));
        }
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


    private WebElement getElementFromTable(String targetName) {
        WebElement tr = Pagination.forEachThePaginationDesc(tab, 2, targetName);
        return tr;
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

    public static void main(String[] args) throws InterruptedException {
        SharedDriver driver = new SharedDriver();
        ConfigManager c = new ConfigManager();
        LoginBeforeAllTests login = new LoginBeforeAllTests(driver,c);
        login.beforeScenario();
        Thread.sleep(3000);
        driver.get("http://192.168.1.134/account/roles/assign/1/");
        LoginBeforeAllTests.setPageFactory("roles.AuthorizationPage");
        ICheckValuesFromCheckBox checkBox = new ICheckValuesFromCheckBox();
        List<String> list = new ArrayList<>();
        list.add("读取");
        list.add("编辑");
//        Pagination pagination = new Pagination();
//        WebElement element = pagination.forEachThePaginationDesc(2, "AutoTest");
        checkBox.iCheckFromThe(list, "{'GroupManagement':['AutoTest']}");
    }

}
