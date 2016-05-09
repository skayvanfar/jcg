package ir.sk.jcg.jcgengine.model.project;

import ir.sk.jcg.jcgengine.model.platform.technology.ormTechnology.hibernate.element.EntityClass;

import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 5/9/2016
 */
@XmlSeeAlso({EntityClass.class})
public abstract class ModelImplElement extends ImplElement {

    public ModelImplElement() {
    }

    /**
     * Copy constructor
     * */
    public ModelImplElement(ModelImplElement anotherModelImplElement) {
        this.name = anotherModelImplElement.getName(); // you can access
    }
}
