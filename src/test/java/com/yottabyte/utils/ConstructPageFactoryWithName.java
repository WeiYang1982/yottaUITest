package com.yottabyte.utils;

import com.yottabyte.hooks.LoginBeforeAllTests;
import org.openqa.selenium.WebDriver;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class ConstructPageFactoryWithName {
    public void constructPageFactoryWithName(String pageName){
        WebDriver webDriver = LoginBeforeAllTests.getWebDriver();
        Constructor c;
        try {
            c = Class.forName("com.yottabyte.pages." + pageName).getDeclaredConstructor(WebDriver.class);
            c.setAccessible(true);
            LoginBeforeAllTests.setPageFactory(c.newInstance(webDriver));
        } catch (NoSuchMethodException | ClassNotFoundException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
