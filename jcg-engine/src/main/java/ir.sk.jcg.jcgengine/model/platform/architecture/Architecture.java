package ir.sk.jcg.jcgengine.model.platform.architecture;

import ir.sk.jcg.jcgcommon.PropertyView.annotation.Prop;
import ir.sk.jcg.jcgengine.ApplicationContext;
import ir.sk.jcg.jcgengine.model.Presentable;
import ir.sk.jcg.jcgengine.model.platform.technology.SpringTechnology.SpringHandler;
import ir.sk.jcg.jcgengine.model.platform.technology.TechnologyHandler;
import ir.sk.jcg.jcgengine.model.platform.technology.TechnologyHandlerType;
import ir.sk.jcg.jcgengine.model.project.Entity;
import ir.sk.jcg.jcgengine.model.project.ModelImplElement;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 4/13/2016
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
@XmlSeeAlso({SpringWebArchitecture.class})
public abstract class Architecture implements Presentable { // TODO: 4/27/2016 may be use interface and must change jaxb provider

    @Prop(label = "Name", required = true)
    private String name;

    protected SpringHandler springHandler;

    protected List<TechnologyHandler> technologies = new ArrayList<>();

    public Architecture() {
        name = "Architecture";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Architecture(String name) {
        this.name = name;
        springHandler = new SpringHandler();
    }

    protected abstract void setBaseDirOfTechnologies();
//    protected abstract void setPackagePrefixOfTechnologies();
//    protected abstract void setConfigPackageOfTechnologies();

    public abstract TechnologyHandlerType[] getTechnologyTypes();

    public SpringHandler getSpringHandler() {
        return springHandler;
    }

    @XmlElement(name = "springHandler")
    public void setSpringHandler(SpringHandler springHandler) {
        this.springHandler = springHandler;
    }

    public List<TechnologyHandler> getTechnologies() {
        return technologies;
    }

    @XmlElement(name = "technologyHandler")
    public void setTechnologies(List<TechnologyHandler> technologies) {
        this.technologies = technologies;
    }


    public abstract void createView();

    /**
     * Create base config and essential files for all technologies
     * */
    public abstract void createBaseArchitecture();

    /**
     * Call after unmrshalling for set temp values
     * */
    public void initialize(String baseDir, String packagePrefix, String configPackage) {
        ApplicationContext.getInstance().setBaseDir(baseDir);
        ApplicationContext.getInstance().setPackagePrefix(packagePrefix);
        ApplicationContext.getInstance().setConfigPackage(configPackage);

        setBaseDirOfTechnologies();
//        setPackagePrefixOfTechnologies();
//        setConfigPackageOfTechnologies();
    }

    public abstract List<ModelImplElement> createEntity(Entity entity, String packagePath);

    public abstract TechnologyHandler getTechnologyByType(TechnologyHandlerType technologyHandlerType);

    @Override
    public String toString() {
        return name;
    }
}
