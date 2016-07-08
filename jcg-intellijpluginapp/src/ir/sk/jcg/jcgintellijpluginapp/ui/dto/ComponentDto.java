package ir.sk.jcg.jcgintellijpluginapp.ui.dto;

import ir.sk.jcg.jcgengine.model.project.Property;
import ir.sk.jcg.jcgengine.model.project.enums.ComponentType;

import java.io.Serializable;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 7/8/2016
 */
public class ComponentDto implements Serializable {

    private String name;
    private Property targetProperty;
    private ComponentType componentType;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Property getTargetProperty() {
        return targetProperty;
    }

    public void setTargetProperty(Property targetProperty) {
        this.targetProperty = targetProperty;
    }

    public ComponentType getComponentType() {
        return componentType;
    }

    public void setComponentType(ComponentType componentType) {
        this.componentType = componentType;
    }
}
