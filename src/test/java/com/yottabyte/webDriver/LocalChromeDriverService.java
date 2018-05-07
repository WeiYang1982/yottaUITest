package com.yottabyte.webDriver;

import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.io.IOException;


public class LocalChromeDriverService {
    private static ChromeDriverService service;

    public static void createAndStartService() {
        try {
            System.setProperty("webdriver.chrome.driver","lib/chromedriver");
            service = new ChromeDriverService.Builder()
                    .usingDriverExecutable(new File("lib/chromedriver"))
                    .usingAnyFreePort()
                    .build();
            service.start();
            System.out.println("Selenium service start at: " + service.getUrl());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ChromeDriverService getService() {
        return service;
    }
}
