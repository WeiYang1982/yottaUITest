package com.yottabyte.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by A on 2017/4/12.
 */
public class PublicNavBarPage extends PageTemplate{

    public PublicNavBarPage(WebDriver driver) {
        super(driver);
    }

    @FindBy( partialLinkText = "搜索")
    private WebElement locateSearchPage;

    public WebElement getLocateSearchPage() {
        return locateSearchPage;
    }
}
