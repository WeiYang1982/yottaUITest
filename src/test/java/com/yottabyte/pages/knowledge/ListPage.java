package com.yottabyte.pages.knowledge;

import com.yottabyte.pages.PageTemplate;
import com.yottabyte.utils.ElementExist;
import com.yottabyte.utils.WaitForElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

public class ListPage extends PageTemplate {

    public ListPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(className = "el-loading-mask")
    private WebElement loadingElement;

    @FindBy(xpath = "//*[text()='新建']")
    private WebElement createKnowledge;

    @FindBy(xpath = "//*[text()='编辑']")
    private WebElement edit;

    @FindBy(xpath = "//label[@class='el-form-item__label'][contains(text(),'事件代码')]/following-sibling::div//input")
    private WebElement eventCode;

    @FindBy(xpath = "//input[@placeholder='默认与事件代码相同']")
    private WebElement knowledgeName;

    @FindBy(className = "el-select")
    private List<WebElement> comboBoxs;

    @FindBy(className = "el-scrollbar")
    private List<WebElement> selectors;

    @FindBy(xpath = "//label[@class='el-form-item__label'][contains(text(),'描述')]/following-sibling::div//textarea")
    private WebElement describe;

    @FindBy(xpath = "//div[@class='el-dialog el-dialog--small']//span[@class='modal-footer']//span[contains(text(),'确定')]")
    private WebElement confirm;

    @FindBy(xpath = "//div[@class='el-dialog el-dialog--tiny']//span[@class='modal-footer']//span[contains(text(),'确定')]")
    private WebElement ensure;

    public WebElement getEnsure() {
        return ensure;
    }

    @FindBy(xpath = "//label[@class='el-form-item__label'][contains(text(),'解决方案')]/following-sibling::div//textarea")
    private WebElement solution;

    @FindBy(className = "el-dialog--tiny")
    private WebElement tinyChangeGroup;

    public WebElement getTinyChangeGroup() {
        return tinyChangeGroup;
    }

    // 列表页下的所有名称
    @FindBy(xpath = "//td[@class='el-table_1_column_1']//span[@class='link']")
    private List<WebElement> elementList;

    @FindBy(className = "el-message-box__message")
    private WebElement errorMessage;

    @FindBy(className = "yw-table-group__dropdown")
    private WebElement sourceGroup;

    @FindBy(className = "el-dropdown-menu__item")
    private List<WebElement> groupList;

    // 搜索输入框
    @FindBy(xpath = "//div[@class='el-input el-input-group el-input-group--append']//input")
    private WebElement searchInput;

    // 分页参数
    @FindBy(className = "number")
    private List<WebElement> paging;

    // 表头
    @FindBy(className = "el-table__header")
    private WebElement tableHeader;

    // 表体
    @FindBy(className = "el-table__body")
    private WebElement tableBody;

    // 全文搜索后出现的列表
    @FindBy(className = "search-list-item")
    private List<WebElement> searchList;

    // 删除成功
    @FindBy(className = "el-message__group")
    private WebElement deleteMsg;

    public WebElement getSuccessMessage() {
        return deleteMsg;
    }

    public WebElement getSearchInput() {
        return searchInput;
    }

    public List<WebElement> getSearchList() {
        WaitForElement.waitForElementWithExpectedCondition(webDriver, ExpectedConditions.visibilityOfAllElements(searchList));
        return searchList;
    }

    public WebElement getTableBody() {

        return tableBody;
    }

    public WebElement getEdit() {
        return edit;
    }

    public WebElement getErrorMessage() {
        return errorMessage;
    }

    public WebElement getSolution() {
        return solution;
    }

    public WebElement getConfirm() {
        return confirm;
    }

    public WebElement getDescribe() {
        if (ElementExist.isElementExist(webDriver, loadingElement)) {
            WaitForElement.waitForElementWithExpectedCondition(webDriver, ExpectedConditions.invisibilityOf(loadingElement));
        }
        return describe;
    }

    public WebElement getEventCode() {
        return eventCode;
    }

    public List<WebElement> getSelectors() {
        return selectors;
    }

    public WebElement getKnowledgeName() {
        return knowledgeName;

    }

    public WebElement getCreateKnowledge() {
        WaitForElement.waitForElementWithExpectedCondition(webDriver, ExpectedConditions.invisibilityOf(loadingElement));
        return createKnowledge;
    }

    // 获取分组下拉菜单
    public WebElement getGroupComboBox() {
        comboBoxs.get(comboBoxs.size() - 1).click();
        WaitForElement.waitForElementWithExpectedCondition(webDriver, ExpectedConditions.visibilityOf(selectors.get(selectors.size() - 1)));
        return selectors.get(selectors.size() - 1);
    }

    // 获取标签下拉菜单
    public List<WebElement> getTagComboBox() {
        comboBoxs.get(2).click();
        WaitForElement.waitForElementWithExpectedCondition(webDriver, ExpectedConditions.visibilityOf(selectors.get(selectors.size() - 1)));
        return selectors.get(selectors.size() - 1).findElements(By.tagName("li"));
    }

    // 获取资源分组下拉菜单
    public List<WebElement> getGroupList() {
        WaitForElement.waitForElementWithExpectedCondition(webDriver, ExpectedConditions.invisibilityOf(loadingElement));
        sourceGroup.click();
        WaitForElement.waitForElementWithExpectedCondition(webDriver, ExpectedConditions.visibilityOfAllElements(groupList));
        return groupList;
    }

    // 获取最后一页列表下的数据
    public List<WebElement> getElementList() {
        webDriver.navigate().refresh();
        WaitForElement.waitForElementWithExpectedCondition(webDriver, ExpectedConditions.elementToBeClickable(paging.get(paging.size() - 1)));
        paging.get(paging.size() - 1).click();
        WaitForElement.waitForElementWithExpectedCondition(webDriver, ExpectedConditions.visibilityOfAllElements(elementList));
        return elementList;
    }

    // 获取列表页所有数据
    public List<WebElement> getAllData(String name) {
        String className = null;
        for (WebElement webElement : tableHeader.findElements(By.tagName("th"))) {
            if (name.equals(webElement.getText())) {
                className = webElement.getAttribute("class").split(" ")[0];
            }
        }
        List<WebElement> list = new ArrayList();
        for (int i = 0; i < paging.size(); i++) {
            WaitForElement.waitForElementWithExpectedCondition(webDriver, ExpectedConditions.elementToBeClickable(paging.get(i)));
            paging.get(i).click();
            list.addAll(tableBody.findElements(By.className(className)));
        }
        return list;
    }

    // 获取列表页所有数据
    public List<WebElement> getAllData() {
        String className = null;
        List<WebElement> list = tableBody.findElements(By.tagName("tr"));
        return list;
    }
}
