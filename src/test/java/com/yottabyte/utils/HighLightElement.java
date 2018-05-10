package com.yottabyte.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HighLightElement {

    /**
     * 设置元素高亮 并且在2s后自动清除高亮
     * @param webDriver 当前webdriver
     * @param element 需要高亮的元素
     */
    public static void highLightElement(WebDriver webDriver, WebElement element) {
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].style.border='3px solid red'", element);
        String js = "var target = arguments[0];\n" +
                "setTimeout(function() {\n" +
                "  target.style.border='0'\n" +
                "}, 2000)\n";
        ((JavascriptExecutor) webDriver).executeScript(js, element);
    }
}
