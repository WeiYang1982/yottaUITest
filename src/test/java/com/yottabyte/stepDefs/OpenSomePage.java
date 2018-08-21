package com.yottabyte.stepDefs;

import com.yottabyte.hooks.LoginBeforeAllTests;
import com.yottabyte.pages.timedTask.DetailPage;
import com.yottabyte.utils.*;
import com.yottabyte.webDriver.SharedDriver;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import static com.yottabyte.utils.JdbcUtils.query;

public class OpenSomePage {
    private WebDriver webDriver = LoginBeforeAllTests.getWebDriver();
    private String baseURL = LoginBeforeAllTests.getBaseURL();
    private TakeScreenShot shot = SharedDriver.getScreenShot();

    /**
     * 打开指定页面  参数由feature提供
     *
     * @param pageName
     */
    @Given("^open the \"([^\"]*)\" page for uri \"([^\"]*)\"$")
    public void openThePageForURI(String pageName, URI uri) {
        Cookie cookie = LoginBeforeAllTests.getCookie();
        if (webDriver.manage().getCookieNamed("sessionid") == null) {
            webDriver.manage().addCookie(cookie);
        }
        webDriver.get(baseURL + uri);
        ConstructPageFactoryWithName c = new ConstructPageFactoryWithName();
        c.constructPageFactoryWithName(pageName);
    }

    /**
     * 打开某页面，且不需要指定URI
     *
     * @param pageName
     */
    @Given("^open the \"([^\"]*)\" page$")
    public void openThePage(String pageName) {
        webDriver.get(baseURL);
        ConstructPageFactoryWithName c = new ConstructPageFactoryWithName();
        c.constructPageFactoryWithName(pageName);
    }


    @Given("^I open the page contains \"([^\"]*)\"$")
    public void openPage(String name) {
        String sql = "select id from SavedSchedule where name like '%" + name + "' and last_run_timestamp !=0";
        List<String> list = JdbcUtils.query(sql);
        try {
            for (int i = 0; i < list.size(); i++) {
                URI uri = new URI("/schedule/" + list.get(i));
                this.openThePageForURI("timedTask.DetailPage", uri);
                DetailPage listPage = new DetailPage(webDriver);
                LoginBeforeAllTests.setPageFactory(listPage);

                WebElement webElement = GetElementFromPage.getWebElementWithName("Show");
                webElement.click();
                if (ElementExist.isElementExist(webDriver, By.className("settings"))){
                    Thread.sleep(5000);
                    shot.screenShot();
                }
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}