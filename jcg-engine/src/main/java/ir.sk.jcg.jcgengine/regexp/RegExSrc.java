package ir.sk.jcg.jcgengine.regexp;

import ir.sk.jcg.jcgengine.exception.ModelElementAlreadyExistException;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 9/2/2017.
 */
public class RegExSrc {

    private String src;

    private RegExType regExType;

    public RegExSrc(String src, RegExType regExType) {
        this.src = src;
        this.regExType = regExType;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public RegExType getRegExType() {
        return regExType;
    }

    public void setRegExType(RegExType regExType) {
        this.regExType = regExType;
    }

    /**
     * Return All generated code sections
     * @param generatedCodeType
     * @return
     */
    public List<String> getGeneratedCode(GeneratedCodeType generatedCodeType) {
        List<String> founded = new ArrayList<>();
        Pattern pattern = Pattern.compile(String.format(regExType.getFindSectionExpression(), generatedCodeType.getDescription()));
        Matcher matcher = pattern.matcher(getSrc());
        while (matcher.find()) {
            founded.add(matcher.group("type"));
            System.out.println(matcher.group(0));
            System.out.println(matcher.group(1));
            System.out.println(matcher.group("type"));
        }
        return founded;
    }

    /**
     * Specify code already exist in generated code section
     * @param generatedCodeType
     * @param code - code of element like property, control, ...
     * @return
     */
    public boolean hasModelElement(GeneratedCodeType generatedCodeType, String code) {
        boolean result = false;
        String str = String.format(regExType.getHasModelElementExpression(), generatedCodeType.getDescription(), generatedCodeType.getFreeFormRegexp(code));
        Pattern pattern = Pattern.compile(str);
        Matcher matcher = pattern.matcher(getSrc());
        if (matcher.find()) {
            result = true;
        }

        return result;
    }

    /**
     * Add the code at the end of generated code section
     * @param generatedCodeType
     * @param code
     */
    public String addModelElement(GeneratedCodeType generatedCodeType, String code) throws ModelElementAlreadyExistException {
        boolean isExist = hasModelElement(generatedCodeType, code);
        if (isExist) {
            throw new ModelElementAlreadyExistException();
        } else {
            Pattern pattern = Pattern.compile(String.format(regExType.getAddModelElementExpression(), generatedCodeType.getDescription()));
            Matcher matcher = pattern.matcher(getSrc());
            if (matcher.find()) {
                StringBuffer sb = new StringBuffer();
                //  matcher.appendReplacement(sb ,matcher.group(0).replaceFirst(Pattern.quote(matcher.group("insertPlace")) + " GENERATED CODE END ", "\n" + code + "\n GENERATED CODE END "));
                //    matcher.appendReplacement(sb ,matcher.group(0).replaceFirst(Pattern.quote(matcher.group("insertPlace")) + "/\\* GENERATED CODE END \\*/", "\n" + code + "\n/\\* GENERATED CODE END \\*/"));
                //   matcher.appendReplacement(sb ,matcher.group(0).replaceFirst(Pattern.quote(matcher.group("insertPlace")), "\n" + code));
                matcher.appendReplacement(sb ,matcher.group(0).replaceFirst(Pattern.quote(matcher.group("insertPlace")) + regExType.getStartCharacters() + " GENERATED CODE END " + regExType.getEndCharacters(), "\n" + code + "\n"+ regExType.getStartCharacters() + " GENERATED CODE END " + regExType.getEndCharacters()));

                // append the rest of the contents
                matcher.appendTail(sb);
                return sb.toString();
            }
        }
        return null;
    }

}
