package com.yottabyte.stepDefs;

import com.yottabyte.hooks.LoginBeforeAllTests;
import cucumber.api.java.en.And;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

public class UploadFile {
    /**
     * 根据绝对路径以及文件名上传文件，指定了input元素为name=file,若控件更新可能需要修改此处
     * @param fileNameWithPath 包含绝对路径的文件名
     */
    @And("^I upload a file with name \"([^\"]*)\"$")
    public void iUploadAFileWithName(String fileNameWithPath) {
        if(fileNameWithPath != null && fileNameWithPath.trim().length() != 0){
            WebDriver webDriver = LoginBeforeAllTests.getWebDriver();
            File directory = new File("");
            String s = File.separator;
            try {
                String courseFile = directory.getCanonicalPath();
                fileNameWithPath = fileNameWithPath.replace("/",s).replace("\\",s);
                if (fileNameWithPath.startsWith(s) || fileNameWithPath.startsWith("." + s)){
                    webDriver.findElement(By.name("file")).sendKeys(courseFile + fileNameWithPath);
                }else {
                    webDriver.findElement(By.name("file")).sendKeys(courseFile + s + fileNameWithPath);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            System.out.println("skip this step !");
        }

    }
}
