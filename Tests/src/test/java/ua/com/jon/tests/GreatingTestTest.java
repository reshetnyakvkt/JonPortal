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
 * User: Admin
 * Date: 19.09.13
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/testContext.xml"})
public class GreatingTestTest {
    @Autowired
    private ClassProcessor classProcessor;

    @Test
    public void testEmpty() throws Exception {
        final String className = "Greating";
        final String classCode =
                "public class Greating{" +
                "   public static void main(String[] args) {" +
                "       System.out.println(\"Здравствуйте Светлана\");" +
                "   }" +
                "}";
        final String testName = "Greating";
        Map.Entry<String,String> processResult = classProcessor.processClass(className, classCode, testName);
        String resultString = processResult.getValue();
        String markString = processResult.getKey();
        assertTrue(resultString.isEmpty());
        assertEquals("100", markString);
    }
}
