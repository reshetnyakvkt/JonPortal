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
 Создать класс Рыба на основе класса "Продукт в супермаркете".
 Классы Fish и Product.
 Рыба должна уметь:
 - Вывести свою цену, метод void printPrice()
 - Вывести срок хранения, метод void printStoragePeriod()
 - Проплыть, метод void swim()
 - Проглотить другую рыбу, метод void cannibalism()
 - Вывести свой штрихкод и цену, метод void printBarAndPrice()
 */
@Unit(testName = "B3FishTest", value = "checked.HelloWorld")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class B3FishTest extends BaseTest {
    public class Fish {
        public void printPrice() {
        }

        public void printStoragePeriod() {
        }

        public void swim() {
        }

        public void cannibalism(Fish fish) {
        }

        public void printBarAndPrice() {
        }
    }

    class Product {

    }

    private static final String FISH_NAME = "Fish";
    private static final String PRODUCT_NAME = "Product";
    private static final String PRINT_METHOD_NAME = "printStoragePeriod";
    private static final String SWIM_METHOD_NAME = "swim";
    private static final String CANIBALISM_METHOD_NAME = "cannibalism";
    private static final String PRINT_BAR_METHOD_NAME = "printBarAndPrice";

    @UnitCode
    private static Map<String, String> codes;
    @UnitClass
    private static Class[] unitClasses;
    @UnitName
    private static String[] unitNames;
    @Unit
    private static String unitJarClasspath;

    private static Object fish;
    private static Object product;
    private static Method addMethod;

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
        assertTrue("В задании должно быть не более 2х классов", unitClasses.length <= 2);
        CodeValidator.checkCode(codes.entrySet().iterator().next().getValue());

        Class fishClass = getUnitClass(unitClasses, FISH_NAME);
        assertNotNull("В задании не найден класс " + FISH_NAME, fishClass);
        Class productClass = getUnitClass(unitClasses, PRODUCT_NAME);
        assertNotNull("В задании не найден класс " + PRODUCT_NAME, productClass);

        fish = instanciate(fishClass);
//        product = instanciate(productClass);
        addMethod = ReflectionUtil.checkMethod(fishClass, PRINT_METHOD_NAME, void.class,
                new MethodModifier[]{MethodModifier.PUBLIC});
        addMethod = ReflectionUtil.checkMethod(fishClass, CANIBALISM_METHOD_NAME, "void",
                new MethodModifier[]{MethodModifier.PUBLIC}, "Fish");
        addMethod = ReflectionUtil.checkMethod(fishClass, SWIM_METHOD_NAME, void.class,
                new MethodModifier[]{MethodModifier.PUBLIC});
        addMethod = ReflectionUtil.checkMethod(fishClass, PRINT_BAR_METHOD_NAME, void.class,
                new MethodModifier[]{MethodModifier.PUBLIC});
    }
}
