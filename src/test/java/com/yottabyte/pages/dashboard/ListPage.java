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

    public WebElement getCreateButton() {
        return createButton;
    }

    @FindBy(xpath = "//label[contains(text(),'名称')]/following-sibling::div//input")
    private WebElement dashBoardName;

    public WebElement getDashBoardName() {
        return dashBoardName;
    }

    @FindBy(xpath = "//label[contains(text(),'分组')]/following-sibling::div//input[@class='el-input__inner']")
    private WebElement dashBoardGroup;

    public WebElement getDashBoardGroup() {
        dashBoardGroup.click();
        return dropdownList.get(dropdownList.size() - 1);
    }

    @FindBy(className = "el-message__group")
    private WebElement successMessage;

    public WebElement getSuccessMessage() {
        return successMessage;
    }

    @FindBy(className = "el-message-box__message")
    private WebElement errorMessage;

    public WebElement getErrorMessage() {
        return errorMessage;
    }

    @FindBy(xpath = "//span[contains(text(),'确定')]")
    private List<WebElement> ensureButtonList;

    public WebElement getEnsureCreateButton() {
        return ensureButtonList.get(0);
    }
}
