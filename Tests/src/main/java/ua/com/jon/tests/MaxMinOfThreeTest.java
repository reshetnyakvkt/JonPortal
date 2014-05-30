package ua.com.jon.tests;

import com.jon.tron.service.junit.Unit;

import com.jon.tron.service.junit.UnitClass;
import com.jon.tron.service.junit.UnitCode;
import com.jon.tron.service.junit.UnitName;
import com.jon.tron.service.reflect.ReflectionUtil;
import org.junit.*;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.Map;
import java.util.Scanner;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Created with IntelliJ IDEA.
 * User: al1
 * Date: 9/24/13
 */
@Unit(testName = "MaxMinOfThree", value = "weekend1.task1")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MaxMinOfThreeTest extends BaseTest {
    public static void main(String[] args) {
        java.util.Scanner scan = new java.util.Scanner(System.in);
        int first = scan.nextInt();
        int second = scan.nextInt();
        int third = scan.nextInt();
        if(first > second) {
            if(first > third) {
                System.out.print(first);
            } else {
                System.out.print(third);
            }
        } else {
            if(second > third) {
                System.out.print(second);
            } else {
                System.out.print(third);
            }
        }
        System.out.print(" ");
        if(first > second) {
            if(second < third) {
                System.out.print(first);
            } else {
                System.out.print(third);
            }
        } else {
            if(first < third) {
                System.out.print(first);
            } else {
                System.out.print(third);
            }
        }
    }

    private static final String UNIT_NAME = "MaxMinOfThree";

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

    @BeforeClass
    public static void before() {
        instance = null;
        unitClass = null;
    }

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

        if(unitClasses.length != 1) {
            unitClass = getUnitClass(unitClasses, UNIT_NAME);
            assertNotNull("В задании не найден класс " + UNIT_NAME, unitClass);
        } else {
            unitClass = unitClasses[0];
        }
//        assertTrue("В задании не найден класс " + UNIT_NAME, UNIT_NAME.equals(unitClass.getSimpleName()));
        validateCode(codes.get(UNIT_NAME));
        instance = instanciate(unitClass);
        ReflectionUtil.checkMainMethod(unitClass);
    }

    @Test(timeout = 1000)
    public void testSuccess() throws Throwable {
        final int MAX_NUMBER = 10;
        if (instance == null) {
            fail();
        }
        int first = rnd.nextInt(MAX_NUMBER);
        int second = rnd.nextInt(MAX_NUMBER);
        int third = rnd.nextInt(MAX_NUMBER);

        int expectedMax = maxOfThree(first, second, third);
        int expectedMin = minOfThree(first, second, third);

        getOut().println(first);
        getOut().println(second);
        getOut().println(third);

        ReflectionUtil.invokeMain(instance);

        String actualMaxString = getIn().toString().trim();
        assertTrue("В задании должен выполняться вывод текста " + actualMaxString, !actualMaxString.isEmpty());
        int actualMax = 0;
        int actualMin = 0;
        String[] numbers = actualMaxString.split(" ");
        assertTrue("Должно быть выведено два значения через пробел, а не [" + actualMaxString + "]", numbers.length == 2);

        Scanner scan = new Scanner(actualMaxString);
        if(scan.hasNextInt()) {
            actualMax = scan.nextInt();
        } else {
            fail("\n--- Проверка наибольшего значения ---\nПервым должно быть выведено наибольшее число, но выведено [" + (scan.hasNext()?scan.next():"") + "]");
        }
        if(scan.hasNextInt()) {
            actualMin = scan.nextInt();
        } else {
            fail("\n--- Проверка наименьшего значения ---\nВторым должно быть выведено наименьшее число, но выведено [" + (scan.hasNext()?scan.next():"") + "]");
        }

        assertTrue("\n--- Проверка наибольшего значения ---\nНе верное наибольшее значение " + expectedMax + ", должно быть " + actualMax,
                expectedMax == actualMax);

        assertTrue("\n--- Проверка наименьшего значения ---\nНе верное наименьшее значение " + expectedMin + ", должно быть " + actualMin,
                expectedMin == actualMin);
    }

    private int minOfThree(int first, int second, int third) {
        if(first < second) {
            if(first < third) {
                return first;
            } else {
                return third;
            }
        } else {
            if(second < third) {
                return second;
            } else {
                return third;
            }
        }
    }

    private int maxOfThree(int first, int second, int third) {
        if(first > second) {
            if(first > third) {
                return first;
            } else {
                return third;
            }
        } else {
            if(second > third) {
               return second;
            } else {
                return third;
            }
        }
    }
}
