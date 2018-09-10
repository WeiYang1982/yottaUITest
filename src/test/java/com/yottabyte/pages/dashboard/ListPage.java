package com.yottabyte.pages.dashboard;

import com.yottabyte.pages.PageTemplate;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * @author sunxj
 */
public class ListPage extends PageTemplate {
    public ListPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(className = "el-select-dropdown__list")
    private List<WebElement> dropdownList;

    @FindBy(xpath = "//button[@class='el-button slot-button new-btn el-button--default']")
    private WebElement createButton;

    @FindBy(xpath = "//label[contains(text(),'分组')]/following-sibling::div//input[@class='el-input__inner']")
    private WebElement dashBoardGroup;

    @FindBy(className = "el-message__group")
    private WebElement successMessage;

    @FindBy(className = "el-message-box__message")
    private WebElement errorMessage;

    @FindBy(xpath = "//span[contains(text(),'确定')]")
    private List<WebElement> ensureButtonList;

    @FindBy(className = "el-select-dropdown__list")
    private List<WebElement> groupInput;

    public WebElement getGroupInput() {
        dashBoardGroup.click();
        return groupInput.get(groupInput.size() - 1);
    }

    public WebElement getCreateButton() {
        return createButton;
    }

    public WebElement getDashBoardName() {
        return super.getInputElement("名称");
    }


    public WebElement getDashBoardGroup() {
        return super.getDropdownList("分组");
    }


    public WebElement getSuccessMessage() {
        return successMessage;
    }


    public WebElement getErrorMessage() {
        return errorMessage;
    }


    public WebElement getEnsureCreateButton() {
        return ensureButtonList.get(0);
    }

    public WebElement getEnsureChangeGroupButton() {
        return ensureButtonList.get(1);
    }

    public WebElement getEnsureDeleteButton() {
        return ensureButtonList.get(4);
    }

    public WebElement getEnsureRenameButton() {
        return ensureButtonList.get(2);
    }
}
