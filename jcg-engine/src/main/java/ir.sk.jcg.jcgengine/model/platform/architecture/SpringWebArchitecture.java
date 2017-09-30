package ir.sk.jcg.jcgengine.model.platform.architecture;

import ir.sk.jcg.jcgcommon.PropertyView.annotation.Editable;
import ir.sk.jcg.jcgengine.ApplicationContext;
import ir.sk.jcg.jcgengine.databaseAccess.DatabaseDao;
import ir.sk.jcg.jcgengine.databaseAccess.JDBCDatabaseDao;
import ir.sk.jcg.jcgengine.databaseAccess.Menu;
import ir.sk.jcg.jcgengine.model.platform.technology.SpringTechnology.Config;
import ir.sk.jcg.jcgengine.model.platform.technology.TechnologyHandler;
import ir.sk.jcg.jcgengine.model.platform.technology.TechnologyHandlerType;
import ir.sk.jcg.jcgengine.model.platform.technology.buildTechnology.BuildTechnologyHandler;
import ir.sk.jcg.jcgengine.model.platform.technology.mvcTechnology.MVCTechnologyHandler;
import ir.sk.jcg.jcgengine.model.platform.technology.mvcTechnology.SpringMVC.element.ViewElement;
import ir.sk.jcg.jcgengine.model.platform.technology.ormTechnology.ORMTechnologyHandler;
import ir.sk.jcg.jcgengine.model.platform.technology.ormTechnology.hibernate.element.EntityClass;
import ir.sk.jcg.jcgengine.model.project.*;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 4/13/2016
 */
@XmlRootElement // TODO: 4/27/2016 must remove from this class
@Editable
public class SpringWebArchitecture extends Architecture {

    private static TechnologyHandlerType[] technologyHandlerTypes = {TechnologyHandlerType.BUILD_TECHNOLOGY, TechnologyHandlerType.ORM_TECHNOLOGY, TechnologyHandlerType.MVC_TECHNOLOGY};

    public SpringWebArchitecture() {
        super("Spring Web Architecture");
    }

    @Override
    public TechnologyHandlerType[] getTechnologyTypes() {
        return technologyHandlerTypes;
    }

