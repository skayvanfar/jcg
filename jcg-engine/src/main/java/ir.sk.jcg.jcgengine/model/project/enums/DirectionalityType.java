package ir.sk.jcg.jcgengine.model.project.enums;

import ir.sk.jcg.jcgcommon.enums.EnumBase;

import java.util.Objects;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 5/31/16.
 */
public enum DirectionalityType implements EnumBase {

    UNIDIRECTIONAL(0, "Unidirectional"),
    BIDIRECTIONAL(0, "Bidirectional");

    private Integer value;
    private String desc;

    DirectionalityType(Integer value, String desc) {
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

    public static DirectionalityType valueOf(Integer type) {
        for (DirectionalityType code : DirectionalityType.values()) {
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
