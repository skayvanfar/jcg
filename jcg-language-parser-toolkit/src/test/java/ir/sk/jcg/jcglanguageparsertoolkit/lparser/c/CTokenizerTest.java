package ir.sk.jcg.jcglanguageparsertoolkit.lparser.c;

import ir.sk.jcg.jcgcommon.util.FileUtils;
import ir.sk.jcg.jcglanguageparsertoolkit.lparser.tokenizer.Tokenizer;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 7/5/2017.
 */
public class CTokenizerTest {
    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void parse() throws Exception {
        int expectedValue = 152;

        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream("FirstTest.java");

        String fileContent = FileUtils.getFile(inputStream);

        Tokenizer tokenizer = new CTokenizer();

        tokenizer.parse(fileContent);

        /*for (Token token : tokenizer.getTokenStream().getTokenStack()) {
            System.out.println(token);
        }*/

        Assert.assertEquals(expectedValue, tokenizer.getTokenStream().size());
    }

}