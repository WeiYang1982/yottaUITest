package com.yottabyte.stepDefs;

import com.yottabyte.webDriver.BrowserMobProxy;
import cucumber.api.java.en.And;

/**
 * 设置带宽
 */
public class SetBandwidth {
    @And("I set the bandwidth to \"([^\"]*)\"")
    public void setBandwidth(long bandwidth) {
        BrowserMobProxy.setProxyBandwidth(bandwidth);
    }
}
