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
import java.util.Map;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 Создать класс Кошка на основе класса Животное.
 Классы Cat и Animal.
 Кошка должна уметь:
 - Вывести на экран имя, метод void printName()
 - Подать голос, метод void say()
 - Поднимать шерсть дыбом, метод void riseHair()
 - Питаться, метод void eat()
 - Орать если ненаелась, метод void yellIfNotGorged()
 */
@Unit(testName = "B3CatTest", value = "checked.HelloWorld")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class B3CatTest extends BaseTest {
    public class Cat {
        public void printName() {
        }

        public void say() {
        }

        public void riseHair() {
        }

        public void eat() {
        }

        public void yellIfNotGorged() {
        }
    }

    class Animal {

    }

    private static final String ANIMAL_NAME = "Animal";
    private static final String CAT_NAME = "Cat";
    private static final String PRINT_METHOD_NAME = "printName";
    private static final String SAY_METHOD_NAME = "say";
    private static final String RISE_HAIR_METHOD_NAME = "riseHair";
    private static final String EAT_METHOD_NAME = "eat";
    private static final String YELL_METHOD_NAME = "yellIfNotGorged";

    @UnitCode
    private static Map<String, String> codes;
    @UnitClass
    private static Class[] unitClasses;
    @UnitName
    private static String[] unitNames;
    @Unit
    private static String unitJarClasspath;

    private static Object instance;
    private static Object animal;
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
        validateCode(codes.entrySet().iterator().next().getValue());

        Class catClass = getUnitClass(unitClasses, CAT_NAME);
        assertNotNull("В задании не найден класс " + CAT_NAME, catClass);
        Class animalClass = getUnitClass(unitClasses, ANIMAL_NAME);
        assertNotNull("В задании не найден класс " + ANIMAL_NAME, animalClass);

//        animal = instanciate(animalClass);
        instance = instanciate(catClass);
        addMethod = ReflectionUtil.checkMethod(catClass, PRINT_METHOD_NAME, void.class,
                new MethodModifier[]{MethodModifier.PUBLIC});
        addMethod = ReflectionUtil.checkMethod(catClass, RISE_HAIR_METHOD_NAME, void.class,
                new MethodModifier[]{MethodModifier.PUBLIC});
        addMethod = ReflectionUtil.checkMethod(catClass, SAY_METHOD_NAME, void.class,
                new MethodModifier[]{MethodModifier.PUBLIC});
        addMethod = ReflectionUtil.checkMethod(catClass, EAT_METHOD_NAME, void.class,
                new MethodModifier[]{MethodModifier.PUBLIC});
        addMethod = ReflectionUtil.checkMethod(catClass, YELL_METHOD_NAME, void.class,
                new MethodModifier[]{MethodModifier.PUBLIC});
    }
}