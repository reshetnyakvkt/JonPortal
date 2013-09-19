package ua.com.jon.tests;

import com.jon.tron.service.evaluation.EvaluationUtil;
import com.jon.tron.service.junit.Unit;
import com.jon.tron.service.reflect.ReflectionUtil;
import org.junit.After;
import org.junit.Before;

import java.io.ByteArrayOutputStream;

import static org.junit.Assert.assertTrue;

/**
 * Created with IntelliJ IDEA.
 * User: Admin
 * Date: 19.09.13
 */
public class BaseTast {

    private ByteArrayOutputStream baos;
    private EvaluationUtil evaluationUtil;

    public Object setUpAndInstanciate(Class unitClass) {
        try {
            evaluationUtil = new EvaluationUtil();
            baos = new ByteArrayOutputStream();
            evaluationUtil.setInOut("", baos);
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

    public ByteArrayOutputStream getBaos() {
        return baos;
    }
}
