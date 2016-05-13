package ir.sk.jcg.jcgengine.model.project.enums;

import ir.sk.jcg.jcgcommon.enums.EnumBase;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 5/12/2016
 */
public enum RelationType implements EnumBase {

    ONE_TO_ONE(0, "1 ... 1"),
    ONE_TO_MANY(1, "1 ... *"),
    MANY_TO_ONE(2, "* ... 1"),
    MANY_TO_MANY(3, "* ... *"); // TODO: 5/12/2016 add Bidirectional

    private Integer value;
    private String desc;

    RelationType(Integer value, String desc) {
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

    public static RelationType valueOf(Integer type) {
        for (RelationType code : RelationType.values()) {
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
