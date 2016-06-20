package ir.sk.jcg.jcgengine.model.platform.architecture;

import ir.sk.jcg.jcgcommon.PropertyView.annotation.Prop;
import ir.sk.jcg.jcgengine.ApplicationContext;
import ir.sk.jcg.jcgengine.model.Presentable;
import ir.sk.jcg.jcgengine.model.platform.technology.SecurityTechnology.SecurityTechnologyHandler;
import ir.sk.jcg.jcgengine.model.platform.technology.SecurityTechnology.SpringSecurity.SpringSecurityHandler;
import ir.sk.jcg.jcgengine.model.platform.technology.SpringTechnology.SpringHandler;
import ir.sk.jcg.jcgengine.model.platform.technology.TechnologyHandler;
import ir.sk.jcg.jcgengine.model.platform.technology.TechnologyHandlerType;
import ir.sk.jcg.jcgengine.model.platform.technology.buildTechnology.BuildTechnologyHandler;
import ir.sk.jcg.jcgengine.model.platform.technology.mvcTechnology.MVCTechnologyHandler;
import ir.sk.jcg.jcgengine.model.platform.technology.ormTechnology.ORMTechnologyHandler;
import ir.sk.jcg.jcgengine.model.project.Entity;
import ir.sk.jcg.jcgengine.model.project.ModelImplElement;

import javax.xml.bind.annotation.*;
import java.io.File;
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

    protected SecurityTechnologyHandler securityTechnologyHandler;

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
        securityTechnologyHandler = new SpringSecurityHandler();
    }

    public abstract TechnologyHandlerType[] getTechnologyTypes();

    public SpringHandler getSpringHandler() {
        return springHandler;
    }

    @XmlElement(name = "springHandler")
    public void setSpringHandler(SpringHandler springHandler) {
        this.springHandler = springHandler;
    }

    public SecurityTechnologyHandler getSecurityTechnologyHandler() {
        return securityTechnologyHandler;
    }

    @XmlElement(name = "SecurityHandler")
    public void setSecurityTechnologyHandler(SecurityTechnologyHandler securityTechnologyHandler) {
        this.securityTechnologyHandler = securityTechnologyHandler;
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
        ApplicationContext applicationContext = ApplicationContext.getInstance();

        applicationContext.setBaseProjectPath(baseDir);
        applicationContext.setPackagePrefix(packagePrefix);
        applicationContext.setConfigPackage(configPackage);

        BuildTechnologyHandler buildTechnology = (BuildTechnologyHandler) getTechnologyByType(TechnologyHandlerType.BUILD_TECHNOLOGY);
        ORMTechnologyHandler ormTechnology = (ORMTechnologyHandler) getTechnologyByType(TechnologyHandlerType.ORM_TECHNOLOGY);
        MVCTechnologyHandler mvcTechnology = (MVCTechnologyHandler) getTechnologyByType(TechnologyHandlerType.MVC_TECHNOLOGY);

        applicationContext.setMainJavaPath(ApplicationContext.getInstance().getBaseProjectPath() + File.separator + buildTechnology.getMainJavaPath());
        applicationContext.setMainResourcesPath(ApplicationContext.getInstance().getBaseProjectPath() + File.separator + buildTechnology.getMainResourcesPath());
        applicationContext.setMainWebPath(ApplicationContext.getInstance().getBaseProjectPath() + File.separator + buildTechnology.getMainWebPath());
        applicationContext.setTestJavaPath(ApplicationContext.getInstance().getBaseProjectPath() + File.separator + buildTechnology.getTestJavaPath());
        applicationContext.setTestResourcesPath(ApplicationContext.getInstance().getBaseProjectPath() + File.separator + buildTechnology.getTestResourcesPath());
        applicationContext.setJavaWithPackagePrefixPath(ApplicationContext.getInstance().getMainJavaPath() + File.separator + ApplicationContext.getInstance().getPackagePrefix().replace('.', '/'));

        applicationContext.setSpringConfigType(springHandler.getSpringConfigType());
        applicationContext.setSpringDIType(springHandler.getSpringDIType());

        applicationContext.setBuildTechnologyHandler(buildTechnology); // TODO: 6/20/2016
        applicationContext.setOrmTechnologyHandler(ormTechnology);
        applicationContext.setMvcTechnologyHandler(mvcTechnology);
        applicationContext.setSpringHandler(springHandler);
        applicationContext.setSecurityTechnologyHandler(securityTechnologyHandler);
    }

    public abstract List<ModelImplElement> createEntity(Entity entity, String packagePath);

    public abstract TechnologyHandler getTechnologyByType(TechnologyHandlerType technologyHandlerType);

    @Override
    public String toString() {
        return name;
    }
}
