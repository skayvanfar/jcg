package ir.sk.jcg.jcgengine.regexp;

import ir.sk.jcg.jcgcommon.enums.EnumBase;

import java.util.Objects;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 7/13/2017.
 */
public enum GeneratedCodeType implements EnumBase {

    PROPERTY(0, "Property") {
        @Override
        public String getFreeFormRegexp(String code) {
            String result = "(?s)" + code;
            result = result.replaceAll("\\s+", "\\\\s+");
            result = result.replaceAll("\\s?;", "\\\\s*;");
            return result;
        }
    },
    GETTER_SETTER(1, "GetterSetter") {
        @Override
        public String getFreeFormRegexp(String code) {
            String result = "(?s)" + code;
            result = result.replaceAll("\\s+", "\\\\s+");
            result = result.replaceAll("\\s?;", "\\\\s*");
            return result;
        }
    },
    CONTROL(2, "Control") {
        @Override
        public String getFreeFormRegexp(String code) {
            String result = code.replaceAll("\\s+", "\\\\s+");
            result = result.replaceAll("\\s*;", "\\\\s*");
            result = result.replaceAll("\\s*\\(\\s*", "\\\\s*\\\\(\\\\s*"); // get(int a) --> get ( int a)
            result = result.replaceAll("\\s*\\)\\s*", "\\\\s*\\\\)\\\\s*"); // get(int a){ --> get(int a ) {
            result = result.replaceAll("\\s*\\{\\s*", "\\\\s*\\{\\\\s*");
            result = result.replaceAll("\\s*\\}\\s*", "\\\\s*\\\\}\\\\s*");
            result = result.replaceAll("\\{.*\\}", "\\\\{.*\\\\}");
            return result;
        }
    };

    private Integer value;
    private String desc;

    GeneratedCodeType(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    @Override
    public Integer getValue() {
        return value;
    }

    @Override
    public String getDescription() {
        return desc;
    }

    public static GeneratedCodeType valueOf(Integer type) {
        for (GeneratedCodeType code : GeneratedCodeType.values()) {
            if (Objects.equals(type, code.getValue())) {
                return code;
            }
        }
        return null;
    }

    public abstract String getFreeFormRegexp(String code);
}
