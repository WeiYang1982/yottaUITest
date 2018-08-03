package com.yottabyte.pages.resourceGroups;

import com.yottabyte.pages.PageTemplate;
import com.yottabyte.stepDefs.ClickSomeButton;
import com.yottabyte.stepDefs.IChooseValueFromSelectList;
import com.yottabyte.stepDefs.IWillSeeNewPage;
import com.yottabyte.stepDefs.SetKeyWithValue;
import com.yottabyte.utils.ElementExist;
import com.yottabyte.utils.GetElementFromPage;
import com.yottabyte.utils.WaitForElement;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

public class ListPage extends PageTemplate {

    public ListPage(WebDriver driver) {
        super(driver);
    }

    @FindBy (className = "el-loading-mask")
    private WebElement loadingElement;

    @FindBy (className = "yw-table-group__dropdown")
    private WebElement groupTypeButton;

    @FindBy (xpath = "//ul[@class='el-dropdown-menu yw-table-group__group-menu']")
    private WebElement groupTypeSelectors;

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
    private WebElement ownerSelectors;

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


    public List<WebElement> getGroupTypes(){
        ExpectedCondition e = ExpectedConditions.invisibilityOf(loadingElement);
        WaitForElement.waitForElementWithExpectedCondition(webDriver,e);
        if (!groupTypeSelectors.isDisplayed()){
            groupTypeButton.click();
        }
        return getListFromElement(groupTypeSelectors);
    }

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
        if (!ownerSelectors.isDisplayed()){
            WaitForElement.waitForElementWithExpectedCondition(webDriver,ExpectedConditions.elementToBeClickable(ownerInputButton));
            ownerInputButton.click();
        }
        return getListFromElement(ownerSelectors);
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

    public WebElement getTableEditButton(int row){
        WebElement e = getTableRowButtons(row);
        return e.findElement(By.xpath("//button/span[contains(text(),'编辑')]"));
    }

