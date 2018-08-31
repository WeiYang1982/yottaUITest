package com.yottabyte.pages.roles;

import com.yottabyte.pages.PageTemplate;
import com.yottabyte.utils.WaitForElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class EditPage extends PageTemplate {

    public EditPage(WebDriver driver) {
        super(driver);
    }

    @FindBy (className = "el-input__inner")
    private List<WebElement> inputs;

    @FindBy (xpath = "//span[text()='更新']/parent::button")
    private WebElement updateButton;

    @FindBy (className = "el-message-box__content")
    private WebElement messageInfo;


    public WebElement getRoleName() {
        return inputs.get(0);
    }

    public WebElement getRoleDes() {
        return inputs.get(1);
    }

    public WebElement getUpdateButton() {
        return updateButton;
    }

    public WebElement getSuccessMessage(){
        ExpectedCondition expectedCondition = ExpectedConditions.textToBePresentInElement(messageInfo,"成功");
        WaitForElement.waitForElementWithExpectedCondition(webDriver,expectedCondition);
        return messageInfo;
    }

    public WebElement getErrorMessage() {
        return messageInfo;
    }
}
