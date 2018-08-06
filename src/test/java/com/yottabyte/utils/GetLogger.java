package com.yottabyte.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class GetLogger {

    private static Logger logger;

    static {
        logger = LoggerFactory.getLogger(Thread.currentThread().getStackTrace()[2].getFileName());
//        logger = LoggerFactory.getLogger(GetLogger.class);
    }

    public static Logger getLogger() {
        return logger;
    }

}
