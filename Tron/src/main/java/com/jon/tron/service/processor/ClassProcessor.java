package com.jon.tron.service.processor;

import org.springframework.stereotype.Service;

import java.util.AbstractMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Admin
 * Date: 09.09.13
 */
@Service
public class ClassProcessor {
    public Map.Entry<String, String> processClass(String name, String className, String classCode, boolean b) {
        return new AbstractMap.SimpleEntry<String, String>("25", "Задание проверено виртуально");
    }
}
