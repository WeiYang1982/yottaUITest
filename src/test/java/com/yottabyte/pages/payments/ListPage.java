package com.yottabyte.pages.payments;

import com.yottabyte.pages.PageTemplate;
import com.yottabyte.utils.WaitForElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * @author sunxj
 */
public class ListPage extends PageTemplate {
    public ListPage(WebDriver driver) {
        super(driver);
    }

    public WebElement getCreateButton() {
        return super.getButton("新建");
    }

    @FindBy(xpath = "//span[contains(text(),'确定')]")
    private WebElement ensureButton;

    @FindBy(xpath = "//span[contains(text(),'取消')]")
    private WebElement cancleButton;

    public WebElement getEnsureButton() {
        return ensureButton;
    }

    @FindBy(className = "el-message-box__message")
    private WebElement successMessage;

    public WebElement getSuccessMessage() {
        WaitForElement.waitForElementWithExpectedCondition(webDriver, ExpectedConditions.invisibilityOf(cancleButton));
        return successMessage;
    }
}
