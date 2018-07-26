package com.yottabyte.utils;

import com.yottabyte.hooks.LoginBeforeAllTests;
import org.openqa.selenium.WebElement;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by A on 2017/4/7.
 */
public class GetElementFromPage {

    public static <T>T getWebElementWithName(String name, Object... paras) {
        Object object = null;
        Object page = LoginBeforeAllTests.getPageFactory();
        if (paras != null) {
            Class c[] = null;
            int len = paras.length;
            c = new Class[len];
            for (int i = 0; i < len; ++i) {
                c[i] = paras[i].getClass();
                c[i] = typeParse(c[i].getName());
            }
            name = getMethodNameWithGet(name);
            try {
                object = page.getClass().getDeclaredMethod(name, c).invoke(page, paras);
            } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                e.printStackTrace();
            }
        } else {
            if (name.startsWith("get")) {
                name = name.split("get")[1];
            }
            if (Character.isLowerCase(name.charAt(0))) {
                System.out.println("\n Wanning: name is " + name + " , might be UpperCase in the first! \n");
                name = "get" + name.substring(0, 1).toUpperCase() + name.substring(1);
            } else {
                name = "get" + name;
            }
            try {
                object = page.getClass().getDeclaredMethod(name).invoke(page);
            } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
        return (T) object;
    }

    public static <T>T getWebElementWithoutGet(String name, Object... paras) {
        Class c[] = null;
        Object object = null;
        if (paras != null) {
            int len = paras.length;
            c = new Class[len];
            for (int i = 0; i < len; ++i) {
                c[i] = paras[i].getClass();
                c[i] = typeParse(c[i].getName());
            }
        }
        Object page = LoginBeforeAllTests.getPageFactory();
        try {
            object = page.getClass().getDeclaredMethod(name, c).invoke(page, paras);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return (T) object;
    }

//    public static <T>T getWebElementWithName(String name) {
//        Object page = LoginBeforeAllTests.getPageFactory();
//        Object object = null;
//        name = getMethodNameWithGet(name);
//        try {
//            object = page.getClass().getDeclaredMethod(name).invoke(page);
//        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
//            e.printStackTrace();
//        }
//        return (T) object;
//    }

//    public static <T>T getWebElementWithoutGet(String name) {
//        Object object = null;
//        Object page = LoginBeforeAllTests.getPageFactory();
//        try {
//            object = page.getClass().getDeclaredMethod(name).invoke(page);
//        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
//            e.printStackTrace();
//        }
//        return (T) object;
//    }

    public static String getCurrentPageTitle() {
        return LoginBeforeAllTests.getWebDriver().getTitle();
    }

    private static String getMethodNameWithGet(String name) {
        if (name.startsWith("get")) {
            name = name.split("get")[1];
        }
        if (Character.isLowerCase(name.charAt(0))) {
            System.out.println("\n Wanning: name is " + name + " , might be UpperCase in the first! \n");
            name = "get" + name.substring(0, 1).toUpperCase() + name.substring(1);
        } else {
            name = "get" + name;
        }
        return name;
    }

    private static Class typeParse(String type) {
        switch (type) {
            case "java.lang.Integer":
                return int.class;
            case "java.lang.String":
                return String.class;
            case "java.util.ArrayList":
                return List.class;
            default:
                System.out.println("There is no type with " + type.toString());
                return null;
        }
    }

}
