package com.yottabyte.utils;

import com.yottabyte.hooks.LoginBeforeAllTests;
import com.yottabyte.pages.DateEditorPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author sunxj
 */
public class GetTime {
    public static WebElement getTime(WebDriver webDriver, String time) {
        WebElement webElement = null;
        try {
            DateEditorPage dateEditorPage = new DateEditorPage(webDriver);
            LoginBeforeAllTests.setPageFactory(dateEditorPage);
            Class<DateEditorPage> dateEditorPageClass = DateEditorPage.class;
            String methodName = "get" + time;
            Method method = dateEditorPageClass.getDeclaredMethod(methodName);
            webElement = (WebElement) method.invoke(dateEditorPageClass.getDeclaredConstructor(WebDriver.class).newInstance(webDriver));
            LoginBeforeAllTests.setPageFactory(new Exception().getStackTrace()[1].getClassName());
            System.out.println(new Exception().getStackTrace()[1].getClassName());
        } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return webElement;
    }
}
