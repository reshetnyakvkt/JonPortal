package ua.com.jon.tests;

import com.jon.tron.service.junit.Unit;
import com.jon.tron.service.reflect.ReflectionUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.lang.reflect.Method;
import java.util.Random;

import static org.junit.Assert.*;

/**
 * Created with IntelliJ IDEA.
 * User: Julia
 * Date: 01.12.13
 * Time: 19:57
 * To change this template use File | Settings | File Templates.
 */
@Unit(testName = "MaxHalf", value = "weekend1.task1")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MaxHalfTest extends BaseTest {

    private void maxHalf(int[] vector) {
        int firstSum = 0;
        int secondSum = 0;
        for (int i = 0; i < vector.length / 2; i++) {
            firstSum += vector[i];
            secondSum += vector[vector.length - 1 - i];
        }
        if (firstSum > secondSum) {
            for (int i = 0; i < vector.length / 2; i++) {
                System.out.print(vector[i] + " ");
            }
        } else {
            for (int i = 0; i < vector.length / 2; i++) {
                System.out.print(vector[vector.length - 1 - i] + " ");
            }
        }
    }

    private Random rnd = new Random();

    @Unit
    private static Class unitClass = MaxHalfTest.class;
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
    public void test1Success() throws Throwable {
        final String signature = "void maxHalf(int[] vector)";
        instance = instanciate(unitClass);
        Method methodSort = ReflectionUtil.getMethod(unitClass, "maxHalf", int[].class);
        assertNotNull("В классе отсутствует метод " + signature, methodSort);

        Class returnType = methodSort.getReturnType();
        if (returnType != void.class) {
            fail("Метод  не должен иметь возвращаемого значения");
        }

        int[] originalVector = generateVector(10, 10);
        String actualVector = maxHalfCheck(originalVector.clone());

        try {
            ReflectionUtil.invokeMethod(instance, "maxHalf", int[].class, originalVector.clone());
            String expectedVector = getIn().toString();
            expectedVector.trim();

            assertEquals("Ожидается " + expectedVector + "но выведено " + actualVector, expectedVector, actualVector);

        } catch (Throwable throwable) {
            fail("Было выброшено исключение " + throwable.getClass().getName() + ": " + throwable.getMessage() + " при вызове метода " + signature);
        }
        assertNotNull("Метод " + signature + " не должен возвращать null");
    }

    private int[] generateVector(int elementCount, int maxRandom) {
        int[] vector = new int[elementCount];
        for (int i = 0; i < vector.length; i++) {
            vector[i] = rnd.nextInt(maxRandom);
        }
        return vector;
    }

    private String maxHalfCheck(int[] vector) {
        int firstSum = 0;
        int secondSum = 0;
        StringBuilder resultMaxHalf = new StringBuilder();
        for (int i = 0; i < vector.length / 2; i++) {
            firstSum += vector[i];
            secondSum += vector[vector.length - 1 - i];
        }
        if (firstSum > secondSum) {
            for (int i = 0; i < vector.length / 2; i++) {
                resultMaxHalf.append(vector[i]).append(" ");
            }
        } else {
            for (int i = 0; i < vector.length / 2; i++) {
                resultMaxHalf.append(vector[vector.length - 1 - i]).append(" ");
            }
        }
        return resultMaxHalf.toString();
    }

}
