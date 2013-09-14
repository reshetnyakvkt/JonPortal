package ua.com.jon.tests;

import com.jon.tron.service.evaluation.EvaluationUtil;
import com.jon.tron.service.junit.Unit;
import com.jon.tron.service.reflect.ReflectionUtil;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.*;

import static junit.framework.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created with IntelliJ IDEA.
 * User: al1
 * Date: 3/8/13
 */
@Unit(testName = "Hello world", value = "weekend1.task1")
public class HelloWorldTest {
    //private static final Logger log = Logger.getLogger(HelloWorldTest.class);

    @Unit
    private static Class unitClass;

//    private Reader in;
    private ByteArrayOutputStream baos;
    EvaluationUtil evaluationUtil;
    private Object instance;

    @Before
    public void setUp() {
        try {
            evaluationUtil = new EvaluationUtil();
            baos = new ByteArrayOutputStream();
            evaluationUtil.setInOut("", baos);
            instance = unitClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @After
    public void tearDown() {
        evaluationUtil.restoreInOut();
    }

    @Test
    public void testMainPresent() {
        assertTrue("Метод main должен быть 'public static void main(String[] args)'", ReflectionUtil.isCorrectMainPresent(unitClass));
    }

    @Test
    public void testMessagePresent() {
        ReflectionUtil.invokeMain(instance, new String[0]);
        assertTrue("Метод main должен выводить в консоль сообщение\'Hello world\'", "Hello world".equals(baos.toString()));
    }
}
