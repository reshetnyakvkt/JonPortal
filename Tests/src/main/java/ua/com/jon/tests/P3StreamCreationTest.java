package ua.com.jon.tests;

import com.jon.tron.service.junit.*;
import com.jon.tron.service.processor.CodeValidator;
import com.jon.tron.service.processor.StyleChecker;
import com.jon.tron.service.reflect.MethodModifier;
import com.jon.tron.service.reflect.ReflectionUtil;
import org.junit.*;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runners.MethodSorters;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.*;

/**
 * Created with IntelliJ IDEA.
 * User: al1
 * Date: 29.01.17
 */
@Unit(testName = "P3StreamCreationTest", value = {"hw3.streams.RandomStream", "hw3.streams.RandomStreamTest"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class P3StreamCreationTest extends BaseTest {
    private static final String UNIT_NAME = "RandomStream";
    private static final String TEST_NAME = "RandomStreamTest";
    private static final String GET_METHOD_NAME = "getStream";
    private static final int ELEM_COUNT = 10;
/*    public static class RandomStream {
        public java.util.stream.Stream getStream(  ) {
            return Stream.of(1,1,1,1,1,1,1,1,2,1);
        }
    }
    public class RandomStreamTest {

    }
    public static void main(String[] args) throws ClassNotFoundException {
        unitClasses = new Class[] {RandomStream.class, RandomStreamTest.class};
        JUnitCore junit = new JUnitCore();
        Result result = junit.run(P3StreamCreationTest.class);
        result.getFailures().forEach(System.out::println);
    }*/

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

    @Troubles
    private static List<String> troubles;

    @Before
    public void setUp() {
        super.setUp();
    }

    @After
    public void tearDown() {
        super.tearDown();
    }

    @Test(timeout = 1000)
    public void test1() throws Throwable { // testCheckUnitPresent()
        Class unitClass;
        assertTrue("В задании должно быть 2 класса", unitClasses.length == 2);

        unitClass = getUnitClass(unitClasses, UNIT_NAME);
        assertNotNull("В задании не найден класс " + UNIT_NAME, unitClass);
        assertTrue("В задании не найден класс " + UNIT_NAME, UNIT_NAME.equals(unitClass.getSimpleName()));
        unitClass = getUnitClass(unitClasses, TEST_NAME);
        assertNotNull("В задании не найден класс теста " + TEST_NAME, unitClass);
        assertTrue("В задании не найден класс теста " + TEST_NAME, TEST_NAME.equals(unitClass.getSimpleName()));
    }

    @Test(timeout = 1100)
    public void test2() throws Throwable { // testTaskContentAndInstantiate
        Class unitClass = getUnitClass(unitClasses, UNIT_NAME);
        Assert.assertNotNull("В задании не найден класс " + UNIT_NAME, unitClass);

        instance = instanciate(unitClass);

        getMethod = ReflectionUtil.checkMethod(unitClass, GET_METHOD_NAME, Stream.class,
                new MethodModifier[]{MethodModifier.PUBLIC});

        CodeValidator.checkCode(codes);
        StyleChecker.checkStyle(codes, troubles);

        instance = instanciate(unitClass);
        if (instance == null || getMethod == null) {
            fail("");
        }

        Stream newStream = (Stream) ReflectionUtil.invokeMethod(instance, getMethod);
        Stream.of(1,1,1,1,1,1,1,1,1,1);

        assertNotNull("Метод возвращает вместо потока null", newStream);

        final Object[] newArray = newStream.toArray();
        Stream anStream = Stream.of(newArray);
        assertEquals("В потоке нет "+ ELEM_COUNT +" элементов", ELEM_COUNT, anStream.count());

        anStream = Stream.of(newArray);
        assertNotEquals("В потоке все элементы повторяются", 1, anStream.distinct().count());

        Stream otherStream = (Stream) ReflectionUtil.invokeMethod(instance, getMethod);
        final Object[] otherArray = otherStream.toArray();
        assertFalse("Метод возвращает одинаковые потоки", Arrays.equals(newArray, otherArray));
    }

}
