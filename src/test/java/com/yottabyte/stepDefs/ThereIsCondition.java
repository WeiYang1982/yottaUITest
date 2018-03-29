package com.yottabyte.stepDefs;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.yottabyte.utils.GetElementFromPage;
import com.yottabyte.utils.JsonStringPaser;
import cucumber.api.java.en.Given;

import java.util.*;

public class ThereIsCondition {
    /**
     *
     * @param needRun 是否需要运行
     * @param methodName 需要调用的函数名称
     * @param arg 调用函数的参数列表，json格式
     */
    @Given("^need run condition \"([^\"]*)\" There is a \"([^\"]*)\" with \"([^\"]*)\"$")
    public void needRunConditionThereIsAWith(String needRun, String methodName, String arg){
        if("N".equals(needRun)){
            System.out.println("skip this steps");
        }else if ("Y".equals(needRun)){
            JsonStringPaser paser = new JsonStringPaser();
            Object pars[] = paser.jsonParser(arg);
            GetElementFromPage.getWebElementsWithoutGet(methodName,pars);
        }
    }

    @Given("^There is a \"([^\"]*)\" with \"([^\"]*)\"$")
    public void ThereIsSomeCondition(String methodName, String arg){
        needRunConditionThereIsAWith("Y",methodName,arg);
    }
}
