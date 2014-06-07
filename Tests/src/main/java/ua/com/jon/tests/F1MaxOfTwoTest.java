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

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Created with IntelliJ IDEA.
 * User: al1
 * Date: 9/22/13
 */
@Unit(testName = "MaxOfTwo", value = "weekend1.task1")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class F1MaxOfTwoTest extends BaseTest {
    public static void main(String[] args) {
        java.util.Scanner scan = new java.util.Scanner(System.in);
        int first = scan.nextInt();
        int second = scan.nextInt();
        if (first > second) {
            System.out.println(first);
        } else {
            System.out.println(second);
        }
    }

    private static final String UNIT_NAME = "MaxOfTwo";

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
        assertTrue("В задании должен быть только один класс", codes.size() == 1);
        validateCode(codes.entrySet().iterator().next().getValue());
        instance = instanciate(unitClass);
        ReflectionUtil.checkMainMethod(unitClass);
    }

    @Test(timeout = 1000)
    public void testSuccess() throws Throwable {
        final int MAX_NUMBER = 10;
        if (instance == null) {
            fail();
        }
        int firstNumber = rnd.nextInt(MAX_NUMBER);
        int secondNumber = rnd.nextInt(MAX_NUMBER);
        int maxOfTwo = firstNumber > secondNumber ? firstNumber : secondNumber;
        getOut().println(firstNumber);
        getOut().println(secondNumber);

        ReflectionUtil.invokeMain(instance);
        String expectedString = String.valueOf(maxOfTwo);
        String actualString = getIn().toString().trim();
        assertTrue("В задании должен выполняться вывод текста " + actualString, !actualString.isEmpty());
        assertTrue("Ожидается другой вывод\nвместо [" + actualString + " должно быть [" + expectedString,
                expectedString.equals(actualString));

    }
}
