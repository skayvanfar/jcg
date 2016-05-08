package ir.sk.jcg.jcgengine.model.project;

import ir.sk.jcg.jcgengine.model.platform.technology.ormTechnology.hibernate.element.DAOImplElement;
import ir.sk.jcg.jcgengine.model.platform.technology.ormTechnology.hibernate.element.DAOInterfaceElement;
import ir.sk.jcg.jcgengine.model.platform.technology.ormTechnology.hibernate.element.EntityClass;

import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 5/8/2016
 */
@XmlSeeAlso({DAOInterfaceElement.class, DAOImplElement.class, EntityClass.class})
public abstract class EntityElement extends Element { // TODO: 5/8/2016 may better use Generic
}
