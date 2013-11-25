package ua.com.jon.tests;

import com.jon.tron.service.processor.ClassProcessor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Map;

import static junit.framework.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: al1
 * Date: 11/24/13
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/testContext.xml"})
public class BubbleSortTestTest {
    @Autowired
    private ClassProcessor classProcessor;

    @Test
    public void testSuccess()  {
        final String className = "";
        final String classCode =
                "package lesson;" +
                        "public class BubbleSorter {" +
                        "    private int[] bubbleSort(int[] vector) {\n" +
                        "        for (int i = 0; i < vector.length; i++) {\n" +
                        "            for (int j = 0; j < vector.length - 1; j++) {\n" +
                        "                if(vector[j] > vector[j + 1]) {\n" +
                        "                    int tmp = vector[j];\n" +
                        "                    vector[j] = vector[j + 1];\n" +
                        "                    vector[j + 1] = tmp;\n" +
                        "                }\n" +
                        "            }\n" +
                        "        }\n" +
                        "        return vector;\n" +
                        "    }" +
//                        "}" +
                        "}";
        final String testName = "Bubble";
        Map.Entry<String,String> processResult = classProcessor.processClass(className, classCode, testName);
        String resultString = processResult.getValue();
        String markString = processResult.getKey();
        assertEquals("Задание выполнено", resultString);
        assertEquals("100", markString);
    }

    @Test
    public void testEmpty() {
        final String className = "";
        final String classCode = "public class A{}";
        String testName = "Any";
        Map.Entry<String,String> processResult = classProcessor.processClass(className, classCode, testName);
        String resultString = processResult.getValue();
        String markString = processResult.getKey();
        assertEquals("Задание принято", resultString);
        assertEquals("10", markString);
    }

    @Test
    public void testInfinitLoop() {
        final String className = "";
        final String classCode = "public class A {" +
                "public static void main(String[] args) {" +
                "while(true){}" +
                "}" +
                "}";
        String testName = "Any";
        Map.Entry<String,String> processResult = classProcessor.processClass(className, classCode, testName);
        String resultString = processResult.getValue();
        String markString = processResult.getKey();
        assertEquals("Задание принято", resultString);
        assertEquals("10", markString);
    }

    @Test
    public void testInfinitLoopInConstractor() {
        final String className = "";
        final String classCode = "public class A {" +
                "public A() {" +
                "   while(true);" +
                "}" +
                "public static void main(String[] args) {" +
                "   while(true);" +
                "}" +
                "}";
        String testName = "Any";
        Map.Entry<String,String> processResult = classProcessor.processClass(className, classCode, testName);
        String resultString = processResult.getValue();
        String markString = processResult.getKey();
        assertEquals("test timed out after 1000 milliseconds", resultString);
        assertEquals("10", markString);
    }

}
