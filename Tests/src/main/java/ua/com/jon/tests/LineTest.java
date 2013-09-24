package ua.com.jon.tests;

import com.jon.tron.service.junit.Unit;

import org.junit.*;
import org.junit.runners.MethodSorters;

import static org.junit.Assert.fail;

/**
 * Created with IntelliJ IDEA.
 * User: al1
 * Date: 9/23/13
 */
@Unit(testName = "Line", value = "weekend1.task1")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LineTest extends BaseTest {
    @Unit
    private static Class unitClass;
    private Object instance;

    @Before
    public void setUp() {
        instance = super.setUpAndInstanciate(unitClass);
    }

    @After
    public void tearDown() {
        super.tearDown();
    }

    @Test(timeout = 1000)
    public void test1IllegalInput() {
        String expectedString = "Неправильный ввод\n";
        getOut().println("\n");

        invokeMain(unitClass, instance);

        String actualString = getIn().toString();

        Assert.assertEquals("Неудачная проверка на неправильный ввод.\nОжидаемый результат [" + expectedString + "], " +
                "но выведено [" + actualString + "]",
                expectedString, actualString);
    }

    @Test(timeout = 1000)
    public void test2Success() {
        int lineLength = rnd.nextInt(6);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < lineLength; i++) {
            sb.append('*');
        }
        String expectedLine = sb.toString();
        getOut().println(lineLength);

        invokeMain(unitClass, instance);

        String actualLine = getIn().toString();

        Assert.assertEquals("Ожидаемый результат [" + expectedLine + "], но выведено [" + actualLine + "]",
                expectedLine, actualLine);
    }
}
