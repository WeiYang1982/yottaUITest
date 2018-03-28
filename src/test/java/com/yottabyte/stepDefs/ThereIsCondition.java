package com.yottabyte.stepDefs;

import com.yottabyte.utils.GetElementFromPage;
import cucumber.api.java.en.Given;

import java.util.ArrayList;
import java.util.List;

public class ThereIsCondition {
    /**
     *
     * @param needRun 是否需要运行
     * @param methodName 需要调用的函数名称
     * @param arg 调用函数的参数列表，格式为： 参数名(参数类型):参数值;
     */
    @Given("^need run condition \"([^\"]*)\" There is a \"([^\"]*)\" with \"([^\"]*)\"$")
    public void needRunConditionThereIsAWith(String needRun, String methodName, String arg){
        if("N".equals(needRun)){
            System.out.println("skip this steps");
        }else if ("Y".equals(needRun)){
            ////TODO 增加对空值的处理
            String[] args = arg.split(";");
            Object pars[] = new Object[args.length];
            for (int i=0;i<args.length;i++){
                String type = args[i].substring(args[i].indexOf("(")+1,args[i].indexOf(")"));
                String value = args[i].split(":")[1];
                if ("list".equalsIgnoreCase(type)){
                    pars[i] = changeStringToList(value);
                }else if ("int".equalsIgnoreCase(type)){
                    pars[i] = Integer.parseInt(value);
                }else if ("string".equalsIgnoreCase(type)){
                    pars[i] = value;
                }
            }
            GetElementFromPage.getWebElementsWithoutGet(methodName,pars);
        }
    }

    @Given("^There is a \"([^\"]*)\" with \"([^\"]*)\"$")
    public void ThereIsSomeCondition(String methodName, String arg){
        needRunConditionThereIsAWith("Y",methodName,arg);
    }

    private List changeStringToList(String longStrings){
        String[] strs = longStrings.split(",");
        List l = new ArrayList();
        for (String s : strs){
            l.add(s);
        }
        return l;
    }
}
