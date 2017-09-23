package ir.sk.jcg.jcgengine.model.project.component;

import ir.sk.jcg.jcgcommon.enums.EnumBase;
import ir.sk.jcg.jcgengine.model.project.enums.CollectionType;

import java.util.Objects;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 9/23/2017.
 */
public enum OrientationType implements EnumBase {

    LIST(0, "Horizontal"),
    SET(1, "Vertical");

    private Integer value;
    private String desc;

    OrientationType(Integer value, String desc) {
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

    public static OrientationType valueOf(Integer type) {
        for (OrientationType code : OrientationType.values()) {
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
