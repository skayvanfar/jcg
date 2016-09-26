package ir.sk.jcg.jcgengine.model.project.enums;

import ir.sk.jcg.jcgcommon.enums.EnumBase;

import java.util.Objects;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 5/12/2016
 */
public enum CardinalityType implements EnumBase {

    ONE_TO_ONE(0, "1 ... 1"),
    ONE_TO_MANY(0, "1 ... *"),
    Many_TO_ONE(0, "* ... 1"),
    MANY_TO_MANY(0, "* ... *");

    private Integer value;
    private String desc;

    CardinalityType(Integer value, String desc) {
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

    public static CardinalityType valueOf(Integer type) {
        for (CardinalityType code : CardinalityType.values()) {
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
