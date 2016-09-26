package ir.sk.jcg.jcgengine.model.project.enums;

import ir.sk.jcg.jcgcommon.enums.EnumBase;

import java.util.Objects;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 5/31/16.
 */
public enum CollectionType implements EnumBase {

    LIST(0, "List"),
    SET(1, "Set"),
    COLLECTION(2, "Collection"),
    NOTHING(3, "Nothing");

    private Integer value;
    private String desc;

    CollectionType(Integer value, String desc) {
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

    public static CollectionType valueOf(Integer type) {
        for (CollectionType code : CollectionType.values()) {
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
