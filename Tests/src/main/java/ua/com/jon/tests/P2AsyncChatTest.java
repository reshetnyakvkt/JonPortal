package ua.com.jon.tests;

import com.jon.tron.service.junit.Unit;
import com.jon.tron.service.junit.UnitClass;
import com.jon.tron.service.junit.UnitCode;
import com.jon.tron.service.junit.UnitName;
import com.jon.tron.service.reflect.MethodModifier;
import com.jon.tron.service.reflect.ReflectionUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.lang.reflect.Method;
import java.util.Map;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created with IntelliJ IDEA.
 * User: al1
 * Date: 23.05.14
 */
@Unit(testName = "P2AsyncChatTest", value = {"hw3.chat.AsyncChat", "hw3.chat.AsyncChatTest"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class P2AsyncChatTest extends BaseTest {
    private static final String UNIT_NAME = "AsyncChat";
    private static final String TEST_NAME = "AsyncChatTest";
    private static final String PROCESS_METHOD_NAME = "process";

    @UnitCode
    private static Map<String, String> codes;
    @UnitClass
    private static Class[] unitClasses;
    @UnitName
    private static String[] unitNames;
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
    public void testCheckUnitPresent() throws Throwable {
        Class unitClass;
        if(unitClasses.length != 1) {
            unitClass = getUnitClass(unitClasses, UNIT_NAME);
            assertNotNull("В задании не найден класс " + UNIT_NAME, unitClass);
        } else {
            unitClass = unitClasses[0];
        }
        assertTrue("В задании не найден класс " + UNIT_NAME, UNIT_NAME.equals(unitClass.getSimpleName()));
        Method methodSort = ReflectionUtil.checkMethod(unitClass, PROCESS_METHOD_NAME, void.class,
                new MethodModifier[]{MethodModifier.PUBLIC});
    }

    @Test(timeout = 1000)
    public void testCheckTestPresent() throws Throwable {
        Class unitClass;
        if(unitClasses.length != 1) {
            unitClass = getUnitClass(unitClasses, TEST_NAME);
            assertNotNull("В задании не найден класс теста " + TEST_NAME, unitClass);
        } else {
            unitClass = unitClasses[0];
        }
        assertTrue("В задании не найден класс теста " + TEST_NAME, TEST_NAME.equals(unitClass.getSimpleName()));
    }
}