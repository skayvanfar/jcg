package ir.sk.jcg.jcgengine.model.project;

import ir.sk.jcg.jcgcommon.PropertyView.ComponentType;
import ir.sk.jcg.jcgcommon.PropertyView.annotation.Editable;
import ir.sk.jcg.jcgcommon.PropertyView.annotation.Prop;
import ir.sk.jcg.jcgengine.model.project.exception.ElementBeforeExistException;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Present an Entity in domain
 *
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
    private boolean isLogicallyDeletable = true;
    @Prop(label = "Is Trackable", editable = true, componentType = ComponentType.BOOLEAN_CHECKBOX, required = true)
    private boolean isTrackable = true;

    private Id id;

    private Set<Property> properties;

    private Set<Relationship> relationships;

    private Set<View> views;

    public Entity() {
        properties = new HashSet<>();
        relationships = new HashSet<>();
        views = new HashSet<>();
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

    public Set<Property> getProperties() {
        return properties;
    }

    @XmlElement(name = "property")
    public void setProperties(Set<Property> properties) {
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

    public Set<Relationship> getRelationships() {
        return relationships;
    }

    @XmlElement(name = "relation")
    public void setRelationships(Set<Relationship> relationships) {
        this.relationships = relationships;
    }

    public Set<View> getViews() {
        return views;
    }

    @XmlElement(name = "views")
    public void setViews(Set<View> views) {
        this.views = views;
    }

    public Set<View> getDisplayViews() {
        return views.stream().filter(view -> view instanceof DisplayView).collect(Collectors.toSet());
    }

    // A convenience method simplifies relationship management
    public void addView(View view) {
        if (view == null)
            throw new NullPointerException("Can't add null View"); // Be defensive
        if (view.getTargetEntity() != null && !view.getTargetEntity().equals(this))
            throw new IllegalStateException("View is already assigned to an Entity");
        getViews().add(view);
        view.setTargetEntity(this);
    }

    /**
     * Add Relation to Entity. If Relation exist, override that.
     *
     * @param relationship
     */
    public void addRelation(Relationship relationship) {
        if (relationships.contains(relationship))
            relationships.remove(relationship);
        relationships.add(relationship);
    }

    public void removeRelation(Relationship relationship) {
        if (relationships.contains(relationship))
            relationships.remove(relationship);
    }

    /**
     * Specify exist a Relation with specifeid relationship name
     *
     * @param relationshipName
     * @return
     */
    public boolean hasRelationshipWithName(String relationshipName) {
        for (Relationship relationship : relationships)
            if (relationship.getName().equals(relationshipName))
                return true;
        return false;
    }

    public Set<EntityElement> getAllEntityElements() {
        Set<EntityElement> entityElements = new HashSet<>();
        entityElements.add(id);
        entityElements.addAll(properties);
        entityElements.addAll(relationships);
        return entityElements;
    }

}
