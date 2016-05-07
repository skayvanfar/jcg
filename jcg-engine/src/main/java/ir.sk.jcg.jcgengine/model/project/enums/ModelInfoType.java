package ir.sk.jcg.jcgengine.model.project.enums;

import ir.sk.jcg.jcgcommon.enums.EnumBase;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 5/7/2016
 */
public enum ModelInfoType implements EnumBase {

    ANNOTATION(0, "Annotation"),
    HBM_XML_FILE(1, "hbm xml file");

    private Integer value;
    private String desc;

    ModelInfoType(Integer value, String desc) {
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

    public static ModelInfoType valueOf(Integer type) {
        for (ModelInfoType code : ModelInfoType.values()) {
            if (type == code.getValue()) {
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

