package com.yottabyte.pages.report;

import com.yottabyte.pages.PageTemplate;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * 报表列表页
 */
public class ListPage extends PageTemplate {

    public ListPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//span[text()='新建']")
    private WebElement createButton;

    public WebElement getCreateButton() {
        return createButton;
    }

    @FindBy(xpath = "//span[text()='已生成报表']")
    private WebElement existReportButton;

    public WebElement getExistReportButton() {
        return existReportButton;
    }

    @FindBy(xpath = "//label[contains(text(),'分组')]/following-sibling::div//input[@class='el-input__inner']")
    private WebElement changeGroup;

    @FindBy(className = "el-select-dropdown__list")
    private List<WebElement> dropdownList;

    public WebElement getChangeGroup() {
        changeGroup.click();
        return dropdownList.get(dropdownList.size() - 1);
    }

    @FindBy(className = "yw-modal-btn-primary")
    private WebElement ensure;

    public WebElement getEnsure() {
        return ensure;
    }

    @FindBy(className = "el-message__group")
    private WebElement successMessage;

    public WebElement getSuccessMessage() {
        return successMessage;
    }

    @FindBy(className = "el-button--primary")
    private List<WebElement> deleteEnsure;

    public WebElement getDeleteEnsure() {
        return deleteEnsure.get(deleteEnsure.size() - 1);
    }
}
