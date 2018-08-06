package com.yottabyte.cucumber;

import cucumber.api.junit.Cucumber;
import org.apache.logging.log4j.core.config.ConfigurationSource;
import org.apache.logging.log4j.core.config.Configurator;
import org.junit.runner.Description;
import org.junit.runner.Runner;
import org.junit.runner.notification.RunNotifier;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * Created by A on 2017/4/7.
 */
public class CustomCucumberRunner extends Runner{
    private Class<?> classValue;
    private Cucumber cucumber;

    public CustomCucumberRunner(Class<?> classValue) throws Exception {
        this.classValue = classValue;
        cucumber = new Cucumber(classValue);
    }

    @Override
    public Description getDescription() {
        return cucumber.getDescription();
    }

    private void runAnnotatedMethods(Class<?> annotation) throws Exception {
        if (!annotation.isAnnotation()) {
            return;
        }
        Method[] methods = this.classValue.getMethods();
        for (Method method : methods) {
            Annotation[] annotations = method.getAnnotations();
            for (Annotation item : annotations) {
                if (item.annotationType().equals(annotation)) {
                    method.invoke(null);
                    break;
                }
            }
        }
    }

    @Override
    public void run(RunNotifier notifier) {
        cucumber.run(notifier);
        try {
            runAnnotatedMethods(AfterAll.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}