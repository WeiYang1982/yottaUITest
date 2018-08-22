package com.yottabyte.stepDefs;

import com.yottabyte.hooks.LoginBeforeAllTests;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * 点击对应名称下的操作按钮
 * 找到名称相匹配的元素后不会继续查找
 */
public class ClickButtonWithGivenName {

    private WebDriver webDriver = LoginBeforeAllTests.getWebDriver();

    /**
     * 寻找对应按钮并点击
     *
     * @param dataName   第一列所要匹配的名称
     * @param buttonName 按钮名称
     */
    @When("^the data name is \"([^\"]*)\" then i click the \"([^\"]*)\" button$")
    public void clickButtonWithGivenName(String dataName, String buttonName) {
        // 表体
        WebElement table = webDriver.findElement(By.className("el-table__body"));
        // 分页标签
        List<WebElement> paging = webDriver.findElements(By.className("number"));
        // 总页数
        int totalPage = Integer.parseInt(paging.get(paging.size() - 1).getText());
        // 下一页按钮
        WebElement nextPage = webDriver.findElement(By.className("btn-next"));
        int i = 0;
        boolean flag = false;
        while (i < totalPage) {
            // 找到一行元素
            List<WebElement> trList = table.findElements(By.tagName("tr"));
            if (i != 0 && i != totalPage - 1)
                nextPage.click();
            for (WebElement tr : trList) {
                if (tr.findElement(By.tagName("td")).getText().equals(dataName)) {
                    String xpath = ".//span[contains(text(),'" + buttonName + "')]";
                    List<WebElement> button = tr.findElements(By.xpath(xpath));
                    // 包含删除的按钮会有两个，因此需通过class属性去判断
                    if (button.get(0).getAttribute("class").equals("")) {
                        button.get(0).click();
                    } else {
                        button.get(1).click();
                    }
                    flag = true;
                    break;
                }
            }
            if (flag) break;
            i++;
        }
    }
}
