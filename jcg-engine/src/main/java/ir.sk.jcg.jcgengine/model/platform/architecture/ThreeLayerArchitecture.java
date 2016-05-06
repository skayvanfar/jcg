package ir.sk.jcg.jcgengine.model.platform.architecture;

import ir.sk.jcg.jcgengine.model.platform.Dependency;
import ir.sk.jcg.jcgengine.model.platform.technology.*;
import ir.sk.jcg.jcgengine.model.project.Entity;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.xml.bind.annotation.*;
import java.io.File;
import java.util.*;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 4/13/2016
 */
@XmlRootElement // TODO: 4/27/2016 must remove from this class
public class ThreeLayerArchitecture extends Architecture {

    private static TechnologyType[] technologyTypes = {TechnologyType.BUILD_TECHNOLOGY, TechnologyType.ORM_TECHNOLOGY, TechnologyType.MVC_TECHNOLOGY};

    private String baseDir; // TODO: 5/3/2016 may beeter use Project
    private String basePackageName;

    @Override
    public void setBaseDirOfTechnologies(String baseDir) {
        this.baseDir = baseDir;
        BuildTechnology buildTechnology = (BuildTechnology) getTechnologyByType(TechnologyType.BUILD_TECHNOLOGY); // TODO: 5/3/2016 repeated code (in setBasePackageNameOfTechnologies) 
        ORMTechnology ormTechnology = (ORMTechnology) getTechnologyByType(TechnologyType.ORM_TECHNOLOGY);
        MVCTechnology mvcTechnology = (MVCTechnology) getTechnologyByType(TechnologyType.MVC_TECHNOLOGY);

        buildTechnology.setBaseDir(baseDir);
        ormTechnology.setBaseDir(baseDir + buildTechnology.getMainJavaDir());
        mvcTechnology.setBaseDir(baseDir + buildTechnology.getMainJavaDir());
    }

    @Override
    protected void setBasePackageNameOfTechnologies(String basePackageName) {
        this.basePackageName = basePackageName;
        BuildTechnology buildTechnology = (BuildTechnology) getTechnologyByType(TechnologyType.BUILD_TECHNOLOGY);
        ORMTechnology ormTechnology = (ORMTechnology) getTechnologyByType(TechnologyType.ORM_TECHNOLOGY);
        MVCTechnology mvcTechnology = (MVCTechnology) getTechnologyByType(TechnologyType.MVC_TECHNOLOGY);
        buildTechnology.setBasePackageName(basePackageName);
        ormTechnology.setBasePackageName(basePackageName);
        mvcTechnology.setBasePackageName(basePackageName);
    }

    @Override
    public TechnologyType[] getTechnologyTypes() {
        return technologyTypes;
    }

    @Override
    public void createView() {
    }

    @Override
    public void createBaseArchitecture() {
        BuildTechnology buildTechnology = (BuildTechnology) getTechnologyByType(TechnologyType.BUILD_TECHNOLOGY);
        ORMTechnology ormTechnology = (ORMTechnology) getTechnologyByType(TechnologyType.ORM_TECHNOLOGY);
        MVCTechnology mvcTechnology = (MVCTechnology) getTechnologyByType(TechnologyType.MVC_TECHNOLOGY);

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
    public void createEntity(Entity entity, String packagePath) {
        BuildTechnology buildTechnology = (BuildTechnology) getTechnologyByType(TechnologyType.BUILD_TECHNOLOGY);
        ORMTechnology ormTechnology = (ORMTechnology) getTechnologyByType(TechnologyType.ORM_TECHNOLOGY);
        MVCTechnology mvcTechnology = (MVCTechnology) getTechnologyByType(TechnologyType.MVC_TECHNOLOGY);

        ormTechnology.createEntity(entity, packagePath);
        ormTechnology.createDao(entity);
    }

    private Technology  getTechnologyByType(TechnologyType technologyType) { // TODO: 4/28/2016 must change
        for (Technology technology : technologies) {
            if (technologyType.getValue() == 0 && technology instanceof BuildTechnology)
                return technology;
            else if (technologyType.getValue() == 1 && technology instanceof ORMTechnology)
                return technology;
            else if (technologyType.getValue() == 2 && technology instanceof MVCTechnology)
                return technology;
        }
        return null;
    }
}
