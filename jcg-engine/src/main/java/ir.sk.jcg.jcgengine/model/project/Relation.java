package ir.sk.jcg.jcgengine.model.project;

import ir.sk.jcg.jcgcommon.PropertyView.ComponentType;
import ir.sk.jcg.jcgcommon.PropertyView.annotation.Editable;
import ir.sk.jcg.jcgcommon.PropertyView.annotation.Prop;
import ir.sk.jcg.jcgengine.model.project.enums.RelationType;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlIDREF;
import java.io.Serializable;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 5/12/2016
 */
@Editable
public class Relation extends ModelElement implements Serializable {

    @Prop(label = "Relation Type", componentType = ComponentType.NON_EDITABLE_COMBO, required = true)
    private RelationType relationType;

  //  @Prop(label = "Target Entity", required = true) // TODO: 5/12/2016 may required = true not needed
    private Entity targetEntity;


    public RelationType getRelationType() {
        return relationType;
    }

    @XmlAttribute
    public void setRelationType(RelationType relationType) {
        this.relationType = relationType;
    }

    public Entity getTargetEntity() {
        return targetEntity;
    }

    @XmlElement(name = "targetEntity")
    @XmlIDREF
    public void setTargetEntity(Entity targetEntity) {
        this.targetEntity = targetEntity;
    }
}
