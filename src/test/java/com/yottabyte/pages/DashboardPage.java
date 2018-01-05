package com.yottabyte.pages;

import com.yottabyte.utils.WaitForElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * Created by A on 2017/4/6.
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
