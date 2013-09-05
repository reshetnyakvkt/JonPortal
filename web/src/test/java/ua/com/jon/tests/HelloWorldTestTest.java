package ua.com.jon.tests;

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

    @Before
    public void setUp() {
        //testsRunner = new UnitTestsRunner();
    }

    @Test
    public void testCompile() throws Exception {
        final String className = "HelloWorld";
        final String classCode = "public class HelloWorld{}";
        final String testName = "Hello world";
        CompilationResult compilationResult;
        compilationResult = compiler.compileSourceCode(className, classCode);

        Class unitClass = compilationResult.getClassByName(className);
        Map<String,String> stringStringMap = testsRunner.testSingleClass(unitClass, testName);
        System.out.println(stringStringMap);
    }
}
