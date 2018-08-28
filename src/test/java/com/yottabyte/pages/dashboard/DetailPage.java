package com.yottabyte.pages.dashboard;

import com.yottabyte.pages.PageTemplate;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * @author sunxj
 */
public class DetailPage extends PageTemplate {
    public DetailPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//label[contains(text(),'名称')]/following-sibling::div//input")
    private WebElement tagName;

    public WebElement getTagName() {
        return tagName;
    }

    @FindBy(xpath = "//span[contains(text(),'确定')]")
    private List<WebElement> ensureList;

    public WebElement getEnsureCreateTagButton() {
        return ensureList.get(0);
    }
}
