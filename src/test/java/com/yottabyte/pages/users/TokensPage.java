package com.yottabyte.pages.users;

import com.yottabyte.pages.PageTemplate;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TokensPage extends PageTemplate {

    public TokensPage(WebDriver driver) {
        super(driver);
    }

    @FindBy (xpath = "//span[text()='域标识:']//following-sibling::span")
    private WebElement token;


    public WebElement getToken() {
        return token;
    }
}
