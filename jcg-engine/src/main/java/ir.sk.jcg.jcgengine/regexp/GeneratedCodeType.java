package ir.sk.jcg.jcgengine.regexp;

import ir.sk.jcg.jcgcommon.enums.EnumBase;

import java.util.Objects;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 7/13/2017.
 */
public enum GeneratedCodeType implements EnumBase {

    PROPERTY(0, "Property", "Property", "", RegExType.JAVA_REG_EX_TYPE) { // TODO: 9/15/2017
        @Override
        public String getFreeFormRegexp(String code) {
            String result = "(?s)" + code;
            result = result.replaceAll("\\s+", "\\\\s+");
            result = result.replaceAll("\\s?;", "\\\\s*;");
            return result;
        }
    }, GETTER_SETTER(1, "GetterSetter", "GetterSetter", "", RegExType.JAVA_REG_EX_TYPE) { // TODO: 9/15/2017
        @Override
        public String getFreeFormRegexp(String code) {
            String result = "(?s)" + code;
            result = result.replaceAll("\\s+", "\\\\s+");
            result = result.replaceAll("\\s?;", "\\\\s*");
            return result;
        }
    },
    CREATE_EDIT_SERVICE(3, "Create Edit Service", "Service", "ormTechnology/hibernate/service/CreateServiceElement.vm", RegExType.JAVA_REG_EX_TYPE) {
        @Override
        public String getFreeFormRegexp(String code) {
            return null; // TODO: 9/23/2017
        }
    },
    CREATE_EDIT_SERVICE_IMPL(3, "Create Edit Service Impl", "ServiceImpl", "ormTechnology/hibernate/service/CreateServiceImplElement.vm", RegExType.JAVA_REG_EX_TYPE) {
        @Override
        public String getFreeFormRegexp(String code) {
            return null; // TODO: 9/23/2017
        }
    },
    SEARCH_CONTROL(2, "Search Controller", "Controller", "mvcTechnology/SpringMVC/controller/SearchControllerElement.vm", RegExType.JAVA_REG_EX_TYPE) {
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
    DISPLAY_CONTROL(3, "Search Controller", "Controller", "mvcTechnology/SpringMVC/controller/DisplayControllerElement.vm", RegExType.JAVA_REG_EX_TYPE) {
        @Override
        public String getFreeFormRegexp(String code) {
            return null; // TODO: 9/23/2017
        }
    },
    CREATE_EDIT_CONTROL(4, "Create Edit Controller", "Controller", "mvcTechnology/SpringMVC/controller/CreateControllerElement.vm", RegExType.JAVA_REG_EX_TYPE) {
        @Override
        public String getFreeFormRegexp(String code) {
            return null; // TODO: 9/23/2017
        }
    },
    TILES_DEFINITION(5, "Tiles Definition", "TilesDefinition", "mvcTechnology/SpringMVC/view/tiles/definition/tile-definitionElement.vm", RegExType.XML_REG_EX_TYPE) {
        @Override
        public String getFreeFormRegexp(String code) {
            String result = code.replaceAll("\\s+", ""); // TODO: 9/15/2017
            return result;
        }
    },
    TAG_CONTROL(4, "Tag", "Controller", "mvcTechnology/SpringMVC/controller/component/TagControllerElement.vm", RegExType.JAVA_REG_EX_TYPE) {
        @Override
        public String getFreeFormRegexp(String code) {
            return "ffefgfdgrgrgrg";
        }
    }; // TODO: 9/15/2017

    private Integer value;
    private String name;
    private String desc;
    private String pathTemplate;
    private RegExType regExType;

    GeneratedCodeType(Integer value, String name, String desc, String pathTemplate, RegExType regExType) {
        this.value = value;
        this. name = name;
        this.desc = desc;
        this.pathTemplate = pathTemplate;
        this.regExType = regExType;
    }

    @Override
    public Integer getValue() {
        return value;
    }

    public String getName() {
        return name;
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

    public static GeneratedCodeType valueOfByName(String name) {
        for (GeneratedCodeType code : GeneratedCodeType.values()) {
            if (Objects.equals(name, code.getName())) {
                return code;
            }
        }
        return null;
    }

    public abstract String getFreeFormRegexp(String code);
}
