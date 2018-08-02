package com.yottabyte.utils;


import org.apache.logging.log4j.core.config.ConfigurationSource;
import org.apache.logging.log4j.core.config.Configurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class GetLogger {

    private static Logger logger;

    static {
        File conFile = new File("config/log4j2.xml");
        try {
            ConfigurationSource config = new ConfigurationSource(new BufferedInputStream(new FileInputStream(conFile)));
            Configurator.initialize(null, config);
        } catch (IOException e) {
            e.printStackTrace();
        }
        logger = LoggerFactory.getLogger(Thread.currentThread().getStackTrace()[2].getFileName());
//        logger = LoggerFactory.getLogger(GetLogger.class);
    }

    public static Logger getLogger() {
        return logger;
    }

}
