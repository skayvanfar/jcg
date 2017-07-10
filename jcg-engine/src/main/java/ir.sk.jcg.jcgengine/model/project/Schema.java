package ir.sk.jcg.jcgengine.model.project;

import ir.sk.jcg.jcgengine.model.project.exception.ElementBeforeExistException;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * This class is a view to Specific part of project
 * and can have list of SchemaItem
 *
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 4/29/2016
 */
@XmlAccessorType(XmlAccessType.NONE)
public class Schema<T extends SchemaItem> extends ModelElement implements Packageable<T>, Serializable {

    // Each Schema maybe has many package of specified SchemaItem
    private Set<Package<T>> packages = new HashSet<>();

    @Override
    public Set<Package<T>> getPackages() {
        return packages;
    }

    public Schema() {
    }

    @Override
    @XmlElement(name = "package")
    public void setPackages(Set<Package<T>> packages) {
        this.packages = packages;
    }

    @Override
    public void addPackage(Package<T> t) {
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

    @Override
    public String toString() {
        return name;
    }
}
