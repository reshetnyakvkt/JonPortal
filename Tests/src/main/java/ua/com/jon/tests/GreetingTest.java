package ua.com.jon.tests;

import com.jon.tron.service.junit.Unit;
import com.jon.tron.service.junit.UnitClass;
import com.jon.tron.service.junit.UnitName;
import com.jon.tron.service.reflect.JavaProcessBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Method;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.fail;

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

    @UnitClass
    private static Class[] unitClasses;
    @UnitName
    private static String[] unitNames;
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

    @Test(timeout = 1000)
    public void testSuccess() throws Throwable {
//        Method mainMethod = this.getClass().getDeclaredMethod("main", new Class[]{String[].class});
//        System.out.println(mainMethod);
//        instance = instanciate(unitJarClasspath);
        String name = names[rnd.nextInt(names.length)];
        //getOut().print(name);
        if(unitNames.length > 1) {
            fail("В задании должен быть только один класс");
        }
        JavaProcessBuilder.buildProcessAndInvokeMethod(unitNames[0], "main", "/forbid.policy", unitJarClasspath,
                name, (Object) new String[0]);
        //invokeMain(unitJarClasspath, unitName);
        String expectedString = "Здравствуйте " + name + lineSeparator;
        String actualString = getIn().toString();
        assertEquals("Ожидается другой вывод\n", expectedString, actualString);
    }
}
