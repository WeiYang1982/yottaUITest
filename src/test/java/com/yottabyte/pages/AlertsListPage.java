package com.yottabyte.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AlertsListPage extends PageTemplate {

    public AlertsListPage(WebDriver driver) {
        super(driver);
    }

    @FindBy ( xpath = "//*[text()='新建']")
    WebElement createAlert;

    public WebElement getCreateAlert() {
        return createAlert;
    }
}
