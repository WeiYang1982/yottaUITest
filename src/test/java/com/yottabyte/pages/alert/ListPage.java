package com.yottabyte.pages.alert;

import com.yottabyte.config.ConfigManager;
import com.yottabyte.hooks.LoginBeforeAllTests;
import com.yottabyte.pages.PageTemplate;
import com.yottabyte.stepDefs.SetKeyWithValue;
import com.yottabyte.utils.ElementExist;
import com.yottabyte.utils.GetLogger;
import com.yottabyte.utils.WaitForElement;
import com.yottabyte.webDriver.SharedDriver;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
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

    public WebElement getSearchResult() {
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

    void thereIsAnAlert(String alertName, List<String> alertGroup, List<String> alertSource, List<String> alertLevel) {
        new SetKeyWithValue().clearElementValue(getSearchInput());
        getSearchInput().sendKeys(alertName);
        if (ElementExist.isElementExist(webDriver,noSearchResultMessage)) {
            getCreateAlert().click();
            new CreatePage(webDriver).createAlert(alertName, alertGroup, alertSource, alertLevel);
        }else {
            System.out.println("do not need create");
        }
    }

    @Override
    protected void load() {
        if (LoginBeforeAllTests.getCookie() != null) {
            webDriver.get("http://" + config.get("rizhiyi_server_host") + "/alerts");
            webDriver.manage().addCookie(LoginBeforeAllTests.getCookie());
        }else {
            LoginBeforeAllTests.login();
        }
    }

    @Override
    protected void isLoaded() throws Error {
        super.isLoaded();
        try {
            WaitForElement.waitForElementWithExpectedCondition(webDriver, ExpectedConditions.invisibilityOf(loadingElement));
        }catch (Exception e){
            GetLogger.getLogger().error("can not load % with error %", this.getClass().getSimpleName(), e);
        }
    }

    public static void main(String args[]) throws InterruptedException {
        SharedDriver driver = new SharedDriver();
        ConfigManager c = new ConfigManager();
        List list = new ArrayList<>();
        List list1 = new ArrayList<>();
        List list2 = new ArrayList<>();
        list.add("default_Alert");
        list1.add("所有日志");
        list2.add("1");
        list2.add("3");
        list2.add("10");
        LoginBeforeAllTests login = new LoginBeforeAllTests(driver,c);
        login.beforeScenario();
        Thread.sleep(5000);
        driver.get("http://" + config.get("rizhiyi_server_host") + "/alerts");
//        ListPage p = new ListPage(driver);
//        p.deleteAlert("AutoTest");
//        p.thereIsAnAlert("AutoTest", list, list1, list2);
    }

}
