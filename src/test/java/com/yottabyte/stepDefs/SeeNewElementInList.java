package com.yottabyte.stepDefs;

import com.yottabyte.hooks.LoginBeforeAllTests;
import com.yottabyte.utils.GetElementFromPage;
import cucumber.api.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * 查询新增结果是否已出现在列表中,仅针对列表按创建时间正序排序情况
 */
public class SeeNewElementInList {
    @Then("I will see the \"([^\"]*)\" in the \"([^\"]*)\"")
    public void iWillSeeNewElement(String name, String elementName) {
        WebDriver webDriver = LoginBeforeAllTests.getWebDriver();
        // 获取分页
        List<WebElement> paging = webDriver.findElements(By.className("number"));
        // 获取最后一页数据列表
        paging.get(paging.size() - 1).click();
        // 获取当前列表页下的所有数据
        List<WebElement> list = GetElementFromPage.getWebElementsWithName(elementName);
        if (!list.get(list.size() - 1).getText().equals(name)) {
            assertTrue(false);
        }
    }
}
