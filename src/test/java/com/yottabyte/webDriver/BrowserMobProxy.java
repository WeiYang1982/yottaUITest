package com.yottabyte.webDriver;

import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.client.ClientUtil;
import org.openqa.selenium.Proxy;

public class BrowserMobProxy {
    private static BrowserMobProxyServer browserMobProxyServer;
    private static Proxy seleniumProxy;

    static void startBrowserMobProxy() {
        browserMobProxyServer = new BrowserMobProxyServer();
        browserMobProxyServer.start(0);
        seleniumProxy = ClientUtil.createSeleniumProxy(browserMobProxyServer);
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
