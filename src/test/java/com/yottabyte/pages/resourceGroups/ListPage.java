package com.yottabyte.pages.resourceGroups;

import com.yottabyte.pages.PageTemplate;
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

    @FindBy (className = "el-table_1_column_1")
    private List<WebElement> searchResultRows;

    @FindBy (className = "el-table__body")
    private WebElement searchResultTable;

    @FindBy (className = "el-table__empty-text")
    private WebElement noSearchResultMessage;


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

    private WebElement getTableRowButtons(int row){
        WebElement table = getSearchResultTable();
        return table.findElements(By.className("el-table_1_column_4")).get(row-1);
    }
}
