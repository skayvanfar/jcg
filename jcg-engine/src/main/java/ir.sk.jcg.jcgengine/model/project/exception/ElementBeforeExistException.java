package ir.sk.jcg.jcgengine.model.project.exception;

import ir.sk.jcg.jcgengine.model.project.Element;
import ir.sk.jcg.jcgengine.model.project.ModelElement;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 5/2/2016
 */
public class ElementBeforeExistException extends RuntimeException { //// TODO: 5/2/2016  may better extend from Exception

    Element element;

    public ElementBeforeExistException(Element element) {
        this.element = element;
    }

    public Element getElement() {
        return element;
    }
}
