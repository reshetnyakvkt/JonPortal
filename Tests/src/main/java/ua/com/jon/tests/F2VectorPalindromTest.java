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

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
Написать метод определяющий, является ли массив палиндромом (одинаково читается и справа и слева)
Пирмер палиндрома: 1234321
Имя метода: isVectorPalindrom

 * Created with IntelliJ IDEA.
 * User: al1
 * Date: 07.06.14
 */
@Unit(testName = "F2VectorPalindromTest", value = "weekend1.task1")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class F2VectorPalindromTest extends BaseTest {
    public boolean isVectorPalindrom(int[] vector) {
        boolean res = true;
        for (int i = 0, j = vector.length - 1; i < vector.length / 2; i++, j--) {
            if (vector[i] != vector[j]) {
                res = false;
            }
        }
        return res;
    }

    private static final String UNIT_NAME = "VectorRandomFill";
    private static final String UNIT_METHOD_NAME = "isVectorPalindrom";

    @UnitCode
    private static Map<String, String> codes;
    @UnitClass
    private static Class[] unitClasses;
    @UnitName
    private static String[] unitNames;
    @Unit
    private static String unitJarClasspath;

    private static Object instance;
    private static Class unitClass;
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
    public void testCheckUnitMethod() throws Throwable {
        if (unitClasses.length != 1) {
            unitClass = getUnitClass(unitClasses, UNIT_NAME);
            assertNotNull("В задании не найден класс " + UNIT_NAME, unitClass);
        } else {
            unitClass = unitClasses[0];
        }
//        assertTrue("В задании не найден класс " + UNIT_NAME, UNIT_NAME.equals(unitClass.getSimpleName()));
        assertTrue("В задании должен быть только один класс", codes.size() == 1);
        validateCode(codes.entrySet().iterator().next().getValue());
        instance = instanciate(unitClass);
//        ReflectionUtil.checkMainMethod(unitClass);
        unitMethod = ReflectionUtil.checkMethod(unitClass, UNIT_METHOD_NAME, boolean.class,
                new MethodModifier[]{MethodModifier.PUBLIC}, int[].class);
    }


    @Test(timeout = 1000)
    public void testNonPalindom() throws Throwable {
        if (instance == null || unitMethod == null) {
            fail();
        }
        int[] actualVector = generateVector(0, 10);
        boolean actualResult = (Boolean)ReflectionUtil.invokeMethod(instance, unitMethod, actualVector);

        assertFalse("Переданный в метод массив не является палиндромом, но метод вернул " + actualResult, actualResult);
    }

    @Test(timeout = 1000)
    public void testPairPalindom() throws Throwable {
        if (instance == null || unitMethod == null) {
            fail();
        }
        int[] actualVector = generatePairPalindrom(0, 10);
        boolean actualResult = (Boolean)ReflectionUtil.invokeMethod(instance, unitMethod, actualVector);

        assertTrue("Переданный в метод массив "+Arrays.toString(actualVector)+" является палиндромом, но метод вернул " +
                actualResult, actualResult);
    }

    @Test(timeout = 1000)
    public void testNonPairPalindom() throws Throwable {
        if (instance == null || unitMethod == null) {
            fail();
        }
        int[] actualVector = generateNonPairPalindrom(0, 10);
        boolean actualResult = (Boolean)ReflectionUtil.invokeMethod(instance, unitMethod, actualVector);

        assertTrue("Переданный в метод массив " + Arrays.toString(actualVector) + " является палиндромом, но метод вернул " +
                actualResult, actualResult);
    }

    private int[] generateVector(int from, int to) {
        int length = (int)(Math.random() * 10 + 10);
        int[] vector = new int[length];
        int range = to - from;
        for (int i = 0; i < vector.length; i++) {
            vector[i] = (int)(Math.random() * range + from);
        }
        return vector;
    }

    private int[] generatePairPalindrom(int from, int to) {
        int length = (int)(Math.random() * 10 + 10) / 2;
        int[] vector = new int[length];
        int range = to - from;
        for (int i = 0, j = vector.length - 1; i < vector.length / 2; i++, j--) {
            int rnd = (int) (Math.random() * range + from);
            vector[i] = rnd;
            vector[j] = rnd;
        }
        return vector;
    }

    private int[] generateNonPairPalindrom(int from, int to) {
        int length = (int)(Math.random() * 10 + 10) / 2 + 1;
        int[] vector = new int[length];
        int range = to - from;
        for (int i = 0, j = vector.length - 1; i < vector.length / 2; i++, j--) {
            int rnd = (int) (Math.random() * range + from);
            vector[i] = rnd;
            vector[j] = rnd;
        }
        return vector;
    }
}