package com.jon.tron.service.reflect;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: sergey
 * Date: 14.09.13
 * Time: 21:23
 * To change this template use File | Settings | File Templates.
 */
public class ReflectionUtil {

    public static void invokeMain(Object instance, String[] str) {

    }

    public static boolean isCorrectMainPresent(Class clazz) {
        return false;
    }

    public static ArrayList<String> findAllTests(String testsPackage) throws IOException, ClassNotFoundException{
        return new ArrayList<String>();
    }

    public static Method getMethod(Class unitClass, String bubbleSort, Class<int[]> aClass) {
        return null;  //To change body of created methods use File | Settings | File Templates.
    }

    public static Object invokeMethod(Object instance, String bubbleSort, Class aClass1, Class aClass, String s, int[] clone) {
        return null;  //To change body of created methods use File | Settings | File Templates.
    }

    public static Method checkMainMethod(Class unitClass) {
        return null;
    }

    public static void invokeMain(String unitName, String unitJarClasspath, String s) {
        //To change body of created methods use File | Settings | File Templates.
    }


    public static void invokeMain(Object instance) {
    }



    public static Method checkMethod(Class unitClass, String equalsMethodName, Class<?> booleanClass, MethodModifier[] methodModifiers) {
        return null;
    }

    public static Method checkMethod(Class unitClass, String markMethodName, Class<?> voidClass, MethodModifier[] methodModifiers, Object ... os) {
        return null;  //To change body of created methods use File | Settings | File Templates.
    }

    public static int[] invokeMethodAsProcess(Object instance, String bubbleSort, Class<int[]> aClass, Class<int[]> aClass1, String s, int[] clone) {
        return new int[0];  //To change body of created methods use File | Settings | File Templates.
    }

    public static boolean invokeMethod(Object instance, Method unitMethod, int[] actualVector) {
        return false;
    }
}
