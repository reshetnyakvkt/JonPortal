package ua.com.jon.tests;

import com.jon.tron.service.processor.ClassProcessor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Map;

import static junit.framework.Assert.*;


/**
 * Created with IntelliJ IDEA.
 * User: al1
 * Date: 9/4/13
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/testContext.xml"})
public class HelloWorldTestTest {
    @Autowired
    private ClassProcessor classProcessor;

    @Test
    public void testEmpty() throws Exception {
        final String className = "HelloWorld";
        final String classCode = "public class HelloWorld{}";
        final String testName = "HelloWorldTest";
        Map.Entry<String,String> processResult = classProcessor.processClass(className, classCode, testName, null);
        String resultString = processResult.getValue();
        assertTrue(resultString.contains("В результате выполнения, было выброшено исключение java.lang.RuntimeException java.lang.NoSuchMethodException"));

    }

    @Test
    public void testWrongMain() throws Exception {
        final String className = "HelloWorld";
        final String classCode = "public class HelloWorld{public void main(){}}";
        final String testName = "HelloWorldTest";

        Map.Entry<String,String> processResult = classProcessor.processClass(className, classCode, testName, null);
        String resultString = processResult.getValue();
        assertTrue(resultString.contains("В результате выполнения, было выброшено исключение java.lang.RuntimeException java.lang.NoSuchMethodException"));

    }

    @Test
    public void testMainWithoutMessage() throws Exception {
        final String className = "HelloWorld";
        final String classCode = "public class HelloWorld{public static void main(String[] args){}}";
        final String testName = "HelloWorldTest";

        Map.Entry<String,String> processResult = classProcessor.processClass(className, classCode, testName, null);
        String resultString = processResult.getValue();
        assertTrue(resultString.contains("Метод main должен выводить в консоль сообщение 'Hello world'"));
    }


    @Test
    public void testNonPublicClass() throws Exception {
        final String className = "HelloWorld";
        final String classCode =
                "    class HelloWorld {\n" +
                        "        public static void main(String[] args) {\n" +
                        "        }\n" +
                        "    }";
        final String testName = "HelloWorldTest";

        Map.Entry<String,String> processResult = classProcessor.processClass(className, classCode, testName, null);
        String resultString = processResult.getValue();
        String resultMarkString = processResult.getKey();
        assertEquals("", resultMarkString, "10");
        assertTrue(resultString.contains("Невозможно создать объект класса, возможно класс не public"));
    }

    @Test
    public void testMainWrongMessage() throws Exception {
        final String className = "HelloWorld";
        final String classCode = "public class HelloWorld{public static void main(String[] args){" +
                "System.out.println(\"hello world\");}}";
        final String testName = "HelloWorldTest";

        Map.Entry<String,String> processResult = classProcessor.processClass(className, classCode, testName, null);
        String resultString = processResult.getValue();
        assertTrue(resultString.contains("Метод main должен выводить в консоль сообщение 'Hello world'"));
    }

    @Test
    public void testMainCorrectMessage() throws Exception {
        final String className = "HelloWorld";
        final String classCode = "public class HelloWorld{public static void main(String[] args){" +
                "System.out.println(\"Hello world\");}}";
        final String testName = "HelloWorldTest";

        Map.Entry<String,String> processResult = classProcessor.processClass(className, classCode, testName, null);
        String resultString = processResult.getValue();
        String resultMarkString = processResult.getKey();
        assertEquals("Задание выполнено", resultString);
        assertTrue("100".equals(resultMarkString));
    }


    @Test
    public void testThrowException() throws Exception {
        final String className = "HelloWorld";
        final String classCode = "package a;" +
                "import java.util.Scanner;" +
                "public class HelloWorld{public static void main(String[] args){" +
                "   throw new RuntimeException();\n" +
                "}}";
        final String testName = "HelloWorldTest";

        Map.Entry<String,String> processResult = classProcessor.processClass(className, classCode, testName, null);
        String resultString = processResult.getValue();
        String resultMarkString = processResult.getKey();
        assertTrue(resultString.contains("В результате выполнения, было выброшено исключение java.lang.RuntimeException"));
        assertTrue("10".equals(resultMarkString));

    }
}

