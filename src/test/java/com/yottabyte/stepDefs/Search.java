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
     * @param columnName 下拉搜索对应的列表列
     * @param keyword    下拉搜索选择的内容
     * @param searchList 输入搜索文本后的列表
     * @param searchName 输入搜索的内容
     */
    @Then("I will see the list of \"([^\"]*)\" contains \"([^\"]*)\" or I see the \"([^\"]*)\" contains \"([^\"]*)\"")
    public void search(String columnName, String keyword, String searchList, String searchName) throws Exception {
        WebDriver webDriver = LoginBeforeAllTests.getWebDriver();
        // 选择下拉菜单时
        if ("".equals(searchName) && !"".equals(keyword)) {
            if ("".equals(columnName)) {
                throw new Exception("缺少第一个参数！");
            }
            // 获取表体
            WebElement tableBody = GetElementFromPage.getWebElementWithName("getTableBody");
            // 获取分页
            List<WebElement> paging = webDriver.findElements(By.className("number"));
            // 获取表头
            WebElement tableHeader = webDriver.findElement(By.className("el-table__header"));

            String className = null;
            // 所要查找元素对应的列
            for (WebElement webElement : tableHeader.findElements(By.tagName("th"))) {
                if (columnName.equals(webElement.getText())) {
                    className = webElement.getAttribute("class").split(" ")[0];
                    break;
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
        } else {
            if("".equals(searchList)){
                throw new Exception("缺少第三个参数！");
            }
            List<WebElement> search = GetElementFromPage.getWebElementsWithName(searchList);
            for (WebElement listElement : search) {
                assertTrue(listElement.getText().contains(searchName));
            }
        }
    }
}
