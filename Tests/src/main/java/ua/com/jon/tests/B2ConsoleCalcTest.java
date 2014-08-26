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
 * Date: 12.08.14
 */
@Unit(testName = "B2FractionTest", value = "checked.HelloWorld")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class B2ConsoleCalcTest extends BaseTest {
    public class Fraction {
        public Fraction add(Fraction num) {
            return new Fraction();
        }
        public Fraction sub(Fraction num) {
            return new Fraction();
        }
        public Fraction mul(Fraction num) {
            return new Fraction();
        }
        public Fraction div(Fraction num) {
            return new Fraction();
        }
        public String toString() {
            return "";
        }
        public void print() {
        }
    }

    private static final String UNIT_NAME = "ConsoleCalc";
    private static final String MAIN_METHOD_NAME = "main";

    @UnitCode
    private static Map<String, String> codes;
    @UnitClass
    private static Class[] unitClasses;
    @UnitName
    private static String[] unitNames;
    @Unit
    private static String unitJarClasspath;

    private static Object instance;
    private static Method addMethod;

    @Before
    public void setUp() {
        super.setUp();
    }

    @After
    public void tearDown() {
        super.tearDown();
    }

    @Test(timeout = 1100)
    public void testCheckMainMethod() throws Throwable {
        assertTrue("В задании должен быть только один класс", unitClasses.length == 1);
        validateCode(codes.entrySet().iterator().next().getValue());

        Class unitClass = getUnitClass(unitClasses, UNIT_NAME);
        assertNotNull("В задании не найден класс " + UNIT_NAME, unitClass);

        instance = instanciate(unitClass);
        addMethod = ReflectionUtil.checkMainMethod(unitClass);
    }
}
