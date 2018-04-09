package com.yottabyte.pages.users;

import com.yottabyte.pages.PageTemplate;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DetailPage extends PageTemplate {

    public DetailPage(WebDriver driver) {
        super(driver);
    }

    @FindBy (className = "update-button")
    private WebElement editButton;

    public WebElement getEditButton() {
        return editButton;
    }
}
