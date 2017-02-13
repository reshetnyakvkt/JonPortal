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
@Unit(testName = "P3StreamCapitalizerTest", value = {"hw3.streams.Capitalizer", "hw3.streams.CapitalizerTest"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class P3StreamCapitalizerTest extends BaseTest {
    private static final String UNIT_NAME = "Capitalizer";
    private static final String TEST_NAME = "CapitalizerTest";
    private static final String CAPITAL_METHOD_NAME = "firstCapitalize";
    private static final int ELEM_COUNT = 5;
    private static final String[] WORDS = {"jedi", "on", "line", "master", "begun"};
    private static final Random rnd = new Random();


    public static class Capitalizer {
        public String firstCapitalize( java.util.stream.Stream<String> line) {
            return line.map(str -> str.substring(0, 1).toUpperCase() + str.substring(1))
                    .collect(Collectors.joining(" "));
        }
    }
    public static class CapitalizerTest {

    }
    public static void main(String[] args) throws ClassNotFoundException {
        unitClasses = new Class[] {Capitalizer.class, CapitalizerTest.class};
        JUnitCore junit = new JUnitCore();
        Result result = junit.run(P3StreamCapitalizerTest.class);
        result.getFailures().forEach(System.out::println);
    }

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

        capitalMethod = ReflectionUtil.checkMethod(unitClass, CAPITAL_METHOD_NAME, String.class,
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

        String actualLine = (String) ReflectionUtil.invokeMethod(instance, capitalMethod, stream);
        Stream<String> anStream = Stream.of(newArray);

        String expectedLine = generateString(anStream);

        assertEquals(String.format("При входящем потоке %s ожидается строка \"%s\", получена строка \"%s\"",
                Arrays.toString(newArray), expectedLine, actualLine),
                expectedLine, actualLine);
    }

    private String generateString(Stream<String> anStream) {
        return anStream.map(str -> str.substring(0, 1).toUpperCase() + str.substring(1))
                .collect(Collectors.joining(" "));
    }

}
