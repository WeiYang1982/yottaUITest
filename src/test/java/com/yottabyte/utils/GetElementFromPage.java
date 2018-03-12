package com.yottabyte.utils;

import com.yottabyte.hooks.LoginBeforeAllTests;
import org.openqa.selenium.WebElement;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * Created by A on 2017/4/7.
 */
public class GetElementFromPage {

    public static WebElement getWebElementWithName(String name){
        WebElement element = null;
        Object page = LoginBeforeAllTests.getPageFactory();
        if (name.startsWith("get")) {
            name = name.split("get")[1];
        }
        if (Character.isLowerCase(name.charAt(0))){
            System.out.println("\n Wanning: name is " + name + " , might be UpperCase in the first! \n");
            name = "get" + name.substring(0,1).toUpperCase() + name.substring(1);
        }else {
            name = "get" + name;
        }
        try {
            element = (WebElement)page.getClass().getDeclaredMethod(name).invoke(page);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return element;
    }

    public static WebElement getWebElementWithName(String name,Object[] paras){
        Class c[]=null;
        if(paras!=null){
            int len=paras.length;
            c=new Class[len];
            for(int i=0;i<len;++i){
                c[i]=paras[i].getClass();
                 c[i] = typeParse(c[i].getName());
            }
        }
        WebElement element = null;
        Object page = LoginBeforeAllTests.getPageFactory();
        name = getMethodNameWithGet(name);
        try {
            element = (WebElement)page.getClass().getDeclaredMethod(name,c).invoke(page,paras);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return element;
    }

    public static WebElement getWebElementsWithNoGetName(String name,Object[] paras){
        Class c[]=null;
        if(paras!=null){
            int len=paras.length;
            c=new Class[len];
            for(int i=0;i<len;++i){
                c[i]=paras[i].getClass();
                c[i] = typeParse(c[i].getName());
            }
        }
        WebElement element = null;
        Object page = LoginBeforeAllTests.getPageFactory();
        try {
            element = (WebElement)page.getClass().getDeclaredMethod(name,c).invoke(page,paras);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return element;
    }

    public static List<WebElement> getWebElementsWithName(String name){
        List<WebElement> list = null;
        Object page = LoginBeforeAllTests.getPageFactory();
        name = getMethodNameWithGet(name);
        try {
            list = (List<WebElement>) page.getClass().getDeclaredMethod(name).invoke(page);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static List<WebElement> getWebElementsWithNoGetName(String name){
        List<WebElement> list = null;
        Object page = LoginBeforeAllTests.getPageFactory();
        try {
            list = (List<WebElement>) page.getClass().getDeclaredMethod(name).invoke(page);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static String getCurrentPageTitle(){
        return LoginBeforeAllTests.getWebDriver().getTitle();
    }

    private static String getMethodNameWithGet(String name){
        if (name.startsWith("get")) {
            name = name.split("get")[1];
        }
        if (Character.isLowerCase(name.charAt(0))){
            System.out.println("\n Wanning: name is " + name + " , might be UpperCase in the first! \n");
            name = "get" + name.substring(0,1).toUpperCase() + name.substring(1);
        }else {
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

//    public static String getSomeString(String methodName){
//        String s = null;
//        Object page = LoginBeforeAllTests.getPageFactory();
//        if (methodName.startsWith("get")) {
//            methodName = methodName.split("get")[1];
//        }
//        if (Character.isLowerCase(methodName.charAt(0))){
//            System.out.println("\n Wanning: name is " + methodName + " , might be UpperCase in the first! \n");
//            methodName = "get" + methodName.substring(0,1).toUpperCase() + methodName.substring(1);
//        }else {
//            methodName = "get" + methodName;
//        }
//        try {
//            s = (String) page.getClass().getDeclaredMethod(methodName).invoke(page);
//        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
//            e.printStackTrace();
//        }
//        return s;
//    }
}
