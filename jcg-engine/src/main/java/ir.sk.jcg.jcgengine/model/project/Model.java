package ir.sk.jcg.jcgengine.model.project;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 4/29/2016
 */
@XmlAccessorType(XmlAccessType.NONE)
public class Model<T> {

    private String name;

    private List<Package<T>> packages = new ArrayList<>();

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

    @Override
    public String toString() {
        return name;
    }
}
