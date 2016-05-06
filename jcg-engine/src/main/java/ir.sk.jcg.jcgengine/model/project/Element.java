package ir.sk.jcg.jcgengine.model.project;

import javax.xml.bind.annotation.*;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 5/2/2016
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
@XmlSeeAlso({Model.class, Package.class, Project.class, Property.class})
public abstract class Element {

    @Prop
    protected String name;

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
