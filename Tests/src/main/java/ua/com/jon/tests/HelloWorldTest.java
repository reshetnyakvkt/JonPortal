package ua.com.jon.tests;

import com.jon.tron.service.junit.Unit;
import com.jon.tron.service.reflect.ReflectionUtil;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created with IntelliJ IDEA.
 * User: al1
 * Date: 3/8/13
 */
@Unit(testName = "Hello world", value = "weekend1.task1")
public class HelloWorldTest {

    @Unit
    private static Class unitClass;

    private Object instance;

    @Before
    public void setUp() {
        try {
            instance = unitClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testMainPresent() {
        assertTrue("Метод main должен быть 'public static void main(String[] args)'", ReflectionUtil.isCorrectMainPresent(unitClass));
    }

    @Test
    public void testMessagePresent() {
        assertTrue(ReflectionUtil.isCorrectMainPresent(unitClass));
    }
}
