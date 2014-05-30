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
 * Created with IntelliJ IDEA.
 * User: al1
 * Date: 9/22/13
 */
@Unit(testName = "SumOfTwo", value = "weekend1.task1")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SumOfTwoTest extends BaseTest {
    public static void main(String[] args) {
        java.util.Scanner scan = new java.util.Scanner(System.in);
        int first = scan.nextInt();
        int second = scan.nextInt();
        System.out.println(first + second);
    }
    private static final String UNIT_NAME = "SumOfTwo";

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
        validateCode(codes.get(UNIT_NAME));
        instance = instanciate(unitClass);
        ReflectionUtil.checkMainMethod(unitClass);
    }

    @Test(timeout = 1000)
    public void testSuccess() throws Throwable {
        instance = instanciate(unitClass);
        int first = rnd.nextInt(100);
        int second = rnd.nextInt(100);
        int expectedSum = first + second;
        getOut().println(first);
        getOut().println(second);

        ReflectionUtil.invokeMain(instance);

        String actualSumString = getIn().toString().trim();
        int actualSum = 0;
        try {
            actualSum = Integer.parseInt(actualSumString);
        } catch (NumberFormatException nfe) {
            fail("Результат должен быть числом, но выведено [" + actualSumString + "]");
        }

        assertTrue("Ожидаемый результат " + expectedSum + ", но выведено " + actualSum,
                expectedSum == actualSum);
    }
}
