package ua.com.jon.tests;

import com.jon.tron.service.junit.Unit;
import com.jon.tron.service.junit.UnitClass;
import com.jon.tron.service.junit.UnitName;
import com.jon.tron.service.reflect.JavaProcessBuilder;
import com.jon.tron.service.reflect.ReflectionUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Method;

import static junit.framework.Assert.fail;
import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created with IntelliJ IDEA.
 * User: al1
 * Date: 3/8/13
 */
@Unit(testName = "HelloWorld", value = "weekend1.task1")
public class HelloWorldTest extends BaseTest {
    public static void main(String[] args) {
        System.out.println("Hello world");
    }

    private static final String UNIT_NAME = "HelloWorld";
    @UnitClass
    private static Class[] unitClasses;
    @UnitName
    private static String unitNames;
    @Unit
    private static String unitJarClasspath;

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
        Class unitClass;
        if(unitClasses.length != 1) {
            unitClass = getUnitClass(unitClasses, UNIT_NAME);
        } else {
            unitClass = unitClasses[0];
        }
        assertNotNull("Нет класса " + UNIT_NAME, unitClass);
        ReflectionUtil.checkMainMethod(unitClass);
    }


    @Test(timeout = 1000)
    public void testClassMainMessage() throws Throwable {
        ReflectionUtil.invokeMain(UNIT_NAME, unitJarClasspath, "");
        String lineSeparator = System.lineSeparator();
        assertTrue("Метод main должен выводить в консоль сообщение \'Hello world\'", ("Hello world" + lineSeparator).equals(getIn().toString()));
    }
}
