package ua.com.jon.tests;

import com.jon.tron.service.junit.Unit;
import org.junit.*;
import org.junit.runners.MethodSorters;

/**
 * Created with IntelliJ IDEA.
 * User: al1
 * Date: 9/29/13
 */
@Unit(testName = "Any", value = "weekend1.task1")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AnyTest extends BaseTest {

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
        //String expectedString = "Неправильный ввод\n";
        getOut().println("1\n2\n3\n4\n1\n");

        invokeMain(unitClass, instance);

        //String actualString = getIn().toString();

    /*    Assert.assertEquals("Неудачная проверка на неправильный ввод.\nОжидаемый результат [" + expectedString + "], " +
                "но выведено [" + actualString + "]",
                expectedString, actualString);*/
    }
}