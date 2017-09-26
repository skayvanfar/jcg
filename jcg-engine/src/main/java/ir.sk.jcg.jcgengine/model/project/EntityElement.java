package ir.sk.jcg.jcgengine.model.project;

import ir.sk.jcg.jcgcommon.PropertyView.ComponentType;
import ir.sk.jcg.jcgcommon.PropertyView.annotation.Editable;
import ir.sk.jcg.jcgcommon.PropertyView.annotation.Prop;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSeeAlso;
import java.io.Serializable;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 9/24/2017.
 */
@XmlSeeAlso({Id.class, Property.class, Relationship.class})
@Editable
public class EntityElement extends ModelElement implements Serializable {

    @Prop(label = "Label Name", editable = true, required = true)
    private String labelName;

    @Prop(label = "Is Unique", componentType = ComponentType.BOOLEAN_CHECKBOX, editable = true, required = true)
    private boolean unique;

    public EntityElement() {
    }

    public EntityElement(String labelName) {
        this.labelName = labelName;
    }

    public String getLabelName() {
        return labelName;
    }

    @XmlAttribute
    public void setLabelName(String labelName) {
        this.labelName = labelName;
    }

    public boolean isUnique() {
        return unique;
    }

    @XmlAttribute
    public void setUnique(boolean unique) {
        this.unique = unique;
    }
}
