package ir.sk.jcg.jcgengine.model.platform.architecture;

import ir.sk.jcg.jcgengine.model.platform.technologyHandler.TechnologyHandler;
import ir.sk.jcg.jcgengine.model.platform.technologyHandler.TechnologyHandlerType;
import ir.sk.jcg.jcgengine.model.project.Entity;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

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
//        for (TechnologyHandler technologyHandler : getTechnologies())
//            technologyHandler.setProject(project);
//    }

    List<TechnologyHandler> technologies = new ArrayList<>();


    protected abstract void setBaseDirOfTechnologies(String baseDir);
    protected abstract void setBasePackageNameOfTechnologies(String basePackageName);

//    public String getBasePackageName() {
//        return basePackageName;
//    }
//
//    @XmlAttribute(name = "basePackageName", required = true)
//    public void setBasePackageName(String basePackageName) {
//        this.basePackageName = basePackageName;
//    }

    public abstract TechnologyHandlerType[] getTechnologyTypes();

    public List<TechnologyHandler> getTechnologies() {
        return technologies;
    }

    @XmlElement(name = "technologyHandler")
    public void setTechnologies(List<TechnologyHandler> technologies) {
        this.technologies = technologies;
    }


    public abstract void createView();

    public abstract void createBaseArchitecture();

    /**
     * Call after unmrshalling for set temp values
     * */
    public void initialize(String baseDir, String packagename) {
        setBaseDirOfTechnologies(baseDir);
        setBasePackageNameOfTechnologies(packagename);
    }

    public abstract void createEntity(Entity entity, String packagePath);
}
