package ir.sk.jcg.jcgengine.model.project;

import ir.sk.jcg.jcgcommon.PropertyView.ComponentType;
import ir.sk.jcg.jcgcommon.PropertyView.annotation.Editable;
import ir.sk.jcg.jcgcommon.PropertyView.annotation.Prop;
import ir.sk.jcg.jcgengine.model.project.exception.ElementBeforeExistException;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 4/13/2016
 */
@XmlAccessorType(XmlAccessType.NONE)
@Editable
public class Entity extends SchemaItem implements Serializable {

    private String packagePath;

    @Prop(label = "Table Name", editable = true, required = true)
    private String tableName;
    @Prop(label = "Label Name", editable = true, required = true)
    private String labelName;
    @Prop(label = "Is Logically Deletable", editable = true, componentType = ComponentType.BOOLEAN_CHECKBOX, required = true)
    private boolean isLogicallyDeletable;
    @Prop(label = "Is Trackable", editable = true, componentType = ComponentType.BOOLEAN_CHECKBOX, required = true)
    private boolean isTrackable;

    private Id id;

    private List<Property> properties;

    private List<Relationship> relationships;

    public Entity() {
        properties = new ArrayList<>();
        relationships = new ArrayList<>();
    }

    /**
     * Copy constructor
     * */
    public Entity(Entity anotherEntity) {
        super(anotherEntity);
        this.packagePath = anotherEntity.getPackagePath();
        this.id = anotherEntity.getId();
        this.properties = anotherEntity.getProperties();
        this.relationships = anotherEntity.getRelationships();
    }

    public String getPackagePath() {
        return packagePath;
    }

    @XmlAttribute
    public void setPackagePath(String packagePath) {
        this.packagePath = packagePath;
    }

    public String getTableName() {
        return tableName;
    }

    @XmlAttribute
     // TODO: 5/12/2016 Id
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getLabelName() {
        return labelName;
    }

    @XmlAttribute
    public void setLabelName(String labelName) {
        this.labelName = labelName;
    }

    public boolean isLogicallyDeletable() {
        return isLogicallyDeletable;
    }

    @XmlAttribute
    public void setLogicallyDeletable(boolean logicallyDeletable) {
        isLogicallyDeletable = logicallyDeletable;
    }

    public boolean isTrackable() {
        return isTrackable;
    }

    @XmlAttribute
    public void setTrackable(boolean trackable) {
        isTrackable = trackable;
    }

    public Id getId() {
        return id;
    }

    @XmlElement(name = "id")
    public void setId(Id id) {
        this.id = id;
    }

    public List<Property> getProperties() {
        return properties;
    }

    @XmlElement(name = "property")
    public void setProperties(List<Property> properties) {
        this.properties = properties;
    }

    public void addProperty(Property property) { // // TODO: 5/2/2016 repeated code(in Schema)
        if (properties.contains(property))
            throw new ElementBeforeExistException(property);
        properties.add(property);
    }

    public void removeProperty(Property property) {
        if (properties.contains(property))
            properties.remove(property);
    }

    public List<Relationship> getRelationships() {
        return relationships;
    }

    @XmlElement(name = "relation")
    public void setRelationships(List<Relationship> relationships) {
        this.relationships = relationships;
    }

    public void addRelation(Relationship relationship) {
        if (relationships.contains(relationship))
            throw new ElementBeforeExistException(relationship);
        relationships.add(relationship);
    }

    public void removeRelation(Relationship relationship) {
        if (relationships.contains(relationship))
            relationships.remove(relationship);
    }

}
