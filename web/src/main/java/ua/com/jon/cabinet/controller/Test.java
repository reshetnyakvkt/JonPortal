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

class Inner implements Cloneable {
    private int a = 5;
    public Inner1 inner1 = new Inner1();

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}

class Inner1 {
    private int b = 5;
}

public class Test {

    public static void main (String[] args) throws CloneNotSupportedException {

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
            throw new Exception("hrul");
            //System.exit(0);
        } catch (Exception e) {
            System.out.println("catch");
        } finally {
            System.out.println("finally");
        }

        int i = func();
        Inner a1 = new Inner();
        Inner a2 = (Inner) a1.clone();
        System.out.println("a1.hashCode()=" + a1.hashCode() + "\na2.hashCode()=" + a2.hashCode());
        System.out.println("a1.inner1.hashCode()=" + a1.inner1.hashCode() + "\na2.inner1.hashCode()=" + a2.inner1.hashCode());
        List<Integer> numbers = new ArrayList<Integer>();
        numbers.add(1);
        numbers.add(2);
        numbers.add(3);
        /*for (Integer n : numbers) {
            if (n > 2) {
                numbers.add(33);
            }
        }*/
        /*Iterator<Integer> it1 = numbers.iterator();
        while(it1.hasNext()) {
            if (it1.next().equals(1))
                numbers.add(5);
        }*/
        ListIterator<Integer> it1 = numbers.listIterator();
        while(it1.hasNext()) {
            if (it1.next().equals(1))
                it1.add(5);
        }

        System.out.println(numbers);
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
