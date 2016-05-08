package ir.sk.jcg.jcgengine.model.platform.architecture;

import ir.sk.jcg.jcgengine.model.platform.technology.*;
import ir.sk.jcg.jcgengine.model.platform.technology.buildTechnology.BuildTechnologyHandler;
import ir.sk.jcg.jcgengine.model.platform.technology.mvcTechnology.MVCTechnologyHandler;
import ir.sk.jcg.jcgengine.model.platform.technology.ormTechnology.ORMTechnologyHandler;
import ir.sk.jcg.jcgengine.model.platform.technology.ormTechnology.hibernate.element.DAOInterfaceElement;
import ir.sk.jcg.jcgengine.model.platform.technology.ormTechnology.hibernate.element.EntityClass;
import ir.sk.jcg.jcgengine.model.project.Entity;
import ir.sk.jcg.jcgengine.model.project.EntityElement;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 4/13/2016
 */
@XmlRootElement // TODO: 4/27/2016 must remove from this class
public class ThreeLayerArchitecture extends Architecture {

    private static TechnologyHandlerType[] technologyHandlerTypes = {TechnologyHandlerType.BUILD_TECHNOLOGY, TechnologyHandlerType.ORM_TECHNOLOGY, TechnologyHandlerType.MVC_TECHNOLOGY};

    private String baseDir; // TODO: 5/3/2016 may beeter use Project
    private String basePackageName;

    @Override
    public void setBaseDirOfTechnologies(String baseDir) {
        this.baseDir = baseDir;
        BuildTechnologyHandler buildTechnology = (BuildTechnologyHandler) getTechnologyByType(TechnologyHandlerType.BUILD_TECHNOLOGY); // TODO: 5/3/2016 repeated code (in setBasePackageNameOfTechnologies)
        ORMTechnologyHandler ormTechnology = (ORMTechnologyHandler) getTechnologyByType(TechnologyHandlerType.ORM_TECHNOLOGY);
        MVCTechnologyHandler mvcTechnology = (MVCTechnologyHandler) getTechnologyByType(TechnologyHandlerType.MVC_TECHNOLOGY);

        buildTechnology.setBaseDir(baseDir);
        ormTechnology.setBaseDir(baseDir + buildTechnology.getMainJavaDir());
        mvcTechnology.setBaseDir(baseDir + buildTechnology.getMainJavaDir());
    }

    @Override
    protected void setBasePackageNameOfTechnologies(String basePackageName) {
        this.basePackageName = basePackageName;
        BuildTechnologyHandler buildTechnology = (BuildTechnologyHandler) getTechnologyByType(TechnologyHandlerType.BUILD_TECHNOLOGY);
        ORMTechnologyHandler ormTechnology = (ORMTechnologyHandler) getTechnologyByType(TechnologyHandlerType.ORM_TECHNOLOGY);
        MVCTechnologyHandler mvcTechnology = (MVCTechnologyHandler) getTechnologyByType(TechnologyHandlerType.MVC_TECHNOLOGY);
        buildTechnology.setBasePackageName(basePackageName);
        ormTechnology.setBasePackageName(basePackageName);
        mvcTechnology.setBasePackageName(basePackageName);
    }

    @Override
    public TechnologyHandlerType[] getTechnologyTypes() {
        return technologyHandlerTypes;
    }

    @Override
    public void createView() {
    }

    @Override
    public void createBaseArchitecture() {
        BuildTechnologyHandler buildTechnology = (BuildTechnologyHandler) getTechnologyByType(TechnologyHandlerType.BUILD_TECHNOLOGY);
        ORMTechnologyHandler ormTechnology = (ORMTechnologyHandler) getTechnologyByType(TechnologyHandlerType.ORM_TECHNOLOGY);
        MVCTechnologyHandler mvcTechnology = (MVCTechnologyHandler) getTechnologyByType(TechnologyHandlerType.MVC_TECHNOLOGY);

        buildTechnology.addDependencies(ormTechnology.getDependencies()); // todo may not be here
        buildTechnology.addDependencies(mvcTechnology.getDependencies()); // todo may not be here
        buildTechnology.createBasePlatform();
        try {
            ormTechnology.createBasePlatform();
            mvcTechnology.createBasePlatform();
        } catch (Exception e) { // todo
            e.printStackTrace();
        }
    }

    @Override
    public List<EntityElement> createEntity(Entity entity, String packagePath) {
        List<EntityElement> entityElements = new ArrayList<>();
        BuildTechnologyHandler buildTechnologyHandler = (BuildTechnologyHandler) getTechnologyByType(TechnologyHandlerType.BUILD_TECHNOLOGY);
        ORMTechnologyHandler ormTechnologyHandler = (ORMTechnologyHandler) getTechnologyByType(TechnologyHandlerType.ORM_TECHNOLOGY);
        MVCTechnologyHandler mvcTechnologyHandler = (MVCTechnologyHandler) getTechnologyByType(TechnologyHandlerType.MVC_TECHNOLOGY);

        EntityClass entityClassElement = ormTechnologyHandler.createEntityClass(entity, packagePath);
        if (entityClassElement != null)
            entityElements.add(entityClassElement);
        List<EntityElement> daoEntityElements = ormTechnologyHandler.createDao(entity); // TODO: 5/8/2016 EntityElement
        if (daoEntityElements != null)
            entityElements.addAll(daoEntityElements);

        return entityElements;
    }

    private TechnologyHandler getTechnologyByType(TechnologyHandlerType technologyHandlerType) { // TODO: 4/28/2016 must change
        for (TechnologyHandler technologyHandler : technologies) {
            if (technologyHandlerType.getValue() == 0 && technologyHandler instanceof BuildTechnologyHandler)
                return technologyHandler;
            else if (technologyHandlerType.getValue() == 1 && technologyHandler instanceof ORMTechnologyHandler)
                return technologyHandler;
            else if (technologyHandlerType.getValue() == 2 && technologyHandler instanceof MVCTechnologyHandler)
                return technologyHandler;
        }
        return null;
    }
}
