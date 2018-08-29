package com.yottabyte.stepDefs;

import com.yottabyte.utils.CreateWithSQL;
import com.yottabyte.utils.DeleteWithSQL;
import com.yottabyte.utils.GetElementFromPage;
import com.yottabyte.utils.JsonStringPaser;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;

import java.util.List;

public class ThereIsCondition {

    JsonStringPaser paser = new JsonStringPaser();

    /**
     *
     * @param needRun 是否需要运行
     * @param methodName 需要调用的函数名称
     * @param arg 调用函数的参数列表，json格式
     */
    @Given("^need run condition \"([^\"]*)\" There is a \"([^\"]*)\" with \"([^\"]*)\"$")
    public void needRunConditionThereIsAWith(String needRun, String methodName, String arg) {
        if("N".equals(needRun)){
            System.out.println("skip this steps");
        }else if ("Y".equals(needRun)){
            JsonStringPaser paser = new JsonStringPaser();
            Object pars[] = paser.jsonParser(arg);
            GetElementFromPage.getWebElementWithoutGet(methodName,pars);
        }
    }

    @Given("^There is a \"([^\"]*)\" with \"([^\"]*)\"$")
    public void ThereIsSomeCondition(String methodName, String arg){
        needRunConditionThereIsAWith("Y",methodName,arg);
    }

    @Given("^Delete a \"([^\"]*)\" with \"([^\"]*)\"$")
    public void deleteAUserWith(String methmodName, String parameters) {
        Object pars[] = paser.jsonParser(parameters);
        GetElementFromPage.getMethod("com.yottabyte.utils.DeleteWithSQL", methmodName, pars);
    }

    @And("^Create a \"([^\"]*)\" with \"([^\"]*)\"$")
    public void createAUserWith(String methmodName, String parameters) {
        needCreateAUserWith("Y", methmodName, parameters);
    }

    @And("^I need \"([^\"]*)\" create a \"([^\"]*)\" with \"([^\"]*)\"$")
    public void needCreateAUserWith(String needRun, String methmodName, String parameters) {
        if("N".equals(needRun)){
            System.out.println("skip this steps");
        }else if ("Y".equals(needRun)) {
            Object pars[] = paser.jsonParser(parameters);
            GetElementFromPage.getMethod("com.yottabyte.utils.CreateWithSQL", methmodName, pars);
        }
    }

    public static void main(String args[]) {
        new ThereIsCondition().deleteAUserWith("user", "{'name':['AutoTestNew','AutoTest','AutoTestTmp']}");
//        new ThereIsCondition().createAUserWith("creatUser", "{'name':'AutoTest','fullname':'','email':'AutoTest@yottabyte.cn','telephone':'','password':'qqqqq11111','userGroup':['admin']}");
    }
}
