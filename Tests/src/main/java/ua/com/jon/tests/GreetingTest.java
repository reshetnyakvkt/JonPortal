package ua.com.jon.tests;

import com.jon.tron.service.junit.Unit;
import com.jon.tron.service.junit.UnitName;
import com.jon.tron.service.reflect.JavaProcessBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: Admin
 * Date: 19.09.13
 */
@Unit(testName = "Greeting", value = "session1.task3.Greeting")
public class GreetingTest extends BaseTest {
    public static void main(String[] args) {
        java.util.Scanner scan = new java.util.Scanner(System.in);
        String name = scan.nextLine();
        System.out.println("Здравствуйте " + name);
    }

    @UnitName
    private static String unitName;
    @Unit
    private static String unitJarClasspath;
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

    @Test/*(timeout = 1000)*/
    public void testSuccess() throws Throwable {
//        instance = instanciate(unitJarClasspath);
        String name = names[rnd.nextInt(names.length)];
        getOut().print(name);
        JavaProcessBuilder.buildProcessAndInvokeMethod(unitName, "main", "/forbid.policy", unitJarClasspath,
                (Object) new String[0]);
        //invokeMain(unitJarClasspath, unitName);
        String expectedString = "Здравствуйте " + name + lineSeparator;
        String actualString = getIn().toString();
        assertEquals("Ожидается строка " + expectedString + ", но выводится [" + actualString + "]",
                expectedString, actualString);
    }
}
