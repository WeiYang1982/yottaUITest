package com.yottabyte.pages.alertPlugins;

import com.yottabyte.pages.PageTemplate;
import com.yottabyte.utils.ElementExist;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ListPage extends PageTemplate {
    public ListPage(WebDriver driver) {
        super(driver);
    }

    @FindBy (className = "plugin-button")
    private WebElement uploadButton;

    @FindBy (className = "verify-text")
    private WebElement verifyMessage;

    @FindBy (xpath = "//div[@class='dialog-footer']/button/span[text()='确定']")
    private WebElement uploadSubmitButton;

    @FindBy (className = "el-message-box__content")
    private WebElement messageInfo;

    @FindBy (xpath = "//div[@class='el-message-box__btns']//span[contains(text(),'确定')]")
    private WebElement messageOKButton;


    @FindBy (className = "el-message__group")
    private WebElement suspensionMessage;

    public WebElement getUploadButton() {
        return uploadButton;
    }


    public WebElement getVerifyMessage() {
        return verifyMessage;
    }

    public WebElement getUploadSubmitButton() {
        return uploadSubmitButton;
    }

    public WebElement getMessageOKButton() {
        return messageOKButton;
    }

    public WebElement getSuccessMessage() {
        return suspensionMessage;
    }

    public WebElement getErrorMessage() {
        if (ElementExist.isElementExist(webDriver,messageInfo)){
            return messageInfo;
        }else {
            return verifyMessage;
        }
    }

}
