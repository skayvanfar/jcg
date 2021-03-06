package ir.sk.jcg.jcgengine.model.project;

import ir.sk.jcg.jcgcommon.PropertyView.annotation.Editable;
import ir.sk.jcg.jcgengine.model.project.component.Label;
import ir.sk.jcg.jcgengine.model.project.component.Link;
import ir.sk.jcg.jcgengine.model.project.component.Tag;
import ir.sk.jcg.jcgengine.model.project.component.TextField;

import javax.xml.bind.annotation.*;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 7/7/2016
 */
@XmlAccessorType(XmlAccessType.NONE)
@XmlSeeAlso({Label.class, TextField.class, Link.class, Tag.class})
@Editable
public abstract class Component extends ModelElement { // TODO: 7/8/2016 may better use Interface

    private EntityElement targetEntityElement;

    // TODO: 7/8/2016 add Converters and Validators


    public EntityElement getTargetEntityElement() {
        return targetEntityElement;
    }

    @XmlElement(name = "targetEntityElement")
    @XmlIDREF
    public void setTargetEntityElement(EntityElement targetEntityElement) {
        this.targetEntityElement = targetEntityElement;
    }

}
