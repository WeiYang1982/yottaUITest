package com.yottabyte.pages.customApplication;

import com.yottabyte.pages.PageTemplate;
import com.yottabyte.utils.ElementExist;
import com.yottabyte.utils.WaitForElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class CreatePage extends PageTemplate {
    public CreatePage(WebDriver driver) {
        super(driver);
    }
    @FindBys({
            @FindBy(className = "yw-system-app-basic"),
            @FindBy(className = "yw-system-input")
    })
    private List<WebElement> inputs;

    @FindBy(className = "el-switch")
    private WebElement autoSearchSwitch;

    @FindBy(className = "el-icon-circle-close")
    private WebElement closeStepFrame;

    @FindBy(xpath = "//button[@class='el-button el-button--primary']/span[text()='保存']")
    private WebElement saveButton;

    @FindBy (className = "el-message-box__content")
    private WebElement messageInfo;

    @FindBy (className = "el-message-box__btns")
    private WebElement messageBoxButtons;

    public WebElement getApplicationName() {
        return inputs.get(0);
    }

    public WebElement getApplicationDes() {
        return inputs.get(1);
    }

    public WebElement getAutoSearchSwitch() {
        return autoSearchSwitch;
    }

    public WebElement getCloseStepFrame() {
        return closeStepFrame;
    }

    public WebElement getMessageInfo() {
        return messageInfo;
    }

    public WebElement getMessageBoxOKButton() {
        WaitForElement.waitForElementWithExpectedCondition(webDriver,
                ExpectedConditions.elementToBeClickable(messageBoxButtons.findElement(By.xpath("//span[contains(text(),'确定')]"))));
        return messageBoxButtons.findElement(By.xpath("//span[contains(text(),'确定')]"));
    }

    public WebElement getSaveButton() {
        if (ElementExist.isElementExist(webDriver,By.className("msgbox-fade-leave-active"))) {
            WaitForElement.waitForElementWithExpectedCondition(webDriver,
                    ExpectedConditions.invisibilityOf(webDriver.findElement(By.className("msgbox-fade-leave-active"))));
        }
        WaitForElement.waitForElementWithExpectedCondition(webDriver,
                ExpectedConditions.elementToBeClickable(saveButton));
        return saveButton;
    }

    public WebElement getSuccessMessage() {
        System.out.println("start wait ");
        if (ElementExist.isElementExist(webDriver,By.className("el-message-box"))) {
            System.out.println(messageInfo.getText());
            WaitForElement.waitForElementWithExpectedCondition(webDriver,ExpectedConditions.visibilityOfElementLocated(By.className("el-message-box")));
        }else {
            System.out.println("is not exist!");
        }
        return webDriver.findElement(By.className("el-message-box__content"));
    }
}
