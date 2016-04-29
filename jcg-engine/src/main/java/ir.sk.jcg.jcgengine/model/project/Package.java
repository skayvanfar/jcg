package ir.sk.jcg.jcgengine.model.project;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 4/29/2016
 */
@XmlAccessorType(XmlAccessType.NONE)
@XmlSeeAlso(Entity.class)
public class Package<T> {

    private String name;

    private List<Package<T>> packages = new ArrayList<>();
    private List<T> elements = new ArrayList<>();

    public String getName() {
        return name;
    }

    @XmlAttribute(required = true)
    public void setName(String name) {
        this.name = name;
    }

    public List<Package<T>> getPackages() {
        return packages;
    }

    @XmlElement(name = "package")
    public void setPackages(List<Package<T>> packages) {
        this.packages = packages;
    }

    public List<T> getElements() {
        return elements;
    }

    @XmlElement(name = "element")
    public void setElements(List<T> elements) {
        this.elements = elements;
    }

    @Override
    public String toString() {
        return name;
    }
}
