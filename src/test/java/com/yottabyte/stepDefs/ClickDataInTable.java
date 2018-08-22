package com.yottabyte.stepDefs;

import com.yottabyte.hooks.LoginBeforeAllTests;
import com.yottabyte.pages.timedTask.ListPage;
import com.yottabyte.utils.GetElementFromPage;
import com.yottabyte.utils.TakeScreenShot;
import com.yottabyte.webDriver.SharedDriver;
import cucumber.api.java.en.Given;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ClickDataInTable {
    private TakeScreenShot shot = SharedDriver.getScreenShot();

    @Given("^I click the data name contais \"([^\"]*)\" in table \"([^\"]*)\"$")
    public void iClickTheDataNameContaisInTable(String name, String tableName) {
        WebDriver webDriver = LoginBeforeAllTests.getWebDriver();
        WebElement table = GetElementFromPage.getWebElementWithName(tableName);
        // 获取分页
        List<WebElement> paging = webDriver.findElements(By.className("number"));
        // 点击最后一页
        paging.get(paging.size() - 1).click();
        // 前一页按钮
        WebElement previousButton = webDriver.findElement(By.className("el-icon-arrow-left"));
        // 总页数
        int totalPages = Integer.parseInt(paging.get(paging.size() - 1).getText());

        List<WebElement> trList = table.findElements(By.tagName("tr"));
        int trSize = trList.size();

        for (int i = 0; i < totalPages; i++) {
            for (int j = 0; j < trSize; j++) {
                WebElement tr = table.findElements(By.tagName("tr")).get(j);
                List<WebElement> tdList = tr.findElements(By.tagName("td"));
                if (!"".equals(tdList.get(3).getText()) && tdList.get(0).getText().contains(name)) {
                    tdList.get(0).click();
                    shot.screenShot();
                    webDriver.navigate().back();
                }
                ListPage listPage = new ListPage(webDriver);
                LoginBeforeAllTests.setPageFactory(listPage);
            }
            previousButton.click();
        }
    }
}
