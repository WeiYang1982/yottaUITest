package com.yottabyte.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * 导航栏页面元素
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
