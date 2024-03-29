package com.yottabyte.pages;

import com.yottabyte.stepDefs.ClickSomeButton;
import com.yottabyte.stepDefs.SetKeyWithValue;
import com.yottabyte.utils.ElementExist;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * 登陆页面的页面元素
 * Created by A on 2017/4/5.
 */
public class LoginPage extends PageTemplate {
    WebDriver webDriver;

    public LoginPage(WebDriver driver) {
        super(driver);
        webDriver = driver;
        parentPageName = "";
    }

    @FindBy (className = "yw-login-form-input")
    private WebElement username;

    @FindBy (css = "#login > div.yw-login-group > div.yw-login-inputs > div:nth-child(3) > input")
    private WebElement password;

    @FindBy (css = "#login > div.yw-login-group > div.yw-login-inputs > div.yw-login-btn-group > button")
    private WebElement loginButton;

    @FindBy (className = "el-message-box__message")
    private WebElement errorMessageBox;

    @FindBy ( className = "el-message__group")
    private WebElement errorMessage;

    public WebElement getUsername() {
        return username;
    }

    public WebElement getPassword() {
        return password;
    }

    public WebElement getLoginButton() {
        return loginButton;
    }

    public String getTitle(){
        return webDriver.getTitle();
    }

    public WebElement getErrorMessage(){
        By by = By.className("el-message__group");
        WebElement message;
        if (ElementExist.isElementExist(webDriver,by)){
            message = errorMessage;
        }else {
            message = errorMessageBox;
        }

        return message;
    }

    public void getLogin(String userName, String password) {
        SetKeyWithValue setKey = new SetKeyWithValue();
        ClickSomeButton click = new ClickSomeButton();
        setKey.iSetTheParameterWithValue(getUsername(), userName);
        setKey.iSetTheParameterWithValue(getPassword(), password);
        click.iClickTheButton(getLoginButton());
        webDriver.navigate().refresh();
    }

}
