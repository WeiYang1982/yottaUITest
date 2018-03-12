package com.yottabyte.pages.userGroups;

import com.yottabyte.pages.PageTemplate;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

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

    public WebElement getSearchInput() {
        return searchInput;
    }

    public WebElement getCreateUserGroup() {
        return createUserGroup;
    }

    public WebElement getTableEditButton(Integer row){
        WebElement e = getTableRowButtons(row);
        return e.findElement(By.xpath("//button/span[contains(text(),'编辑')]"));
    }

    public WebElement getTableDeleteButton(Integer row){
        WebElement e = getTableRowButtons(row);
        return e.findElement(By.xpath("//button/span[contains(text(),'删除')]"));
    }

    ////TODO 实现前置条件函数
    public void thereIsAUserGroup(){}

    private WebElement getTableRowButtons(int row){
        WebElement table = searchResultTable;
        return table.findElements(By.className("el-table_1_column_4")).get(row-1);
    }


}
