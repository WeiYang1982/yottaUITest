package com.yottabyte.pages.sourceGroup;

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

    @FindBy(xpath = "//span[text()='新建']")
    private WebElement createButton;

    @FindBy(className = "el-message-box__message")
    private WebElement errorMessage;

    @FindBy(className = "el-message__group")
    private WebElement successMessage;

    @FindBy(xpath = "//label[contains(text(),'分组')]/following-sibling::div//input")
    private WebElement groupInput;

    @FindBy(className = "el-select-dropdown__list")
    private List<WebElement> dropdownLists;

    @FindBy(xpath = "//button[@class='el-button yw-modal-btn yw-modal-btn-primary el-button--primary']/span[contains(text(),'确定')]")
    private WebElement ensureButton;

    @FindBy(xpath = "//button[@class='el-button el-button--default el-button--primary ']/span[contains(text(),'确定')]")
    private WebElement ensure;

    @FindBy(className = "yw-table-group__group-menu")
    private WebElement GroupDropdownList;

    @FindBy(className = "el-icon-arrow-down")
    private WebElement arrowDown;

    public WebElement getGroupDropdownList() {
        arrowDown.click();
        return GroupDropdownList;
    }

    public WebElement getEnsure() {
        return ensure;
    }

    public WebElement getEnsureButton() {
        return ensureButton;
    }

    public WebElement getGroupInput() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        groupInput.click();
        return dropdownLists.get(dropdownLists.size() - 1);
    }

    public WebElement getErrorMessage() {
        return errorMessage;
    }

    public WebElement getSuccessMessage() {
        return successMessage;
    }

    public WebElement getCreateButton() {
        return createButton;
    }
}
