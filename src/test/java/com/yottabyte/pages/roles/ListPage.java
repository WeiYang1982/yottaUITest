package com.yottabyte.pages.roles;

import com.yottabyte.pages.PageTemplate;
import com.yottabyte.stepDefs.IWillSeeNewPage;
import com.yottabyte.stepDefs.WaitForSomeSecond;
import com.yottabyte.utils.ElementExist;
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

    @FindBy(className = "el-loading-mask")
    private WebElement loadingElement;

    @FindBys({
            @FindBy (className = "yw-table-group__basic"),
            @FindBy (className = "el-input__inner")
    })
    private WebElement searchInput;

    @FindBy (className = "slot-button")
    private WebElement createRoleButton;

    @FindBy (className = "el-table__body")
    private WebElement searchResultTable;

    @FindBy (className = "el-table_1_column_1")
    private List<WebElement> searchResultRows;

    @FindBy (className = "el-table__empty-text")
    private WebElement noSearchResultMessage;

    @FindBy (className = "el-message-box__content")
    private WebElement messageInfo;

    @FindBy (className = "el-message-box__btns")
    private WebElement messageBoxButtons;


    public WebElement getSearchInput() {
        return searchInput;
    }

    public WebElement getSearchResult(){
        try{
            Thread.sleep(1000);
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (!ElementExist.isElementExist(webDriver,noSearchResultMessage)){
            return searchResultRows.get(1);
        }else {
            if (searchResultRows.size() == 1) {
                ExpectedCondition expectedCondition = ExpectedConditions.visibilityOf(noSearchResultMessage);
                WaitForElement.waitForElementWithExpectedCondition(webDriver,expectedCondition);
                return noSearchResultMessage;
            }else {
                throw new NoSuchElementException("未找到list: el-table_1_column_1");
            }
        }
    }

    public WebElement getCreateRoleButton() {
        ExpectedCondition expectedCondition = ExpectedConditions.elementToBeClickable(createRoleButton);
        WaitForElement.waitForElementWithExpectedCondition(webDriver,expectedCondition);
        return createRoleButton;
    }

    public WebElement getSearchResultTable() {
        return searchResultTable;
    }

    public WebElement getTableEditButton(int row){
        WebElement e = getTableRowButtons(row);
        return e.findElement(By.xpath("//button/span[contains(text(),'编辑')]"));
    }

    public WebElement getTableAuthorizeButton(int row){
        WebElement e = getTableRowButtons(row);
        return e.findElement(By.xpath("//button/span[contains(text(),'授权')]"));
    }

    public WebElement getTableCopyButton(int row){
        WebElement e = getTableRowButtons(row);
        return e.findElement(By.xpath("//button/span[contains(text(),'复制')]"));
    }

    public WebElement getTableDeleteButton(int row){
        WebElement e = getTableRowButtons(row);
        return e.findElement(By.xpath("//button/span[contains(text(),'删除')]"));
    }

    public WebElement getMessageBoxOKButton(){
        ExpectedCondition expectedCondition = ExpectedConditions.textToBePresentInElement(messageInfo,"确认删除");
        WaitForElement.waitForElementWithExpectedCondition(webDriver,expectedCondition);
        List<WebElement> list = messageBoxButtons.findElements(By.tagName("button"));
        for (WebElement e : list){
            if ("确定".equals(e.getText())){
                return e;
            }
        }
        return null;
    }

    public WebElement getSuccessMessage(){
        ExpectedCondition expectedCondition = ExpectedConditions.textToBePresentInElement(messageInfo,"成功");
        WaitForElement.waitForElementWithExpectedCondition(webDriver,expectedCondition);
        return messageInfo;
    }

    public void thereIsARole(String roleName, String roleDes, List<String> resourceGroups) {
        if (searchARole(roleName)){
            getSearchInput().sendKeys(Keys.END);
            getSearchInput().sendKeys(Keys.SHIFT, Keys.HOME);
            getSearchInput().sendKeys(Keys.BACK_SPACE);
        }else {
            getCreateRoleButton().click();
            IWillSeeNewPage page = new IWillSeeNewPage();
            CreatePage createPage = new CreatePage(webDriver);
            page.iWillSeeNewPage("roles.CreatePage");
            createPage.createARole(roleName,roleDes,resourceGroups);
            page.iWillSeeNewPage("roles.ListPage");
        }
    }

    public void thereIsNoRole(String roleName){
        if (searchARole(roleName)){
            getTableDeleteButton(1).click();
            ExpectedCondition e = ExpectedConditions.elementToBeClickable(getMessageBoxOKButton());
            WaitForElement.waitForElementWithExpectedCondition(webDriver,e);
            getMessageBoxOKButton().click();
            ExpectedCondition expectedCondition = ExpectedConditions.invisibilityOf(loadingElement);
            WaitForElement.waitForElementWithExpectedCondition(webDriver,expectedCondition);
            webDriver.navigate().refresh();
            WaitForElement.waitForElementWithExpectedCondition(webDriver,ExpectedConditions.visibilityOf(searchInput));
        }
    }

    private boolean searchARole(String roleName){
        getSearchInput().sendKeys(Keys.END);
        getSearchInput().sendKeys(Keys.SHIFT, Keys.HOME);
        getSearchInput().sendKeys(Keys.BACK_SPACE);
        getSearchInput().sendKeys(roleName);
        String text = getSearchResult().getText();
        if ("暂无数据".equals(text)){
            return false;
        }else {
            return true;
        }
    }

    private WebElement getTableRowButtons(int row){
        WebElement table = searchResultTable;
        return table.findElements(By.className("el-table_1_column_3")).get(row-1);
    }
}
