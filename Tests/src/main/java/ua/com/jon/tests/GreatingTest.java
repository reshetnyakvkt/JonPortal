package ua.com.jon.tests;

import com.jon.tron.service.junit.Unit;
import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: Admin
 * Date: 19.09.13
 */
@Unit(testName = "Greeting", value = "weekend1.task3")
public class GreatingTest extends BaseTest {
    public static void main(String[] args) {
        java.util.Scanner scan = new java.util.Scanner(System.in);
        String name = scan.nextLine();
        System.out.println("Здравствуйте " + name);
    }

    @Unit
    private static Class unitClass;
    private Object instance;
    private String[] names = {"Oksana", "Vampu", "Sinaps", "Maika"};

    @Before
    public void setUp() {
        super.setUp();
    }

    @After
    public void tearDown() {
        super.tearDown();
    }

    @Test(timeout = 1000)
    public void testSuccess() {
        instance = instanciate(unitClass);
        String name = names[rnd.nextInt(names.length)];
        getOut().print(name);
        invokeMain(unitClass, instance);
        String expectedString = "Здравствуйте " + name + lineSeparator;
        String actualString = getIn().toString();
        assertEquals("Ожидается строка " + expectedString + ", но выводится [" + actualString + "]",
                expectedString, actualString);
    }
}
