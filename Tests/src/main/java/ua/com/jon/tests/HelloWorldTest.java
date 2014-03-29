package ua.com.jon.tests;

import com.jon.tron.service.junit.Unit;
import com.jon.tron.service.junit.UnitName;
import com.jon.tron.service.reflect.JavaProcessBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created with IntelliJ IDEA.
 * User: al1
 * Date: 3/8/13
 */
@Unit(testName = "HelloWorld", value = "weekend1.task1")
public class HelloWorldTest extends BaseTest {
    public static void main(String[] args) {
        System.out.println("Hello world");
    }

    @UnitName
    private static String unitName;

    @Unit
    private static String unit;

    @Before
    public void setUp() {
        super.setUp();
    }

    @After
    public void tearDown() {
        super.tearDown();
    }

    @Test(timeout = 1000)
    public void testClassMainMessage() throws Throwable {
        //instance = instanciate(unitClass);
        JavaProcessBuilder.buildProcessAndInvokeMethod(unitName, null, "main", "/forbid.policy",
                (Object) new String[0]);
        String lineSeparator = System.lineSeparator();
        assertTrue("Метод main должен выводить в консоль сообщение \'Hello world\'", ("Hello world" + lineSeparator).equals(getIn().toString()));
    }
}
