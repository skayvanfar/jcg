package ir.sk.jcg.jcgengine.model.project;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 5/9/2016
 */
@XmlSeeAlso({Schema.class, Package.class, Project.class, Property.class, Id.class})
public class ModelElement extends Element {

    private List<ImplElement> implElements;


    public ModelElement() {
        implElements = new ArrayList<>();
    }

    /**
     * Copy constructor
     * */
    public ModelElement(ModelElement anotherModelElement) {
        this.name = anotherModelElement.getName(); // you can access
    }

    public List<ImplElement> getImplElements() {
        return implElements;
    }

    @XmlElement(name = "implElement")
    public void setImplElements(List<ImplElement> implElements) {
        this.implElements = implElements;
    }

    public void addAllImplElements(List<ImplElement> implElements) {
        implElements.addAll(implElements); // TODO: 5/9/2016 must check implElement exist
    }

    public void addImplElement(ImplElement implElement) {
        implElements.add(implElement); // TODO: 5/9/2016 must check implElement exist
    }
}
