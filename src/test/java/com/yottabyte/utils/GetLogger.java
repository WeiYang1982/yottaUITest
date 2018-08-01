package com.yottabyte.utils;


import org.apache.log4j.Logger;

public class GetLogger {

    private static Logger logger = Logger.getLogger(GetLogger.class);

    public static void main(String args[]) {
        int i =0;
        System.out.println("debug" );
        System.out.println(logger);
        logger.debug("this is debug message {}" +  i);
        logger.info("console");
    }
}
