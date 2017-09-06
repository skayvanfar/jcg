package ir.sk.jcg.jcgengine.regexp;

import ir.sk.jcg.jcgcommon.util.FileUtils;
import ir.sk.jcg.jcgengine.exception.ModelElementAlreadyExistException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 9/2/2017.
 */
public class JavaRegExSrcTest {
    private String fileContent;
    private RegExSrc regExSrc;

    @Before
    public void setUp() throws Exception {

        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream("ClassWithGeneratedCode.java");

        fileContent = FileUtils.getFileContentByInputStream(inputStream);
        regExSrc = new JavaRegExSrc(fileContent);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getGeneratedCode() throws Exception {
        regExSrc.getGeneratedCode(GeneratedCodeType.PROPERTY);
    }

    @Test
    public void hasModelElementForProperty() throws Exception {
        /*boolean expectedValue = true;
        boolean actualValue = regExSrc.hasModelElement(GeneratedCodeType.PROPERTY, "private Long testId;");
        Assert.assertEquals(expectedValue, actualValue);*/
    }

    @Test
    public void hasModelElementForPropertyByMistake() throws Exception {
        /*boolean expectedValue = false;
        boolean actualValue = regExSrc.hasModelElement(GeneratedCodeType.PROPERTY, "private Longg testId;");
        Assert.assertEquals(expectedValue, actualValue);*/
    }

    /*
    @Test
    public void hasModelElementForGetterSetter() throws Exception {
        boolean expectedValue = true;
        boolean actualValue = regExSrc.hasModelElement(GeneratedCodeType.PROPERTY, "private Long testId;");
        Assert.assertEquals(expectedValue, actualValue);
    }

    @Test
    public void hasModelElementForGetterSetterByMistake() throws Exception {
        boolean expectedValue = false;
        boolean actualValue = regExSrc.hasModelElement(GeneratedCodeType.PROPERTY, "private Longg testId;");
        Assert.assertEquals(expectedValue, actualValue);
    }*/

    @Test
    public void hasModelElementForControl() throws Exception {
        /*String code = "public void addUser(String User) {\n" +
                "        dao.saveUser(user);\n" +
                "    }";
        boolean expectedValue = true;
        boolean actualValue = regExSrc.hasModelElement(GeneratedCodeType.CONTROL, code);
        Assert.assertEquals(expectedValue, actualValue);*/
    }

    @Test
    public void hasModelElementForControlByMistake() throws Exception {
        /*String code = "public int addUser(String User) {\n" +
                "        dao.saveUser(user);\n" +
                "    }";
        boolean expectedValue = false;
        boolean actualValue = regExSrc.hasModelElement(GeneratedCodeType.CONTROL, code);
        Assert.assertEquals(expectedValue, actualValue);*/
    }

    @Test
    public void addModelElement() throws Exception {
        /*regExSrc.addModelElement(GeneratedCodeType.PROPERTY, "private int bb;");*/
    }

    /*@Test(expected = ModelElementAlreadyExistException.class)
    public void addModelElementWithExistedElement() throws Exception {
        regExSrc.addModelElement(GeneratedCodeType.PROPERTY, "private Long testId;");
    }*/

}