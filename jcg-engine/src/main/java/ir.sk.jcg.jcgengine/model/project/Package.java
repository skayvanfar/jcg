package ir.sk.jcg.jcgengine.model.project;

import ir.sk.jcg.jcgcommon.PropertyView.annotation.Editable;
import ir.sk.jcg.jcgengine.model.project.exception.ElementBeforeExistException;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 4/29/2016
 */
@XmlAccessorType(XmlAccessType.NONE)
@XmlSeeAlso(Entity.class)
@Editable
public class Package<T extends SchemaItem> extends ModelElement implements Packageable<T>, Serializable {


    private List<Package<T>> packages = new ArrayList<>();
    private List<T> elements = new ArrayList<>();

    public Package() {}

    /**
     * Copy constructor
     * */
    public Package(Package<T> anotherPackage) {
        super(anotherPackage);
        this.packages = anotherPackage.getPackages();
        this.elements = anotherPackage.getElements();
    }

    public List<Package<T>> getPackages() {
        return packages;
    }

    @XmlElement(name = "package")
    public void setPackages(List<Package<T>> packages) {
        this.packages = packages;
    }

    @Override
    public void addPackage(Package<T> t) { // // TODO: 5/2/2016 repeated code(in Schema)
        if (packages.contains(t))
            throw new ElementBeforeExistException(t);
        packages.add(t);
    }

    @Override
    public void removePackage(Package<T> t) {
        if (packages.contains(t))
            packages.remove(t);
    }

    public List<T> getElements() {
        return elements;
    }

    @XmlElement(name = "element")
    public void setElements(List<T> elements) {
        this.elements = elements;
    }

    public void addElement(T t) {
        if (elements.contains(t))
            throw new ElementBeforeExistException(t);
        elements.add(t);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Package)) return false;

        Package<?> aPackage = (Package<?>) o;

        return name != null ? name.equals(aPackage.name) : aPackage.name == null && (elements != null ? elements.equals(aPackage.elements) : aPackage.elements == null);

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (elements != null ? elements.hashCode() : 0);
        return result;
    }

}
