package com.yottabyte.pages.resourceGroups;

import com.yottabyte.pages.PageTemplate;
import com.yottabyte.utils.ElementExist;
import com.yottabyte.utils.WaitForElement;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
    private WebElement createResourceGroup;

    @FindBy (xpath = "//button/span[text()='导入/导出']")
    private WebElement uploadAndDownloadButton;

    @FindBy (css = "body > div.el-popover.port-menu")
    private WebElement uploadAndDownloadMenu;

    @FindBy (xpath = "//button/span[text()='导入']")
    private WebElement uploadButton;

    @FindBy (xpath = "//button/span[text()='导出']")
    private WebElement downloadButton;

    @FindBy (className = "el-upload__input")
    private WebElement uploadInputElement;

    @FindBy (className = "verify-text")
    private WebElement uploadInfo;

    @FindBy (xpath = "//*[@class='button-block']/button[1]")
    private WebElement bothSaveButton;

    @FindBy (xpath = "//*[@class='button-block']/button[2]")
    private WebElement notCoverButton;

    @FindBy (xpath = "//*[@class='button-block']/button[3]")
    private WebElement coverButton;

    @FindBy (xpath = "//div[@class='el-form-item__content']//div[@class='el-input']")
    private WebElement ownerInputButton;

    @FindBy (xpath = "//div[@class='el-select-dropdown is-multiple import-popper']")
    private WebElement selectors;

    @FindBy (xpath = "//div[@class='dialog-footer']/button/span[text()='确定']")
    private WebElement OKButton;

    @FindBy (xpath = "//div[@class='dialog-footer']/button/span[text()='确定']")
    private WebElement CancelButton;

    @FindBy (className = "el-table_1_column_1")
    private List<WebElement> searchResultRows;

    @FindBy (className = "el-table__body")
    private WebElement searchResultTable;

    @FindBy (className = "el-table__empty-text")
    private WebElement noSearchResultMessage;

    @FindBy (className = "el-message-box__btns")
    private WebElement messageBoxButtons;

    @FindBy (className = "el-message-box__content")
    private WebElement messageInfo;

    @FindBy (xpath = "//div[@class='el-form-item'][2]//span")
    private WebElement dialogErrorMessage;

    public WebElement getUploadButton() {
        ExpectedCondition e = ExpectedConditions.invisibilityOf(loadingElement);
        WaitForElement.waitForElementWithExpectedCondition(webDriver,e);
        uploadAndDownloadButton.click();
        ExpectedCondition expectedCondition = ExpectedConditions.visibilityOf(uploadButton);
        WaitForElement.waitForElementWithExpectedCondition(webDriver,expectedCondition);
        return uploadButton;
    }

    public WebElement getUploadInputElement() {
        return uploadInputElement;
    }

    public WebElement getBothSaveButton() {
        return bothSaveButton;
    }

    public WebElement getNotCoverButton() {
        return notCoverButton;
    }

    public WebElement getCoverButton() {
        return coverButton;
    }

    public WebElement getOKButton() {
        return OKButton;
    }

    public WebElement getCancelButton() {
        return CancelButton;
    }

    public List<WebElement> getResourceGroupOwner() {
        if (!selectors.isDisplayed()){
            ownerInputButton.click();
        }
        ExpectedCondition expectedCondition = ExpectedConditions.visibilityOf(selectors);
        WaitForElement.waitForElementWithExpectedCondition(webDriver,expectedCondition);
        List<WebElement> list = selectors.findElements(By.tagName("li"));
        return list;
    }

    public WebElement getCreateResourceGroup(){
        ExpectedCondition expectedCondition = ExpectedConditions.invisibilityOf(loadingElement);
        WaitForElement.waitForElementWithExpectedCondition(webDriver,expectedCondition);
        expectedCondition = ExpectedConditions.elementToBeClickable(createResourceGroup);
        WaitForElement.waitForElementWithExpectedCondition(webDriver,expectedCondition);
        return createResourceGroup;
    }

    public WebElement getSearchInput() {
        ExpectedCondition expectedCondition = ExpectedConditions.invisibilityOf(loadingElement);
        WaitForElement.waitForElementWithExpectedCondition(webDriver,expectedCondition);
        return searchInput;
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

    public WebElement getTableEditButton(Integer row){
        WebElement e = getTableRowButtons(row);
        return e.findElement(By.xpath("//button/span[contains(text(),'编辑')]"));
    }

    public WebElement getDeleteButton(Integer row){
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

    public WebElement getMessageBoxCancelButton(){
        ExpectedCondition expectedCondition = ExpectedConditions.textToBePresentInElement(messageInfo,"确认删除");
        WaitForElement.waitForElementWithExpectedCondition(webDriver,expectedCondition);
        List<WebElement> list = messageBoxButtons.findElements(By.tagName("button"));
        for (WebElement e : list){
            if ("取消".equals(e.getText())){
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

    public WebElement getErrorMessage(){
        if (ElementExist.isElementExist(webDriver,messageInfo)){
            return messageInfo;
        }else {
            return dialogErrorMessage;
        }
    }

    private WebElement getTableRowButtons(int row){
        WebElement table = getSearchResultTable();
        return table.findElements(By.className("el-table_1_column_4")).get(row-1);
    }
}
