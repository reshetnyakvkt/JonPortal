package ua.com.jon.tests;

import com.jon.tron.service.junit.Unit;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

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
        invokeMain(unitClass, instance);
    }
}
