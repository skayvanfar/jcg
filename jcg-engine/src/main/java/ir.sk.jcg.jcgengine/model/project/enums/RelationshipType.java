package ir.sk.jcg.jcgengine.model.project.enums;

import ir.sk.jcg.jcgcommon.enums.EnumBase;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 5/12/2016
 */
public enum RelationshipType implements EnumBase {

    ONE_TO_ONE_UNIDIRECTIONAL(0, "1 ... 1 Unidirectional"),
    ONE_TO_MANY_UNIDIRECTIONAL(0, "1 ... * Unidirectional"),
    Many_TO_ONE_UNIDIRECTIONAL(0, "* ... 1 Unidirectional"),
    MANY_TO_MANY_UNIDIRECTIONAL(0, "* ... * Unidirectional"),
    ONE_TO_ONE_BIDIRECTIONAL(1, "1 ... 1 Bidirectional"),
    ONE_TO_MANY_BIDIRECTIONAL(2, "1 ... * Bidirectional"),
    MANY_TO_ONE_BIDIRECTIONAL(2, "* ... 1 Bidirectional"),
    MANY_TO_MANY_BIDIRECTIONAL(3, "* ... * Bidirectional");

    private Integer value;
    private String desc;

    RelationshipType(Integer value, String desc) {
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

    public static RelationshipType valueOf(Integer type) {
        for (RelationshipType code : RelationshipType.values()) {
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
