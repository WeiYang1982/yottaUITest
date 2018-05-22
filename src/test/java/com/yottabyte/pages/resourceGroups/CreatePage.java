package com.yottabyte.pages.resourceGroups;

import com.yottabyte.pages.PageTemplate;
import com.yottabyte.utils.WaitForElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class CreatePage extends PageTemplate {

    public CreatePage(WebDriver driver) {
        super(driver);
    }

    @FindAll({
            @FindBy(className = "el-input__inner")
    })
    private List<WebElement> inputs;

    @FindAll({
            @FindBy(className = "el-scrollbar__view")
    })
    private List<WebElement> selectors;

    @FindBy(xpath = "//span[text()='新建']")
    private WebElement createButton;

    @FindBy(className = "el-message-box__message")
    private WebElement message;

    @FindBy(xpath = "//*[@class='el-message-box__btns']//span[contains(text(),'确定')]")
    private WebElement OKButton;

    public WebElement getResourceGroupName(){
        return inputs.get(0);
    }

    public List<WebElement> getResourceGroupType(){
        inputs.get(1).click();
        ExpectedCondition expectedCondition = ExpectedConditions.visibilityOf(selectors.get(1));
        WaitForElement.waitForElementWithExpectedCondition(webDriver,expectedCondition);
        List<WebElement> list = selectors.get(1).findElements(By.tagName("li"));
        return list;
    }

    public WebElement getResourceGroupDes(){
        return inputs.get(2);
    }

    public List<WebElement> getResourceGroupOwner(){
        inputs.get(3).click();
        ExpectedCondition expectedCondition = ExpectedConditions.visibilityOf(selectors.get(1));
        WaitForElement.waitForElementWithExpectedCondition(webDriver,expectedCondition);
        List<WebElement> list = selectors.get(1).findElements(By.tagName("li"));
        return list;
    }

    public WebElement getCreateButton() {
//        webDriver.switchTo().activeElement().click();
        ExpectedCondition expectedCondition = ExpectedConditions.elementToBeClickable(createButton);
        WaitForElement.waitForElementWithExpectedCondition(webDriver,expectedCondition);
        return createButton;
    }

    public WebElement getSuccessMessage() {
        ExpectedCondition expectedCondition = ExpectedConditions.visibilityOf(message);
        return message;
    }

    public WebElement getErrorMessage() {
        return message;
    }

    public WebElement getOKButton() {
        return OKButton;
    }
}
