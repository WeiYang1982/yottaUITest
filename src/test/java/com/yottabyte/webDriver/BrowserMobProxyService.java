package com.yottabyte.webDriver;

import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.client.ClientUtil;
import org.openqa.selenium.Proxy;

import java.io.IOException;
import java.net.ServerSocket;

public class BrowserMobProxyService {
    private static BrowserMobProxyServer browserMobProxyServer;
    private static Proxy seleniumProxy;

    static void startBrowserMobProxy() {
        browserMobProxyServer = new BrowserMobProxyServer();
        try {
            int port = new ServerSocket().getLocalPort();
            browserMobProxyServer.start();
            System.out.println("port:" + port);
            seleniumProxy = ClientUtil.createSeleniumProxy(browserMobProxyServer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static BrowserMobProxyServer getBrowserMobProxyServer() {
        return browserMobProxyServer;
    }

    static Proxy getSeleniumProxy() {
        return seleniumProxy;
    }

    public static void setProxyBandwidth(long time) {
        browserMobProxyServer.setReadBandwidthLimit(time);
        browserMobProxyServer.setWriteBandwidthLimit(time);
    }
}
