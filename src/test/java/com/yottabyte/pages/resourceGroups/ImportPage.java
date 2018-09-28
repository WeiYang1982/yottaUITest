package com.yottabyte.pages.resourceGroups;

import com.yottabyte.pages.PageTemplate;
import com.yottabyte.utils.ElementExist;
import com.yottabyte.utils.WaitForElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class ImportPage extends PageTemplate {
    public ImportPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(className = "el-select-dropdown__list")
    private WebElement dropdownList;

    @FindBy(className = "yw-extract-primary-btn")
    private WebElement nextStepButton;

    @FindBy(xpath = "//input[@placeholder='请分配角色']")
    private WebElement assignRoleButton;

    @FindBy (xpath = "//div[@class='el-form-item'][2]//span")
    private WebElement dialogErrorMessage;

    @FindBy (className = "el-message-box__content")
    private WebElement messageInfo;

    @Override
    public WebElement getErrorMessage() {
        if (ElementExist.isElementExist(webDriver,messageInfo)){
            return messageInfo;
        }else {
            return dialogErrorMessage;
        }
    }

    public WebElement getSuccessMessage() {
        return  webDriver.findElement(By.className("yw-upload-success"));
    }

    public WebElement getAssignRole() {
        assignRoleButton.click();
        WaitForElement.waitForElementWithExpectedCondition(webDriver, ExpectedConditions.visibilityOf(dropdownList));
        return dropdownList;
    }

    public WebElement getNextStepButton() {
        WaitForElement.waitForElementWithExpectedCondition(webDriver, ExpectedConditions.elementToBeClickable(nextStepButton));
        return nextStepButton;
    }
}
