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
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Пользователь вводит коэффициенты квадратного уравнения
 * Вывести на экран корни уравнения
 * Если первый коэффициент равен нулю, то вывести "Первый коэффициент не может быть 0"
 * Если уравнение не имеет корней, то вывести "Нет действитльных решений уравнения"
 * Если уравнение имеет два решения, то вывести их в строку через пробел
 * Пример:
 * 1
 * 3
 * -4
 * 1 -4
 * Created with IntelliJ IDEA.
 * User: al1
 * Date: 27.05.14
 */
@Unit(testName = "QuadraticEquationTest", value = "checked.HelloWorld")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class QuadraticEquationTest extends BaseTest {
    public static void main(String[] args) {
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        int coefA = scanner.nextInt();
        int coefB = scanner.nextInt();
        int coefC = scanner.nextInt();
        if (coefA == 0) {
            System.out.println("Первый коэффициент не может быть 0");
            return;
        }
        double desc = coefB * coefB - 4 * coefA * coefC;
        if (desc < 0) {
            System.out.println("Нет действитльных решений уравнения");
        } else if (desc > 0) {
            double res1 = -coefB + Math.sqrt(desc) / 2 * coefA;
            double res2 = -coefB - Math.sqrt(desc) / 2 * coefA;
            System.out.println(res1 + " " + res2);
        } else {
            double res = -coefB / 2 * coefA;
            System.out.println(res);
        }
        System.out.println();
    }

    private String[][] coefficients =
            {{"1", "3", "-4", "1", "-4"},
                    {"2", "-6", "0", "3", "0"},
//                    {"-2", "-1", "1", "-1", "0.5"},
                    {"1", "-4", "0", "4", "0"},
                    {"-1", "1", "2", "-1", "2"}};
    private static final String UNIT_NAME = "QuadraticEquation";

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
        validateCode(codes.get(UNIT_NAME));
        instance = instanciate(unitClass);
        ReflectionUtil.checkMainMethod(unitClass);
    }

    @Test(timeout = 1000)
    public void testNoSolutions() throws Throwable {
        if (instance == null) {
            fail();
        }
        int coefA = -3;
        int coefB = 6;
        int coefC = -18;
        String expectedResult = "Нет действительных решений уравнения";
        getOut().println(coefA);
        getOut().println(coefB);
        getOut().println(coefC);

        ReflectionUtil.invokeMain(instance);
        String actualString = getIn().toString();
        if (actualString != null) {
            actualString = actualString.trim();
            assertTrue("В задании должен выполняться вывод текста " + actualString, !actualString.isEmpty());
        }
        assertTrue("\n--- Проверка отсутствия решений ---\nвместо [" + actualString + "], при коэффициентах " + coefA + " " + coefB + " " + coefC +
                ", приложение должно выводить [" + expectedResult + "]", expectedResult.equals(actualString));
    }

    @Test(timeout = 1000)
    public void testCoefAZero() throws Throwable {
        if (instance == null) {
            fail();
        }
        int coefA = 0;
        int coefB = 0;
        int coefC = 0;
        String expectedResult = "Первый коэффициент не может быть 0";
        getOut().println(coefA);
        getOut().println(coefB);
        getOut().println(coefC);

        ReflectionUtil.invokeMain(instance);
        String actualString = getIn().toString();
        if (actualString != null) {
            actualString = actualString.trim();
            assertTrue("В задании должен выполняться вывод текста " + actualString, !actualString.isEmpty());
        }
        assertTrue("\n--- Проверка нулевого коэффициента ---\nвместо [" + actualString + "], при нулевых коэффициэнтах должно быть выведено [" + expectedResult + "]", expectedResult.equals(actualString));
    }

    @Test(timeout = 1000)
    public void testOneRoot() throws Throwable {
        if (instance == null) {
            fail();
        }
        int coefA = 1;
        int coefB = -6;
        int coefC = 9;
        String expectedResult = "1";
        getOut().println(coefA);
        getOut().println(coefB);
        getOut().println(coefC);

        ReflectionUtil.invokeMain(instance);
        String actualString = getIn().toString();
        if (actualString != null) {
            actualString = actualString.trim();
            assertTrue("В задании должен выполняться вывод текста " + actualString, !actualString.isEmpty());
        }
        int res1 = 3;
        double actRes1 = 0.0;
        try {
            actRes1 = Double.parseDouble(actualString);
        } catch (NumberFormatException e) {
            fail("Корнем уравнения должно быть число а не " + actualString);
        }
        assertTrue("\n--- Проверка решения с одним корнем ---\nвместо [" + actualString + "], при коэффициентах " + coefA + " " + coefB + " " + coefC +
                ", приложение должно выводить [" + expectedResult + "]", res1 == actRes1);

    }

    @Test(timeout = 1000)
    public void testSuccess() throws Throwable {
        String[] coefs = coefficients[rnd.nextInt(coefficients.length)];
        if (instance == null) {
            fail();
        }
        int coefA = Integer.parseInt(coefs[0]);
        int coefB = Integer.parseInt(coefs[1]);
        int coefC = Integer.parseInt(coefs[2]);
        String expectedResult = coefs[3];
        getOut().println(coefA);
        getOut().println(coefB);
        getOut().println(coefC);

        ReflectionUtil.invokeMain(instance);
        String actualString = getIn().toString();
        String leedingStr = "\n--- Проверка решения с двумя корнями ---\n";
        if (actualString != null) {
            actualString = actualString.trim();
            assertTrue(leedingStr + "В задании должен выполняться вывод текста " + actualString, !actualString.isEmpty());
            int res1 = Integer.parseInt(expectedResult);
            int res2 = Integer.parseInt(coefs[4]);
            String[] strings = actualString.split(" ");
            assertTrue(leedingStr + "Решением уровнения должны быть два корня, при выводе разделенные пробелом, а не " + actualString,
                    strings.length <= 2);
            double actRes1 = 0.0;
            try {
                actRes1 = Double.parseDouble(strings[0]);
                assertTrue(leedingStr + "Первый корень должен быть " + res1 + ", а не " + actRes1, actRes1 == res1);
            } catch (NumberFormatException e) {
                fail(leedingStr + "Первый корень уровнения должен быть числом, а не " + strings[0]);
            }
            double actRes2 = 0.0;
            try {
                actRes2 = Double.parseDouble(strings[1]);
                assertTrue(leedingStr + "Второй корень должен быть " + res1 + ", а не " + actRes2, actRes2 == res2);
            } catch (NumberFormatException e) {
                fail(leedingStr + "Второй корень уровнения должен быть числом, а не " + strings[1]);
            }
//            assertTrue("\nОжидается другой вывод\nвместо [" + actualString + "], при коэффициентах " + coefA + " " + coefB + " " + coefC +
//                    ", корни должны быть " + res1 + " и " + res2, expectedResult.equals(actualString));
        }
    }
}
