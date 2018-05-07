package com.yottabyte.pages.alert;

import com.yottabyte.pages.PageTemplate;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AlertsListPage extends PageTemplate {

    public AlertsListPage(WebDriver driver) {
        super(driver);
    }

    @FindBy ( xpath = "//button[@class='el-button slot-button el-button--default']//span[text()='新建']")
    WebElement createAlert;

    public WebElement getCreateAlert() {
        return createAlert;
    }

}
