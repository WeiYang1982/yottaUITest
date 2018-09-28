package com.yottabyte.pages.users;

import com.yottabyte.hooks.LoginBeforeAllTests;
import com.yottabyte.pages.PageTemplate;
import com.yottabyte.utils.WaitForElement;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.ui.*;

import javax.annotation.Nullable;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class ListPage extends PageTemplate{

    public ListPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(className = "el-loading-mask")
    private WebElement loadingElement;

    @FindBys({
            @FindBy (className = "yw-table-group__basic"),
            @FindBy (className = "el-input__inner")
    })
    private WebElement searchInput;

    @FindBy (xpath = "//*[text()='新建']")
    private WebElement createUser;

    @FindBy (className = "el-table__body")
    private WebElement searchResultTable;
    // 搜索结果 (只搜索显示出来的元素)
    @FindBy (xpath = "//div[@class='runner-cell']/*[not(@style='display: none;')]")
    private List<WebElement> searchResultRows;

    @FindBy (className = "el-table__empty-text")
    private WebElement noSearchResultMessage;

    @FindBy (className = "el-message-box__content")
    private WebElement messageInfo;

    @FindBy (className = "el-message-box__btns")
    private WebElement messageBoxButtons;

    @FindBy (className = "el-message__group")
    private WebElement suspensionMessage;

    @FindBy (className = "el-dialog")
    private WebElement dialog;

    @FindBy (className = "group-modal-select")
    private WebElement changeGroupButton;

    @FindBy (className = "el-select-dropdown")
    private List<WebElement> dropdownLists;

    @FindBy(className = "el-message-box__message")
    private WebElement message;

    public WebElement getSearchInput() {
        ExpectedCondition expectedCondition = ExpectedConditions.invisibilityOf(loadingElement);
        WaitForElement.waitForElementWithExpectedCondition(webDriver,expectedCondition);
        return searchInput;
    }

    public WebElement getCreateUser() {
        return createUser;
    }

    public WebElement getSearchResult(){
        if (searchResultRows.size() >= 1){
            List<WebElement> list = searchResultRows.get(0).findElements(By.tagName("span"));
            if (list.size() >= 1){
                return list.get(0);
            }else {
                return searchResultRows.get(0);
            }
        }else {
            ExpectedCondition expectedCondition = ExpectedConditions.visibilityOf(noSearchResultMessage);
            WaitForElement.waitForElementWithExpectedCondition(webDriver,expectedCondition);
            return noSearchResultMessage;
        }
    }

    public WebElement getUserStatus(){
        ExpectedCondition expectedCondition = ExpectedConditions.refreshed(new ExpectedCondition<Object>() {
            @Nullable
            @Override
            public Object apply(@Nullable WebDriver webDriver) {
                return webDriver.findElements(By.xpath("//div[@class='runner-cell']/*[not(@style='display: none;')]")).get(0).findElements(By.tagName("span")).get(1);
            }
        });
        WaitForElement.waitForElementWithExpectedCondition(webDriver,expectedCondition);
//        List<WebElement> list = searchResultRows.get(0).findElements(By.tagName("span"));
//        if (list.size() >= 1){
//            return list.get(1);
//        }else {
//            throw new NoSuchElementException("未找到用户状态");
//        }
        return webDriver.findElements(By.xpath("//div[@class='runner-cell']/*[not(@style='display: none;')]")).get(0).findElements(By.tagName("span")).get(1);
    }

    public WebElement getSearchResultTable() {
        return searchResultTable;
    }

    public WebElement getTableSeeDetailButton(int row){
        WebElement e = getTableRowButtons(row);
        return e.findElement(By.xpath("//button/span[contains(text(),'查看')]"));
    }

    public WebElement getTableChangeGroupButton(int row){
        WebElement e = getTableRowButtons(row);
        return e.findElement(By.xpath("//button/span[contains(text(),'分组')]"));
    }

    public WebElement getTableDeleteButton(int row){
        WebElement e = getTableRowButtons(row);
        return e.findElement(By.xpath("//button/span[contains(text(),'删除')]"));
    }

    public WebElement getTableDisableButton(int row){
        WebElement e = getTableRowButtons(row);
        return e.findElement(By.xpath("//button/span[contains(text(),'禁用')]"));
    }

    public WebElement getTableEnableButton(int row){
        ExpectedCondition expectedCondition = ExpectedConditions.invisibilityOf(loadingElement);
        WaitForElement.waitForElementWithExpectedCondition(webDriver,expectedCondition);
        WebElement e = getTableRowButtons(row);
        return e.findElement(By.xpath("//button/span[contains(text(),'启用')]"));
    }

    public WebElement getUserGroups() {
        WaitForElement.waitForElementWithExpectedCondition(webDriver,ExpectedConditions.visibilityOf(dialog));
        changeGroupButton.click();
        WaitForElement.waitForElementWithExpectedCondition(webDriver,ExpectedConditions.visibilityOf(dropdownLists.get(dropdownLists.size()-1)));
        return dropdownLists.get(dropdownLists.size()-1);
    }

    public WebElement getConfirmButton() {
        return dialog.findElement(By.xpath("//button//span[contains(text(),'确定')]"));
    }

    public WebElement getMessageBoxButtons() {
        return messageBoxButtons;
    }

    public WebElement getMessageBoxOKButton(){
        ExpectedCondition expectedCondition = ExpectedConditions.textToBePresentInElement(messageInfo,"将删除");
        WaitForElement.waitForElementWithExpectedCondition(webDriver,expectedCondition);
        List<WebElement> list = getMessageBoxButtons().findElements(By.tagName("button"));
        for (WebElement e : list){
            if ("确定".equals(e.getText())){
                return e;
            }
        }
        return null;
    }

    public WebElement getSuccessMessage() {
        ExpectedCondition expectedCondition = ExpectedConditions.visibilityOf(suspensionMessage);
        WaitForElement.waitForElementWithExpectedCondition(webDriver,expectedCondition);
        return suspensionMessage;
    }

    public WebElement getErrorMessage(){
        return message;
    }

    @Override
    protected void load() {
        webDriver.get("http://" + config.get("rizhiyi_server_host") + "/account/users/");
        LoginBeforeAllTests.login();
        webDriver.get("http://" + config.get("rizhiyi_server_host") + "/account/users/");
    }

    @Override
    protected void isLoaded() throws Error {
        try {
            WaitForElement.waitForElementWithExpectedCondition(webDriver, ExpectedConditions.invisibilityOf(loadingElement));
            assertTrue(webDriver.getCurrentUrl().contains("/account/users/"));
        } catch (Exception e) {
            throw new Error("Cannot locate account page");
        }
    }




    private WebElement getTableRowButtons(int row){
        WebElement table = getSearchResultTable();
        return table.findElements(By.className("el-table_1_column_4")).get(row-1);
    }

}
