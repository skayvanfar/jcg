package ir.sk.jcg.jcgengine.model.project;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 5/9/2016
 */
public class BusinessImplElement extends ImplElement {

    public BusinessImplElement() {
    }

    /**
     * Copy constructor
     * */
    public BusinessImplElement(BusinessImplElement anotherBusinessImplElement) {
        this.name = anotherBusinessImplElement.getName(); // you can access
    }
}
