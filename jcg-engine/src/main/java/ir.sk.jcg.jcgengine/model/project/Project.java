package ir.sk.jcg.jcgengine.model.project;

import ir.sk.jcg.jcgengine.model.platform.architecture.Architecture;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 4/13/2016
 */
public class Project {

    private String Name;
    private String persianName;
    private String packagePrefix;
    private String tableNamePattern;

    private Architecture architecture;

    public String getName() {
        return Name;
    }

    @XmlAttribute
    public void setName(String name) {
        Name = name;
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


    public Architecture getArchitecture() {
        return architecture;
    }

    @XmlElement
    public void setArchitecture(Architecture architecture) {
        this.architecture = architecture;
    }

    @Override
    public String toString() {
        return "Project{" +
                "Name='" + Name + '\'' +
                ", persianName='" + persianName + '\'' +
                ", packagePrefix='" + packagePrefix + '\'' +
                ", tableNamePattern='" + tableNamePattern + '\'' +
                '}';
    }

}
