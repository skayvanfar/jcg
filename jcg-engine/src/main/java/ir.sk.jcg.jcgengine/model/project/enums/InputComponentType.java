package ir.sk.jcg.jcgengine.model.project.enums;

import ir.sk.jcg.jcgcommon.enums.EnumBase;
import ir.sk.jcg.jcgengine.model.project.Component;
import ir.sk.jcg.jcgengine.model.project.component.Label;
import ir.sk.jcg.jcgengine.model.project.component.Tag;
import ir.sk.jcg.jcgengine.model.project.component.TextArea;
import ir.sk.jcg.jcgengine.model.project.component.TextField;

import java.util.Objects;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 7/8/2016
 */
public enum InputComponentType implements EnumBase {

    LABEL_COMPONENT(0, "Label"),
    TEXT_BOX_COMPONENT(1, "Text Field"),
    TEXT_AREA_COMPONENT(2, "Text Area"),
    TAG(3, "Tag");

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
                component = new Label();
                break;
            case TEXT_BOX_COMPONENT:
                component = new TextField();
                break;
            case TEXT_AREA_COMPONENT:
                component = new TextArea();
                break;
            case TAG:
                component = new Tag();
                break;
        }
        return component;
    }

    @Override
    public String toString() {
        return desc;
    }

}
