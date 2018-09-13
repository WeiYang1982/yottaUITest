package com.yottabyte.utils;

import com.alibaba.fastjson.JSONArray;
import com.yottabyte.hooks.LoginBeforeAllTests;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

/**
 * Created by A on 2017/4/7.
 */
public class GetElementFromPage {
    public static Type type;
    static Object object = null;
    static Class c[] = null;

    /**
     * 根据名称获取页面中的元素
     * @param name 元素名称 如果是json格式，value必须是列表
     * @param paras 获取元素的参数
     * @param <T>
     * @return
     */
    public static <T>T getWebElementWithName(String name, Object... paras) {
        if (JsonStringPaser.isJson(name)) {
            Map<String, Object> map = JsonStringPaser.json2Stirng(name);
            Object[] parameters = new Object[0];
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                name = entry.getKey();
                parameters = ((JSONArray) entry.getValue()).toArray();
            }
            invokeMethod(name, parameters);
        }else {
            if (paras != null) {
                invokeMethod(name, paras);
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
                    Object page = LoginBeforeAllTests.getPageFactory();
                    Method method = page.getClass().getDeclaredMethod(name);
                    type = method.getAnnotatedReturnType().getType();
                    object = page.getClass().getDeclaredMethod(name).invoke(page);
                } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                    e.printStackTrace();
                }
            }
        }

        return (T) object;
    }

    public static <T>T getWebElementWithoutGet(String name, Object... paras) {
        Class c[] = null;
        Object object = null;
        c = getClasses(c, paras);
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

    public static String getCurrentPageTitle() {
        return LoginBeforeAllTests.getWebDriver().getTitle();
    }

    public static void getMethod(String className, String methodName, Object... paras) {
        Class c[] = null;
        c = getClasses(c, paras);
        Object page = className;
        try {
            Class<?> cla = Class.forName(className);
            cla.getDeclaredMethod(methodName, c).invoke(page, paras);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static Class[] getClasses(Class[] c, Object[] paras) {
        if (paras != null) {
            int len = paras.length;
            c = new Class[len];
            for (int i = 0; i < len; ++i) {
                c[i] = paras[i].getClass();
                c[i] = typeParse(c[i].getName());
            }
        }
        return c;
    }

    private static void invokeMethod(String methodName, Object[] paras) {
        int len = paras.length;
        c = new Class[len];
        for (int i = 0; i < len; ++i) {
            c[i] = paras[i].getClass();
            c[i] = typeParse(c[i].getName());
        }
        methodName = getMethodNameWithGet(methodName);
        try {
            Object page = LoginBeforeAllTests.getPageFactory();
            type = page.getClass().getDeclaredMethod(methodName, c).getAnnotatedReturnType().getType();
            object = page.getClass().getDeclaredMethod(methodName, c).invoke(page, paras);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
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
