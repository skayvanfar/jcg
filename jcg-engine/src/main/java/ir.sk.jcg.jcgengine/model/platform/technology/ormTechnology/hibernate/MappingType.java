package ir.sk.jcg.jcgengine.model.platform.technology.ormTechnology.hibernate;

import ir.sk.jcg.jcgengine.model.platform.technology.ormTechnology.hibernate.element.EntityClass;
import ir.sk.jcg.jcgengine.model.project.DomainImplElement;
import ir.sk.jcg.jcgengine.model.project.Entity;

import java.util.List;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 9/21/2016
 */
public abstract class MappingType {

    /**
     * Static factory method
     *
     * @param mappingTypeEnum
     * @return
     */
    static MappingType newInstance(MappingTypeEnum mappingTypeEnum) {
        switch (mappingTypeEnum) {
            case ANNOTATION:
                return new AnnotationMappingType();
            case HBM_XML_FILE:
                return new HbmXmlFileMappingType();
            default:
                //  return null; // TODO: 9/21/2016 use null object
                throw new IllegalArgumentException("Incorrect type code value");
        }
    }

    abstract EntityClass createEntityClass(HibernateHandler hibernateHandler, Entity entity, String packagePath);

    abstract List<DomainImplElement> createDao(HibernateHandler hibernateHandler, Entity entity);
}
