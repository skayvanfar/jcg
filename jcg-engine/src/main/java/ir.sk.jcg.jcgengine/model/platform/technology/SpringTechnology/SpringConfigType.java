package ir.sk.jcg.jcgengine.model.platform.technology.SpringTechnology;

import ir.sk.jcg.jcgcommon.enums.EnumBase;

import java.util.Objects;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 5/20/2016
 */
public enum SpringConfigType implements EnumBase {

    JAVA(1, "Java"),
    XML_FILE(2, "xml file");

    private Integer value;
    private String desc;

    SpringConfigType(Integer value, String desc) {
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

    public static SpringConfigType valueOf(Integer type) {
        for (SpringConfigType code : SpringConfigType.values()) {
            if (Objects.equals(type, code.getValue())) {
                return code;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return desc;
    }
}
