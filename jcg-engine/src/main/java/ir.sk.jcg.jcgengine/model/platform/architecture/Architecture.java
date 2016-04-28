package ir.sk.jcg.jcgengine.model.platform.architecture;

import ir.sk.jcg.jcgengine.model.platform.technology.Technology;
import ir.sk.jcg.jcgengine.model.platform.technology.TechnologyType;
import ir.sk.jcg.jcgengine.model.project.Project;
import org.apache.commons.collections.map.HashedMap;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import java.util.*;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 4/13/2016
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
@XmlSeeAlso({ThreeLayerArchitecture.class})
public abstract class Architecture { // TODO: 4/27/2016 may be use interface and must change jaxb provider

  //  private Project project;

//    public File getBaseDir() {
//        return baseDir;
//    }
//
//    @XmlAttribute(name = "baseDir", required = true)
//    public void setBaseDir(File baseDir) {
//        this.baseDir = baseDir;
////        setBaseDirOfTechnologies(baseDir);
////        setBasePackageNameOfTechnologies(basePackageName);
//    }

//    public Architecture(Project project) {
//        this.project = project;
//    }

//    public Project getProject() {
//        return project;
//    }
//
//    public void setProject(Project project) {
//        this.project = project;
//        for (Technology technology : getTechnologies())
//            technology.setProject(project);
//    }

    List<Technology> technologies = new ArrayList<>();


    protected abstract void setBaseDirOfTechnologies(String baseDir);
    protected abstract void setBasePackageNameOfTechnologies(String baseDir);

//    public String getBasePackageName() {
//        return basePackageName;
//    }
//
//    @XmlAttribute(name = "basePackageName", required = true)
//    public void setBasePackageName(String basePackageName) {
//        this.basePackageName = basePackageName;
//    }

    public abstract TechnologyType[] getTechnologyTypes();

    public List<Technology> getTechnologies() {
        return technologies;
    }

    @XmlElement
    public void setTechnologies(List<Technology> technologies) {
        this.technologies = technologies;
    }


    public abstract void createView();

    public abstract void createBaseArchitecture();

    public void initialize(String baseDir, String packagename) {
        setBaseDirOfTechnologies(baseDir);
        setBasePackageNameOfTechnologies(packagename);
    }
}