    @Override
    public void createBaseArchitecture() {
        createBasePackage();

        BuildTechnologyHandler buildTechnology = (BuildTechnologyHandler) getTechnologyByType(TechnologyHandlerType.BUILD_TECHNOLOGY);
        ORMTechnologyHandler ormTechnology = (ORMTechnologyHandler) getTechnologyByType(TechnologyHandlerType.ORM_TECHNOLOGY);
        MVCTechnologyHandler mvcTechnology = (MVCTechnologyHandler) getTechnologyByType(TechnologyHandlerType.MVC_TECHNOLOGY);

        buildTechnology.addDependencies(springHandler.getDependencies()); // add dependencies of iocTechnologyHandler
        buildTechnology.addDependencies(securityTechnologyHandler.getDependencies());
        buildTechnology.addDependencies(ormTechnology.getDependencies()); // todo may not be here
        buildTechnology.addDependencies(mvcTechnology.getDependencies());
        try {
            buildTechnology.createBasePlatform();
            Config securityTechnologyHandlerConfig = securityTechnologyHandler.createBasePlatform();
            Config ormTechnologyConfig = ormTechnology.createBasePlatform();
            Config mvcTechnologyConfig = mvcTechnology.createBasePlatform();

            List<Config> allConfigs = new ArrayList<>();

            allConfigs.add(ormTechnologyConfig);
            allConfigs.add(mvcTechnologyConfig);
            springHandler.addTechnologiesConfig(allConfigs, securityTechnologyHandlerConfig);
            springHandler.createBasePlatform();
        } catch (Exception e) { // todo
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(String baseDir, Project project) {
        ApplicationContext applicationContext = ApplicationContext.getInstance();

        applicationContext.setBaseProjectPath(baseDir);
        applicationContext.setPackagePrefix(project.getPackagePrefix());
        applicationContext.setConfigPackage(project.getConfigPackage());
        applicationContext.setProjectName(project.getName());
        applicationContext.setPersianProjectName(project.getPersianName());
        BuildTechnologyHandler buildTechnology = (BuildTechnologyHandler) getTechnologyByType(TechnologyHandlerType.BUILD_TECHNOLOGY);
        ORMTechnologyHandler ormTechnology = (ORMTechnologyHandler) getTechnologyByType(TechnologyHandlerType.ORM_TECHNOLOGY);
        MVCTechnologyHandler mvcTechnology = (MVCTechnologyHandler) getTechnologyByType(TechnologyHandlerType.MVC_TECHNOLOGY);

        applicationContext.setMainJavaPath(ApplicationContext.getInstance().getBaseProjectPath() + File.separator + buildTechnology.getMainJavaPath());
        applicationContext.setMainResourcesPath(ApplicationContext.getInstance().getBaseProjectPath() + File.separator + buildTechnology.getMainResourcesPath());
        applicationContext.setMainWebPath(ApplicationContext.getInstance().getBaseProjectPath() + File.separator + buildTechnology.getMainWebPath());
        applicationContext.setTestJavaPath(ApplicationContext.getInstance().getBaseProjectPath() + File.separator + buildTechnology.getTestJavaPath());
        applicationContext.setTestResourcesPath(ApplicationContext.getInstance().getBaseProjectPath() + File.separator + buildTechnology.getTestResourcesPath());
        applicationContext.setJavaWithPackagePrefixPath(ApplicationContext.getInstance().getMainJavaPath() + File.separator + ApplicationContext.getInstance().getPackagePrefix().replace('.', '/'));

        applicationContext.setBuildTechnologyHandler(buildTechnology); // TODO: 6/20/2016
        applicationContext.setOrmTechnologyHandler(ormTechnology);
        applicationContext.setMvcTechnologyHandler(mvcTechnology);
        applicationContext.setSpringHandler(springHandler);
        applicationContext.setSecurityTechnologyHandler(securityTechnologyHandler);
    }

    /**
     * Create base dirs
     */
    private void createBasePackage() {
        BuildTechnologyHandler buildTechnology = (BuildTechnologyHandler) getTechnologyByType(TechnologyHandlerType.BUILD_TECHNOLOGY);

        // Create config package
        File configDirFile = new File(ApplicationContext.getInstance().getBaseProjectPath() + File.separator + buildTechnology.getMainJavaPath() + File.separator + ApplicationContext.getInstance().getPackagePrefix().replace('.', '/')
                + File.separator + ApplicationContext.getInstance().getConfigPackage()); // TODO: 5/20/2016
        configDirFile.mkdirs();

        // Create commons package
        File commonDirFile = new File(ApplicationContext.getInstance().getBaseProjectPath() + File.separator + buildTechnology.getMainJavaPath() + File.separator + ApplicationContext.getInstance().getPackagePrefix().replace('.', '/')
                + File.separator + "commons");
        commonDirFile.mkdirs();
    }

    @Override
    public void createEntity(Entity entity, String packagePath) {
        List<DomainImplElement> domainImplElements = new ArrayList<>();
        ORMTechnologyHandler ormTechnologyHandler = (ORMTechnologyHandler) getTechnologyByType(TechnologyHandlerType.ORM_TECHNOLOGY);
        MVCTechnologyHandler mvcTechnologyHandler = (MVCTechnologyHandler) getTechnologyByType(TechnologyHandlerType.MVC_TECHNOLOGY);

        EntityClass entityClassElement = ormTechnologyHandler.createEntityClass(entity, packagePath);
        if (entityClassElement != null)
            domainImplElements.add(entityClassElement);
        // TODO: 7/9/2016 must create Dao ,Service and Controller for specefic Entities as Regards Relationship between Daoes
        List<DomainImplElement> daoDomainImplElements = ormTechnologyHandler.createDao(entity, packagePath); // TODO: 5/8/2016 EntityElement
        if (daoDomainImplElements != null)
            domainImplElements.addAll(daoDomainImplElements);
        List<DomainImplElement> controllerDomainImplElements = mvcTechnologyHandler.createController(entity, packagePath); // TODO: 6/20/2016 may better not use Controller hear
        if (controllerDomainImplElements != null)
            domainImplElements.addAll(controllerDomainImplElements);
        entity.addAllImplElements(domainImplElements);

        // Add Menu record to db
        // TODO: 9/22/2017
        Menu modelMenu = new Menu();
        modelMenu.setTitle(entity.getLabelName());
        modelMenu.setPriority(0L);
        String connectionUrl = "jdbc:mysql://localhost:3306/" + ApplicationContext.getInstance().getProjectName() + "?characterEncoding=UTF-8";
        DatabaseDao databaseDao = new JDBCDatabaseDao("com.mysql.jdbc.Driver", connectionUrl, 3306, "root", "metalgearsolid5");
        try {
            databaseDao.save(modelMenu);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void createView(View view, String packagePath) {
        List<DomainImplElement> domainImplElements = new ArrayList<>();
        ORMTechnologyHandler ormTechnologyHandler = (ORMTechnologyHandler) getTechnologyByType(TechnologyHandlerType.ORM_TECHNOLOGY);
        MVCTechnologyHandler mvcTechnologyHandler = (MVCTechnologyHandler) getTechnologyByType(TechnologyHandlerType.MVC_TECHNOLOGY);

        // TODO: 9/30/2017 for search must added
        if (view instanceof CreateEditView)
            ormTechnologyHandler.addViewService(view, packagePath);

        ViewElement viewElement = mvcTechnologyHandler.createView(view, packagePath);
        if (viewElement != null)
            domainImplElements.add(viewElement);

        //////////////////////////////////////
        Menu subMenu = new Menu();
        String connectionUrl = "jdbc:mysql://localhost:3306/" + ApplicationContext.getInstance().getProjectName() + "?characterEncoding=UTF-8";
        DatabaseDao databaseDao = new JDBCDatabaseDao("com.mysql.jdbc.Driver", connectionUrl, 3306, "root", "metalgearsolid5");
        try {
            Long mainIdMenu = databaseDao.loadMenuIdByTitleName(view.getTargetEntity().getLabelName());
            Menu viewMenu = new Menu();
            viewMenu.setTitle(view.getName());
            viewMenu.setPriority(0l);
            String lowerCaseEntityName = Character.toLowerCase(view.getTargetEntity().getName().charAt(0)) + view.getTargetEntity().getName().substring(1);
            String lowerCaseViewFileName = Character.toLowerCase(view.getViewFileName().charAt(0)) + view.getViewFileName().substring(1);
            viewMenu.setUrl(lowerCaseEntityName + "s/" + lowerCaseViewFileName);
            viewMenu.setParentId(mainIdMenu);
            databaseDao.save(viewMenu);
        } catch (SQLException e) {
            e.printStackTrace();
        }

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
