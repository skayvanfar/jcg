package ir.sk.jcg.jcglanguageparsertoolkit.lparser.java;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 7/6/2017.
 */
public class JavaDocTest {

    JavaDoc javaDoc;

    @Before
    public void setUp() throws Exception {
        javaDoc = new JavaDoc();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void parse() throws Exception {
        String text = "/**\n" +
                "     * This adds some more text to the tag. This is the method you would\n" +
                "     * override if you wanted to add your own JavaDoc tag to a parser, but\n" +
                "     * only if that tag had special parsing requirements.\n" +
                "     * @param key - The tag name (e.g. '@see' )\n" +
                "     * @param text - The text of the tag\n" +
                "     */";

        javaDoc.parse(text);
        System.out.println();

    }

    @Test
    public void addToTags() throws Exception {

    }

    @Test
    public void cleanTags() throws Exception {
    }

}