package com.yottabyte.stepDefs;

import com.yottabyte.hooks.LoginBeforeAllTests;
import com.yottabyte.utils.GetElementFromPage;
import com.yottabyte.utils.WaitForElement;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

import static org.junit.Assert.assertTrue;


public class SeeNewElementInList {
    /**
     * 查询新增结果是否已出现在列表中,仅针对列表按创建时间正序排序情况
     */

    @Then("I will see the \"([^\"]*)\" in the \"([^\"]*)\"")
    public void iWillSeeNewElement(String name, String elementName) {
        WebDriver webDriver = LoginBeforeAllTests.getWebDriver();
        webDriver.navigate().refresh();
        // 获取分页
        List<WebElement> paging = webDriver.findElements(By.className("number"));
        WaitForElement.waitForElementWithExpectedCondition(webDriver, ExpectedConditions.elementToBeClickable(paging.get(paging.size() - 1)));
        // 获取最后一页数据列表
        paging.get(paging.size() - 1).click();
        // 获取当前列表页下的所有数据
        List<WebElement> list = GetElementFromPage.getWebElementWithName(elementName);
        // 判断最后一条数据是否为新增数据
        if (!list.get(list.size() - 1).getText().equals(name)) {
            assertTrue(false);
        }
    }

    /**
     * 知识中根据名称点击修改
     */
    @Given("I click the edit button which name is \"([^\"]*)\"")
    public void clickEditButton(String name) {
        List<WebElement> list = GetElementFromPage.getWebElementWithName("AllData");
        for (WebElement webElement : list) {
            String td = webElement.findElement(By.className("el-table_1_column_1")).getText();
            if (name.equals(td)) {
                webElement.findElement(By.className("el-table_1_column_7")).findElement(By.xpath("./div[@class='cell']//button[@class='el-button el-button--text']//span[contains(text(),'编辑')]")).click();
            }
        }

    }
}
