package ua.com.jon.tests;

import com.jon.tron.service.junit.Unit;
import com.jon.tron.service.junit.UnitClass;
import com.jon.tron.service.junit.UnitCode;
import com.jon.tron.service.junit.UnitName;
import com.jon.tron.service.reflect.MethodModifier;
import com.jon.tron.service.reflect.ReflectionUtil;
import org.junit.*;
import org.junit.runners.MethodSorters;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.*;

/**
 Написать метод заполняющий массив случайными числами в диапазоне от 0 до 10
 Имя метода: fillVectorByRandom

 * Created with IntelliJ IDEA.
 * User: al1
 * Date: 06.06.14
 */
@Unit(testName = "F2VectorRandomFillTest", value = "weekend1.task1")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class F2VectorRandomFillTest extends BaseTest {
    public void fillVectorByRandom(int[] vector) {
        for (int i = 0; i < vector.length; i++) {
            vector[i] = (int)(Math.random() * 10);
        }
    }

    private static final String UNIT_NAME = "VectorRandomFill";
    private static final String UNIT_METHOD_NAME = "fillVectorByRandom";

    @UnitCode
    private static Map<String, String> codes;
    @UnitClass
    private static Class[] unitClasses;
    @UnitName
    private static String[] unitNames;
    @Unit
    private static String unitJarClasspath;

    private static Object instance;
    private static Method unitMethod;

    @Before
    public void setUp() {
        super.setUp();
    }

    @After
    public void tearDown() {
        super.tearDown();
    }

    @Test(timeout = 1000)
    public void testCheckMainMethod() throws Throwable {
        assertTrue("В задании должен быть только один класс", unitClasses.length == 1);
        validateCode(codes.entrySet().iterator().next().getValue());
        instance = instanciate(unitClasses[0]);
        unitMethod = ReflectionUtil.checkMethod(unitClasses[0], UNIT_METHOD_NAME, boolean.class,
                new MethodModifier[]{MethodModifier.PUBLIC}, int[].class);
    }


    @Test(timeout = 1000)
    public void testSuccess() throws Throwable {
        if (instance == null || unitMethod == null) {
            fail();
        }
        int[] actualVector = generateVector();
        ReflectionUtil.invokeMethod(instance, unitMethod, actualVector);

        boolean isAllZero = true;
        boolean isInRange = true;

        for (int elem : actualVector) {
            if (elem != 0) {
                isAllZero = false;
            }
            if (elem < 0 || elem > 10) {
                isInRange = false;
            }
        }

        assertFalse("Массив должен быть заполнен случайными значениями, а не быть пустым ", isAllZero);
        assertTrue("Значения в массиве должны быть в диапазоне [0-10], а массив " + Arrays.toString(actualVector), isInRange);

        int[] secondVector = generateVector();
        ReflectionUtil.invokeMethod(instance, unitMethod, secondVector);

        assertTrue("При разных вызовах метода, массив заполняется одинаково " + Arrays.toString(actualVector), !Arrays.equals(actualVector, secondVector));
    }

    private int[] generateVector() {
        int length = (int)(Math.random() * 10 + 10);
        int[] vector = new int[length];
/*        int range = to - from;
        for (int i = 0; i < vector.length; i++) {
            vector[i] = (int)(Math.random() * range + from);
        }*/
        return vector;
    }

}
