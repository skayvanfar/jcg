package ir.sk.jcg.jcgengine.model.project;

import ir.sk.jcg.jcgcommon.PropertyView.annotation.Editable;
import ir.sk.jcg.jcgengine.model.project.component.LabelComponent;
import ir.sk.jcg.jcgengine.model.project.component.TextFieldComponent;

import javax.xml.bind.annotation.*;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 7/7/2016
 */
@XmlAccessorType(XmlAccessType.NONE)
@XmlSeeAlso({LabelComponent.class, TextFieldComponent.class})
@Editable
public abstract class Component extends ModelElement { // TODO: 7/8/2016 may better use Interface

    private Property targetProperty;

    // TODO: 7/8/2016 add Converters and Validators

    public Property getTargetProperty() {
        return targetProperty;
    }

    @XmlElement
    public void setTargetProperty(Property targetProperty) {
        this.targetProperty = targetProperty;
    }
}
