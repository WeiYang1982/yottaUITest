package com.yottabyte.webDriver;

import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.client.ClientUtil;
import net.lightbody.bmp.proxy.CaptureType;
import org.openqa.selenium.Proxy;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;

public class BrowserMobProxyService {
    private static BrowserMobProxyServer browserMobProxyServer;
    private static Proxy seleniumProxy;

    static void startBrowserMobProxy() {
        try {
            browserMobProxyServer = new BrowserMobProxyServer();
            browserMobProxyServer.start(0);
            int port = browserMobProxyServer.getPort();
            String PROXY = "localhost:" + port;
            System.out.println("port: " + port);
            seleniumProxy = ClientUtil.createSeleniumProxy(new InetSocketAddress(InetAddress.getLocalHost().getHostAddress(), port));
            System.out.println("seleniumProxy: " + seleniumProxy);
            seleniumProxy.setHttpProxy(PROXY).setSslProxy(PROXY);
            browserMobProxyServer.enableHarCaptureTypes(CaptureType.REQUEST_CONTENT, CaptureType.REQUEST_HEADERS);
        } catch (UnknownHostException e) {
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
