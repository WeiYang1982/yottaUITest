package com.yottabyte.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class getMD5 {

    public static String getMD5(String str) {
        MessageDigest md = null;
        String md5 = "";
        try {
            md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            md5=new BigInteger(1, md.digest()).toString(16);
            md5 = fillMD5(md5);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return md5;
    }

    private static String fillMD5(String md5){
        return md5.length()==32?md5:fillMD5("0"+md5);
    }

}
