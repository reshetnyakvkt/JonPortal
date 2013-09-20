package ua.com.jon.tests;

import com.jon.tron.service.evaluation.EvaluationUtil;
import com.jon.tron.service.reflect.ReflectionUtil;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertTrue;

/**
 * Created with IntelliJ IDEA.
 * User: Admin
 * Date: 19.09.13
 */
public class BaseTast {
    private PrintStream in;
    private ByteArrayOutputStream out;
    private EvaluationUtil evaluationUtil;

    public Object setUpAndInstanciate(Class unitClass) {
        try {
            evaluationUtil = new EvaluationUtil();
            out = new ByteArrayOutputStream();
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            in = new PrintStream(outputStream);
            evaluationUtil.setInOut(null, out);
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
        } catch (Exception e) {
            System.out.println("'main' method invocation error for " + instance);
            e.printStackTrace();
        }
    }

    public ByteArrayOutputStream getOut() {
        return out;
    }

    public PrintStream getIn() {
        return in;
    }
}
