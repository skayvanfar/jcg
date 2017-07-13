package ir.sk.jcg.jcgengine.codeFormatter;

import ir.sk.jcg.jcgcommon.util.FileUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.InputStream;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 7/13/2017.
 */
public class JavaCodeFormatterTest {

    private CodeFormatter codeFormatter;

    @Before
    public void setUp() throws Exception {
        codeFormatter = new JavaCodeFormatter();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void format() throws Exception {

        // retrieve the source to format
        /*String source = null;

        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream("FirstTestUnformatted.java");

        String fileContent = FileUtils.getFile(inputStream);

        classLoader = getClass().getClassLoader();
        inputStream = classLoader.getResourceAsStream("FirstTestFormatted.java");
        String expectedValue = FileUtils.getFile(inputStream);
        String actualValue = codeFormatter.format(fileContent);*/
 //       Assert.assertEquals(expectedValue, actualValue);
    }

    @Test
    public void formatFile() throws Exception {

        /*boolean actualValue = codeFormatter.formatFile(new File(getClass().getClassLoader().getResource("FirstTestUnformatted.java").getFile()));
        System.out.println(actualValue);*/
    }
}