package ir.sk.jcg.jcgengine.model.platform.architecture;

import ir.sk.jcg.jcgcommon.PropertyView.annotation.Editable;
import ir.sk.jcg.jcgengine.ApplicationContext;
import ir.sk.jcg.jcgengine.model.platform.technology.*;
import ir.sk.jcg.jcgengine.model.platform.technology.SpringTechnology.Config;
import ir.sk.jcg.jcgengine.model.platform.technology.SpringTechnology.SpringHandler;
import ir.sk.jcg.jcgengine.model.platform.technology.buildTechnology.BuildTechnologyHandler;
import ir.sk.jcg.jcgengine.model.platform.technology.mvcTechnology.MVCTechnologyHandler;
import ir.sk.jcg.jcgengine.model.platform.technology.ormTechnology.ORMTechnologyHandler;
import ir.sk.jcg.jcgengine.model.platform.technology.ormTechnology.hibernate.element.EntityClass;
import ir.sk.jcg.jcgengine.model.project.Entity;
import ir.sk.jcg.jcgengine.model.project.ModelImplElement;

import javax.xml.bind.annotation.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 4/13/2016
 */
@XmlRootElement // TODO: 4/27/2016 must remove from this class
@Editable
public class SpringWebArchitecture extends Architecture {

    private static TechnologyHandlerType[] technologyHandlerTypes = {TechnologyHandlerType.BUILD_TECHNOLOGY, TechnologyHandlerType.ORM_TECHNOLOGY, TechnologyHandlerType.MVC_TECHNOLOGY};

  //  private String baseDir; // TODO: 5/3/2016 may better use Project
 //   private String basePackageName; // TODO: 5/20/2016 may better go to ApplicationCotext
 //   private String baseConfigDir; // TODO: 5/20/2016 may better go to ApplicationCotext

    private SpringHandler springHandler;

    public SpringWebArchitecture() {
        super("Spring Web Architecture");
        springHandler = new SpringHandler();
    }

    @Override
    public void setBaseDirOfTechnologies() {
 //       this.baseDir = baseDir;
        
        BuildTechnologyHandler buildTechnology = (BuildTechnologyHandler) getTechnologyByType(TechnologyHandlerType.BUILD_TECHNOLOGY); // TODO: 5/3/2016 repeated code (in setBasePackageNameOfTechnologies)
        ORMTechnologyHandler ormTechnology = (ORMTechnologyHandler) getTechnologyByType(TechnologyHandlerType.ORM_TECHNOLOGY);
        MVCTechnologyHandler mvcTechnology = (MVCTechnologyHandler) getTechnologyByType(TechnologyHandlerType.MVC_TECHNOLOGY);

        buildTechnology.setBaseDir(ApplicationContext.getInstance().getBaseDir());
        String baseJavaDir = ApplicationContext.getInstance().getBaseDir() + buildTechnology.getMainJavaDir();
        String baseDir = baseJavaDir + File.separator + ApplicationContext.getInstance().getPackagePrefix().replace('.', '/');
        springHandler.setBaseDir(baseDir);
        ormTechnology.setBaseDir(baseDir);
        mvcTechnology.setBaseDir(baseDir);
    }

//    @Override
//    protected void setPackagePrefixOfTechnologies() {
//   //     this.basePackageName = basePackageName;
//        BuildTechnologyHandler buildTechnology = (BuildTechnologyHandler) getTechnologyByType(TechnologyHandlerType.BUILD_TECHNOLOGY);
//        ORMTechnologyHandler ormTechnology = (ORMTechnologyHandler) getTechnologyByType(TechnologyHandlerType.ORM_TECHNOLOGY);
//        MVCTechnologyHandler mvcTechnology = (MVCTechnologyHandler) getTechnologyByType(TechnologyHandlerType.MVC_TECHNOLOGY);
//        springHandler.setPackagePrefixName(ApplicationContext.getInstance().getPackagePrefix());
//        buildTechnology.setPackagePrefixName(ApplicationContext.getInstance().getPackagePrefix());
//        ormTechnology.setPackagePrefixName(ApplicationContext.getInstance().getPackagePrefix());
//        mvcTechnology.setPackagePrefixName(ApplicationContext.getInstance().getPackagePrefix());
//    }

//    @Override
//    public void setConfigPackageOfTechnologies() {
// //       this.baseConfigDir = configDir;
//
//        BuildTechnologyHandler buildTechnology = (BuildTechnologyHandler) getTechnologyByType(TechnologyHandlerType.BUILD_TECHNOLOGY); // TODO: 5/3/2016 repeated code (in setBasePackageNameOfTechnologies)
//        ORMTechnologyHandler ormTechnology = (ORMTechnologyHandler) getTechnologyByType(TechnologyHandlerType.ORM_TECHNOLOGY);
//        MVCTechnologyHandler mvcTechnology = (MVCTechnologyHandler) getTechnologyByType(TechnologyHandlerType.MVC_TECHNOLOGY);
//
//        springHandler.setConfigDir(ApplicationContext.getInstance().getConfigPackage());
//        buildTechnology.setConfigDir(ApplicationContext.getInstance().getConfigPackage());
//        ormTechnology.setConfigDir(ApplicationContext.getInstance().getConfigPackage()); // TODO: 5/20/2016 bad code
//        mvcTechnology.setConfigDir(ApplicationContext.getInstance().getConfigPackage()); // TODO: 5/20/2016 bad code
//    }

