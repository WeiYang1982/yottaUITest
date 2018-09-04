package com.yottabyte.pages.users;

import com.yottabyte.pages.PageTemplate;
import com.yottabyte.utils.ElementExist;
import com.yottabyte.utils.WaitForElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class UsagePage extends PageTemplate {

    public UsagePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(className = "el-date-editor")
    private WebElement dateButton;

    @FindBy(xpath = "//input[@placeholder='开始日期']")
    private WebElement startTime;

    @FindBy(xpath = "//input[@placeholder='结束日期']")
    private WebElement endTime;

    @FindBy(xpath = "//span[text()='应用']/parent::button")
    private WebElement confirm;

    @FindBy(xpath = "//span[text()='查询']/parent::button")
    private WebElement searchButton;

    @FindBy(className = "el-message-box__message")
    private WebElement message;

    @FindBy (className = "el-message__group")
    private WebElement suspensionMessage;


    public WebElement getDateButton() {
        return dateButton;
    }

    public WebElement getStartTime() {
        return startTime;
    }

    public WebElement getEndTime() {
        return endTime;
    }

    public WebElement getConfirm() {
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].blur();", getEndTime());  //让结束日期失去焦点
        return confirm;
    }

    public WebElement getSearchButton() {
        return searchButton;
    }

    public WebElement getErrorMessage(){
        try {
            if (ElementExist.isElementExist(webDriver,suspensionMessage)) {
                return suspensionMessage;
            }else {
                return message;
            }
        }catch (NullPointerException e) {
            e.printStackTrace();
        }
        return null;
    }

}
