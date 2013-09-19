package ua.com.jon.tests;

import com.jon.tron.service.junit.Unit;
import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.PrintStream;

/**
 * Created with IntelliJ IDEA.
 * User: Admin
 * Date: 19.09.13
 */
@Unit(testName = "Greating", value = "weekend1.task3")
public class GreatingTest extends BaseTast {
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
    public void testSuccess() {
        PrintStream printStream = getIn();
        String name = "Oksana";
        printStream.print(name);
        invokeMain(unitClass, instance);
        String expectedString = "Здравствуйте " + name;
        String actualString = getOut().toString();
        Assert.assertEquals("Ожидается строка " + expectedString + ", но выводится " + actualString,
                expectedString, actualString);
        System.out.println(actualString);
    }
}
