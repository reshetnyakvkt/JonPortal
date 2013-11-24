package ua.com.jon.tests;

import com.jon.tron.service.junit.Unit;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Scanner;

import static org.junit.Assert.fail;

/**
 * Created with IntelliJ IDEA.
 * User: al1
 * Date: 9/24/13
 */
@Unit(testName = "MaxOfThree", value = "weekend1.task1")
public class MaxOfThreeTest extends BaseTest {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
                int first = scan.nextInt();
                int second = scan.nextInt();
                int third = scan.nextInt();
    }

    @Unit
    private static Class unitClass;
    private Object instance;

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
        int first = rnd.nextInt(100);
        int second = rnd.nextInt(100);
        int third = rnd.nextInt(100);

        int expectedMax = maxOfThree(first, second, third);
        int expectedMin = minOfThree(first, second, third);

        getOut().println(first);
        getOut().println(second);
        getOut().println(third);

        invokeMain(unitClass, instance);

        String actualMaxString = getIn().toString();
        Scanner scan = new Scanner(actualMaxString);
        int actualMax = 0;
        int actualMin = 0;
        if(scan.hasNextInt()) {
            actualMax = scan.nextInt();
        } else {
            fail("Первым должно быть выведено наибольшее число, но выведено [" + (scan.hasNext()?scan.next():"") + "]");
        }
        if(scan.hasNextInt()) {
            actualMin = scan.nextInt();
        } else {
            fail("Вторым должно быть выведено наименьшее число, но выведено [" + (scan.hasNext()?scan.next():"") + "]");
        }

        Assert.assertEquals("Ожидаемый результат " + expectedMax + ", но выведено " + actualMax,
                expectedMax, actualMax);

        Assert.assertEquals("Ожидаемый результат " + expectedMin + ", но выведено " + actualMin,
                expectedMin, actualMin);
    }

    private int minOfThree(int first, int second, int third) {
        if(first < second) {
            if(first < third) {
                return first;
            } else {
                return third;
            }
        } else {
            if(second < third) {
                return second;
            } else {
                return third;
            }
        }
    }

    private int maxOfThree(int first, int second, int third) {
        if(first > second) {
            if(first > third) {
                return first;
            } else {
                return third;
            }
        } else {
            if(second > third) {
               return second;
            } else {
                return third;
            }
        }
    }
}
