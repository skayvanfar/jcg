package ir.sk.jcg.jcgengine.regexp;

import ir.sk.jcg.jcgcommon.enums.EnumBase;

import java.util.Objects;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 7/13/2017.
 */
public enum GeneratedCodeType implements EnumBase {

    PROPERTY(0, "Property", "", RegExType.JAVA_REG_EX_TYPE) { // TODO: 9/15/2017
        @Override
        public String getFreeFormRegexp(String code) {
            String result = "(?s)" + code;
            result = result.replaceAll("\\s+", "\\\\s+");
            result = result.replaceAll("\\s?;", "\\\\s*;");
            return result;
        }
    },
    GETTER_SETTER(1, "GetterSetter", "", RegExType.JAVA_REG_EX_TYPE) { // TODO: 9/15/2017
        @Override
        public String getFreeFormRegexp(String code) {
            String result = "(?s)" + code;
            result = result.replaceAll("\\s+", "\\\\s+");
            result = result.replaceAll("\\s?;", "\\\\s*");
            return result;
        }
    },
    SEARCH_CONTROL(2, "Controller", "mvcTechnology/SpringMVC/controller/SearchControllerElement.vm", RegExType.JAVA_REG_EX_TYPE) {
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
    DISPLAY_CONTROL(2, "Controller", "mvcTechnology/SpringMVC/controller/DisplayControllerElement.vm", RegExType.JAVA_REG_EX_TYPE) {
        @Override
        public String getFreeFormRegexp(String code) {
            return null; // TODO: 9/23/2017
        }
    },
    CREATE_CONTROL(2, "Controller", "mvcTechnology/SpringMVC/controller/CreateControllerElement.vm", RegExType.JAVA_REG_EX_TYPE) {
        @Override
        public String getFreeFormRegexp(String code) {
            return null; // TODO: 9/23/2017
        }
    },
    TILES_DEFINITION(3, "TilesDefinition", "mvcTechnology/SpringMVC/view/tiles/definition/tile-definitionElement.vm", RegExType.XML_REG_EX_TYPE) {
        @Override
        public String getFreeFormRegexp(String code) {
            String result = code.replaceAll("\\s+", ""); // TODO: 9/15/2017
            return result;
        }
    }; // TODO: 9/15/2017

    private Integer value;
    private String desc;
    private String pathTemplate;
    private RegExType regExType;

    GeneratedCodeType(Integer value, String desc, String pathTemplate, RegExType regExType) {
        this.value = value;
        this.desc = desc;
        this.pathTemplate = pathTemplate;
        this.regExType = regExType;
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

    public RegExType getRegExType() {
        return regExType;
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
