package ua.com.jon.tests;

import com.jon.tron.service.junit.Unit;
import com.jon.tron.service.junit.UnitClass;
import com.jon.tron.service.junit.UnitCode;
import com.jon.tron.service.junit.UnitName;
import com.jon.tron.service.processor.CodeValidator;
import com.jon.tron.service.reflect.MethodModifier;
import com.jon.tron.service.reflect.ReflectionUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.lang.reflect.Method;
import java.util.Map;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 Создать двоичное дерево поиска и реализовать методы:
 1. Поиск элемента в дереве, boolean contains(int element)
 2. Прямой обход дерева в ширину (вершина, левое, правое), void traversPreOrder()
 3. Вычисления количества элементов дерева, int count()
 4. Вычисление высоты дерева, int calcHeight()
 5. Нахождение наибольшего элемента, int max()

 Классы задания:
 hw8.bst.BST
 hw8.bst.TNode

 */
@Unit(testName = "B7TreeRecTest", value = "hw8.bst.BST, hw8.bst.TNode")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class B7TreeRecTest extends BaseTest {
    private static final String UNIT_NAME = "BST";
    private static final String CONTAINS_METHOD_NAME = "contains";
    private static final String TRAVERS_METHOD_NAME = "traversPreOrder";
    private static final String COUNT_METHOD_NAME = "count";
    private static final String HEIGHT_METHOD_NAME = "calcHeight";
    private static final String MAX_METHOD_NAME = "max";

    @UnitCode
    private static Map<String, String> codes;
    @UnitClass
    private static Class[] unitClasses;
    @UnitName
    private static String[] unitNames;
    @Unit
    private static String unitJarClasspath;

    private static Object instance;
    private static Method method;

    @Before
    public void setUp() {
        super.setUp();
    }

    @After
    public void tearDown() {
        super.tearDown();
    }

    @Test(timeout = 1100)
    public void test() throws Throwable {
        assertTrue("В задании должно быть более 2х классов", unitClasses.length <= 2);

        Class unitClass = getUnitClass(unitClasses, UNIT_NAME);
        assertNotNull("В задании не найден класс " + UNIT_NAME, unitClass);
        CodeValidator.checkCode(codes.get(unitClass.getName()));
        ReflectionUtil.checkDefaultConstructor(unitClass);

        ReflectionUtil.checkMethod(unitClass, CONTAINS_METHOD_NAME, boolean.class,
                new MethodModifier[]{MethodModifier.PUBLIC}, int.class);
        ReflectionUtil.checkMethod(unitClass, TRAVERS_METHOD_NAME, void.class,
                new MethodModifier[]{MethodModifier.PUBLIC});
        ReflectionUtil.checkMethod(unitClass, COUNT_METHOD_NAME, int.class,
                new MethodModifier[]{MethodModifier.PUBLIC});
        ReflectionUtil.checkMethod(unitClass, HEIGHT_METHOD_NAME, int.class,
                new MethodModifier[]{MethodModifier.PUBLIC});
        ReflectionUtil.checkMethod(unitClass, MAX_METHOD_NAME, int.class,
                new MethodModifier[]{MethodModifier.PUBLIC});
    }
}
