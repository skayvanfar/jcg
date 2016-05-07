package ir.sk.jcg.jcgengine.model.project;

import ir.sk.jcg.jcgengine.model.project.annotation.Prop;

import javax.xml.bind.annotation.*;
import java.io.Serializable;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 5/2/2016
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
@XmlSeeAlso({Model.class, Package.class, Project.class, Property.class})
public abstract class Element implements Serializable {

    @Prop
    protected String name;

    protected Element() {
    }

    /**
     * Copy constructor
     * */
    public Element(Element anotherElement) {
        this.name = anotherElement.getName(); // you can access
    }

    public String getName() {
        return name;
    }


    @XmlAttribute(required = true)
    public void setName(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return name;
    }

}
