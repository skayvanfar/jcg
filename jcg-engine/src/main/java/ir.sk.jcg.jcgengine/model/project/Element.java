package ir.sk.jcg.jcgengine.model.project;

import ir.sk.jcg.jcgengine.model.platform.architecture.ThreeLayerArchitecture;

import javax.xml.bind.annotation.*;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 5/2/2016
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
@XmlSeeAlso({Model.class, Package.class, Project.class, Property.class})
public class Element {

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
