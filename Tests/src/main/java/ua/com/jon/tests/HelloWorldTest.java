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

import static junit.framework.Assert.assertSame;
import static junit.framework.Assert.fail;
import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created with IntelliJ IDEA.
 * User: al1
 * Date: 3/8/13
 */
@Unit(testName = "HelloWorld", value = "checked.HelloWorld")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class HelloWorldTest extends BaseTest {
    public static void main(String[] args) {
        System.out.println("Hello world");
    }

    private static final String UNIT_NAME = "HelloWorld";

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
    public void testClassMainMessage() throws Throwable {
        if (instance == null) {
            fail();
        }

        try {
//            getOut().println("\n");
            ReflectionUtil.invokeMain(instance);
            assertTrue("Метод main должен выводить в консоль сообщение \'Hello world\'", "Hello world".equals(getIn().toString().trim()));
        } catch (Exception e) {
        }
        assertTrue("Метод main должен выводить в консоль сообщение \'Hello world\'", "Hello world".equals(getIn().toString().trim()));

    }
}
