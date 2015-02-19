package ua.com.jon.tests;

import com.jon.tron.service.junit.Unit;
import com.jon.tron.service.junit.UnitClass;
import com.jon.tron.service.junit.UnitCode;
import com.jon.tron.service.junit.UnitName;
import com.jon.tron.service.processor.CodeValidator;
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
 Написать метод, выполняющий двоичный поиск (не рекурсивный) элемента в массиве.
 */
@Unit(testName = "B7BinarySearchTest", value = "hw8.bsearch.BinarySearcher")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class B7BinarySearchTest extends BaseTest {
    class BinarySearcher {
        public void binarySearch(int[] vector) {
        }
    }
    private static final String UNIT_NAME = "BinarySearcher";
    private static final String SEARCH_METHOD_NAME = "binarySearch";

    @UnitCode
    private static Map<String, String> codes;
    @UnitClass
    private static Class[] unitClasses;
    @UnitName
    private static String[] unitNames;
    @Unit
    private static String unitJarClasspath;

    private static Object instance;
    private static Method method;

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
        assertTrue("В задании должен быть только один класс", unitClasses.length == 1);

        Class unitClass = getUnitClass(unitClasses, UNIT_NAME);
        assertNotNull("В задании не найден класс " + UNIT_NAME, unitClass);

        CodeValidator.checkCode(codes.get(unitClass.getName()));
        ReflectionUtil.checkDefaultConstructor(unitClass);

        ReflectionUtil.checkMethod(unitClass, SEARCH_METHOD_NAME, int.class,
                new MethodModifier[]{MethodModifier.PUBLIC}, int[].class, int.class);
    }
}
