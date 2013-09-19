package ua.com.jon.tests;

import com.jon.tron.service.processor.ClassProcessor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Map;

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
        final String className = "HelloWorld";
        final String classCode = "public class HelloWorld{}";
        final String testName = "Hello world";
        Map.Entry<String,String> processResult = classProcessor.processClass(className, classCode, testName);
        String resultString = processResult.getValue();
        assertTrue(resultString.contains("Метод main должен быть 'public static void main(String[] args)"));
    }
}
