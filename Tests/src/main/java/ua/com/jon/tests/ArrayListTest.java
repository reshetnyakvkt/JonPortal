package ua.com.jon.tests;

import com.jon.tron.service.junit.Unit;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.fail;
import static org.junit.Assert.assertTrue;

/**
 * Created with IntelliJ IDEA.
 * User: Julia
 * Date: 20.10.13
 */
@Unit(testName = "ArrayListTest", value = "weekend1.task1")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ArrayListTest extends BaseTest {

    public static class MyArrayList {
        private Integer[] myArray;
        private int realSize = 0;

        public MyArrayList(int size) {
            this.myArray = new Integer[size];
        }

        public MyArrayList() {
            this.myArray = new Integer[16];
        }

        public boolean add(Integer element){
            checkSize();
            myArray[realSize++] = element;
            return true;
        }

        public boolean add(int index, Integer element){
            checkSize();
            //myArray[realSize++] = element;
            return true;
        }

        public Integer get(int index){
            if((index < 0)||(index > realSize)){
                throw new IndexOutOfBoundsException();
            } else {
                return myArray[index];
            }
        }

        public void checkSize(){
            if(realSize == myArray.length){
                myArray = Arrays.copyOf(myArray, myArray.length + 16);
            }
        }

        public int size(){
            return realSize;
        }

        public Integer remove(int index){
            if(index >= myArray.length){
                return null;
            }
            Integer removedElement = myArray[index];
            myArray[index] = null;
            return removedElement;
        }

        public Integer set(int index, Integer element){
            return element;
        }

        public int indexOf(Integer element){
            for(int i=0; i<myArray.length; i++){
                if(myArray[i].equals(element)){
                    return i;
                }
            }
            return -1;
        }

        public boolean contains(Integer element){
            for(int i=0; i<myArray.length; i++){
                if(myArray[i].equals(element)){
                    return true;
                }
            }
            return false;
        }

        public boolean equals(MyArrayList arrayList){
            if(size() != arrayList.size()) {
                return false;
            }
            for(int i=0; i<arrayList.size(); i++){
                if(!get(i).equals(arrayList.get(i))) {
                   return false;
                }
            }
            return true;
        }

        public String toString(){
            return "{1-2-3}";
        }

        public void clear(){
            myArray = new Integer[16];
            realSize = 16;
        }
    }


    @Unit
    private static Class unitClass;
    private Object instance;

    @Before
    public void setUp() {
        super.setUp();
    }

    @After
    public void tearDown() {
        super.tearDown();
    }

    @Test(timeout = 1000)
    public void testMethodsExists() {
        try {
            instance = instanciate(unitClass);
            Method methodAdd = unitClass.getMethod("add", Integer.class);
            Class returnType = methodAdd.getReturnType();
            if(returnType != boolean.class) {
                fail("Метод boolean add(Integer) должен возвращать логическое значение");
            }
            Method methodAddByIndex = unitClass.getMethod("add", int.class, Integer.class);
            returnType = methodAddByIndex.getReturnType();
            if(returnType != boolean.class) {
                fail("Метод boolean add(int index, Integer) должен возвращать логическое значение");
            }
            Method methodRemove = unitClass.getMethod("remove", int.class);
            returnType = methodRemove.getReturnType();
            if(returnType != Integer.class){
                fail("Метод Integer remove(int index) должен возвращать элемент, который был удален");
            }
            Method methodSet = unitClass.getMethod("set", int.class, Integer.class);
            returnType = methodSet.getReturnType();
            if(returnType != Integer.class){
                fail("Метод Integer set(int index, Integer) должен возвращать элемент, который ранее был в указанной позиции");
            }
            Method methodIndexOf = unitClass.getMethod("indexOf", Integer.class);
            returnType = methodIndexOf.getReturnType();
            if(returnType != int.class){
                fail("Метод int indexOf(Integer) должен возвращать индекс элемента, или -1, если список не содержит этот индекс");
            }
            Method methodContains = unitClass.getMethod("contains", Integer.class);
            returnType = methodContains.getReturnType();
            if(returnType != boolean.class) {
                fail("Метод boolean contains(Integer) должен возвращать логическое значение");
            }
            Method methodSize = unitClass.getMethod("size");
            returnType = methodSize.getReturnType();
            if(returnType != int.class){
                fail("Метод int size() должен возвращать целое число (размер списка)");
            }
            Method methodClear = unitClass.getMethod("clear");
            returnType = methodClear.getReturnType();
            if(returnType != void.class){
                fail("Метод void clear() ничего не должен возвращать");
            }
            Method methodEquals = unitClass.getMethod("equals", Object.class);
            returnType = methodEquals.getReturnType();
            if(returnType != boolean.class) {
                fail("Метод boolean equals(MyArrayList) должен возвращать логическое значение");
            }
            Method methodGet = unitClass.getMethod("get", int.class);
            returnType = methodGet.getReturnType();
            if(returnType != Integer.class){
                fail("Метод Integer get(int index) throws IndexOutOfBoundsException должен возвращать целое число");
            }
        } catch (NoSuchMethodException e) {
            fail("Метод " + e.getMessage() + " отсутствует");
        }
    }

    private void invokeAdd(Integer expectedResult){
        Method add = null;
        try {
            add = unitClass.getMethod("add", Integer.class);
        } catch (NoSuchMethodException e) {
            fail("Метод add отсутствует");
        }
        try {
            add.invoke(instance, expectedResult);
        } catch (Exception e) {
            fail("Ошибка тестирования! Невозможно вызвать метод add");
        }
    }
    @Test(timeout = 1000)
    public void testAdd() {
        instance = instanciate(unitClass);
        Integer expectedResult = 12;
        invokeAdd(expectedResult);
        Integer actualResult = null;
        try {
            actualResult = getElementFromList();
        } catch (Exception e) {
            fail("Ошибка тестирования! Невозможно получить добавленный элемент");
        }
        assertEquals("В метод add передано "+expectedResult+", но в списке находится "+actualResult, expectedResult,actualResult);
    }

    private Integer getElementFromList() throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        List<Field> array = new ArrayList<Field>();
        Field[] fields = instance.getClass().getDeclaredFields();
           for (Field field : fields){
              if(field.getType() == Integer[].class){
                  field.setAccessible(true);
                  array.add(field);
              }
           }
        if(array.size() == 1){
            try {
                Integer[] elements = (Integer[])array.get(0).get(instance);
                if(elements.length > 0) {
                    return elements[0];
                }
            } catch (IllegalAccessException e) {
                fail("Ошибка тестирования!, массив с элементами недоступен");
            }
        } else if(!array.isEmpty()) {
             return invokeGet();
        }
        fail("Список должен содержать массив типа Integer");
        return null;
    }

    public Integer invokeGet() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method get = unitClass.getMethod("get", int.class);
        return (Integer)get.invoke(instance, 0);
    }

    @Test(timeout = 1000)
    public void testGet() {
        instance = instanciate(unitClass);
        Integer expectedResult = 21;
        invokeAdd(expectedResult);
        Integer actualResult = null;
        try {
            actualResult = invokeGet();
        } catch (NoSuchMethodException e) {
            fail("Метод get отсутствует");
        } catch (Exception e){
            fail("Невозможно вызвать метод get");
        }
        assertTrue("Метод get должен возвращать значение типа Integer", actualResult.getClass() == Integer.class);
        assertEquals("Возвращаемое значение метода get должно быть "+expectedResult+", но получено "+actualResult,
                expectedResult,actualResult);
    }
}
