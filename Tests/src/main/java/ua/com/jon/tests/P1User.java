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

import java.io.Reader;
import java.lang.reflect.Method;
import java.util.Map;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created with IntelliJ IDEA.
 * User: al1
 * Date: 11.05.14
 */
@Unit(testName = "P1User", value = {"hw2.hash.User", "hw2.hash.UserTest"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class P1User extends BaseTest {

    private static final String UNIT_NAME = "User";
    private static final String TEST_NAME = "UserTest";
    private static final String EQUALS_METHOD_NAME = "equals";
    private static final String HASHCODE_METHOD_NAME = "hashCode";

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
    public void test() throws Throwable {
        assertTrue("В задании должено быть не более 2х классов", unitClasses.length <= 2);
        validateCode(codes.entrySet().iterator().next().getValue());

        Class unitClass = getUnitClass(unitClasses, UNIT_NAME);
        assertNotNull("В задании не найден класс " + UNIT_NAME, unitClass);

//        ReflectionUtil.checkConstructor(unitClass, Reader.class);
//        ReflectionUtil.checkConstructor(unitClass, String.class);

        instance = instanciate(unitClass);
        addMethod = ReflectionUtil.checkMethod(unitClass, EQUALS_METHOD_NAME, boolean.class,
                new MethodModifier[]{MethodModifier.PUBLIC}, Object.class);
        addMethod = ReflectionUtil.checkMethod(unitClass, HASHCODE_METHOD_NAME, int.class,
                new MethodModifier[]{MethodModifier.PUBLIC});
    }
}
