package ir.sk.jcg.jcgengine.model.project.enums;

import ir.sk.jcg.jcgcommon.enums.EnumBase;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 5/12/2016
 */
public enum IdGeneratorType implements EnumBase {
    MANUAL(0, "Manual"),
    INCREMENTAL(1, "incremental"),
    NATIVE(2, "Native"); // TODO: 5/12/2016 more need

    private Integer value;
    private String desc;

    IdGeneratorType(Integer value, String desc) {
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

    public static IdGeneratorType valueOf(Integer type) {
        for (IdGeneratorType code : IdGeneratorType.values()) {
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
