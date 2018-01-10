package com.yottabyte.pages;

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

public class ResourceGroupsCreatePage extends PageTemplate{

    public ResourceGroupsCreatePage(WebDriver driver) {
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

    @FindBys({
            @FindBy(className = "el-button"),
            @FindBy(xpath = "//span[text()='新建']")
    })
    private WebElement createButton;

    @FindBy(className = "el-message-box__message")
    private WebElement successMessage;

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
        webDriver.switchTo().activeElement().click();
        return createButton;
    }

    public WebElement getSuccessMessage() {
        return successMessage;
    }
}
