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
Пользователь вводит год, определить является ли он високосным
(год является високосным в двух случаях: либо он кратен 4, но при этом не кратен 100, либо кратен 400)
Пример:
2014
Невисокосный
 * Created with IntelliJ IDEA.
 * User: al1
 * Date: 31.05.14
 */
@Unit(testName = "DigitsAvg", value = "weekend1.task1")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(Parameterized.class)
public class LeapYearTest extends BaseTest {
    public static void main(String[] args) {
        java.util.Scanner scan = new java.util.Scanner(System.in);
        int year = scan.nextInt();
        if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) {
            System.out.println("Високосный");
        } else {
            System.out.println("Невисокосный");
        }
    }

    private int year;
    private String yearType;

    public LeapYearTest(int year, String yearType) {
        this.year = year;
        this.yearType = yearType;
    }

    @Parameterized.Parameters
    public static List<Object[]> isEmptyData() {
        return Arrays.asList(new Object[][]{
                {400, "Високосный"},
                {1600, "Високосный"},
                {2008, "Високосный"},
                {2016, "Високосный"},
                {1500, "Невисокосный"},
                {1800, "Невисокосный"},
                {2015, "Невисокосный"},
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
        getOut().println(year);
        String expectedRes = calcYearType(year);//yearType;

        ReflectionUtil.invokeMain(instance);
        String actualString = getIn().toString().trim();

        assertTrue("В задании должен выполняться вывод текста " + actualString, !actualString.isEmpty());
        assertTrue("\n--- Проверка корректности результата ---\nПри введенном году " + year + ", должно быть выведено [" + expectedRes + "], а не [" + actualString + "]",
                expectedRes.equals(actualString));
    }

    private String calcYearType(int year) {
        if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) {
            return "Високосный";
        } else {
            return "Невисокосный";
        }
    }
}
