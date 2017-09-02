package ir.sk.jcg.jcgengine.model.platform.technology.mvcTechnology.SpringMVC.element;

import ir.sk.jcg.jcgengine.model.project.DomainImplElement;

import java.io.Serializable;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 9/1/2017
 */
public class ViewElement extends DomainImplElement implements Serializable {
    @Override
    public boolean equals(Object obj) {
        return name.equals(((ViewElement) obj).getName());
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