    public WebElement getTableLinkButton(int row){
        WebElement e = getTableRowButtons(row);
        return e.findElement(By.xpath("//button/span[contains(text(),'跳转')]"));
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

    public void thereIsAResourceGroup(String resourceGroupsName, List<String> typeName, List<String> ownerName){
        ExpectedCondition expectedCondition = ExpectedConditions.invisibilityOf(loadingElement);
        WaitForElement.waitForElementWithExpectedCondition(webDriver,expectedCondition);
        getSearchInput().sendKeys(Keys.END);
        getSearchInput().sendKeys(Keys.SHIFT, Keys.HOME);
        getSearchInput().sendKeys(Keys.BACK_SPACE);
        getSearchInput().sendKeys(resourceGroupsName);
        String text = getSearchResult().getText();
        SetKeyWithValue setKeyWithValue = new SetKeyWithValue();
        if ("暂无数据".equals(text)){
            getCreateResourceGroup().click();
            IWillSeeNewPage page = new IWillSeeNewPage();
            page.iWillSeeNewPage("resourceGroups.CreatePage");
            setKeyWithValue.iSetTheParameterWithValue("ResourceGroupName",resourceGroupsName);
            IChooseValueFromSelectList chooseValueFromSelectList = new IChooseValueFromSelectList();
            chooseValueFromSelectList.iChooseTheFromThe(typeName,"ResourceGroupType");
            chooseValueFromSelectList.iChooseTheFromThe(ownerName,"ResourceGroupOwner");
            ClickSomeButton clickSomeButton = new ClickSomeButton();
            clickSomeButton.iClickTheButton("CreateButton");
            WebElement element = GetElementFromPage.getWebElementWithName("OKButton");
            element.click();
            page.iWillSeeNewPage("resourceGroups.ListPage");
        }else if (text.equals(resourceGroupsName)){
            System.out.println("There is a resource groups");
        }
    }

    public void thereIsNoResourceGroup(String resourceGroupsName){
        List<String> list = new ArrayList<>();
        list.add("all");
        System.out.println("delete all of types!");
        thereIsNoResourceGroup(resourceGroupsName,list);
    }

    public void thereIsNoResourceGroup(String resourceGroupsName, List<String> resourceGroupTypes) {
        ExpectedCondition expectedCondition = ExpectedConditions.invisibilityOf(loadingElement);
        WaitForElement.waitForElementWithExpectedCondition(webDriver,expectedCondition);
        if (resourceGroupTypes.size() > 1){
            for (int i=0;i<resourceGroupTypes.size();i++){
                deleteResourceGroups(resourceGroupsName,resourceGroupTypes.get(i));
            }
        }else {
            String resourceGroupType = resourceGroupTypes.get(0);
            if (resourceGroupType.equalsIgnoreCase("all")){
                List<String> list = new ArrayList<>();
                List<WebElement> elements = groupTypeSelectors.findElements(By.className("el-dropdown-menu__item"));
                WaitForElement.waitForElementWithExpectedCondition(webDriver,ExpectedConditions.invisibilityOf(loadingElement));
                WaitForElement.waitForElementWithExpectedCondition(webDriver,ExpectedConditions.elementToBeClickable(groupTypeButton));
                groupTypeButton.click();
                for (WebElement e : elements){
                    if (e.getText() != null && e.getText().trim().length() != 0 && !e.getText().equalsIgnoreCase("全部分组类型")){
                        list.add(e.getText());
                    }
                }
                thereIsNoResourceGroup(resourceGroupsName,list);
            }else {
                deleteResourceGroups(resourceGroupsName,resourceGroupType);
            }
        }
    }

    private void deleteResourceGroups(String resourceGroupsName, String resourceGroupType) {
        WaitForElement.waitForElementWithExpectedCondition(webDriver,ExpectedConditions.invisibilityOf(loadingElement));
        WaitForElement.waitForElementWithExpectedCondition(webDriver,ExpectedConditions.elementToBeClickable(groupTypeButton));
        if (!groupTypeSelectors.isDisplayed()){
            groupTypeButton.click();
        }
        WaitForElement.waitForElementWithExpectedCondition(webDriver,ExpectedConditions.visibilityOf(groupTypeSelectors));
        groupTypeSelectors.findElement(By.tagName("input")).sendKeys(Keys.END);
        groupTypeSelectors.findElement(By.tagName("input")).sendKeys(Keys.SHIFT, Keys.HOME);
        groupTypeSelectors.findElement(By.tagName("input")).sendKeys(Keys.BACK_SPACE);
        groupTypeSelectors.findElement(By.tagName("input")).sendKeys(resourceGroupType);
        groupTypeSelectors.findElements(By.className("el-dropdown-menu__item")).get(2).click();
        getSearchInput().sendKeys(Keys.END);
        getSearchInput().sendKeys(Keys.SHIFT, Keys.HOME);
        getSearchInput().sendKeys(Keys.BACK_SPACE);
        getSearchInput().sendKeys(resourceGroupsName);
        String text = getSearchResult().getText();
        if (text.equals(resourceGroupsName)){
            getTableDeleteButton(1).click();
            WaitForElement.waitForElementWithExpectedCondition(webDriver,ExpectedConditions.elementToBeClickable(getMessageBoxOKButton()));
            getMessageBoxOKButton().click();
            WaitForElement.waitForElementWithExpectedCondition(webDriver,ExpectedConditions.invisibilityOf(loadingElement));
            webDriver.navigate().refresh();
            WaitForElement.waitForElementWithExpectedCondition(webDriver,ExpectedConditions.invisibilityOf(loadingElement));
        }
        if (!groupTypeSelectors.isDisplayed()){
            WaitForElement.waitForElementWithExpectedCondition(webDriver,ExpectedConditions.invisibilityOf(loadingElement));
            WaitForElement.waitForElementWithExpectedCondition(webDriver,ExpectedConditions.elementToBeClickable(groupTypeButton));
            groupTypeButton.click();
        }
        groupTypeSelectors.findElement(By.tagName("input")).sendKeys(Keys.END);
        groupTypeSelectors.findElement(By.tagName("input")).sendKeys(Keys.SHIFT, Keys.HOME);
        groupTypeSelectors.findElement(By.tagName("input")).sendKeys(Keys.BACK_SPACE);
        groupTypeSelectors.findElements(By.className("el-dropdown-menu__item")).get(2).click();
    }

    private List<WebElement> getListFromElement(WebElement element){
        ExpectedCondition expectedCondition = ExpectedConditions.visibilityOf(element);
        WaitForElement.waitForElementWithExpectedCondition(webDriver,expectedCondition);
        return element.findElements(By.tagName("li"));
    }

    private WebElement getTableRowButtons(int row){
        WebElement table = getSearchResultTable();
        return table.findElements(By.className("el-table_1_column_4")).get(row-1);
    }
}
