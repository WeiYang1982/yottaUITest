package com.yottabyte.pages.roles;

import com.yottabyte.pages.PageTemplate;
import com.yottabyte.stepDefs.ClickSomeButton;
import com.yottabyte.stepDefs.ICheckValuesFromCheckBox;
import com.yottabyte.stepDefs.SetKeyWithValue;
import com.yottabyte.utils.WaitForElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class CreatePage extends PageTemplate {

    public CreatePage(WebDriver driver) {
        super(driver);
    }

    @FindBy (className = "el-input__inner")
    private List<WebElement> inputs;

    @FindBy (className = "el-checkbox__input")
    private WebElement resourceGroupCheckbox;

    @FindBy (className = "checkbox-group")
    private WebElement checkBoxs;

    @FindBy (className = "el-button--primary")
    private WebElement createButton;

    @FindBy(className = "el-message-box__message")
    private WebElement message;

    @FindBy(xpath = "//*[@class='el-message-box__btns']//span[contains(text(),'确定')]")
    private WebElement OKButton;


    public WebElement getRoleName() {
        return inputs.get(0);
    }

    public WebElement getRoleDes() {
        return inputs.get(1);
    }

    public List<WebElement> getResourceGroupCheckbox() {
        resourceGroupCheckbox.click();
        ExpectedCondition expectedCondition = ExpectedConditions.visibilityOf(checkBoxs);
        WaitForElement.waitForElementWithExpectedCondition(webDriver,expectedCondition);
        return checkBoxs.findElements(By.tagName("label"));
    }

    public WebElement getCreateButton() {
        return createButton;
    }

    public WebElement getOKButton() {
        return OKButton;
    }

    public WebElement getSuccessMessage() {
        return message;
    }

    public WebElement getErrorMessage() {
        return message;
    }

    public void createARole(String roleName, String roleDes, List<String> roleResourceGroup){
        SetKeyWithValue setKey = new SetKeyWithValue();
        ClickSomeButton click = new ClickSomeButton();
        ICheckValuesFromCheckBox checkBox = new ICheckValuesFromCheckBox();
        setKey.iSetTheParameterWithValue1("RoleName",roleName);
        setKey.iSetTheParameterWithValue1("RoleDes",roleDes);
        checkBox.iCheckFromThe(roleResourceGroup,"ResourceGroupCheckbox");
        click.iClickTheButton("CreateButton");
        click.iClickTheButton("OKButton");
    }
}
