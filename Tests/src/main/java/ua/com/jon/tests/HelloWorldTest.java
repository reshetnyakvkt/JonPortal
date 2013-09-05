package ua.com.jon.tests;

import com.jon.tron.service.junit.Unit;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertNotNull;

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
    public void testExists() {
        assertNotNull(unitClass);
    }

    @Test
    public void testInstance() {
        assertNotNull(instance);
    }
}
