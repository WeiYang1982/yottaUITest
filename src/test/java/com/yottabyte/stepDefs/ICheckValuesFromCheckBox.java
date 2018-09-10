package com.yottabyte.stepDefs;

import com.yottabyte.hooks.LoginBeforeAllTests;
import com.yottabyte.utils.GetElementFromPage;
import cucumber.api.java.en.And;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ICheckValuesFromCheckBox {
    /**
     * 对复选框的操作,将指定文本的复选框选中
     * @param values 需要被选中的复选框的文本内容，如果只传入一个all，会将全部都选中
     * @param checkBoxName 需要处理的复选框的元素名称
     */
    @And("^I check \"([^\"]*)\" from the \"([^\"]*)\"$")
    public void iCheckFromThe(List<String> values , String checkBoxName) {
        WebDriver webDriver = LoginBeforeAllTests.getWebDriver();
        List<WebElement> fatherSelectList = GetElementFromPage.getWebElementWithName(checkBoxName);
        if (values.size() == 1){
            String value = values.get(0);
            if (value != null && value.trim().length() != 0){
                for (WebElement e : fatherSelectList) {
                    ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView();", e);
                    WebElement checkbox = e.findElement(By.className("el-checkbox__input"));
                    WebElement label = e.findElement(By.className("el-checkbox__label"));
                    String attribute = checkbox.getAttribute("class");
                    if (value.equalsIgnoreCase(label.getText()) || value.equalsIgnoreCase("all")){
                        if (!attribute.contains("is-checked")){
                            checkbox.click();
                            break;
                        }
                    }else {
                        if (attribute.contains("is-checked")){
                            checkbox.click();
                        }
                    }
                }
            }
        }else {
            for (WebElement e : fatherSelectList) {
                ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView();", e);
                WebElement checkbox = e.findElement(By.className("el-checkbox__input"));
                WebElement label = e.findElement(By.className("el-checkbox__label"));
                String attribute = checkbox.getAttribute("class");
                if (attribute.contains("is-checked")){
                    checkbox.click();
                }
                for (String value : values){
                    if (value.equalsIgnoreCase(label.getText())){
                        if (!attribute.contains("is-checked")){
                            checkbox.click();
                        }
                    }else {
                        if (attribute.contains("is-checked")){
                            checkbox.click();
                        }
                    }
                }
            }
        }
    }


}
