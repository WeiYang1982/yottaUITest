package com.yottabyte.pages.resourceGroups;

import com.yottabyte.pages.PageTemplate;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class EditPage extends PageTemplate{

    public EditPage(WebDriver driver) {
        super(driver);
    }

    @FindAll({
            @FindBy(className = "el-input__inner")
    })
    private List<WebElement> inputs;

    @FindBy(xpath = "//button/span[contains(text(),'保存')]")
    private WebElement saveButton;

    @FindBy(className = "el-message__group")
    private WebElement suspensionMessage;

    public WebElement getResourceGroupName(){
        return inputs.get(0);
    }

    public WebElement getResourceGroupDes(){
        return inputs.get(2);
    }

    public WebElement getSaveButton() {
        return saveButton;
    }

    public WebElement getSuccessMessage() {
        return suspensionMessage;
    }
}
