package com.yottabyte.stepDefs;

import com.yottabyte.utils.GetElementFromPage;
import com.yottabyte.utils.JsonStringPaser;
import cucumber.api.java.en.And;

public class runAFunction {
    @And("^I add a \"([^\"]*)\" with paramter \"([^\"]*)\"$")
    public void iAddAWithParamter(String functionName, String parameters){
        JsonStringPaser paser = new JsonStringPaser();
        Object pars[] = paser.jsonParser(parameters);
        GetElementFromPage.getWebElementsWithoutGet(functionName,pars);
    }

    public static void main(String args[]){
        JsonStringPaser paser = new JsonStringPaser();
        String s = "\\{\\{ alert.name \\}\\}|\\{\\{ alert.strategy.trigger.start_time|date\\:\\'Y-m-d H\\:i\\:s\\' \\}\\}|\\{\\{ alert.strategy.trigger.end_time|date:\\'Y-m-d H\\:i\\:s\\' \\}\\}|\\{\\{ alert.search.query \\}\\}";
        String parameters = "{'address':'alltest.rizhiyi.com:514','protocol':['UDP'],'level':['INFO'],'facility':'local0','condision':[''],'content':'{{ alert.name }}|{{ alert.strategy.trigger.start_time|date:\"Y-m-d H:i:s\" }}|{{ alert.strategy.trigger.end_time|date:\"Y-m-d H:i:s\" }}|{{ alert.search.query }}'}";
        Object pars[] = paser.jsonParser(parameters);
        for (Object o:pars){
            System.out.println(o.toString());
        }


    }
}
