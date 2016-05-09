package ir.sk.jcg.jcgengine.model.project;

import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 5/9/2016
 */
@XmlSeeAlso({ModelImplElement.class})
public abstract class ImplElement extends Element {

    public ImplElement() {
    }

    /**
     * Copy constructor
     * */
    public ImplElement(ImplElement anotherImplElement) {
        this.name = anotherImplElement.getName(); // you can access
    }
}
