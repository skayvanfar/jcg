package ir.sk.jcg.jcgengine.model.project.enums;

import ir.sk.jcg.jcgcommon.enums.EnumBase;
import ir.sk.jcg.jcgengine.model.project.Component;
import ir.sk.jcg.jcgengine.model.project.component.LabelComponent;
import ir.sk.jcg.jcgengine.model.project.component.TextFieldComponent;

import java.util.Objects;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 7/8/2016
 */
public enum OutputComponentType implements EnumBase {

    LABEL_COMPONENT(0, "Label");

    private Integer value;
    private String desc;

    OutputComponentType(Integer value, String desc) {
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

    public static OutputComponentType valueOf(Integer type) {
        for (OutputComponentType code : OutputComponentType.values()) {
            if (Objects.equals(type, code.getValue())) {
                return code;
            }
        }
        return null;
    }

    public Component createComponent() {
        Component component = null;
        switch (this) {
            case LABEL_COMPONENT:
                component = new LabelComponent();
                break;
        }
        return component;
    }

    @Override
    public String toString() {
        return desc;
    }
}
