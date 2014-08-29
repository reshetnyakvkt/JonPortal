package ua.com.jon.tests;

import com.jon.tron.service.junit.Unit;
import com.jon.tron.service.junit.UnitClass;
import com.jon.tron.service.junit.UnitCode;
import com.jon.tron.service.junit.UnitName;
import com.jon.tron.service.reflect.MethodModifier;
import com.jon.tron.service.reflect.ReflectionUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

import static junit.framework.Assert.fail;
import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 Написать собственную реализацию динамического массива MyArrayList,
 содержащего целые числа.
 Сделать параметризированную релизацию списка, параметр E.
 Реализовать в списке интерфейсы Iterable.
 Реализовать следующие методы
 - void add(E value)
 - int get(int index)
 - boolean set(int index, E value)
 - boolean add(int index, E value)
 - int indexOf(E value)
 - int size()
 - E remove(int index)
 - Iterator<E> iterator()
 */
@Unit(testName = "P0ArrayListGenericTest", value = "weekend1.task1")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class P0ArrayListGenericTest extends BaseTest {
    private static final int MAX_VALUE = 10;
    private static final int MIN_VALUE = 1;

//    import java.util.Arrays;
//    import java.util.Iterator;
    public class MyArrayList<E> implements Iterable {
        private E[] myArray;
        private int realSize = 0;

        public MyArrayList(int size) {
            this.myArray = (E[]) new Object[size];
        }

        public MyArrayList() {
            this.myArray = (E[]) new Object[16];
        }

        public void add(E element) {
            checkSize();
            myArray[realSize++] = element;
//        realSize++;
        }

        public boolean add(int index, E element) {
            if ((index < 0) || (index > realSize)) {
                return false;
            } else {
                resize(index, index + 1);
                myArray[index] = element;
                realSize++;
            }

            return true;
        }

        public E get(int index) {
            if ((index < 0) || (index > realSize)) {
                throw new IndexOutOfBoundsException(String.valueOf(index));
            } else {
                return myArray[index];
            }
        }

        public void checkSize() {
            if (realSize == myArray.length) {
                myArray = Arrays.copyOf(myArray, myArray.length + 16);
            }
        }

        public void resize(int indexFrom, int indexTo) {
            if ((indexFrom < 0) || indexFrom > realSize - 1 || indexTo < 0 || indexTo > realSize - 1 ||
                    indexFrom > myArray.length - 1 || indexTo > myArray.length - 1) {
                return;
            } else {
                System.arraycopy(myArray, indexFrom, myArray, indexTo, myArray.length - Math.max(indexFrom, indexTo));
            }
        }

        public int size() {
            return realSize;
        }

        public E remove(int index) {
            if ((index < 0) || (index > realSize) && (index - 1 < 0) || (index - 1 > realSize)) {
                throw new IndexOutOfBoundsException(String.valueOf(index));
            }
            E removedElement = myArray[index];
            resize(index + 1, index);
            realSize--;
            return removedElement;
        }

        public boolean set(int index, E element) {
            if ((index < 0) || (index > realSize)) {
                return false;
                //throw new IndexOutOfBoundsException();
            } else {
                myArray[index] = element;
            }

            return true;
        }

        public int indexOf(E element) {
            for (int i = 0; i < myArray.length; i++) {
                if (myArray[i].equals(element)) {
                    return i;
                }
            }
            return -1;
        }

        public boolean contains(Integer element) {
            for (int i = 0; i < myArray.length; i++) {
                if (myArray[i] == element) {
                    return true;
                }
            }
            return false;
        }

        public boolean equals(MyArrayList arrayList) {
            if (size() != arrayList.size()) {
                return false;
            }
            for (int i = 0; i < arrayList.size(); i++) {
                if (get(i) != (arrayList.get(i))) {
                    return false;
                }
            }
            return true;
        }

        public String toString() {
            return Arrays.toString(myArray);
        }

        public void clear() {
            myArray = (E[]) new Object[16];
            realSize = 16;
        }

        @Override
        public Iterator iterator() {
            return new Iterator() {
                @Override
                public boolean hasNext() {
                    return false;
                }

                @Override
                public Object next() {
                    return null;
                }

                @Override
                public void remove() {

                }
            };
        }
    }


    private static final String UNIT_NAME = "MyArrayList";
    private static final String ADD_METHOD_NAME = "add";
    private static final String ADD2_METHOD_NAME = "add";
    private static final String GET_METHOD_NAME = "get";
    private static final String SET_METHOD_NAME = "set";
    private static final String INDEX_OF_METHOD_NAME = "indexOf";
    private static final String SIZE_METHOD_NAME = "size";
    private static final String REMOVE_METHOD_NAME = "remove";
    private static final String ITERATOR_METHOD_NAME = "iterator";

    @UnitCode
    private static Map<String, String> codes;
    @UnitClass
    private static Class[] unitClasses;
    @UnitName
    private static String[] unitNames;
    @Unit
    private static String unitJarClasspath;

    private static Object instance;
    private static Method addMethod;
    private static Method add2Method;
    private static Method getMethod;
    private static Method setMethod;
    private static Method indexOfMethod;
    private static Method sizeMethod;
    private static Method removeMethod;
    private static Method iteratorMethod;

    @Before
    public void setUp() {
        super.setUp();
    }

    @After
    public void tearDown() {
        super.tearDown();
    }

    @Test(timeout = 1100)
    public void test() throws Throwable {
        assertTrue("В задании должен не более 2х классов", unitClasses.length <= 2);
        validateCode(codes.entrySet().iterator().next().getValue());

        Class unitClass = unitClasses[0];
        assertNotNull("В задании не найден класс " + UNIT_NAME, unitClass);

        instance = instanciate(unitClass);
        ReflectionUtil.checkHasParent(unitClass, "Iterable");
        ReflectionUtil.checkHasGeneric(unitClass, "E");
        addMethod = ReflectionUtil.checkMethod(unitClass, ADD_METHOD_NAME, void.class,
                new MethodModifier[]{MethodModifier.PUBLIC}, Object.class);
        add2Method = ReflectionUtil.checkMethod(unitClass, ADD2_METHOD_NAME, boolean.class,
                new MethodModifier[]{MethodModifier.PUBLIC}, int.class, Object.class);
        getMethod = ReflectionUtil.checkMethod(unitClass, GET_METHOD_NAME, Object.class,
                new MethodModifier[]{MethodModifier.PUBLIC}, int.class);
        setMethod = ReflectionUtil.checkMethod(unitClass, SET_METHOD_NAME, boolean.class,
                new MethodModifier[]{MethodModifier.PUBLIC}, int.class, Object.class);
        indexOfMethod = ReflectionUtil.checkMethod(unitClass, INDEX_OF_METHOD_NAME, int.class,
                new MethodModifier[]{MethodModifier.PUBLIC}, Object.class);
        sizeMethod = ReflectionUtil.checkMethod(unitClass, SIZE_METHOD_NAME, int.class,
                new MethodModifier[]{MethodModifier.PUBLIC});
        removeMethod = ReflectionUtil.checkMethod(unitClass, REMOVE_METHOD_NAME, Object.class,
                new MethodModifier[]{MethodModifier.PUBLIC}, int.class);
        iteratorMethod = ReflectionUtil.checkMethod(unitClass, ITERATOR_METHOD_NAME, Iterator.class,
                new MethodModifier[]{MethodModifier.PUBLIC});
    }

    @Test(timeout = 1100)
    public void testAdd() throws Throwable {
        Class unitClass = unitClasses[0];
        instance = instanciate(unitClass);
        if (instance == null || addMethod == null) {
            fail();
        }
        Integer expectedElement = rnd.nextInt(MAX_VALUE) + MIN_VALUE;

        ReflectionUtil.invokeMethod(instance, addMethod, expectedElement);
        Integer actualElement = getElementFromList(0);
        assertTrue("Метод add работает не верно, после добавления первого элемента " + expectedElement + ", список содержит " +
                actualElement, expectedElement.equals(actualElement));

        expectedElement = rnd.nextInt(MAX_VALUE) + MIN_VALUE;
        ReflectionUtil.invokeMethod(instance, addMethod, expectedElement);
        actualElement = getElementFromList(1);
        assertTrue("Метод add работает не верно, после добавления второго элемента " + expectedElement + ", список содержит " +
                actualElement, expectedElement.equals(actualElement));
    }

    @Test(timeout = 1100)
    public void testAdd2() throws Throwable {
        Class unitClass = unitClasses[0];
        instance = instanciate(unitClass);
        if (instance == null || add2Method == null) {
            fail();
        }
        int expectedElement = rnd.nextInt(MAX_VALUE) + MIN_VALUE;
        ReflectionUtil.invokeMethod(instance, add2Method, 0, expectedElement);
        expectedElement = rnd.nextInt(MAX_VALUE) + MIN_VALUE;
        ReflectionUtil.invokeMethod(instance, add2Method, 0, expectedElement);
        int actualElement = getElementFromList(0);
        assertTrue("Метод add(int, int) работает не верно, после вставки элемента " + expectedElement + " в начало, список содержит " +
                actualElement, expectedElement == actualElement);

        expectedElement = rnd.nextInt(MAX_VALUE) + MIN_VALUE;
        ReflectionUtil.invokeMethod(instance, add2Method, 1, expectedElement);
        actualElement = getElementFromList(1);
        assertTrue("Метод add(int, int) работает не верно, после вставки второго элемента " + expectedElement + ", список содержит " +
                actualElement, expectedElement == actualElement);
    }

    private Integer getElementFromList(int index) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        List<Field> array = new ArrayList<Field>();
        Field[] fields = instance.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.getType() == Object[].class) {
                field.setAccessible(true);
                array.add(field);
            }
        }
        if (array.size() == 1) {
            try {
                Object[] elements = (Object[]) array.get(0).get(instance);
                if (elements.length > 0) {
                    return (Integer) elements[index];
                }
            } catch (IllegalAccessException e) {
                fail("Ошибка тестирования!, массив с элементами недоступен");
            }
        } else if (!array.isEmpty()) {
            return invokeGet();
        }
        fail("Список должен содержать массив типа E");
        return null;
    }

    public int invokeGet() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        return (Integer) getMethod.invoke(instance, 0);
    }

    @Test(timeout = 1100)
    public void testGet() throws Throwable {
        Class unitClass = unitClasses[0];
        instance = instanciate(unitClass);
        if (instance == null || getMethod == null) {
            fail();
        }
        int expectedElement = rnd.nextInt(MAX_VALUE) + MIN_VALUE;
        ReflectionUtil.invokeMethod(instance, addMethod, expectedElement);
        int actualElement = (Integer) ReflectionUtil.invokeMethod(instance, getMethod, 0);
        assertTrue("Метод get работает не верно, после добавления элемента " + expectedElement + ", список содержит " +
                actualElement, expectedElement == actualElement);

        expectedElement = rnd.nextInt(MAX_VALUE) + MIN_VALUE;
        ReflectionUtil.invokeMethod(instance, addMethod, expectedElement);
        actualElement = (Integer) ReflectionUtil.invokeMethod(instance, getMethod, 1);
        assertTrue("Метод get работает не верно, после добавления второго элемента " + expectedElement + ", список содержит " +
                actualElement, expectedElement == actualElement);
    }

    @Test(timeout = 1100)
    public void testSet() throws Throwable {
        Class unitClass = unitClasses[0];
        instance = instanciate(unitClass);
        if (instance == null || setMethod == null) {
            fail();
        }
        int expectedElement = rnd.nextInt(MAX_VALUE) + MIN_VALUE;
        ReflectionUtil.invokeMethod(instance, addMethod, expectedElement);
        expectedElement = rnd.nextInt(MAX_VALUE) + MIN_VALUE;
        ReflectionUtil.invokeMethod(instance, setMethod, 0, expectedElement);
        int actualElement = (Integer) ReflectionUtil.invokeMethod(instance, getMethod, 0);
        assertTrue("Метод set работает не верно, после замены первого элемента на " + expectedElement + ", список содержит " +
                actualElement, expectedElement == actualElement);

        expectedElement = rnd.nextInt(MAX_VALUE) + MIN_VALUE;
        ReflectionUtil.invokeMethod(instance, addMethod, expectedElement);
        expectedElement = rnd.nextInt(MAX_VALUE) + MIN_VALUE;
        ReflectionUtil.invokeMethod(instance, setMethod, 1, expectedElement);
        actualElement = (Integer) ReflectionUtil.invokeMethod(instance, getMethod, 1);
        assertTrue("Метод get работает не верно, после добавления второго элемента " + expectedElement + ", список содержит " +
                actualElement, expectedElement == actualElement);
    }

    @Test(timeout = 1100)
    public void testIndexOf() throws Throwable {
        Class unitClass = unitClasses[0];
        instance = instanciate(unitClass);
        if (instance == null || indexOfMethod == null) {
            fail();
        }
        int expectedElement = rnd.nextInt(MAX_VALUE) + MIN_VALUE;
        ReflectionUtil.invokeMethod(instance, addMethod, expectedElement);
        int actualIndex = (Integer) ReflectionUtil.invokeMethod(instance, indexOfMethod, expectedElement);
        assertTrue("Метод indexOf работает не верно, при поиске первого элемента " + expectedElement + ", индекс найденного элемента " +
                actualIndex, 0 == actualIndex);

        int newExpected = rnd.nextInt(MAX_VALUE) + MIN_VALUE;
        while (newExpected == expectedElement) {
            newExpected = rnd.nextInt(MAX_VALUE) + MIN_VALUE;
        }
        ReflectionUtil.invokeMethod(instance, addMethod, newExpected);
        actualIndex = (Integer) ReflectionUtil.invokeMethod(instance, indexOfMethod, newExpected);

        assertTrue("Метод indexOf работает не верно, при поиске второго элемента " + expectedElement + ", индекс найденного элемента " +
                actualIndex, 1 == actualIndex);
    }

    @Test(timeout = 1100)
    public void testSize() throws Throwable {
        Class unitClass = unitClasses[0];
        instance = instanciate(unitClass);
        if (instance == null || sizeMethod == null) {
            fail();
        }
        Integer expectedElement = rnd.nextInt(MAX_VALUE) + MIN_VALUE;
        ReflectionUtil.invokeMethod(instance, addMethod, expectedElement);
        Integer actualSize = (Integer) ReflectionUtil.invokeMethod(instance, sizeMethod);
        assertTrue("Метод size работает не верно, после дабавления одного элемента " + expectedElement + ", размер списка равен " +
                actualSize, 1 == actualSize);

        expectedElement = rnd.nextInt(MAX_VALUE) + MIN_VALUE;
        ReflectionUtil.invokeMethod(instance, addMethod, expectedElement);
        actualSize = (Integer) ReflectionUtil.invokeMethod(instance, sizeMethod);
        assertTrue("Метод size работает не верно, после дабавления второго элемента " + expectedElement + ", размер списка равен " +
                actualSize, 2 == actualSize);
    }

    @Test(timeout = 1100)
    public void testRemove() throws Throwable {
        Class unitClass = unitClasses[0];
        instance = instanciate(unitClass);
        if (instance == null || removeMethod == null) {
            fail();
        }
        int expectedElement = rnd.nextInt(MAX_VALUE) + MIN_VALUE;
        ReflectionUtil.invokeMethod(instance, addMethod, expectedElement);
        int removedElement = (Integer) ReflectionUtil.invokeMethod(instance, removeMethod, 0);
        int actualSize = (Integer) ReflectionUtil.invokeMethod(instance, sizeMethod);
        assertTrue("Метод remove работает не верно, при удалении первого элемента " + expectedElement + ", удаляемый элемент должен быть " +
                removedElement, expectedElement == removedElement);
        assertTrue("Метод remove работает не верно, после удалении первого элемента " + expectedElement + ", размер списка равен " +
                actualSize, 0 == actualSize);

        expectedElement = rnd.nextInt(MAX_VALUE) + MIN_VALUE;
        ReflectionUtil.invokeMethod(instance, addMethod, expectedElement);
        expectedElement = rnd.nextInt(MAX_VALUE) + MIN_VALUE;
        ReflectionUtil.invokeMethod(instance, addMethod, expectedElement);
        removedElement = (Integer) ReflectionUtil.invokeMethod(instance, removeMethod, 0);
        int actualElement = getElementFromList(0);
        actualSize = (Integer) ReflectionUtil.invokeMethod(instance, sizeMethod);
        assertTrue("Метод remove работает не верно, при удалении первого элемента из двух" + expectedElement + ", удаляемый элемент должен быть " +
                removedElement, expectedElement == actualElement);
        assertTrue("Метод remove работает не верно, после удалении первого элемента из двух" + expectedElement + ", размер списка равен " +
                actualSize, 1 == actualSize);
    }

    @Test(timeout = 1100)
    public void testIterator() throws Throwable {
        Class unitClass = unitClasses[0];
        instance = instanciate(unitClass);
        if (instance == null || removeMethod == null) {
            fail();
        }
        Object iterator = ReflectionUtil.invokeMethod(instance, iteratorMethod);
        if (!(iterator instanceof Iterator)) {
            fail("Метод itearator() должен возрващать значение типа Iterator");
        }
    }
}
