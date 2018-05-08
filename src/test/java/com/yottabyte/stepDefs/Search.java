package com.yottabyte.stepDefs;

import com.yottabyte.hooks.LoginBeforeAllTests;
import com.yottabyte.utils.GetElementFromPage;
import com.yottabyte.utils.WaitForElement;
import cucumber.api.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

import static junit.framework.TestCase.assertTrue;

public class Search {

    /**
     * 查找搜索内容是否正确
     *
     * @param columnName 搜索的列
     * @param keyword    搜索的关键字
     */
    @Then("I will see the list of \"([^\"]*)\" contains \"([^\"]*)\"")
    public void search(String columnName, String keyword) {
        WebDriver webDriver = LoginBeforeAllTests.getWebDriver();
        // 获取表体
        WebElement tableBody = GetElementFromPage.getWebElementWithName("getTableBody");
        // 获取分页
        List<WebElement> paging = webDriver.findElements(By.className("number"));
        // 获取表头
        WebElement tableHeader = webDriver.findElement(By.className("el-table__header"));

        String className = null;
        // 查找待查找元素在第几列
        for (WebElement webElement : tableHeader.findElements(By.tagName("th"))) {
            if (columnName.equals(webElement.getText())) {
                className = webElement.getAttribute("class").split(" ")[0];
            }
        }
        for (int i = 0; i < paging.size(); i++) {
            WaitForElement.waitForElementWithExpectedCondition(webDriver, ExpectedConditions.elementToBeClickable(paging.get(i)));
            paging.get(i).click();
            for (WebElement element : tableBody.findElements(By.className(className))) {
                if (!element.getText().contains(keyword)) {
                    assertTrue(false);
                }
            }
        }
    }
}
