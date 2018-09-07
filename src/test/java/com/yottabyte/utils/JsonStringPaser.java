package com.yottabyte.utils;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;

import java.util.*;

public class JsonStringPaser {

    /**
     * 将json格式的字符串解析并转换成数组
     *
     * @param jsonString json格式字符串
     * @return 参数数组
     */
    public Object[] jsonParser(String jsonString) {
        LinkedHashMap<String, Object> object = JSONObject.parseObject(jsonString, new TypeReference<LinkedHashMap<String, Object>>() {
        });
        Object pars[] = new Object[object.size()];
        int j = 0;
        for (Map.Entry<String, Object> entry : object.entrySet()) {
            if (entry.getValue().getClass().getSimpleName().equalsIgnoreCase("jsonarray")) {
                List list = new ArrayList();
                JSONArray array = JSONObject.parseArray(entry.getValue().toString());
                for (int i = 0; i < array.size(); i++) {
                    list.add(array.get(i).toString());
                }
                pars[j] = list;
            } else {
                pars[j] = entry.getValue();
            }
            j++;
        }
        return pars;
    }

    public static Map<String, Object> json2Stirng(String json) {
        LinkedHashMap<String, Object> map = JSONObject.parseObject(json, new TypeReference<LinkedHashMap<String, Object>>() {
        });
        return map;
    }

    public static boolean isJson(String content) {
        try {
            JSONObject.parseObject(content);
        } catch (JSONException ex) {
            try {
                JSONObject.parseArray(content);
            } catch (JSONException ex1) {
                return false;
            }
        }
        return true;
    }

}
