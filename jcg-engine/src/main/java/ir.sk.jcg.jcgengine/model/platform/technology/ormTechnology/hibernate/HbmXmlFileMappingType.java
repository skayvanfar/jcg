package ir.sk.jcg.jcgengine.model.platform.technology.ormTechnology.hibernate;

import ir.sk.jcg.jcgengine.model.platform.technology.ormTechnology.hibernate.element.EntityClass;
import ir.sk.jcg.jcgengine.model.project.Entity;
import ir.sk.jcg.jcgengine.model.project.ModelImplElement;

import java.util.List;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 9/21/2016
 */
public class HbmXmlFileMappingType extends MappingType {
    @Override
    EntityClass createEntityClass(HibernateHandler hibernateHandler, Entity entity, String packagePath) {
        return null;
    }

    @Override
    List<ModelImplElement> createDao(HibernateHandler hibernateHandler, Entity entity) {
        return null;
    }
}