    @Override
    public TechnologyHandlerType[] getTechnologyTypes() {
        return technologyHandlerTypes;
    }

    @Override
    public void createView() {
    }

    @Override
    public void createBaseArchitecture() {
        createBasePackage();

        BuildTechnologyHandler buildTechnology = (BuildTechnologyHandler) getTechnologyByType(TechnologyHandlerType.BUILD_TECHNOLOGY);
        ORMTechnologyHandler ormTechnology = (ORMTechnologyHandler) getTechnologyByType(TechnologyHandlerType.ORM_TECHNOLOGY);
        MVCTechnologyHandler mvcTechnology = (MVCTechnologyHandler) getTechnologyByType(TechnologyHandlerType.MVC_TECHNOLOGY);

        buildTechnology.addDependencies(springHandler.getDependencies()); // add dependencies of iocTechnologyHandler
        buildTechnology.addDependencies(ormTechnology.getDependencies()); // todo may not be here
        buildTechnology.addDependencies(mvcTechnology.getDependencies()); // todo may not be here
        try {
            buildTechnology.createBasePlatform();
            List<Config> ormTechnologyConfigs = ormTechnology.createBasePlatform();
        //    List<Config> buildTechnologyConfigs = mvcTechnology.createBasePlatform();
            springHandler.createBasePlatform();
            List<Config> allConfigs = new ArrayList<>();
            allConfigs.addAll(ormTechnologyConfigs);
            springHandler.addTechnologiesConfig(allConfigs);
        } catch (Exception e) { // todo
            e.printStackTrace();
        }
    }

    private void createBasePackage() {

        BuildTechnologyHandler buildTechnology = (BuildTechnologyHandler) getTechnologyByType(TechnologyHandlerType.BUILD_TECHNOLOGY);

        File configDirFile = new File(ApplicationContext.getInstance().getBaseDir() + File.separator + buildTechnology.getMainJavaDir() + File.separator + ApplicationContext.getInstance().getPackagePrefix().replace('.', '/')
                + File.separator + ApplicationContext.getInstance().getConfigPackage()); // TODO: 5/20/2016
        configDirFile.mkdirs();
    }

    @Override
    public List<ModelImplElement> createEntity(Entity entity, String packagePath) {
        List<ModelImplElement> modelImplElements = new ArrayList<>();
        ORMTechnologyHandler ormTechnologyHandler = (ORMTechnologyHandler) getTechnologyByType(TechnologyHandlerType.ORM_TECHNOLOGY);

        EntityClass entityClassElement = ormTechnologyHandler.createEntityClass(entity, packagePath);
        if (entityClassElement != null)
            modelImplElements.add(entityClassElement);
        List<ModelImplElement> daoModelImplElements = ormTechnologyHandler.createDao(entity); // TODO: 5/8/2016 EntityElement
        if (daoModelImplElements != null)
            modelImplElements.addAll(daoModelImplElements);

        return modelImplElements;
    }

    public TechnologyHandler getTechnologyByType(TechnologyHandlerType technologyHandlerType) { // TODO: 4/28/2016 must change
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
