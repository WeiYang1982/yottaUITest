package com.yottabyte.stepDefs;

import com.yottabyte.constants.WebDriverConst;
import com.yottabyte.hooks.LoginBeforeAllTests;
import com.yottabyte.utils.GetElementFromPage;
import cucumber.api.java.en.And;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.FluentWait;

import java.util.concurrent.TimeUnit;

/**
 * Created by A on 2017/4/14.
 */
public class WaitForSomething {

    @And("^I wait for something$")
    public void iWaitForSomething() throws Throwable {
        WebDriver webDriver = LoginBeforeAllTests.getWebDriver();
        System.out.println("wait for some thing");
        final WebElement element = GetElementFromPage.getWebElementWithName("FirstSavedQueryName");
        FluentWait wait = new FluentWait(webDriver)
                .withTimeout(WebDriverConst.WAIT_FOR_DOM_READY_TIMEOUT, TimeUnit.MILLISECONDS)
                .pollingEvery(WebDriverConst.WAIT_FOR_ELEMENT_POLLING_DURING,TimeUnit.MILLISECONDS)
                .ignoring(NoSuchElementException.class);
//        ExpectedCondition expectedCondition = new ExpectedCondition<Boolean>(){
//            @Override
//            public Boolean apply(WebDriver driver) {
//                try {
//                    Boolean flag = element.getText().contains("alert_yctv");
//                    System.out.println("flag: " + flag);
//                    return flag;
//                }catch (StaleElementReferenceException s){
//                    wait.until(new ExpectedCondition() {
//                        @Override
//                        public Object apply(Object o) {
//                            System.out.println("text : " + OpenSomePage.webDriver.findElement(By.className("saved-names")).getText());
//                            return OpenSomePage.webDriver.findElement(By.className("saved-names")).getText().contains("alert_yctv");
//                        }
//                    });
//                }
//                return true;
//            }
//        };

//        wait.until(expectedCondition);
    }
}
