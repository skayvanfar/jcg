package ir.sk.jcg.jcgengine.model.project;

import ir.sk.jcg.jcgcommon.PropertyView.annotation.Prop;
import ir.sk.jcg.jcgengine.model.Presentable;

import javax.xml.bind.annotation.*;
import java.io.Serializable;

/**
 * This class is base class for elements that belong to a project
 *
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 5/2/2016
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
@XmlSeeAlso({ModelElement.class, ImplElement.class})
public abstract class Element implements Presentable, Serializable {

    @Prop(label = "Name", editable = true, required = true)
    protected String name;

    protected Element() {
    }

    public String getName() {
        return name;
    }

    @XmlAttribute(required = true)
    @XmlID
    public void setName(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return name + " " + getClass().getSimpleName();
    }

}
