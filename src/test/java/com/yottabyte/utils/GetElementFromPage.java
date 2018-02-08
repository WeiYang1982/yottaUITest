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
        if(paras!=null){//存在
            int len=paras.length;
            c=new Class[len];
            for(int i=0;i<len;++i){
                c[i]=paras[i].getClass();
            }
        }
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
            element = (WebElement)page.getClass().getDeclaredMethod(name,c).invoke(page,paras);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return element;
    }

    public static List<WebElement> getWebElementsWithName(String name){
        List<WebElement> list = null;
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
            list = (List<WebElement>) page.getClass().getDeclaredMethod(name).invoke(page);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static String getCurrentPageTitle(){
        String title = null;
        Object page = LoginBeforeAllTests.getPageFactory();
        try {
            title = (String) page.getClass().getDeclaredMethod("getTitle").invoke(page);
        } catch (IllegalAccessException |  NoSuchMethodException e) {
            e.printStackTrace();
        } catch ( InvocationTargetException target){
            Throwable t = target.getTargetException();
            t.printStackTrace();
        }
        return title;
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
