package ua.com.jon.tests;

import com.jon.tron.service.evaluation.EvaluationUtil;
import com.jon.tron.service.reflect.ReflectionUtil;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Random;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Created with IntelliJ IDEA.
 * User: Admin
 * Date: 19.09.13
 */
public class BaseTast {
    private PrintStream out;
    private ByteArrayOutputStream in;
    private EvaluationUtil evaluationUtil;

    public static String lineSeparator = System.lineSeparator();
    public static Random rnd = new Random();

    public Object setUpAndInstanciate(Class unitClass) {
        try {
            evaluationUtil = new EvaluationUtil();
            in = new ByteArrayOutputStream();
            out = new PrintStream(evaluationUtil.setInGetOut(in));
            return unitClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void tearDown() {
        evaluationUtil.restoreInOut();
    }

    public void invokeMain(Class unitClass, Object instance) {
        assertTrue("Метод main должен быть 'public static void main(String[] args)'", ReflectionUtil.isCorrectMainPresent(unitClass));
        assertTrue("Класс должен быть public", instance != null);

        try {
            ReflectionUtil.invokeMain(instance, new String[0]);
        } catch (Throwable throwable) {
            //System.out.println("Во время выполнения метода main произошла ошибка");
            throwable.printStackTrace();
            fail("Во время выполнения метода main произошла ошибка " + throwable.toString());
        }
    }

    public PrintStream getOut() {
        return out;
    }

    public OutputStream getIn() {
        return in;
    }}
