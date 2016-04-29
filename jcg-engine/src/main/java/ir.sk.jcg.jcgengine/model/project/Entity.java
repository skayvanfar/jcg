package ir.sk.jcg.jcgengine.model.project;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import java.util.List;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 4/13/2016
 */
@XmlAccessorType(XmlAccessType.NONE)
public class Entity {

    private String name;
    private String packagePath;
    private List<Property> properties;

    public String getName() {
        return name;
    }

    @XmlAttribute(required = true)
    public void setName(String name) {
        this.name = name;
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

    @Override
    public String toString() {
        return name;
    }
}
