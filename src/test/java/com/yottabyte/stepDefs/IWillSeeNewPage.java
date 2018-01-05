package com.yottabyte.stepDefs;

import com.yottabyte.utils.ConstructPageFactoryWithName;
import cucumber.api.java.en.Then;

/**
 * Created by A on 2017/4/7.
 */
public class IWillSeeNewPage {

    /**
     * 加载新页面，当产生页面跳转时，用于加载新的page用
     * @param pageName 页面名称
     */
    @Then("^I will see the \"([^\"]*)\" page$")
    public void iWillSeeNewPage(String pageName){
        ConstructPageFactoryWithName c = new ConstructPageFactoryWithName();
        c.constructPageFactoryWithName(pageName);
    }
}
