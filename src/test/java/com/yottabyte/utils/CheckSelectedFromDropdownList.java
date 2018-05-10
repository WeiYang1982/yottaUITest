package com.yottabyte.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

public class CheckSelectedFromDropdownList {
    /**
     * 判断下拉列表中的元素中 被选中的元素的文本
     * @param webDriver Page中定义的webdriver
     * @param element 需要传入点击后出现指定下拉列表的按钮元素
     * @return
     */
    public List<String> checkSelected (WebDriver webDriver, WebElement element) {
        List<String> list = new ArrayList<>();
        WaitForElement.waitForElementWithExpectedCondition(webDriver,ExpectedConditions.elementToBeClickable(element));
        element.click();
        List<WebElement> elements = webDriver.findElements(By.className("el-select-dropdown"));
        WebElement selectorElement = elements.get(elements.size() - 1).findElement(By.tagName("ul"));

        if (!selectorElement.isDisplayed()){
            ((JavascriptExecutor) webDriver).executeScript("arguments[0].style.display='block';", selectorElement);
        }
        List<WebElement> elementList = selectorElement.findElements(By.className("selected"));
        for (WebElement e:elementList) {
            WaitForElement.waitForElementWithExpectedCondition(webDriver,ExpectedConditions.visibilityOf(e));
            list.add(e.getText());
        }
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].style.display='none';", selectorElement);
        return list;
    }
}
