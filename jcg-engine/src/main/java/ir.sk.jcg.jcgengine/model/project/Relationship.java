package ir.sk.jcg.jcgengine.model.project;

import ir.sk.jcg.jcgcommon.PropertyView.ComponentType;
import ir.sk.jcg.jcgcommon.PropertyView.annotation.Editable;
import ir.sk.jcg.jcgcommon.PropertyView.annotation.Prop;
import ir.sk.jcg.jcgengine.model.project.enums.RelationshipType;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlIDREF;
import java.io.Serializable;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 5/12/2016
 */
@Editable
public class Relationship extends ModelElement implements Serializable {

    @Prop(label = "Relationship Type", componentType = ComponentType.NON_EDITABLE_COMBO, required = true)
    private RelationshipType relationshipType;

  //  @Prop(label = "Target Entity", required = true) // TODO: 5/12/2016 may required = true not needed
    private Entity targetEntity;


    public RelationshipType getRelationshipType() {
        return relationshipType;
    }

    @XmlAttribute
    public void setRelationshipType(RelationshipType relationshipType) {
        this.relationshipType = relationshipType;
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
