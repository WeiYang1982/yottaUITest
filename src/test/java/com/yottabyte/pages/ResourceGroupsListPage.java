package com.yottabyte.pages;

import com.yottabyte.utils.WaitForElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ResourceGroupsListPage extends PageTemplate{

    public ResourceGroupsListPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(className = "el-loading-mask")
    private WebElement loadingElement;

    @FindBy (xpath = "//*[text()='新建']")
    private WebElement createResourceGroup;

    public WebElement getCreateResourceGroup() {
        ExpectedCondition expectedCondition = ExpectedConditions.invisibilityOf(loadingElement);
        WaitForElement.waitForElementWithExpectedCondition(webDriver,expectedCondition);
        expectedCondition = ExpectedConditions.elementToBeClickable(createResourceGroup);
        WaitForElement.waitForElementWithExpectedCondition(webDriver,expectedCondition);
        return createResourceGroup;
    }
}
