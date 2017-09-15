package ir.sk.jcg.jcgengine.regexp;

import ir.sk.jcg.jcgcommon.enums.EnumBase;

import java.util.Objects;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 7/13/2017.
 */
public enum GeneratedCodeType implements EnumBase {

    PROPERTY(0, "Property", "") { // TODO: 9/15/2017
        @Override
        public String getFreeFormRegexp(String code) {
            String result = "(?s)" + code;
            result = result.replaceAll("\\s+", "\\\\s+");
            result = result.replaceAll("\\s?;", "\\\\s*;");
            return result;
        }
    },
    GETTER_SETTER(1, "GetterSetter", "") { // TODO: 9/15/2017
        @Override
        public String getFreeFormRegexp(String code) {
            String result = "(?s)" + code;
            result = result.replaceAll("\\s+", "\\\\s+");
            result = result.replaceAll("\\s?;", "\\\\s*");
            return result;
        }
    },
    CONTROL(2, "Controller", "mvcTechnology/SpringMVC/controller/ControllerElement.vm") {
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
    },
    TILES_DEFINITIONS(3, "Tiles Definition", "") {
        @Override
        public String getFreeFormRegexp(String code) {
            String result = code.replaceAll("\\s+", ""); // TODO: 9/15/2017
            return result;
        }
    }; // TODO: 9/15/2017

    private Integer value;
    private String desc;
    private String pathTemplate;

    GeneratedCodeType(Integer value, String desc, String pathTemplate) {
        this.value = value;
        this.desc = desc;
        this.pathTemplate = pathTemplate;
    }

    @Override
    public Integer getValue() {
        return value;
    }

    @Override
    public String getDescription() {
        return desc;
    }

    public String getPathTemplate() {
        return pathTemplate;
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
