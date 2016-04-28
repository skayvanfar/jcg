package ir.sk.jcg.jcgengine.model.project;

import ir.sk.jcg.jcgengine.model.platform.architecture.Architecture;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.File;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 4/13/2016
 */
@XmlRootElement
public class Project {

    private String name;
    private String persianName;
    private String packagePrefix;
    private String tableNamePattern;

    public String getName() {
        return name;
    }

    @XmlAttribute
    public void setName(String name) {
        this.name = name;
    }

    public String getPersianName() {
        return persianName;
    }

    @XmlAttribute
    public void setPersianName(String persianName) {
        this.persianName = persianName;
    }

    public String getPackagePrefix() {
        return packagePrefix;
    }

    @XmlAttribute
    public void setPackagePrefix(String packagePrefix) {
        this.packagePrefix = packagePrefix;
    }

    public String getTableNamePattern() {
        return tableNamePattern;
    }

    @XmlAttribute
    public void setTableNamePattern(String tableNamePattern) {
        this.tableNamePattern = tableNamePattern;
    }

    @Override
    public String toString() {
        return name;
    }
}
