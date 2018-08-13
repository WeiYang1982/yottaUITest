package com.yottabyte.pages.timedTask;

import com.yottabyte.pages.PageTemplate;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class EditPage extends PageTemplate {

    @FindBy(xpath = "//label[text()='名称']/following-sibling::div//input")
    private WebElement name;

    @FindBy(xpath = "//label[text()='描述']/following-sibling::div//input")
    private WebElement describe;

    @FindBy(xpath = "//label[text()='运行用户']/following-sibling::div//input")
    private WebElement user;

    @FindBy(xpath = "//label[text()='日志来源']/following-sibling::div//input")
    private WebElement resource;

    @FindBy(xpath = "//label[text()='任务分组']/following-sibling::div//input")
    private WebElement taskGroup;

    @FindBy(xpath = "//div[@class='custom']//input[@placeholder='请输入']")
    private WebElement period;

    @FindBy(xpath = "//div[@class='custom']//input[@placeholder='请选择时间']")
    private WebElement startTime;

    @FindBy(className = "el-select-dropdown__list")
    private List<WebElement> dropdownLists;

    @FindBy(className = "el-button--primary")
    private WebElement saveButton;

    @FindBy(className = "el-message-box__message")
    private WebElement result;

    public WebElement getSuccessMessage() {
        return result;
    }

    public WebElement getSaveButton() {
        return saveButton;
    }

    public EditPage(WebDriver driver) {
        super(driver);
    }

    public WebElement getName() {
        return name;
    }

    public WebElement getDescribe() {
        return describe;
    }

    public WebElement getUser() {
        user.click();
        return dropdownLists.get(dropdownLists.size() - 1);
    }

    public WebElement getResource() {

        resource.click();
        return dropdownLists.get(dropdownLists.size() - 1);
    }

    public WebElement getTaskGroup() {
        taskGroup.click();
        return dropdownLists.get(dropdownLists.size() - 1);
    }

    public WebElement getPeriod() {
        return period;
    }

    public WebElement getStartTime() {
        return startTime;
    }

}
