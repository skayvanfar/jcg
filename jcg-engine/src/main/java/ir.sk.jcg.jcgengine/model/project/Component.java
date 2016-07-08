package ir.sk.jcg.jcgengine.model.project;

import ir.sk.jcg.jcgcommon.PropertyView.annotation.Editable;
import ir.sk.jcg.jcgengine.model.project.component.TextAreaComponent;
import ir.sk.jcg.jcgengine.model.project.component.TextBoxComponent;

import javax.xml.bind.annotation.*;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 7/7/2016
 */
@XmlAccessorType(XmlAccessType.NONE)
@XmlSeeAlso({TextBoxComponent.class})
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
