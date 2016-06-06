package ir.sk.jcg.jcgengine.model.platform.technology.ormTechnology.hibernate.element;

import ir.sk.jcg.jcgengine.model.project.ModelImplElement;

import java.io.Serializable;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 5/8/2016
 */
public class EntityClass extends ModelImplElement implements Serializable {

    @Override
    public boolean equals(Object obj) {
        return name.equals(((EntityClass)obj).getName());
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
