package ir.sk.jcg.jcgengine.model.project;

import ir.sk.jcg.jcgcommon.PropertyView.ComponentType;
import ir.sk.jcg.jcgcommon.PropertyView.annotation.Prop;
import ir.sk.jcg.jcgengine.model.project.enums.CardinalityType;
import ir.sk.jcg.jcgengine.model.project.enums.CollectionType;
import ir.sk.jcg.jcgengine.model.project.enums.DirectionalityType;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlIDREF;
import java.io.Serializable;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 5/12/2016
 */
// @Editable
public class Relationship extends EntityElement implements Serializable {

    // Unique id
    @Prop(label = "Id")
    private String id;

    @Prop(label = "Cardinality Type", componentType = ComponentType.NON_EDITABLE_COMBO, editable = true, required = true)
    private CardinalityType cardinalityType;

    @Prop(label = "Directionality", componentType = ComponentType.NON_EDITABLE_COMBO, editable = true, required = true)
    private DirectionalityType directionalityType;

    // if direction head is hear
    @Prop(label = "Is Head")
    private boolean head;

    //  @Prop(label = "Target Entity", required = true) // TODO: 5/12/2016 may required = true not needed
    private Entity targetEntity;

    @Prop(label = "Collection Type", componentType = ComponentType.NON_EDITABLE_COMBO, editable = true, required = true)
    private CollectionType collectionType;

    private Relationship otherSideRelationship;

    public String getId() {
        return id;
    }

    @XmlAttribute
    public void setId(String id) {
        this.id = id;
    }

    public CardinalityType getCardinalityType() {
        return cardinalityType;
    }

    @XmlAttribute
    public void setCardinalityType(CardinalityType cardinalityType) {
        this.cardinalityType = cardinalityType;
    }


    public DirectionalityType getDirectionalityType() {
        return directionalityType;
    }

    @XmlAttribute
    public void setDirectionalityType(DirectionalityType directionalityType) {
        this.directionalityType = directionalityType;
    }

    public boolean isHead() {
        return head;
    }

    @XmlAttribute
    public void setHead(boolean head) {
        this.head = head;
    }

    public Entity getTargetEntity() {
        return targetEntity;
    }

    @XmlElement(name = "targetEntity")
    @XmlIDREF
    public void setTargetEntity(Entity targetEntity) {
        this.targetEntity = targetEntity;
    }

    public CollectionType getCollectionType() {
        return collectionType;
    }

    @XmlAttribute
    public void setCollectionType(CollectionType collectionType) {
        this.collectionType = collectionType;
    }

    public Relationship getOtherSideRelationship() {
        return otherSideRelationship;
    }

    @XmlElement(name = "otherSideRelationship")
    @XmlIDREF
    public void setOtherSideRelationship(Relationship otherSideRelationship) {
        this.otherSideRelationship = otherSideRelationship;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Relationship)) return false;

        Relationship that = (Relationship) o;

        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        String name = this.name;
        if (!collectionType.equals(CollectionType.NOTHING))
            name += " " + collectionType;

        return name;
    }
}
