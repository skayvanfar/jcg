package ir.sk.jcg.jcgengine.model.project;

import ir.sk.jcg.jcgengine.model.project.annotation.Editable;
import ir.sk.jcg.jcgengine.model.project.exception.ElementBeforeExistException;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 4/13/2016
 */
@XmlAccessorType(XmlAccessType.NONE)
@Editable
public class Entity extends ModelElement implements Serializable {

    private String packagePath;
    private List<Property> properties;

    public Entity() {
        properties = new ArrayList<>();
    }

    /**
     * Copy constructor
     * */
    public Entity(Entity anotherEntity) {
        super(anotherEntity);
        this.packagePath = anotherEntity.getPackagePath();
        this.properties = anotherEntity.getProperties();
    }

    public String getPackagePath() {
        return packagePath;
    }

    @XmlAttribute
    public void setPackagePath(String packagePath) {
        this.packagePath = packagePath;
    }

    public List<Property> getProperties() {
        return properties;
    }

    @XmlElement(name = "property")
    public void setProperties(List<Property> properties) {
        this.properties = properties;
    }

    public void addProperty(Property property) { // // TODO: 5/2/2016 repeated code(in Model)
        if (properties.contains(property))
            throw new ElementBeforeExistException(property);
        properties.add(property);
    }

    public void removeProperty(Property property) {
        if (properties.contains(property))
            properties.remove(property);
    }

}
