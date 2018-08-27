package com.yottabyte.stepDefs;

import com.yottabyte.hooks.LoginBeforeAllTests;
import cucumber.api.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * @author sunxj
 */
public class DownLoadFiles {

    WebDriver webDriver = LoginBeforeAllTests.getWebDriver();

    /**
     * 点击列表页第一行第一列的链接进行下载
     */
    @Then("^I download the file$")
    public void downLoadFiles() {
        WebElement table = webDriver.findElement(By.className("el-table__body"));
        try {
            table.findElement(By.tagName("td")).click();
        } catch (NoSuchElementException e) {
            return;
        }
    }
}
