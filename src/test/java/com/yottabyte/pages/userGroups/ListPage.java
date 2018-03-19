package com.yottabyte.pages.userGroups;

import com.yottabyte.pages.PageTemplate;
import com.yottabyte.stepDefs.ClickSomeButton;
import com.yottabyte.stepDefs.IChooseValueFromSelectList;
import com.yottabyte.stepDefs.IWillSeeNewPage;
import com.yottabyte.stepDefs.SetKeyWithValue;
import com.yottabyte.utils.WaitForElement;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class ListPage extends PageTemplate {

    public ListPage(WebDriver driver) {
        super(driver);
    }

    @FindBy (className = "el-loading-mask")
    private WebElement loadingElement;

    @FindBys({
            @FindBy (className = "yw-table-group__basic"),
            @FindBy (className = "el-input__inner")
    })
    private WebElement searchInput;

    @FindBy (xpath = "//*[text()='新建']")
    private WebElement createUserGroup;

    @FindBy (className = "el-table__body")
    private WebElement searchResultTable;

    @FindBy (className = "el-table_1_column_2")
    private List<WebElement> searchResultRows;

    @FindBy (className = "el-table__empty-text")
    private WebElement noSearchResultMessage;

    @FindBy (className = "el-message-box__content")
    private WebElement messageInfo;

    @FindBy (className = "el-message-box__btns")
    private WebElement messageBoxButtons;

    @FindBy (className = "el-message__group")
    private WebElement suspensionMessage;

    public WebElement getSearchInput() {
        ExpectedCondition expectedCondition = ExpectedConditions.invisibilityOf(loadingElement);
        WaitForElement.waitForElementWithExpectedCondition(webDriver,expectedCondition);
        return searchInput;
    }

    public WebElement getCreateUserGroup() {
        return createUserGroup;
    }

    public WebElement getSearchResult(){
        if (searchResultRows.size() > 1){
            return searchResultRows.get(1);
        }else if (searchResultRows.size() == 1){
            ExpectedCondition expectedCondition = ExpectedConditions.visibilityOf(noSearchResultMessage);
            WaitForElement.waitForElementWithExpectedCondition(webDriver,expectedCondition);
            return noSearchResultMessage;
        }else {
            throw new NoSuchElementException("未找到list: el-table_1_column_1");
        }
    }

    public WebElement getSearchResultTable() {
        return searchResultTable;
    }

    public WebElement getTableEditButton(int row){
        WebElement e = getTableRowButtons(row);
        return e.findElement(By.xpath("//button/span[contains(text(),'编辑')]"));
    }

    public WebElement getTableDeleteButton(int row){
        WebElement e = getTableRowButtons(row);
        return e.findElement(By.xpath("//button/span[contains(text(),'删除')]"));
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

    public WebElement getSuccessMessage(){
        return suspensionMessage;
    }

    public void thereIsAUserGroup(String userGroupName, List<String> ownerName, List<String> roleName){
        ExpectedCondition expectedCondition = ExpectedConditions.invisibilityOf(loadingElement);
        WaitForElement.waitForElementWithExpectedCondition(webDriver,expectedCondition);
        getSearchInput().sendKeys(Keys.CONTROL + "a");
        getSearchInput().sendKeys(Keys.BACK_SPACE);
        getSearchInput().sendKeys(userGroupName);
        String text = getSearchResult().getText();
        SetKeyWithValue setKey = new SetKeyWithValue();
        ClickSomeButton click = new ClickSomeButton();
        IChooseValueFromSelectList choose = new IChooseValueFromSelectList();
        if ("暂无数据".equals(text)){
            getCreateUserGroup().click();
            IWillSeeNewPage page = new IWillSeeNewPage();
            page.iWillSeeNewPage("userGroups.CreatePage");
            setKey.iSetTheParameterWithValue1("UserGroupName",userGroupName);
            choose.iChooseTheFromThe(ownerName,"UserGroupOwner");
            choose.iChooseTheFromThe(roleName,"UserGroupRole");
            click.iClickTheButton("CreateButton");
            click.iClickTheButton("OKButton");
            page.iWillSeeNewPage("userGroups.ListPage");
        }else if (text.equals(userGroupName)){
            System.out.println("There is a user groups");
            getSearchInput().sendKeys(Keys.CONTROL + "a");
            getSearchInput().sendKeys(Keys.BACK_SPACE);
        }
    }

    public void thereIsNoUserGroup(String userGroupName){
        ExpectedCondition expectedCondition = ExpectedConditions.invisibilityOf(loadingElement);
        WaitForElement.waitForElementWithExpectedCondition(webDriver,expectedCondition);
        getSearchInput().sendKeys(Keys.CONTROL + "a");
        getSearchInput().sendKeys(Keys.BACK_SPACE);
        getSearchInput().sendKeys(userGroupName);
        String text = getSearchResult().getText();
        SetKeyWithValue setKey = new SetKeyWithValue();
        ClickSomeButton click = new ClickSomeButton();
        IChooseValueFromSelectList choose = new IChooseValueFromSelectList();
        if (text.equals(userGroupName)){
            getTableDeleteButton(1).click();
            ExpectedCondition e = ExpectedConditions.elementToBeClickable(getMessageBoxOKButton());
            WaitForElement.waitForElementWithExpectedCondition(webDriver,e);
            getMessageBoxOKButton().click();
            WaitForElement.waitForElementWithExpectedCondition(webDriver,expectedCondition);
            webDriver.navigate().refresh();
        }
    }

    private WebElement getTableRowButtons(int row){
        WebElement table = getSearchResultTable();
        return table.findElements(By.className("el-table_1_column_4")).get(row-1);
    }


}
