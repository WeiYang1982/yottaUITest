package com.yottabyte.pages;

import com.yottabyte.utils.WaitForElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * 仪表盘页面元素
 */
public class DashboardPage extends PageTemplate {

    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    public String getTitle(){
        ExpectedCondition expectedCondition = ExpectedConditions.titleIs("仪表盘");
        WaitForElement.waitForElementWithExpectedCondition(webDriver,expectedCondition);
        return webDriver.getTitle();
    }
}
