package com.yottabyte.utils;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class ElementExist {
    public static boolean isElementExist(WebDriver driver,By by){
        boolean status = false;
        try {
            driver.findElement(by);
            status = true;
        }catch (NoSuchElementException e){
            status = false;
            System.out.println("'" + by + "' doesn't exist!\n");
        }catch (TimeoutException e){
            System.out.println("'" + by + "' doesn't exist for time out! \n");
//            throw e;
        }
        return status;
    }

    public static boolean isElementExist(WebDriver driver,String content){
        boolean status = false;
        try {
            driver.findElement(By.xpath("//*[contains(.,'" + content + "')]"));
            status = true;
        }catch (NoSuchElementException e){
            status = false;
            System.out.println("'" + content + "' doesn't exist!");
        }
        return status;
    }

    public static boolean isElementExist(WebDriver driver, WebElement element){
        boolean status = false;
        try {
            FluentWait wait = new FluentWait(driver)
                    .withTimeout(500, TimeUnit.MILLISECONDS)
                    .pollingEvery(100,TimeUnit.MILLISECONDS)
                    .ignoring(NoSuchElementException.class);
            ExpectedCondition expectedCondition = ExpectedConditions.visibilityOf(element);
            wait.until(expectedCondition);
            status = true;
        }catch (NoSuchElementException e){
            status = false;
            System.out.println("'" + element + "' doesn't exist!\n");
        }catch (TimeoutException e){
            System.out.println("'" + element + "' doesn't exist for time out! \n");
        }catch (StaleElementReferenceException s){
            System.out.println("'" + element + "' wait refresh the page \n");
        }
        return status;

    }
}
