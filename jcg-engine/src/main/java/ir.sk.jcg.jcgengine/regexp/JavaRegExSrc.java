package ir.sk.jcg.jcgengine.regexp;

import ir.sk.jcg.jcgengine.exception.ModelElementAlreadyExistException;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 9/2/2017.
 */
public class JavaRegExSrc extends RegExSrc {

    private static final String FIND_SECTION_EXPRESSION = "(?s)/\\* GENERATED CODE SECTION TYPE\\((?<type>%s)\\)(((?!GENERATED CODE SECTION END).)*?)/\\* GENERATED CODE END \\*/";

    private static final String HAS_MODEL_ELEMENT_EXPRESSION = "(?s)/\\* GENERATED CODE SECTION TYPE\\((?<type>%s)\\)(((?!GENERATED CODE SECTION END).*%s.*)*?)/\\* GENERATED CODE END \\*/";

    private static final String ADD_MODEL_ELEMENT_EXPRESSION = "(?s)/\\* GENERATED CODE SECTION TYPE\\((?<type>%s)\\)(((?!GENERATED CODE SECTION END).)*?)(?<insertPlace>)/\\* GENERATED CODE END \\*/";

    private static final String FIND_GENERATED_CODE_SECTION_EXPRESSION = "(?s)/\\* GENERATED CODE SECTION TYPE\\((?<type>%s)\\)(((?!GENERATED CODE SECTION END).)*?)*(?<insertPlace>)/\\* GENERATED CODE END \\*/";

    static String tst = "(?s)/\\* GENERATED CODE SECTION TYPE\\((?<type>%s)\\)(((?!GENERATED CODE SECTION END).)*?)(?<insertPlace>)\\s/\\* GENERATED CODE END \\*/";

    /*
    * Example:

    String:

    "TEST 123"
    RegExp:

    "(?<login>\\w+) (?<id>\\d+)"
    Access

    matcher.group(1) ==> TEST
    matcher.group("login") ==> TEST
    matcher.name(1) ==> login
    Replace

    matcher.replaceAll("aaaaa_$1_sssss_$2____") ==> aaaaa_TEST_sssss_123____
    matcher.replaceAll("aaaaa_${login}_sssss_${id}____") ==> aaaaa_TEST_sssss_123____
    * */

    public JavaRegExSrc(String src) {
        super(src);
    }



    @Override
    public List<String> getGeneratedCode(GeneratedCodeType generatedCodeType) {
        List<String> founded = new ArrayList<>();
        Pattern pattern = Pattern.compile(String.format(FIND_SECTION_EXPRESSION, generatedCodeType.getDescription()));
        Matcher matcher = pattern.matcher(getSrc());
        while (matcher.find()) {
            founded.add(matcher.group("type"));
            System.out.println(matcher.group(0));
            System.out.println(matcher.group(1));
            System.out.println(matcher.group("type"));
        }
        return founded;
    }

    @Override
    public boolean hasModelElement(GeneratedCodeType generatedCodeType, String code) {
        boolean result = false;
        String str = String.format(HAS_MODEL_ELEMENT_EXPRESSION, generatedCodeType.getDescription(), generatedCodeType.getFreeFormRegexp(code));
        Pattern pattern = Pattern.compile(str);
        Matcher matcher = pattern.matcher(getSrc());
        if (matcher.find()) {
            result = true;
        }

        return result;
    }

    @Override
    public String addModelElement(GeneratedCodeType generatedCodeType, String code) throws ModelElementAlreadyExistException {
        boolean isExist = hasModelElement(generatedCodeType, code);
        if (isExist) {
            throw new ModelElementAlreadyExistException();
        } else {
            Pattern pattern = Pattern.compile(String.format(ADD_MODEL_ELEMENT_EXPRESSION, generatedCodeType.getDescription()));
            Matcher matcher = pattern.matcher(getSrc());
            if (matcher.find()) {
                StringBuffer sb = new StringBuffer();
                matcher.appendReplacement(sb ,matcher.group(0).replaceFirst(Pattern.quote(matcher.group("insertPlace")) + "/\\* GENERATED CODE END \\*/", "\n" + code + "\n/\\* GENERATED CODE END \\*/"));
                // append the rest of the contents
                matcher.appendTail(sb);
                return sb.toString();
            }
        }
        return null;
    }

    /*@Override
    public void addModelElement(GeneratedCodeType generatedCodeType, String code) throws ModelElementAlreadyExistException {
        boolean isExist = hasModelElement(generatedCodeType, code);
        if (isExist) {
            throw new ModelElementAlreadyExistException();
        } else {
            Pattern pattern = Pattern.compile(String.format(ADD_MODEL_ELEMENT_EXPRESSION, generatedCodeType.getDescription()));
            Matcher matcher = pattern.matcher(getSrc());
            if (matcher.find()) {
                StringBuffer sb = new StringBuffer(matcher.group(0));
                sb.insert(matcher.group(0).indexOf("*//* GENERATED CODE END *//*") - 1, code + '\n');

                // append the rest of the contents
                matcher.appendTail(sb);
                replaceGeneratedCodeSection(sb);
                System.out.println(sb.toString());
            }
        }
    }

    public void replaceGeneratedCodeSection(String code) {
        List<String> founded = new ArrayList<>();
        Pattern pattern = Pattern.compile(FIND_GENERATED_CODE_SECTION_EXPRESSION);
        Matcher matcher = pattern.matcher(getSrc());
        while (matcher.find()) {
            founded.add(matcher.group("type"));
            System.out.println(matcher.group(0));
            System.out.println(matcher.group(1));
            System.out.println(matcher.group("type"));
        }
        return founded;
    }*/
}
