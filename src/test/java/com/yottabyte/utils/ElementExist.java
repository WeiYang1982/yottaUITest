package com.yottabyte.utils;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

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
            ExpectedCondition expectedCondition = ExpectedConditions.visibilityOf(element);
            WaitForElement.waitForElementWithExpectedCondition(driver,expectedCondition);
            status = true;
        }catch (NoSuchElementException e){
            status = false;
            System.out.println("'" + element + "' doesn't exist!\n");
        }catch (TimeoutException e){
            System.out.println("'" + element + "' doesn't exist for time out! \n");
        }
        return status;
    }
}
