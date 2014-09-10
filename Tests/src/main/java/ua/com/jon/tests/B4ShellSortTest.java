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
 Пользователь вводит количество сортируемых чисел, затем вводит числа.
 Отсортировать введенные числа методом Шелла.
 Метод void sortShell(int[] vector)

 Класс задания: hw5.shell.ShellSorter
 */
@Unit(testName = "B4ShellSortTest", value = "weekend1.task1")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class B4ShellSortTest extends BaseTest {
    public class ShellSorter {
        public void sortShell(int[] vector) {}
    }

    private static final String UNIT_NAME = "ShellSorter";
    private static final String SORT_METHOD_NAME = "sortShell";

    @UnitCode
    private static Map<String, String> codes;
    @UnitClass
    private static Class[] unitClasses;
    @UnitName
    private static String[] unitNames;
    @Unit
    private static String unitJarClasspath;

    private static Object instance;
    private static Method getMethod;
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
        assertTrue("В задании должен быть только один класс", unitClasses.length == 1);
        CodeValidator.checkCode(codes.entrySet().iterator().next().getValue());

        Class unitClass = getUnitClass(unitClasses, UNIT_NAME);
        assertNotNull("В задании не найден класс " + UNIT_NAME, unitClass);

        ReflectionUtil.checkConstructor(unitClass);
//        ReflectionUtil.checkConstructor(unitClass, "");

        addMethod = ReflectionUtil.checkMethod(unitClass, SORT_METHOD_NAME, void.class,
                new MethodModifier[]{MethodModifier.PUBLIC}, int[].class);
    }

}
