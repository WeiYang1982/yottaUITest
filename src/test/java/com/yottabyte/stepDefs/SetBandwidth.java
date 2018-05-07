package com.yottabyte.stepDefs;

import com.yottabyte.webDriver.BrowserMobProxyService;
import cucumber.api.java.en.And;

/**
 * 设置带宽
 */
public class SetBandwidth {
    @And("I set the bandwidth to \"([^\"]*)\"")
    public void setBandwidth(long bandwidth) {
        BrowserMobProxyService.setProxyBandwidth(bandwidth);
    }
}
