package ir.sk.jcg.jcgengine.model.project;

import ir.sk.jcg.jcgcommon.PropertyView.annotation.Editable;
import ir.sk.jcg.jcgengine.model.project.exception.ElementBeforeExistException;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Present a package that can have list of other packages and elements
 *
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 4/29/2016
 */
@XmlAccessorType(XmlAccessType.NONE)
@XmlSeeAlso(Entity.class)
@Editable
public class Package<T extends SchemaItem> extends ModelElement implements Packageable<T>, Serializable {


    private Set<Package<T>> packages = new HashSet<>();
    private Set<T> elements = new HashSet<>();

    public Package() {
    }

    @Override
    public Set<Package<T>> getPackages() {
        return packages;
    }

    @Override
    @XmlElement(name = "package")
    public void setPackages(Set<Package<T>> packages) {
        this.packages = packages;
    }

    @Override
    public void addPackage(Package<T> t) { // // TODO: 5/2/2016 repeated code(in Schema)
        if (packages.contains(t))
            throw new ElementBeforeExistException(t);
        packages.add(t);
    }

    @Override
    public void addPackageAll(Set<? extends Package<T>> packages) {
        for (Package<T> tPackage : packages)
            addPackage(tPackage);
    }

    @Override
    public void removePackage(Package<T> t) {
        if (packages.contains(t))
            packages.remove(t);
    }

    public Set<T> getElements() {
        return elements;
    }

    @XmlElement(name = "element")
    public void setElements(Set<T> elements) {
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
