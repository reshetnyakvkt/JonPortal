package com.jon.tron.service;

import java.util.AbstractMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Admin
 * Date: 09.09.13
 */
public class ClassProcessor {
    private static ClassProcessor classProcessor = new ClassProcessor();

    private ClassProcessor() {}

    public static ClassProcessor getInstance() {
        return classProcessor;
    }

    public Map.Entry<String, String> processClass(String className, String classCode) {
        return new AbstractMap.SimpleEntry<String, String>("25", "Задание проверено виртуально");
    }
}
