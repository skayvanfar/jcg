package ir.sk.jcg.jcgengine.model.project;

import ir.sk.jcg.jcgengine.model.platform.technology.ormTechnology.hibernate.element.EntityClass;

import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 5/9/2016
 */
@XmlSeeAlso({EntityClass.class})
public abstract class DomainImplElement extends ImplElement {

    public DomainImplElement() {
    }
}
