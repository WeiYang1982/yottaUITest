package com.yottabyte.utils;


import com.yottabyte.hooks.LoginBeforeAllTests;
import com.yottabyte.webDriver.SharedDriver;
import cucumber.api.Scenario;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TakeScreenShot {
    String actualImageName = "";

    String sp = File.separator;
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss");
    String dateString = formatter.format(new Date());
    String ActualImgFilePath = System.getProperty("user.dir") + sp + "target" + sp +
            "cucumber-html-reports" + sp + "embeddings" + sp + "actual_img" + sp +
            "Actual-" + dateString + ".png";
    File screenShotFile = new File(ActualImgFilePath);

    public void screenShot() {
        Scenario scenario = SharedDriver.getScenario();
        WebDriver webDriver = LoginBeforeAllTests.getWebDriver();
        try {
            byte[] screenshot = ((TakesScreenshot)webDriver).getScreenshotAs(OutputType.BYTES);
            File file = ((TakesScreenshot)webDriver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(file,screenShotFile);
            actualImageName = screenShotFile.getAbsolutePath();
            System.out.println("take screenshot actual " + actualImageName );
            scenario.embed(screenshot, "image/png");
        }catch (WebDriverException somePlatformsDontSupportScreenshots) {
            System.err.println(somePlatformsDontSupportScreenshots.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getActualImageName() {
        return actualImageName;
    }

    public void setActualImageName(String actualImageName) {
        this.actualImageName = actualImageName;
    }
}
