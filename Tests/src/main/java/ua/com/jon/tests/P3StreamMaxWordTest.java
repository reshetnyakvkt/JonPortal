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
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.*;

/**
 * Created with IntelliJ IDEA.
 * User: al1
 * Date: 29.01.17
 */
@Unit(testName = "P3StreamMaxWordTest", value = {"hw3.streams.MaxWord", "hw3.streams.MaxWordTest"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class P3StreamMaxWordTest extends BaseTest {
    private static final String UNIT_NAME = "MaxWord";
    private static final String TEST_NAME = "MaxWordTest";
    private static final String GET_MAX_WORD_LENGTH = "getMaxWordLength";
    private static final int ELEM_COUNT = 5;
    private static final String[] WORDS = {"jedi", "on", "line", "master", "begun"};
    private static final Random rnd = new Random();

/*
    public static class MaxWord {
        public int getMaxWordLength( Stream<String> line) {
            return line.reduce("", (prev, curr) -> prev.length() > curr.length() ? prev : curr).length();
        }
    }
    public static class MaxWordTest {

    }
    public static void main(String[] args) throws ClassNotFoundException {
        unitClasses = new Class[] {MaxWord.class, MaxWordTest.class};
        JUnitCore junit = new JUnitCore();
        Result result = junit.run(P3StreamMaxWordTest.class);
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
    private static Method capitalMethod;

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
    public void test1CheckCodePresent() throws Throwable { // testCheckUnitPresent()
        Class unitClass;
        Class testClass;
        assertTrue("В задании должно быть 2 класса", unitClasses.length == 2);

        unitClass = getUnitClass(unitClasses, UNIT_NAME);
        assertNotNull("В задании не найден класс " + UNIT_NAME, unitClass);
        assertTrue("В задании не найден класс " + UNIT_NAME, UNIT_NAME.equals(unitClass.getSimpleName()));
        testClass = getUnitClass(unitClasses, TEST_NAME);
        assertNotNull("В задании не найден класс теста " + TEST_NAME, testClass);
        assertTrue("В задании не найден класс теста " + TEST_NAME, TEST_NAME.equals(testClass.getSimpleName()));

        instance = instanciate(unitClass);

        capitalMethod = ReflectionUtil.checkMethod(unitClass, GET_MAX_WORD_LENGTH, int.class,
                new MethodModifier[]{MethodModifier.PUBLIC}, Stream.class);

        CodeValidator.checkCode(codes);
        StyleChecker.checkStyle(codes, troubles);

        instance = instanciate(unitClass);
        if (instance == null || capitalMethod == null) {
            fail("");
        }
    }

    @Test(timeout = 1100)
    public void test2Functonality() throws Throwable {
        Class unitClass = getUnitClass(unitClasses, UNIT_NAME);
        Assert.assertNotNull("В задании не найден класс " + UNIT_NAME, unitClass);
        if (instance == null || capitalMethod == null) {
            fail("");
        }

        Stream<String> stream = Stream.generate(() -> WORDS[rnd.nextInt(WORDS.length)]).limit(ELEM_COUNT);
        final String[] newArray = stream.toArray(String[]::new); // size -> new String[size]
        stream = Stream.of(newArray);

        int actualMax = (int) ReflectionUtil.invokeMethod(instance, capitalMethod, stream);
        Stream<String> anStream = Stream.of(newArray);

        int expectedMax = getMax(anStream);

        assertEquals(String.format("При входящем потоке %s ожидается длина строки \"%d\", получена длина \"%d\"",
                Arrays.toString(newArray), expectedMax, actualMax),
                expectedMax, actualMax);
    }

    private int getMax(Stream<String> anStream) {
        return anStream.reduce("", (prev, curr) -> prev.length() > curr.length() ? prev : curr).length();
    }
}
