package com.yottabyte.utils;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;

public class GetDecoderQuery {
    /**
     * 获取URL中的请求参数 对URL解码处理
     * @param url 需要处理的url
     * @return
     */
    public String getQuery(String url) {
        try {
            String encodeUrl = java.net.URLDecoder.decode(url, "UTF-8");
            return new URL(encodeUrl).getQuery();
        } catch (MalformedURLException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String args[]) {
        String s = "http://192.168.1.200/search/?query=*%20%7C%20stats%20avg(raw_message_length)%20as%20avg_length%2C%20count(apache.clientip)%20as%20ip_count%20by%20appname%20%7C%20sort%20by%20ip_count&time_range=-10m%2Cnow&order=desc&size=20&page=1&sourcegroup=all&_t=1534320267272&title=%E6%96%B0%E6%90%9C%E7%B4%A2";
        GetDecoderQuery decoder = new GetDecoderQuery();
        System.out.println(decoder.getQuery(s));

    }
}
