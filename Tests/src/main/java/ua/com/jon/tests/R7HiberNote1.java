package ua.com.jon.tests;

import com.jon.tron.service.junit.Unit;
import com.jon.tron.service.junit.UnitName;
import com.jon.tron.service.reflect.JavaProcessBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

/**
 * Created with IntelliJ IDEA.
 * User: al1
 * Date: 3/10/14
 */
@Unit(testName = "RegionHibernateDao", value = "hw7.hibernote.Main")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class R7HiberNote1 extends BaseTest {

    @UnitName
    private static String unitName;

    @Unit
    private static String unitJarClasspath;

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
        JavaProcessBuilder.buildProcessAndInvokeMethod(unitName, null, "/forbid.policy", unitJarClasspath,
                null, (Object) new String[0]);
    }


}
