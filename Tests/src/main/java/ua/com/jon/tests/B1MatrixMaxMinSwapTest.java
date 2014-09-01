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

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
Написать метод, меняющий местами наибольший и наимешьний элементы в матрице.
Если размер массива неверный, выдавать сообщение "Не верный размер массива".
public void swapMaxMin(int[][] matrix)
Пример;
1234
5678
9123

9234
5678
1123
*/
@Unit(testName = "B1MatrixMaxMinSwapTest", value = "checked.HelloWorld")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class B1MatrixMaxMinSwapTest  extends BaseTest {
    public static final String ILLEGAL_SIZE = "Не верный размер массива";
    public static void main(String[] args) {

        int[][] matrix = new int[][] {{1,2},
                {3,4}};
        swapMaxMin(matrix);
    }
    public static void swapMaxMin(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = (int)(Math.random() * 10);
            }
        }
    }

    private static final int MAX_SIZE = 9;
    private static final int MIN_SIZE = 1;

    private static final String UNIT_NAME = "VectorRandomFill";
    private static final String UNIT_METHOD_NAME = "swapMaxMin";

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
        unitMethod = ReflectionUtil.checkMethod(unitClasses[0], UNIT_METHOD_NAME, void.class,
                new MethodModifier[]{MethodModifier.PUBLIC}, int[][].class);
    }

    @Test(timeout = 1000)
    public void testSuccess() throws Throwable {
        if (instance == null || unitMethod == null) {
            fail();
        }
        int height = rnd.nextInt(MAX_SIZE) + MIN_SIZE;
        int width = rnd.nextInt(MAX_SIZE) + MIN_SIZE;

        int[][] actualMatrix = buildMatrix(height, width);

        int[][] clone = actualMatrix.clone();
        ReflectionUtil.invokeMethod(instance, UNIT_METHOD_NAME, int[][].class, void.class, clone);

        boolean isAllZero = true;

        for (int[] line : actualMatrix) {
            for (int elem : line) {
                if (elem != 0) {
                    isAllZero = false;
                }
                if (elem < 0 || elem > 9) {
                    fail("Значения в массиве должны быть в диапазоне [0-9], а элемент " + elem + " нет");
                }
            }
        }

        assertFalse("Массив должен быть заполнен случайными значениями, а не быть пустым ", isAllZero);

        super.setUp();
        getOut().println(height);
        getOut().println(width);
        ReflectionUtil.invokeMain(instance);
        String secondMatrixStr = getIn().toString();

//        assertTrue("При разных вызовах метода, массив заполняется одинаково " + actualMatrixStr, !actualMatrixStr.equals(secondMatrixStr));

        super.setUp();
        getOut().println(1);
        getOut().println(1);
        ReflectionUtil.invokeMain(instance);
        String singletonMatrixStr = getIn().toString().trim();
        try {
            int elem = Integer.parseInt(singletonMatrixStr);
            if (elem < 0 || elem > 9) {
                fail("Значения в массиве должны быть в диапазоне [0-9], а элемент " + elem + " нет");
            }
        } catch (Exception e) {
            fail("В массиве не число " + singletonMatrixStr);
        }
    }

    @Test(timeout = 1000)
    public void testIllegal() throws Throwable {
        if (instance == null || unitMethod == null) {
            fail();
        }
        int height = -1;
        int width = rnd.nextInt(MAX_SIZE + MIN_SIZE);

        getOut().println(height);
        getOut().println(width);

        ReflectionUtil.invokeMain(instance);
        String actualMatrixStr = getIn().toString().trim();

        assertTrue("При неправильом размере массива, необходимо выводить сообщение " + ILLEGAL_SIZE, ILLEGAL_SIZE.equals(actualMatrixStr));

        height = rnd.nextInt(MAX_SIZE + MIN_SIZE);
        width = -1;

        getOut().println(height);
        getOut().println(width);
        ReflectionUtil.invokeMain(instance);
        actualMatrixStr = getIn().toString().trim();

        assertTrue("При неправильом размере массива, необходимо выводить сообщение " + ILLEGAL_SIZE, ILLEGAL_SIZE.equals(actualMatrixStr));
    }

    private int[][] buildMatrix(int height, int width) throws IllegalArgumentException {
        int[][] matrix = new int[height][width];
        int range = 10 - 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = (int)(Math.random() * range + 0);
            }
        }
        return matrix;
    }
}
