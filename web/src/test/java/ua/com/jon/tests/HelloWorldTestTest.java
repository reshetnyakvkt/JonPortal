package ua.com.jon.tests;

import com.jon.tron.service.processor.ClassProcessor;
import com.jon.tron.service.compiler.CompilationResult;
import com.jon.tron.service.compiler.SourceCompiler;
import com.jon.tron.service.junit.UnitTestsRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Map;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;
import static junit.framework.Assert.assertTrue;


/**
 * Created with IntelliJ IDEA.
 * User: al1
 * Date: 9/4/13
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/testContext.xml"})
//@ComponentScan(basePackages = {"com.jon.tron.service.junit"})
public class HelloWorldTestTest {
    private SourceCompiler compiler = new SourceCompiler();

    @Autowired
    private UnitTestsRunner testsRunner;

    @Autowired
    private ClassProcessor classProcessor;

    @Before
    public void setUp() {
        //testsRunner = new UnitTestsRunner();
    }

    @Test
    public void testEmpty() throws Exception {
        final String className = "HelloWorld";
        final String classCode = "public class HelloWorld{}";
        final String testName = "Hello world";
        Map.Entry<String,String> processResult = classProcessor.processClass(className, classCode, testName);
        String resultString = processResult.getValue();
        assertTrue(resultString.contains("Метод main должен быть 'public static void main(String[] args)"));
    }

    @Test
    public void testWrongMain() throws Exception {
        final String className = "HelloWorld";
        final String classCode = "public class HelloWorld{public void main(){}}";
        final String testName = "Hello world";

        Map.Entry<String,String> processResult = classProcessor.processClass(className, classCode, testName);
        String resultString = processResult.getValue();
        assertTrue(resultString.contains("Метод main должен быть 'public static void main(String[] args)"));
    }

    @Test
    public void testMainWithoutMessage() throws Exception {
        final String className = "HelloWorld";
        final String classCode = "public class HelloWorld{public static void main(String[] args){}}";
        final String testName = "Hello world";

        Map.Entry<String,String> processResult = classProcessor.processClass(className, classCode, testName);
        String resultString = processResult.getValue();
        assertTrue(resultString.contains("Метод main должен выводить в консоль сообщение'Hello world'"));

        assertNotNull(processResult.getValue());
    }
}

