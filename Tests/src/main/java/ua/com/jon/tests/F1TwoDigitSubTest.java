package ua.com.jon.tests;

import com.jon.tron.service.junit.Unit;
import com.jon.tron.service.junit.UnitClass;
import com.jon.tron.service.junit.UnitCode;
import com.jon.tron.service.junit.UnitName;
import com.jon.tron.service.reflect.ReflectionUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.Map;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Created with IntelliJ IDEA.
 * User: al1
 * Date: 31.05.14
 */
@Unit(testName = "TwoDigitSubTest", value = "weekend1.task1")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class F1TwoDigitSubTest extends BaseTest {
    public static void main(String[] args) {
        java.util.Scanner scan = new java.util.Scanner(System.in);
        int number = scan.nextInt();
        int firstDig = (number) % 10;
        int secondDig = (number / 10) % 10;
        System.out.println(firstDig + secondDig);
    }

    private static final String UNIT_NAME = "TwoDigitSub";

    @UnitCode
    private static Map<String, String> codes;
    @UnitClass
    private static Class[] unitClasses;
    @UnitName
    private static String[] unitNames;
    @Unit
    private static String unitJarClasspath;
    private static Class unitClass;
    private static Object instance;

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
        ReflectionUtil.checkMainMethod(unitClass);
    }

    @Test(timeout = 1000)
    public void testSuccess() throws Throwable {
        final String PREFIX = "\n--- Проверка корректности результата ---\n";

        instance = instanciate(unitClass);
        int twoDigits = generateNumber(10, 99);
        getOut().println(twoDigits);
        ReflectionUtil.invokeMain(instance);

        String actualString = getIn().toString().trim();
        int actualSum = 0;
        try {
            actualSum = Integer.parseInt(actualString);
        } catch (NumberFormatException e) {
            fail(PREFIX + "Должно быть выведено число, а не " + actualString);
        }

        int expectedSum = calcTwoDigitsSum(twoDigits);

        assertTrue(PREFIX + "Сумма цифр числ " + twoDigits + " должна быть равна " + expectedSum + " а не " + actualSum,
                expectedSum == actualSum);
    }

    private int calcTwoDigitsSum(int number) {
        int firstDig = (number) % 10;
        int secondDig = (number / 10) % 10;
        return firstDig + secondDig;
    }

    private int generateNumber(int from, int to) {
        int range = to - from;
        return (int)(Math.random() * range + from);
    }
}