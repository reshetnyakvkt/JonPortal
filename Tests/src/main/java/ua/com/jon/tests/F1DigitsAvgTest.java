package ua.com.jon.tests;

import com.jon.tron.service.junit.Unit;
import com.jon.tron.service.junit.UnitClass;
import com.jon.tron.service.junit.UnitCode;
import com.jon.tron.service.junit.UnitName;
import com.jon.tron.service.reflect.ReflectionUtil;
import org.junit.*;
import org.junit.runners.MethodSorters;

import java.util.Map;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
Пользователь вводит семизначное число, вывести в консоль среднее арифметическое его цифр
Пример:
1600061
2
 * Created with IntelliJ IDEA.
 * User: al1
 * Date: 30.05.14
 */
@Unit(testName = "DigitsAvg", value = "weekend1.task1")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class F1DigitsAvgTest extends BaseTest {
    public static void main(String[] args) {
        java.util.Scanner scan = new java.util.Scanner(System.in);
        int number = scan.nextInt();
        double res = 0.0;
        int por = 1;
        while (number % por != 0) {
            res += number % por;
            number /= number;
        }
        System.out.println(res);
    }

    private static final String UNIT_NAME = "DigitsAvg";

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
        //assertTrue("В задании не найден класс " + UNIT_NAME, UNIT_NAME.equals(unitClass.getSimpleName()));
        assertTrue("В задании должен быть только один класс", codes.size() == 1);
        validateCode(codes.entrySet().iterator().next().getValue());
        instance = instanciate(unitClass);
        ReflectionUtil.checkMainMethod(unitClass);
    }

    @Test(timeout = 1000)
    public void testSuccess() throws Throwable {
        final int MAX_NUMBER = 10;
        final int DIGITS_NUMBER = 7;
        if (instance == null) {
            fail();
        }
        int res = generateNumber(MAX_NUMBER, DIGITS_NUMBER);
        getOut().println(res);
        int expectedRes = (int)calcAvgOfDigits(res);

        ReflectionUtil.invokeMain(instance);
        String actualString = getIn().toString().trim();
//        assertTrue("\n--- Проверка величины числа ---\nВместо [" + DIGITS_NUMBER + "] размер числа [" +
//                actualString.length() + "] разрядов", actualString.length() == DIGITS_NUMBER);
        double actualRes = 0.0;
        try {
            actualRes = Double.parseDouble(actualString);
        } catch (NumberFormatException e) {
            fail("\n--- Проверка корректности результата---\nРезультатом должно быть числом, а не " + actualString);
        }

        assertTrue("В задании должен выполняться вывод текста " + actualString, !actualString.isEmpty());
        assertTrue("\n--- Проверка корректности результата---\nПри введенном числе " + res + ", результат должен быть [" + expectedRes + "], а не [" + actualString + "]",
                expectedRes == actualRes);
    }

    private int generateNumber(int MAX_NUMBER, int DIGITS_NUMBER) {
        int por = 1;
        int res = 0;
        for (int i = 0; i < DIGITS_NUMBER; i++) {
            res += por * rnd.nextInt(MAX_NUMBER);
            por *= 10;
        }
        return res;
    }

    private double calcAvgOfDigits (int number) {
        double res = 0.0;
        int por = 1;
        int digitCount = 0;
        while (number / por != 0) {
            res += (number/por)%10;
            por *= 10;
            digitCount++;
        }
        return res/digitCount;
    }
}