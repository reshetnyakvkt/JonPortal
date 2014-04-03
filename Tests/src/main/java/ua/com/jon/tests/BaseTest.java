package ua.com.jon.tests;

import com.jon.tron.service.evaluation.EvaluationUtil;
import com.jon.tron.service.reflect.JavaProcessBuilder;
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
public class BaseTest {
    private PrintStream out;
    private ByteArrayOutputStream in;
    private EvaluationUtil evaluationUtil;

    public static String lineSeparator = System.getProperty("line.separator");
    public static Random rnd = new Random();

    @Deprecated
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

    public void setUp() {
        evaluationUtil = new EvaluationUtil();
        in = new ByteArrayOutputStream();
        out = new PrintStream(evaluationUtil.setInGetOut(in));
    }

    public Object instanciate(Class unitClass) throws Throwable {
        String instantateErrorMEssage = "Ошибка создания объекта класса " + unitClass.getName();
        Throwable throwable;
        try {
            return unitClass.newInstance();
        } catch (InstantiationException e) {
            instantateErrorMEssage = e.getMessage();
            throwable = e;
        } catch (IllegalAccessException e) {
            instantateErrorMEssage = "Невозможно создать объект класса, возможно класс не public";
            throwable = new IllegalAccessException(instantateErrorMEssage);
        } catch (Throwable t) {
            instantateErrorMEssage = t.getMessage();
            throwable = t;
        }
        throwable.printStackTrace();
        //fail(instantateErrorMEssage);
        throw throwable;
    }

    public void tearDown() {
        evaluationUtil.restoreInOut();
    }

    public void invokeMain(Class unitClass, String unitName, String input) {
        assertTrue("Метод main должен быть 'public static void main(String[] args)'", ReflectionUtil.isCorrectMainPresent(unitClass));
        //assertTrue("Класс должен быть public", instance != null);

        try {
            if(unitClass != null) {
                Object instance = instanciate(unitClass);
                // TODO check is correct invocation
                JavaProcessBuilder.buildProcessAndInvokeMethod(unitClass.getSimpleName(), "main", "/forbid.policy", null,
                        "", (Object) new String[0]);
            } else {
                JavaProcessBuilder.buildProcessAndInvokeMethod(unitName, "main", "/forbid.policy", "",
                        "", (Object) new String[0]);
            }
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
    }
}
