package com.yottabyte.stepDefs;

import com.yottabyte.hooks.LoginBeforeAllTests;
import com.yottabyte.utils.GetLogger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class Pagination {

    static WebDriver webDriver = LoginBeforeAllTests.getWebDriver();
    static ClickSomeButton click = new ClickSomeButton();

    /**
     * 从后往前循环分页，根据指定列找到指定文本的元素
     * @param fatherElement 如果有多个分页元素，则根据提供的父级元素来唯一定位
     * @param col 根据列来判断元素是否是需要的
     * @param targetName 指定的元素文本内容
     * @return 行元素
     */
    public static WebElement forEachThePaginationDesc(WebElement fatherElement, int col, String targetName) {
        By by = By.xpath(".//ul[@class='el-pager']/li");
        if (isExist(fatherElement)) {
            List<WebElement> pages = fatherElement.findElements(by);
            WebElement lastPage = fatherElement.findElement(By.xpath(".//ul[@class='el-pager']/li[last()]"));
            click.iClickTheButton(lastPage);
            for (int i=pages.size()-1;i>=0;i--) {
                List<WebElement> tables = fatherElement.findElements(By.className("el-table__body"));
                for (WebElement table : tables) {
                    if (table.isDisplayed()) {
                        List<WebElement> trs = table.findElements(By.tagName("tr"));
                        for (WebElement tr : trs) {
                            if (tr.findElements(By.tagName("td")).get(col-1).getText().equalsIgnoreCase(targetName)) {
                                return tr;
                            }
                        }
                    } else {
                        return null;
                    }
                }
                click.iClickTheButton(fatherElement.findElement(By.className("btn-prev")));
            }
        } else {
            WebElement table = fatherElement.findElement(By.className("el-table__body"));
            if (table.isDisplayed()) {
                List<WebElement> trs = table.findElements(By.tagName("tr"));
                for (WebElement tr : trs) {
                    if (tr.findElements(By.tagName("td")).get(col-1).getText().equalsIgnoreCase(targetName)) {
                        return tr;
                    }
                }
            }
        }
        GetLogger.getLogger().error("没有找到指定元素%!", targetName);
        throw new NoSuchElementException("没有找到指定元素!");
    }

    public WebElement forEachThePaginationDesc(int col, String targetName)  {
        WebElement fatherElement = webDriver.findElement(By.tagName("html"));
        return forEachThePaginationDesc(fatherElement, col, targetName);
    }

    private static Boolean isExist(WebElement fatherElement) {
        try {
            fatherElement.findElement(By.className("el-pagination"));
            return true;
        }catch (NoSuchElementException e) {
            GetLogger.getLogger().error("没有找到对应的分页元素!");
            return false;
        }
    }


}
