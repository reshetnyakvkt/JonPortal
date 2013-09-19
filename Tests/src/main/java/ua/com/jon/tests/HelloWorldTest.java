package ua.com.jon.tests;

import com.jon.tron.service.junit.Unit;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created with IntelliJ IDEA.
 * User: al1
 * Date: 3/8/13
 */
@Unit(testName = "Hello world", value = "weekend1.task1")
public class HelloWorldTest extends BaseTast {
    public static void main(String[] args) {

    }
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

    @Test
    public void testClassMainMessage() {
        invokeMain(unitClass, instance);
        String lineSeparator = System.lineSeparator();
        assertTrue("Метод main должен выводить в консоль сообщение \'Hello world\'", ("Hello world" + lineSeparator).equals(getBaos().toString()));
        System.out.println(getBaos());
    }
}
