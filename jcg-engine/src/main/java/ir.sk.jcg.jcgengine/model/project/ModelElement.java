package ir.sk.jcg.jcgengine.model.project;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is base class for all Model elements
 *
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 5/9/2016
 */
@XmlSeeAlso({Schema.class, Package.class, Project.class, Property.class, Id.class})
public class ModelElement extends Element {

    // List of all impl elements that created for this element
    private List<ImplElement> implElements;

    public ModelElement() {
        implElements = new ArrayList<>();
    }

    public List<ImplElement> getImplElements() {
        return implElements;
    }

    @XmlElement(name = "implElement")
    public void setImplElements(List<ImplElement> implElements) {
        this.implElements = implElements;
    }

    public void addAllImplElements(List<? extends ImplElement> implElements) {
        for (ImplElement implElement : implElements) {
            if (this.implElements.contains(implElement))
                this.implElements.remove(implElement);
        }
        this.implElements.addAll(implElements);
    }

    public void addImplElement(ImplElement implElement) {
        if (implElements.contains(implElement))
            implElements.remove(implElement);
        implElements.add(implElement);
    }
}
