package ir.sk.jcg.jcgintellijpluginapp.ui.dto;

import ir.sk.jcg.jcgcommon.PropertyView.ComponentType;
import ir.sk.jcg.jcgcommon.PropertyView.annotation.Prop;
import ir.sk.jcg.jcgengine.model.project.Entity;
import ir.sk.jcg.jcgengine.model.project.Relationship;
import ir.sk.jcg.jcgengine.model.project.enums.CardinalityType;
import ir.sk.jcg.jcgengine.model.project.enums.CollectionType;
import ir.sk.jcg.jcgengine.model.project.enums.DirectionalityType;

import java.io.Serializable;

/**
 * This class contaion information about a relationship with its sides.
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 6/1/2016.
 */
public class RelationShipDto implements Serializable {

    public RelationShipDto(CardinalityType cardinalityType, DirectionalityType directionalityType, Entity targetEntity, CollectionType collectionType) {
        this.cardinalityType = cardinalityType;
        this.directionalityType = directionalityType;
        this.targetEntity = targetEntity;
        this.collectionType = collectionType;
    }

    public RelationShipDto(Relationship relationShip) {
        this.cardinalityType = relationShip.getCardinalityType();
        this.directionalityType = relationShip.getDirectionalityType();
        this.targetEntity = relationShip.getTargetEntity();
        this.collectionType = relationShip.getCollectionType();
    }

    private CardinalityType cardinalityType;

    private DirectionalityType directionalityType;

    private Entity targetEntity;

    private CollectionType collectionType;

    public RelationShipDto() {

    }

    public CardinalityType getCardinalityType() {
        return cardinalityType;
    }

    public void setCardinalityType(CardinalityType cardinalityType) {
        this.cardinalityType = cardinalityType;
    }

    public DirectionalityType getDirectionalityType() {
        return directionalityType;
    }

    public void setDirectionalityType(DirectionalityType directionalityType) {
        this.directionalityType = directionalityType;
    }

    public Entity getTargetEntity() {
        return targetEntity;
    }

    public void setTargetEntity(Entity targetEntity) {
        this.targetEntity = targetEntity;
    }

    public CollectionType getCollectionType() {
        return collectionType;
    }

    public void setCollectionType(CollectionType collectionType) {
        this.collectionType = collectionType;
    }
}
