package ir.sk.jcg.jcgintellijpluginapp.ui.controller;

import ir.sk.jcg.jcgengine.model.project.Entity;
import ir.sk.jcg.jcgengine.model.project.Relationship;
import ir.sk.jcg.jcgintellijpluginapp.ui.dto.RelationShipDto;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 6/1/2016.
 */
public interface RelationshipController {

    /**
     * Create Relationships of a RelationshipDto
     * @param relationShipDto
     * @param entity that is parent of head Relation
     */
    void createRelationship(RelationShipDto relationShipDto, Entity entity);


    /**
     * Create anf full a RelationshipDto by head Relationship
     * @param headRelationship
     * @return
     */
    RelationShipDto initRelationshipDto(Relationship headRelationship);


    /**
     * Edit Relationships of a RelationshipDto
     * @param editedRelationShipDto
     * @param headRelationship
     * @param entity
     */
    void editRelationship(RelationShipDto editedRelationShipDto, Relationship headRelationship, Entity entity);
}
