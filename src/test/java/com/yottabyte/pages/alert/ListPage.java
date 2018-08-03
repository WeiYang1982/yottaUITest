package com.yottabyte.pages.alert;

import com.yottabyte.config.ConfigManager;
import com.yottabyte.hooks.LoginBeforeAllTests;
import com.yottabyte.pages.PageTemplate;
import com.yottabyte.utils.ElementExist;
import com.yottabyte.utils.GetLogger;
import com.yottabyte.utils.WaitForElement;
import com.yottabyte.webDriver.SharedDriver;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class ListPage extends PageTemplate {

    public ListPage(WebDriver driver) {
        super(driver);
    }

    @FindBy (className = "el-loading-mask")
    private WebElement loadingElement;
    // 搜索输入框
    @FindBy (xpath = "//input[@placeholder='请输入']")
    private WebElement searchInput;
    // 新建按钮
    @FindBy ( xpath = "//button[@class='el-button slot-button el-button--default']//span[text()='新建']")
    WebElement createAlert;
    // 无搜索结果
    @FindBy (className = "el-table__empty-text")
    private WebElement noSearchResultMessage;
    // 搜索结果表格
    @FindBy(className = "el-table__body")
    private WebElement searchResult;

    @FindBy(className = "el-message-box")
    private WebElement message;

    public WebElement getSearchInput() {
        WaitForElement.waitForElementWithExpectedCondition(webDriver,ExpectedConditions.invisibilityOf(loadingElement));
        return searchInput;
    }

    public WebElement getTableDeleteButton(int row) {
        WebElement element = getSearchResult();
        if (element.findElements(By.tagName("tr")).size() > 0) {
            return element.findElements(By.tagName("tr")).get(row - 1).findElement(By.xpath("//td[@class='el-table_1_column_5']//span[contains(text(),'删除')]"));
        }else if (noSearchResultMessage.isDisplayed()){
            GetLogger.getLogger().error("没有搜索结果");
            throw new NoSuchElementException("没有搜索结果");
        }else {
            GetLogger.getLogger().error("请检查输入");
            throw new NoSuchElementException("请检查输入");
        }
    }

    public WebElement getCreateAlert() {
        WaitForElement.waitForElementWithExpectedCondition(webDriver,ExpectedConditions.elementToBeClickable(createAlert));
        return createAlert;
    }

    public WebElement getMessage() {
        WaitForElement.waitForElementWithExpectedCondition(webDriver,ExpectedConditions.visibilityOf(message));
        return message;
    }

    public void thereIsNoAlert(String alertName) {
        deleteAlert(alertName);
    }

    private WebElement getSearchResult() {
        if (ElementExist.isElementExist(webDriver,searchResult)) {
            return searchResult;
        }else if (ElementExist.isElementExist(webDriver,noSearchResultMessage)){
            return noSearchResultMessage;
        }else {
            GetLogger.getLogger().error("请检查输入");
            throw new NoSuchElementException("请检查输入");
        }
    }

    void deleteAlert(String alertName) {
        while (true) {
            getSearchInput().sendKeys(Keys.END);
            getSearchInput().sendKeys(Keys.SHIFT, Keys.HOME);
            getSearchInput().sendKeys(Keys.BACK_SPACE);
            getSearchInput().sendKeys(alertName);
            try {
                if (searchResult.isDisplayed()) {
                    List<WebElement> elements = getSearchResult().findElements(By.className("el-table_1_column_1"));
                    if (elements.get(0).getText().trim().contains(alertName)) {
                        getTableDeleteButton(1).click();
                        WaitForElement.waitForElementWithExpectedCondition(webDriver,ExpectedConditions.visibilityOf(message));
                        WaitForElement.waitForElementWithExpectedCondition(webDriver,ExpectedConditions.textToBePresentInElement(message.findElement(By.className("el-message-box__content")),"确认删除"));
                        message.findElement(By.className("el-message-box__btns")).findElement(By.xpath(".//span[contains(text(),'确定')]")).click();
                        webDriver.navigate().refresh();
                        WaitForElement.waitForElementWithExpectedCondition(webDriver,ExpectedConditions.invisibilityOf(loadingElement));
                    }
                }else if (noSearchResultMessage.isDisplayed()) {
                    System.out.println("do not need delete!");
                    break;
                }else {
                    GetLogger.getLogger().error("没有找到搜索结果");
                    throw new NoSuchElementException("没有找到搜索结果");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            getSearchInput().sendKeys(Keys.END);
            getSearchInput().sendKeys(Keys.SHIFT,Keys.HOME);
            getSearchInput().sendKeys(Keys.BACK_SPACE);
            getSearchInput().sendKeys(alertName);
        }

    }

    public static void main(String args[]) throws InterruptedException {
        SharedDriver driver = new SharedDriver();
        ConfigManager c = new ConfigManager();
        LoginBeforeAllTests login = new LoginBeforeAllTests(driver,c);
        login.beforeScenario();
        Thread.sleep(2000);
        driver.get("http://alltest.rizhiyi.com/alerts");
        ListPage p = new ListPage(driver);
        p.deleteAlert("AutoTest");
    }

}
