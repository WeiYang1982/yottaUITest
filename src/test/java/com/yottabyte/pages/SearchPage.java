package com.yottabyte.pages;

import com.yottabyte.hooks.LoginBeforeAllTests;
import com.yottabyte.utils.ElementExist;
import com.yottabyte.utils.TakeScreenShot;
import com.yottabyte.utils.WaitForElement;
import com.yottabyte.webDriver.SharedDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.io.File;

/**
 * 搜索页面页面元素
 */
public class SearchPage extends PageTemplate {
    WebDriver webDriver = LoginBeforeAllTests.getWebDriver();
    TakeScreenShot shot = SharedDriver.getScreenShot();
    ExpectedCondition expectedCondition;

    public SearchPage(WebDriver driver) {
        super(driver);
    }

    @FindBy (xpath = "//div[@class='yw-searchbar__inner el-textarea']/textarea")
    private WebElement searchInput;

    @FindBy ( className = "yw-searchbar__append")
    private WebElement searchButton;

    // 搜索历史下拉框
    @FindBy ( className = "el-collapse-item__wrap")
    private WebElement searchHistoryWindow;
//
//    @FindBy ( className = "modal-content")
//    private WebElement savedQueryWindows;
//
    @FindBy ( className = "yw-search-tabbar-status")
    private WebElement searchStatus;

    @FindBy ( xpath = "//div[@class='yw-search-tabbar']//div[text()='统计']")
    private WebElement statisticalTab;

    @FindBy ( xpath = "//div[@class='yw-search-tabbar']//div[text()='事件']")
    private WebElement eventTab;

    @FindBy ( xpath = "//div[contains(@class,'yw-search-tabbar-item')]//span")
    private WebElement eventCount;

    @FindBy ( xpath = "//div[@class='yw-search-events']/div[@class='yw-search-info']//span")
    private WebElement eventWarningMessage;

    @FindBy ( xpath = "//div[contains(@class,'yw-search-stats')]/div[@class='yw-search-info']//span")
    private WebElement statsWarningMessage;


    public WebElement getSearchInput() throws InterruptedException {
        ExpectedCondition expectedCondition = ExpectedConditions.elementToBeClickable(searchInput);
        WaitForElement.waitForElementWithExpectedCondition(webDriver,expectedCondition);
        return searchInput;
    }

    public WebElement getDateEditor() {
        DateEditorPage date = new DateEditorPage(webDriver);
        LoginBeforeAllTests.setPageFactory(date);
        WebElement webElement = date.getPublicDateEditor();
        return webElement;
    }

    public WebElement getSearchButton() {
        expectedCondition = ExpectedConditions.elementToBeClickable(searchButton);
        WaitForElement.waitForElementWithExpectedCondition(webDriver,expectedCondition);
        return searchButton;
    }

    public WebElement getSearchStatus() {
        return searchStatus;
    }

    public WebElement getSearchResult() {
        WebElement searchResult = null;
        String sp = File.separator;
        String path = System.getProperty("user.dir") + sp + "target" + sp +
                "cucumber-html-reports" + sp + "embeddings";
//        System.out.println("webDriver:  "+ webDriver);
        int warningMessageCount=webDriver.findElements(By.className("yw-search-info-content")).size();
//        System.out.println("warningMessageCount:  "+ warningMessageCount);
        if (ElementExist.isElementExist(webDriver,eventCount)){
            System.out.println("event count is : " + eventCount);
            searchResult = eventCount;
        }else if (warningMessageCount == 2 && statsWarningMessage.isDisplayed() ){
            System.out.println("warningMessageCount=2  and statsWarningMessage: " + statsWarningMessage);
            searchResult = statsWarningMessage;
        }else if (warningMessageCount == 2 && eventWarningMessage.isDisplayed() ){
            System.out.println("warningMessageCount=2  and eventWarningMessage: " + eventWarningMessage);
            searchResult = eventWarningMessage;
        }else if (warningMessageCount == 1 && statisticalTab.isDisplayed()){
            System.out.println("warningMessageCount=1  and statisticalTab is displayed: " + eventWarningMessage.getText());
            shot.screenShot();
            eventTab.click();
            searchResult = eventWarningMessage;
        }else if (warningMessageCount == 1 && eventTab.isDisplayed()){
            System.out.println("warningMessageCount=1  and eventTab is displayed: " + eventWarningMessage.getText());
            shot.screenShot();
            statisticalTab.click();
            searchResult = statsWarningMessage;
        }

        return searchResult;
    }

    public TakeScreenShot getShot() {
        return shot;
    }
}
