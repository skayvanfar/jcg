package ir.sk.jcg.jcgengine.model.platform.architecture;

import ir.sk.jcg.jcgcommon.PropertyView.annotation.Prop;
import ir.sk.jcg.jcgengine.model.Presentable;
import ir.sk.jcg.jcgengine.model.platform.technology.SecurityTechnology.SecurityTechnologyHandler;
import ir.sk.jcg.jcgengine.model.platform.technology.SecurityTechnology.SpringSecurity.SpringSecurityHandler;
import ir.sk.jcg.jcgengine.model.platform.technology.SpringTechnology.SpringHandler;
import ir.sk.jcg.jcgengine.model.platform.technology.TechnologyHandler;
import ir.sk.jcg.jcgengine.model.platform.technology.TechnologyHandlerType;
import ir.sk.jcg.jcgengine.model.project.Entity;
import ir.sk.jcg.jcgengine.model.project.Project;
import ir.sk.jcg.jcgengine.model.project.View;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 4/13/2016
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
@XmlSeeAlso({SpringWebArchitecture.class})
public abstract class Architecture implements Presentable {

    @Prop(label = "Name", required = true)
    private String name;

    SpringHandler springHandler;

    SecurityTechnologyHandler securityTechnologyHandler;

    List<TechnologyHandler> technologies = new ArrayList<>();

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

    /**
     * Create base config and essential files for all technologies
     */
    public abstract void createBaseArchitecture();

    /**
     * Call after unmrshalling for set temp values
     */
    public abstract void initialize(String baseDir, Project project);

    public abstract void createEntity(Entity entity, String packagePath);

    public abstract void createView(View view, String packagePath);

    public abstract TechnologyHandler getTechnologyByType(TechnologyHandlerType technologyHandlerType);

    @Override
    public String toString() {
        return name;
    }
}
