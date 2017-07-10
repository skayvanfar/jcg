package ir.sk.jcg.jcgintellijpluginapp.ui.controller.impl;

import ir.sk.jcg.jcgcommon.util.StringUtils;
import ir.sk.jcg.jcgengine.model.project.Entity;
import ir.sk.jcg.jcgengine.model.project.Relationship;
import ir.sk.jcg.jcgengine.model.project.enums.CardinalityType;
import ir.sk.jcg.jcgengine.model.project.enums.CollectionType;
import ir.sk.jcg.jcgengine.model.project.enums.DirectionalityType;
import ir.sk.jcg.jcgintellijpluginapp.ui.controller.RelationshipController;
import ir.sk.jcg.jcgintellijpluginapp.ui.dto.RelationShipDto;

import java.util.UUID;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 6/1/2016.
 */
public class RelationshipControllerImpl implements RelationshipController {

    private static final RelationshipController INSTANCE = new RelationshipControllerImpl();

    public static RelationshipController getInstance() {
        return INSTANCE;
    }

    @Override
    public void createRelationship(RelationShipDto relationShipDto, Entity entity) {
        Relationship headRelationship = new Relationship();
        String uuid = UUID.randomUUID().toString();
        headRelationship.setId(uuid);
        initRelationships(entity, headRelationship, relationShipDto);
    }

    /**
     * @param entity
     * @param headRelationship
     * @param relationShipDto
     */
    private void initRelationships(Entity entity, Relationship headRelationship, RelationShipDto relationShipDto) {
        headRelationship.setHead(true); // This Relation is head
        headRelationship.setCardinalityType(relationShipDto.getCardinalityType());
        headRelationship.setDirectionalityType(relationShipDto.getDirectionalityType());

        Entity targetEntity = relationShipDto.getTargetEntity();
        headRelationship.setTargetEntity(targetEntity);

        Relationship tailRelationship = createTailRelationShipIfNeed(relationShipDto, entity);

        setNameAndCollectionType(relationShipDto.getCardinalityType(), relationShipDto.getCollectionType(), headRelationship, entity);
        entity.addRelation(headRelationship);

        if (tailRelationship != null) {
            headRelationship.setOtherSideRelationship(tailRelationship);
            tailRelationship.setOtherSideRelationship(headRelationship);
            setNameAndCollectionType(relationShipDto.getCardinalityType(), relationShipDto.getCollectionType(), tailRelationship, headRelationship.getTargetEntity());
            headRelationship.getTargetEntity().addRelation(tailRelationship);
        }
    }

    /**
     * Create tail RelationShip If RelationSHipDto is bidirectional
     *
     * @param relationShipDto
     * @param parentEntity
     * @return Relationship or null if not needed
     */
    private Relationship createTailRelationShipIfNeed(RelationShipDto relationShipDto, Entity parentEntity) {

        Relationship targetRelationship = null;

        CardinalityType cardinalityType = relationShipDto.getCardinalityType();
        DirectionalityType directionalityType = relationShipDto.getDirectionalityType();

        if ((directionalityType.equals(DirectionalityType.BIDIRECTIONAL))) {
            targetRelationship = new Relationship();
            String uuid = UUID.randomUUID().toString();
            targetRelationship.setId(uuid);
            //   targetRelationship.setName(relationShipDto.getName());
            targetRelationship.setDirectionalityType(directionalityType);
            targetRelationship.setTargetEntity(parentEntity);
            switch (cardinalityType) {
                case ONE_TO_ONE:
                    targetRelationship.setCardinalityType(CardinalityType.ONE_TO_ONE);
                    break;
                case ONE_TO_MANY:
                    targetRelationship.setCardinalityType(CardinalityType.Many_TO_ONE);
                    break;
                case Many_TO_ONE:
                    targetRelationship.setCardinalityType(CardinalityType.ONE_TO_MANY);
                    break;
                case MANY_TO_MANY:
                    targetRelationship.setCardinalityType(CardinalityType.MANY_TO_MANY);
                    break;
            }

        }
        return targetRelationship;
    }


    /**
     * @param cardinalityType
     * @param collectionType
     * @param relationship
     * @param entity
     */
    private void setNameAndCollectionType(CardinalityType cardinalityType, CollectionType collectionType, Relationship relationship, Entity entity) {
        // when cardinality is one
        String relationshipName = getUnRepeatedNameByAddIndexToEnd(entity, StringUtils.toCamelCase(relationship.getTargetEntity().getName()));
        // when cardinality is many
        String relationshipsName = getUnRepeatedNameByAddIndexToEnd(entity, StringUtils.toCamelCase(relationship.getTargetEntity().getName()) + "s");

        switch (cardinalityType) {
            case ONE_TO_ONE:
                relationship.setName(relationshipName);
                relationship.setCollectionType(CollectionType.NOTHING);
                break;
            case ONE_TO_MANY:
                relationship.setName(relationshipsName);
                relationship.setCollectionType(collectionType);
                break;
            case Many_TO_ONE:
                relationship.setName(relationshipName);
                relationship.setCollectionType(CollectionType.NOTHING);
                break;
            case MANY_TO_MANY:
                relationship.setName(relationshipsName);
                relationship.setCollectionType(collectionType);
                break;
        }
    }

    private String getUnRepeatedNameByAddIndexToEnd(Entity entity, String relationshipName) {
        byte index = 1;
        String realRelationshipName = relationshipName;
        while (true) {
            if (!entity.hasRelationshipWithName(realRelationshipName)) {
                break;
            }
            realRelationshipName = relationshipName + index;
            index++;
        }
        return realRelationshipName;
    }

    /**
     * Initialize a relationship with two side of that
     */
    @Override
    public RelationShipDto initRelationshipDto(Relationship headSideRelationship) {
        Relationship tailSideRelationship = headSideRelationship.getOtherSideRelationship();
        RelationShipDto relationShipDto = new RelationShipDto();

        //
        if (headSideRelationship.isHead())
            relationShipDto.setCardinalityType(headSideRelationship.getCardinalityType());
        else
            relationShipDto.setCardinalityType(tailSideRelationship.getCardinalityType());

        //
        relationShipDto.setTargetEntity(headSideRelationship.getTargetEntity());

        //
        relationShipDto.setDirectionalityType(headSideRelationship.getDirectionalityType());

        //
        if (!headSideRelationship.getCollectionType().equals(CollectionType.NOTHING) && tailSideRelationship == null)
            relationShipDto.setCollectionType(headSideRelationship.getCollectionType());
        else
            relationShipDto.setCollectionType(tailSideRelationship.getCollectionType());

        return relationShipDto;
    }

    @Override
    public void editRelationship(RelationShipDto editedRelationShipDto, Relationship headRelationship, Entity entity) {
        deleteBeforeOtherRelationship(headRelationship);
        initRelationships(entity, headRelationship, editedRelationShipDto);
    }


    /**
     * @param headRelationship
     */
    private void deleteBeforeOtherRelationship(Relationship headRelationship) {
        headRelationship.getTargetEntity().removeRelation(headRelationship.getOtherSideRelationship());
        headRelationship.setOtherSideRelationship(null);
    }
}
