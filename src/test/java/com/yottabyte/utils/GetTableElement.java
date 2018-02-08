package com.yottabyte.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class GetTableElement {
    public static WebElement getTableElementWithRowAndCol(WebElement table, int rowNum, int colNum){
        if (rowNum > 0){
            WebElement row = table.findElements(By.tagName("tr")).get(rowNum - 1);
            WebElement e = row.findElements(By.tagName("td")).get(colNum - 1);
            return e;
        }else {
            WebElement row = table.findElements(By.tagName("tr")).get(rowNum);
            WebElement e = row.findElements(By.tagName("td")).get(colNum);
            return e;
        }
    }
}
