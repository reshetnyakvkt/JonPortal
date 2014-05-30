package ua.com.jon.tests;

import com.jon.tron.service.junit.Unit;
import com.jon.tron.service.junit.UnitClass;
import com.jon.tron.service.junit.UnitCode;
import com.jon.tron.service.junit.UnitName;
import com.jon.tron.service.reflect.JavaProcessBuilder;
import com.jon.tron.service.reflect.ReflectionUtil;
import org.junit.*;
import org.junit.runners.MethodSorters;

import java.lang.reflect.Method;
import java.util.Map;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.fail;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created with IntelliJ IDEA.
 * User: Admin
 * Date: 19.09.13
 */
@Unit(testName = "Greeting", value = "session1.task3.Greeting")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class GreetingTest extends BaseTest {
    public static void main(String[] args) {
        java.util.Scanner scan = new java.util.Scanner(System.in);
        String name = scan.nextLine();
        System.out.println("Здравствуйте " + name);
    }

    private static final String UNIT_NAME = "Greeting";

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
    private String[] names = {"Oksana", "Vampu", "Sinaps", "Maika"};

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
        if (instance == null) {
            fail();
        }
        String name = names[rnd.nextInt(names.length)];
        getOut().print(name);
/*        if(unitNames.length > 1) {
            fail("В задании должен быть только один класс");
        }*/
        ReflectionUtil.invokeMain(instance);
        String expectedString = "Здравствуйте " + name;
        String actualString = getIn().toString().trim();
        assertTrue("В задании должен выполняться вывод текста " + actualString, !actualString.isEmpty());
        assertTrue("Ожидается другой вывод\nвместо [" + actualString + " должно быть [" + expectedString,
                expectedString.equals(actualString));

    }
}
