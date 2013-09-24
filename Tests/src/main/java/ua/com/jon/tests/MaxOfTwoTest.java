package ua.com.jon.tests;

import com.jon.tron.service.junit.Unit;
import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Created with IntelliJ IDEA.
 * User: al1
 * Date: 9/22/13
 */
@Unit(testName = "MaxOfTwo", value = "weekend1.task1")
public class MaxOfTwoTest extends BaseTest {
    public static void main(String[] args) {
        java.util.Scanner scan = new java.util.Scanner(System.in);
        int first = scan.nextInt();
        int second = scan.nextInt();
        System.out.println(first + second);
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

    @Test(timeout = 1000)
    public void testSuccess() {
        int first = rnd.nextInt(100);
        int second = rnd.nextInt(100);
        int expectedSum = first + second;
        getOut().println(first);
        getOut().println(second);

        invokeMain(unitClass, instance);

        String actualSumString = getIn().toString();
        int actualSum = 0;
        try {
            actualSum = Integer.parseInt(actualSumString.trim());
        } catch (NumberFormatException nfe) {
            fail("Результат должен быть числом, но выведено [" + actualSumString + "]");
        }

        Assert.assertEquals("Ожидаемый результат " + expectedSum + ", но выведено " + actualSum,
                expectedSum, actualSum);
    }
}
