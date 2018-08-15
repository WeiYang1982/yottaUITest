package com.yottabyte.stepDefs;

import com.yottabyte.hooks.LoginBeforeAllTests;
import com.yottabyte.utils.ElementExist;
import com.yottabyte.utils.GetElementFromPage;
import com.yottabyte.utils.WaitForElement;
import cucumber.api.java.en.Given;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

import static junit.framework.TestCase.assertTrue;

/**
 * 应用场景：删除最后一页列表最后一条数据，不支持模糊匹配
 */
public class Delete {


    /**
     * @param elementName 包含待删除元素的列表
     * @param header      待删除元素所在列的表头名称
     * @param name        待删除元素
     */
    @Given("I delete the data in \"([^\"]*)\" which \"([^\"]*)\" is \"([^\"]*)\"")
    public void delete(String elementName, String header, String name) {
        WebDriver webDriver = LoginBeforeAllTests.getWebDriver();

        if (ElementExist.isElementExist(webDriver, By.className("number"))) {
            // 获取分页
            List<WebElement> paging = webDriver.findElements(By.className("number"));
            // 点击最后一页
            paging.get(paging.size() - 1).click();
            // 获取当前列表页下的所有数据
            WebElement webElement = GetElementFromPage.getWebElementWithName(elementName);
            // 获取所有行
            List<WebElement> rows = webElement.findElements(By.tagName("tr"));
            // 获取最后一行
            List<WebElement> rowNames = rows.get(rows.size() - 1).findElements(By.tagName("td"));
            // 获取表头名称
            String tableHeader = webDriver.findElement(By.className("el-table__header")).getText();
            // 表头下标
            int index = 0;
            for (String headerName : tableHeader.split(" ")) {
                if (headerName.equals(header)) {
                    break;
                }
                index++;
            }

            if (rowNames.get(index).getText().equals(name)) {
                // 删除按钮
                WebElement deleteButton = rowNames.get(rowNames.size() - 1).findElements(By.tagName("button")).get(2);
                // 等待加载图标消失
                if (ElementExist.isElementExist(webDriver, By.className("el-loading-mask"))) {
                    WaitForElement.waitForElementWithExpectedCondition(webDriver, ExpectedConditions.invisibilityOfElementLocated(By.className("el-loading-mask")));
                }
                WaitForElement.waitForElementWithExpectedCondition(webDriver, ExpectedConditions.elementToBeClickable(deleteButton));
                deleteButton.click();

                WaitForElement.waitForElementWithExpectedCondition(webDriver, ExpectedConditions.elementToBeClickable(webDriver.findElements(By.className("el-button--primary")).get(2)));
                List<WebElement> confirmButtons = webDriver.findElements(By.className("el-button--primary"));
                // 确认按钮
                WebElement confirm = confirmButtons.get(confirmButtons.size() - 1);
                confirm.click();
            } else {
                System.out.println("元素不匹配，删除失败");
                assertTrue(false);
            }
        } else {
            System.out.println("暂无数据");
            assertTrue(false);
        }
    }
}
