package ir.sk.jcg.jcgengine.model.project;

import ir.sk.jcg.jcgengine.model.project.exception.ElementBeforeExistException;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 4/29/2016
 */
@XmlAccessorType(XmlAccessType.NONE)
public class Model<T extends SubModelElement> extends ModelElement implements Packageable<T>, Serializable {


    private List<Package<T>> packages = new ArrayList<>();

    @Override
    public List<Package<T>> getPackages() {
        return packages;
    }

    public Model() {}

    /**
     * Copy constructor
     * */
    public Model(Model<T> anotherModel) {
        super(anotherModel);
        this.packages = anotherModel.getPackages();
    }

    @Override
    @XmlElement(name = "package")
    public void setPackages(List<Package<T>> packages) {
        this.packages = packages;
    }

    @Override
    public void addPackage(Package<T> t) {
        if (packages.contains(t))
            throw new ElementBeforeExistException(t);
        packages.add(t);
    }

    @Override
    public void removePackage(Package<T> t) {
        if (packages.contains(t))
            packages.remove(t);
    }

}
