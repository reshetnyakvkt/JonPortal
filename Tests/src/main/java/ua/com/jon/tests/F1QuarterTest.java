package ua.com.jon.tests;

import com.jon.tron.service.junit.Unit;
import com.jon.tron.service.junit.UnitClass;
import com.jon.tron.service.junit.UnitCode;
import com.jon.tron.service.junit.UnitName;
import com.jon.tron.service.reflect.ReflectionUtil;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
Пользователь вводит координаты точки, определить в какой она находится четверти (декартова система координат)
Если точка находится между четвертями, то выводить 0
Пример:
-2 2
2
 * Created with IntelliJ IDEA.
 * User: al1
 * Date: 31.05.14
 */
@Unit(testName = "DigitsAvg", value = "weekend1.task1")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(Parameterized.class)
public class F1QuarterTest extends BaseTest {
    public static void main(String[] args) {
        java.util.Scanner scan = new java.util.Scanner(System.in);
        int x = scan.nextInt();
        int y = scan.nextInt();
        if (x == 0 || y == 0) {
            System.out.println(0);
        } else if (x > 0 && y > 0) {
            System.out.println(1);
        } else if (x < 0 && y > 0) {
            System.out.println(2);
        } else if (x < 0 && y < 0) {
            System.out.println(3);
        } else {
            System.out.println(4);
        }
    }

    private int xFrom;
    private int yFrom;
    private int xTo;
    private int yTo;
    private int quarterNumber;

    public F1QuarterTest(int xFrom, int xTo, int yFrom, int yTo, int quarterNumber) {
        this.xFrom = xFrom;
        this.xTo = xTo;
        this.yFrom = yFrom;
        this.yTo = yTo;
        this.quarterNumber = quarterNumber;
    }

    @Parameterized.Parameters
    public static List<Object[]> isEmptyData() {
        return Arrays.asList(new Object[][]{
                {0, 10, 0, 10, 1},
                {-10, 0, 0, 10, 2},
                {-10, 0, -10, 0, 3},
                {0, 10, -10, 0, 4},
                {0, 0, 0, 10, 0},
        });
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
        validateCode(codes.get(UNIT_NAME));
        instance = instanciate(unitClass);
        ReflectionUtil.checkMainMethod(unitClass);
    }

    @Test(timeout = 1000)
    public void testFirstQuarter() throws Throwable {

        if (instance == null) {
            fail();
        }
        int x = generateNumber(xFrom, xTo);
        int y = generateNumber(yFrom, yTo);
        getOut().println(x);
        getOut().println(y);
        int expectedRes = quarterNumber;//calcQuarter(x, y);

        ReflectionUtil.invokeMain(instance);
        String actualString = getIn().toString().trim();
        double actualRes = 0.0;
        try {
            actualRes = Double.parseDouble(actualString);
        } catch (NumberFormatException e) {
            fail("\n--- Проверка корректности результата ---\nРезультатом должно быть числом, а не " + actualString);
        }

        assertTrue("В задании должен выполняться вывод текста " + actualString, !actualString.isEmpty());
        assertTrue("\n--- Проверка корректности результата---\nПри введенных x=" + x + " и y= "+ y + ", четверть должна быть [" + expectedRes + "], а не [" + actualString + "]",
                expectedRes == actualRes);
    }

    private int calcQuarter(int x, int y) {
        if (x > 0 && y > 0) {
            return 1;
        } else if (x < 0 && y > 0) {
            return 2;
        } else if (x < 0 && y < 0) {
            return 3;
        } else {
            return 4;
        }
    }

    private int generateNumber(int from, int to) {
        int range = to - from;
        return (int)(Math.random() * range + from);
    }
}
