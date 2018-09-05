package com.yottabyte.pages.sourceGroup;

import com.yottabyte.pages.PageTemplate;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * @author sunxj
 */
public class CreatePage extends PageTemplate {
    public CreatePage(WebDriver driver) {
        super(driver);
    }

    private WebElement findInputAfterText(String text) {
        String xpath = "//label[contains(text(),'" + text + "')]/following-sibling::div/input";
        WebElement element = webDriver.findElement(By.xpath(xpath));
        return element;
    }

    @FindBy(xpath = "//label[contains(text(),'来源分组')]/following-sibling::div//input")
    private WebElement sourceGroup;

    @FindBy(className = "el-select-dropdown__list")
    private List<WebElement> dropdownLists;

    @FindBy(className = "el-button--primary")
    private WebElement ensureCreateButton;

    @FindBy(className = "el-message-box__message")
    private WebElement successMessage;

    public WebElement getSuccessMessage() {
        return successMessage;
    }

    public WebElement getEnsureCreateButton() {
        return ensureCreateButton;
    }

    public WebElement getName() {
        return this.findInputAfterText("名称");
    }

    public WebElement getDescribe() {
        return this.findInputAfterText("描述");
    }

    public WebElement getHostname() {
        return this.findInputAfterText("hostname");
    }

    public WebElement getAppname() {
        return this.findInputAfterText("appname");
    }

    public WebElement getTag() {
        return this.findInputAfterText("tag");
    }

    public WebElement getSpl() {
        return this.findInputAfterText("过滤语句");
    }

    public WebElement getSourceGroup() {
        sourceGroup.click();
        return dropdownLists.get(dropdownLists.size() - 1);
    }
}
