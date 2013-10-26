package ua.com.jon.cabinet.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 * Created with IntelliJ IDEA.
 * User: sergey
 * Date: 19.10.13
 * Time: 18:13
 * To change this template use File | Settings | File Templates.
 */
public class Test {

    public static void main (String[] args) {

        List<Integer> list = new ArrayList<Integer>();
        list.add(1);
        list.add(2);
        list.add(3);
        System.out.println(list);

        ListIterator it = list.listIterator();
        while(it.hasNext()) {
            if (it.next().equals(1))
            it.add(5);
        }

        System.out.println(list);
/*
        for (Integer elem : list) {
            if (elem.equals(2)) {
                list.add(6);
            }
        }
*/
        Long l = 1L;

        try {
            System.out.println("try");
            //throw new Exception("hrul");
            //System.exit(0);
        } catch (Exception e) {
            System.out.println("catch");
        } finally {
            System.out.println("finally");
        }

        int i = func();
    }

    public static int func() {
        try {
            System.out.println("try func");
            throw new Exception("hrul");
            //System.exit(0);
        } catch (Exception e) {
            System.out.println("catch func");
            //throw new Exception(e.getMessage());
            return 0;
        } finally {
            System.out.println("finally func");
        }
        //System.out.println("return func");
        //return 0;
    }
}
