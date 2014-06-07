package ua.com.jon.tests;

import com.jon.tron.service.junit.Unit;
import com.jon.tron.service.junit.UnitClass;
import com.jon.tron.service.junit.UnitCode;
import com.jon.tron.service.junit.UnitName;
import com.jon.tron.service.reflect.ReflectionUtil;
import org.junit.*;
import org.junit.runners.MethodSorters;

import java.lang.reflect.Method;
import java.util.Map;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
Пользователь вводит число, если число чётное, то вывести на экран "четное",
если число не четное, вывести на экран "нечетное".
Пример:
5
нечетное
 * Created with IntelliJ IDEA.
 * User: al1
 * Date: 29.05.14
 */
@Unit(testName = "PairOfTwo", value = "weekend1.task1")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class F1PairOfTwo extends BaseTest {
    public static void main(String[] args) {
        java.util.Scanner scan = new java.util.Scanner(System.in);
        int first = scan.nextInt();
        if (first % 2 == 0) {
            System.out.println("четное");
        } else {
            System.out.println("нечетное");
        }
    }

    private static final String UNIT_NAME = "MaxOfTwo";

    @UnitCode
    private static Map<String, String> codes;
    @UnitClass
    private static Class[] unitClasses;
    @UnitName
    private static String[] unitNames;
    @Unit
    private static String unitJarClasspath;

    private static Object instance;
    private static Class unitClass;
    private static Method mainMethod;

    @BeforeClass
    public static void before() {
        instance = null;
        unitClass = null;
    }

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

        if(unitClasses.length != 1) {
            unitClass = getUnitClass(unitClasses, UNIT_NAME);
            assertNotNull("В задании не найден класс " + UNIT_NAME, unitClass);
        } else {
            unitClass = unitClasses[0];
        }
        //assertTrue("В задании не найден класс " + UNIT_NAME, UNIT_NAME.equals(unitClass.getSimpleName()));
        assertTrue("В задании должен быть только один класс", codes.size() == 1);
        validateCode(codes.entrySet().iterator().next().getValue());
        instance = instanciate(unitClass);
        mainMethod = ReflectionUtil.checkMainMethod(unitClass);
    }

    @Test(timeout = 1000)
    public void testSuccess() throws Throwable {
        final int MAX_NUMBER = 10;
        if (instance == null || mainMethod == null) {
            fail();
        }
        int firstNumber = rnd.nextInt(MAX_NUMBER);

        String expectedString = firstNumber % 2 == 0 ? "четное" : "нечетное";
        getOut().println(firstNumber);

        ReflectionUtil.invokeMain(instance);
        String actualString = getIn().toString().trim();
        assertTrue("В задании должен выполняться вывод текста " + actualString, !actualString.isEmpty());
        assertTrue("При введенном числе " + firstNumber + ", должно быть выведено [" + expectedString + "], а не [" + actualString + "]",
                expectedString.equals(actualString));

    }
}