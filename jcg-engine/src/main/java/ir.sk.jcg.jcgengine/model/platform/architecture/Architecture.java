package ir.sk.jcg.jcgengine.model.platform.architecture;

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
@XmlSeeAlso({ThreeLayerArchitecture.class})
public abstract class Architecture { // TODO: 4/27/2016 may be use interface and must change jaxb provider

    List<TechnologyHandler> technologies = new ArrayList<>();


    protected abstract void setBaseDirOfTechnologies(String baseDir);
    protected abstract void setBasePackageNameOfTechnologies(String basePackageName);

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

    public abstract List<ModelImplElement> createEntity(Entity entity, String packagePath);

    public abstract TechnologyHandler getTechnologyByType(TechnologyHandlerType technologyHandlerType);
}
