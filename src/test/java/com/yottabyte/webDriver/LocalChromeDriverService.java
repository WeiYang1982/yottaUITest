package com.yottabyte.webDriver;

import org.openqa.selenium.chrome.ChromeDriverService;

import java.io.File;
import java.io.IOException;


public class LocalChromeDriverService {
    private static ChromeDriverService service;

    public static void createAndStartService() {
        try {
            service = new ChromeDriverService.Builder()
                    .usingDriverExecutable(new File("lib/chromedriver.exe"))
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
