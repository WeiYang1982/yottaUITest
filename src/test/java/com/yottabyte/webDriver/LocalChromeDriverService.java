package com.yottabyte.webDriver;

import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.io.IOException;


public class LocalChromeDriverService {
    private static ChromeDriverService service;

    public static void createAndStartService() {
        try {
            File driverFile;
            System.out.println(System.getProperty("os.name"));
            String osName = System.getProperty("os.name");
            if (osName.startsWith("Windows")) {
                driverFile = new File("lib/chromedriver.exe");
            }else {
                System.setProperty("webdriver.chrome.driver","lib/chromedriver");
                driverFile = new File("lib/chromedriver");
            }
            service = new ChromeDriverService.Builder()
                    .usingDriverExecutable(driverFile)
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
