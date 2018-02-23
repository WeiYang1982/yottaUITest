package com.yottabyte.stepDefs;

import com.yottabyte.hooks.LoginBeforeAllTests;
import cucumber.api.java.en.And;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class UploadFile {
    /**
     * 根据绝对路径以及文件名上传文件，指定了input元素为name=file,若控件更新可能需要修改此处
     * @param fileNameWithPath 包含绝对路径的文件名
     */
    @And("^I upload a file with name \"([^\"]*)\"$")
    public void iUploadAFileWithName(String fileNameWithPath) {
        WebDriver webDriver = LoginBeforeAllTests.getWebDriver();
        webDriver.findElement(By.name("file")).sendKeys(fileNameWithPath);
    }
}
