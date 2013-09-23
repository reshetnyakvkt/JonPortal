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
 * Date: 9/22/13
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/testContext.xml"})
public class MaxOfTwoTestTest {
    @Autowired
    private ClassProcessor classProcessor;

    @Test
    public void testSuccess() throws Exception {
        final String className = "MaxOfTwo";
        final String classCode =
                "package lesson;" +
                        "import java.util.Scanner;" +
                        "public class MaxOfTwo {" +
                        "   public static void main(String[] args) {" +
                        "       Scanner scan = new Scanner(System.in);" +
                        "       int first = scan.nextInt();" +
                        "       int second = scan.nextInt();" +
                        "       System.out.println(first + second);" +
                        "   }" +
                        "}";
        final String testName = "MaxOfTwo";
        Map.Entry<String,String> processResult = classProcessor.processClass(className, classCode, testName);
        String resultString = processResult.getValue();
        String markString = processResult.getKey();
        assertEquals("Задание выполнено", resultString);
        assertEquals("100", markString);
    }

    @Test
    public void testNoOut() throws Exception {
        final String className = "MaxOfTwo";
        final String classCode =
                "package lesson;" +
                        "import java.util.Scanner;" +
                        "public class MaxOfTwo {" +
                        "   public static void main(String[] args) {" +
                        "       Scanner scan = new Scanner(System.in);" +
                        "       int first = scan.nextInt();" +
                        "       int second = scan.nextInt();" +
                        "       //System.out.println(first + second);\n" +
                        "   }" +
                        "}";
        final String testName = "MaxOfTwo";
        Map.Entry<String,String> processResult = classProcessor.processClass(className, classCode, testName);
        String resultString = processResult.getValue();
        String markString = processResult.getKey();
        assertEquals("Результат должен быть числом, но выведено []\n", resultString);
        assertEquals("10", markString);
    }

    @Test
    public void testInfinitLoop() throws Exception {
        final String className = "MaxOfTwo";
        final String classCode =
                "public class MaxOfTwo {" +
                        "   public static void main(String[] args) {" +
                        "       while(true);" +
                        "   }" +
                        "}";
        final String testName = "MaxOfTwo";
        Map.Entry<String,String> processResult = classProcessor.processClass(className, classCode, testName);
        String resultString = processResult.getValue();
        String markString = processResult.getKey();
        assertEquals("test timed out after 1000 milliseconds\n", resultString);
        assertEquals("10", markString);
    }
}
