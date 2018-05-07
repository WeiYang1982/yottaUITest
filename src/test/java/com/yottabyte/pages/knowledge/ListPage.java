package com.yottabyte.pages.knowledge;

import com.yottabyte.pages.PageTemplate;
import com.yottabyte.utils.WaitForElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class ListPage extends PageTemplate {

    public ListPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(className = "el-loading-mask")
    private WebElement loadingElement;

    @FindBy(xpath = "//*[text()='新建']")
    private WebElement createKnowledge;

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

    @FindBy(xpath = "//label[@class='el-form-item__label'][contains(text(),'解决方案')]/following-sibling::div//textarea")
    private WebElement solution;

    @FindBy(className = "el-message__group")
    private WebElement success;

    @FindBy(xpath = "//td[@class='el-table_1_column_1']//span[@class='link']")
    private List<WebElement> elementList;

    public List<WebElement> getElementList() {
        return elementList;
    }

    public WebElement getSolution() {
        return solution;
    }

    public WebElement getConfirm() {
        return confirm;
    }

    public WebElement getDescribe() {
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
    public List<WebElement> getGroupComboBox() {
//        BrowserMobProxyService.setProxyBandwidth(1);
        comboBoxs.get(1).click();
        WaitForElement.waitForElementWithExpectedCondition(webDriver, ExpectedConditions.visibilityOf(selectors.get(selectors.size() - 1)));
        return selectors.get(selectors.size() - 1).findElements(By.tagName("li"));
    }

    // 获取标签下拉菜单
    public List<WebElement> getTagComboBox() {
        comboBoxs.get(2).click();
        WaitForElement.waitForElementWithExpectedCondition(webDriver,ExpectedConditions.visibilityOf(selectors.get(selectors.size() - 1)));
        return selectors.get(selectors.size() - 1).findElements(By.tagName("li"));
    }

    public List<WebElement> getComboBoxs() {
        return comboBoxs;
    }
}
