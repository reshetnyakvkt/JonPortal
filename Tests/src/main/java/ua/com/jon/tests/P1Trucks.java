package ua.com.jon.tests;

import com.jon.tron.service.junit.Unit;
import com.jon.tron.service.junit.UnitClass;
import com.jon.tron.service.junit.UnitCode;
import com.jon.tron.service.junit.UnitName;
import com.jon.tron.service.reflect.JavaProcessBuilder;
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
 * Date: 11.05.14
 */
@Unit(testName = "P1Trucks", value = "hw2.park.TrucksParking")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class P1Trucks extends BaseTest {
    private static final String UNIT_NAME = "TrucksParking";
    private static final String TEST_NAME = "TrucksParkingTest";
    private static final String PARK_METHOD_NAME = "park";

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

    @Test(timeout = 1100)
    public void test() throws Throwable {
        assertTrue("В задании должено быть не более 6и классов", unitClasses.length <= 6);
        validateCode(codes.entrySet().iterator().next().getValue());

        Class unitClass = getUnitClass(unitClasses, UNIT_NAME);
        assertNotNull("В задании не найден класс " + UNIT_NAME, unitClass);

        Method methodPut = ReflectionUtil.checkMethod(unitClass, PARK_METHOD_NAME, "int",
                new MethodModifier[]{MethodModifier.PUBLIC}, "Car");
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
