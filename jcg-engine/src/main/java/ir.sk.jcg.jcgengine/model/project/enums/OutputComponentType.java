package ir.sk.jcg.jcgengine.model.project.enums;

import ir.sk.jcg.jcgcommon.enums.EnumBase;
import ir.sk.jcg.jcgengine.model.project.Component;
import ir.sk.jcg.jcgengine.model.project.component.Label;
import ir.sk.jcg.jcgengine.model.project.component.Link;
import ir.sk.jcg.jcgengine.model.project.component.LinkList;

import java.util.Objects;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 7/8/2016
 */
public enum OutputComponentType implements EnumBase {

    LABEL(0, "Label"),
    LINK(1, "Link"),
    LINK_LIST(2, "LinkList");

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
            case LABEL:
                component = new Label();
                break;
            case LINK:
                component = new Link();
                break;
            case LINK_LIST:
                component = new LinkList();
                break;
        }
        return component;
    }

    @Override
    public String toString() {
        return desc;
    }
}
