package com.yottabyte.pages;

import com.yottabyte.constants.WebDriverConst;
import com.yottabyte.hooks.LoginBeforeAllTests;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.LoadableComponent;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 页面元素模板，每一个页面都需要继承该模板
 */
public class PageTemplate extends LoadableComponent<PageTemplate> {

    public WebDriver webDriver;
    String parentPageName;

    public PageTemplate(WebDriver driver) {
        this.webDriver = driver;
        PageFactory.initElements(driver, this);
        parentPageName = LoginBeforeAllTests.getPageFactory() == null ? "" : LoginBeforeAllTests.getPageFactory().getClass().getSimpleName();
    }

    @Override
    protected void load() {
    }

    @Override
    protected void isLoaded() throws Error {
        FluentWait wait = new FluentWait(webDriver)
                .withTimeout(WebDriverConst.WAIT_FOR_ELEMENT_TIMEOUT_WHEN_PAGE_LOADING, TimeUnit.MILLISECONDS)
                .pollingEvery(WebDriverConst.WAIT_FOR_ELEMENT_POLLING_DURING, TimeUnit.MILLISECONDS)
                .ignoring(NoSuchElementException.class);
        wait.until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                System.out.println("Waiting " + this.getClass().getName() + " Dom loading complete");
                return ((JavascriptExecutor) driver).executeScript(
                        "return document.readyState"
                ).equals("complete");
            }
        });
    }

    public WebElement getInputElement(String text) {
        String xpath = "//label[contains(text(),'" + text + "')]/following-sibling::div//input[@class='el-input__inner']";
        return webDriver.findElement(By.xpath(xpath));
    }

    public WebElement getDropdownList(String text) {
        String xpath = "//label[contains(text(),'" + text + "')]/following-sibling::div//input[@class='el-input__inner']";
        WebElement element = webDriver.findElement(By.xpath(xpath));
        element.click();
        List<WebElement> dropdownList = webDriver.findElements(By.className("el-select-dropdown__list"));
        return dropdownList.get(dropdownList.size() - 1);
    }

    public WebElement getButton(String text) {
        String xpath = "//span[text()='" + text + "']";
        return webDriver.findElement(By.xpath(xpath));
    }
}
