package com.yottabyte.pages.timedTask;

import com.yottabyte.pages.PageTemplate;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class DetailPage extends PageTemplate {

    public DetailPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(className = "el-button--text")
    private List<WebElement> show;

    public WebElement getShow() {
        return show.get(0);
    }
}
