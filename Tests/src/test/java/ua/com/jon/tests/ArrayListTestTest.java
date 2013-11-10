package ua.com.jon.tests;

import com.jon.tron.service.processor.ClassProcessor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Map;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

/**
 * Created with IntelliJ IDEA.
 * User: Julia
 * Date: 27.10.13
 * Time: 19:28
 * To change this template use File | Settings | File Templates.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/testContext.xml"})
public class ArrayListTestTest {
    @Autowired
    private ClassProcessor classProcessor;

    @Test
    public void testSuccess() throws Exception {
        final String className = "";
        final String classCode = "";
        final String testName = "ArrayListTest";
        Map.Entry<String,String> processResult = classProcessor.processClass(className, classCode, testName);
        String resultString = processResult.getValue();
        String markString = processResult.getKey();
        assertEquals("Задание выполнено", resultString);
        assertEquals("100", markString);
    }

    @Test
    public void testNoOut() throws Exception {
        final String className = "MyArrayList";
        final String classCode =
                "package forProject;\n" +
                        "\n" +
                        "import java.util.Scanner;\n" +
                        "\n" +
                        "public class MyArrayList {\n" +
                        "\n" +
                        "    public static void getText(){\n" +
                        "        Scanner scan = new Scanner(System.in);\n" +
                        "        String listStr = scan.nextLine();\n" +
                        "    }\n" +
                        "\n" +
                        "    public static void main(String[] args) {\n" +
                        "        MyArrayList.getText();\n" +
                        "    }\n" +
                        "}\n";
        final String testName = "ArrayListTest";
        Map.Entry<String,String> processResult = classProcessor.processClass(className, classCode, testName);
        String resultString = processResult.getValue();
        String markString = processResult.getKey();
        assertTrue(resultString.contains("Ожидаемый результат"));
        assertEquals("10", markString);
    }

    @Test
    public void testInfinitLoop() throws Exception {
        final String className = "MyArrayList";
        final String classCode =
                "public class MyArrayList {" +
                        "   public static void main(String[] args) {" +
                        "       while(true);" +
                        "   }" +
                        "}";
        final String testName = "ArrayListTest";
        Map.Entry<String,String> processResult = classProcessor.processClass(className, classCode, testName);
        String resultString = processResult.getValue();
        String markString = processResult.getKey();
        assertEquals("test timed out after 1000 milliseconds", resultString);
        assertEquals("10", markString);
    }

    @Test
    public void testAddIncorrect(){
        final String className = "MyArrayList";
        final String classCode ="    public class MyArrayList {\n" +

                "        public boolean add(Integer element){\n" +
                "            return true;\n" +
                "        }\n" +
                "    }";
        final String testName = "ArrayListTest";
        Map.Entry<String,String> processResult = classProcessor.processClass(className, classCode, testName);
        String resultString = processResult.getValue();
        String markString = processResult.getKey();
        assertTrue(resultString.contains("отсутствует"));
        assertEquals("10", markString);
    }
}
