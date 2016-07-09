package ir.sk.jcg.jcgengine.model.project.enums;

import ir.sk.jcg.jcgcommon.enums.EnumBase;
import ir.sk.jcg.jcgengine.model.project.*;
import ir.sk.jcg.jcgengine.model.project.component.LabelComponent;
import ir.sk.jcg.jcgengine.model.project.component.TextAreaComponent;
import ir.sk.jcg.jcgengine.model.project.component.TextFieldComponent;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 7/8/2016
 */
public enum InputComponentType implements EnumBase {

    LABEL_COMPONENT(0, "Label"),
    TEXT_BOX_COMPONENT(0, "Text Field"),
    TEXT_AREA_COMPONENT(1, "Text Area");

    private Integer value;
    private String desc;

    InputComponentType(Integer value, String desc) {
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

    public static InputComponentType valueOf(Integer type) {
        for (InputComponentType code : InputComponentType.values()) {
            if (type == code.getValue()) {
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
            case TEXT_BOX_COMPONENT:
                component = new TextFieldComponent();
                break;
            case TEXT_AREA_COMPONENT:
                component = new TextAreaComponent();
                break;
        }
        return component;
    }

    @Override
    public String toString() {
        return desc;
    }

}
