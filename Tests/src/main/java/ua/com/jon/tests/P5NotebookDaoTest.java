package ua.com.jon.tests;

import com.jon.tron.service.junit.Unit;
import com.jon.tron.service.junit.UnitClass;
import com.jon.tron.service.junit.UnitCode;
import com.jon.tron.service.junit.UnitName;
import com.jon.tron.service.reflect.MethodModifier;
import com.jon.tron.service.reflect.ReflectionUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.Map;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created with IntelliJ IDEA.
 * User: al1
 * Date: 14.06.14
 */
@Unit(testName = "P5NotebookDaoTest", value = {"hw6.notes.domain.Notebook", "hw6.notes.dao.NotebookDao", "hw6.notes.dao.NotebookDaoImpl"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class P5NotebookDaoTest extends BaseTest {
    private static final String UNIT_DAO_NAME = "NotebookDao";
    private static final String UNIT_DAO_IMPL_NAME = "NotebookDaoImpl";
    private static final String UNIT_DOMAIN_NAME = "Notebook";
    //    private static final String TEST_NAME = "SequenceSummatorTest";
    private static final String MANAGER_CREATE_METHOD_NAME = "create";
    private static final String MANAGER_FIND_ALL_METHOD_NAME = "findAll";


    @UnitCode
    private static Map<String, String> codes;
    @UnitClass
    private static Class[] unitClasses;
    @UnitName
    private static String[] unitNames;
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

    @Test(timeout = 1000)
    public void testCheckMainUnitPresent() throws Throwable {
        Class unitClass;
        if(unitClasses.length != 1) {
            unitClass = getUnitClass(unitClasses, UNIT_DAO_NAME);
            assertNotNull("В задании не найден класс " + UNIT_DAO_NAME, unitClass);
        } else {
            unitClass = unitClasses[0];
        }
//        assertTrue("В задании не найден класс " + UNIT_NAME, UNIT_NAME.equals(unitClass.getSimpleName()));
//        Method methodProduce = ReflectionUtil.checkMethod(unitClass, PARALLEL_SUM_METHOD_NAME, long.class,
//                new MethodModifier[]{MethodModifier.PUBLIC}, int.class);
    }

    @Test(timeout = 1000)
    public void testCheckManagerUnitPresent() throws Throwable {
        Class unitClass;
        if(unitClasses.length != 1) {
            unitClass = getUnitClass(unitClasses, UNIT_DAO_IMPL_NAME);
            assertNotNull("В задании не найден класс " + UNIT_DAO_IMPL_NAME, unitClass);
        } else {
            unitClass = unitClasses[0];
        }
        assertTrue("В задании не найден класс " + UNIT_DAO_IMPL_NAME, UNIT_DAO_IMPL_NAME.equals(unitClass.getSimpleName()));
        Method methodCreate = ReflectionUtil.checkMethod(unitClass, MANAGER_CREATE_METHOD_NAME, int.class,
                new MethodModifier[]{MethodModifier.PUBLIC}, hw6.notes.domain.Notebook.class);
        Method methodFindAll = ReflectionUtil.checkMethod(unitClass, MANAGER_FIND_ALL_METHOD_NAME, ParameterizedType.class,
                new MethodModifier[]{MethodModifier.PUBLIC});
    }
}
