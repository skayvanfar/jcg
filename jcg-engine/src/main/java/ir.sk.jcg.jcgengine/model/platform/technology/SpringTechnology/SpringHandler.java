package ir.sk.jcg.jcgengine.model.platform.technology.SpringTechnology;

import ir.sk.jcg.jcgcommon.PropertyView.ComponentType;
import ir.sk.jcg.jcgcommon.PropertyView.annotation.Editable;
import ir.sk.jcg.jcgcommon.PropertyView.annotation.Prop;
import ir.sk.jcg.jcgengine.ApplicationContext;
import ir.sk.jcg.jcgengine.model.platform.Dependency;
import ir.sk.jcg.jcgengine.model.platform.Exclusion;
import ir.sk.jcg.jcgengine.model.platform.technology.TechnologyHandler;
import ir.sk.jcg.jcgengine.velocity.Template;

import javax.xml.bind.annotation.XmlAttribute;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 5/20/2016
 */
@Editable
public class SpringHandler extends TechnologyHandler {

    private static final String SPRING_GROUP_ID = "org.springframework";
    private static final String SPRING_VERSION = "4.0.6.RELEASE";

    private static final String ASPECTJ_GROUP_ID = "org.aspectj";
    private static final String ASPECTJ_VERSION = "1.8.1";

    @Prop(label = "Spring Config Type", componentType = ComponentType.NON_EDITABLE_COMBO, required = true)
    private SpringConfigType springConfigType;

    @Prop(label = "Spring DI Type", componentType = ComponentType.NON_EDITABLE_COMBO, required = true)
    private SpringDIType springDIType;

    private List<Config> configs = new ArrayList<>(); // TODO: 5/21/2016 may not need
    private Config securityConfig;

    public SpringHandler() {
        super("Spring Handler");
        dependencies.add(new Dependency(SPRING_GROUP_ID, "spring-core", SPRING_VERSION, "compile")); // TODO: 5/20/2016 may better create SpringTechnologyHandler
        Dependency springContextDependency = new Dependency(SPRING_GROUP_ID, "spring-context", SPRING_VERSION, "compile");
        springContextDependency.addExclusion(new Exclusion("commons-logging", "commons-logging"));
        dependencies.add(springContextDependency);
        dependencies.add(new Dependency(SPRING_GROUP_ID, "spring-orm", SPRING_VERSION, "compile"));
        dependencies.add(new Dependency(SPRING_GROUP_ID, "spring-tx", SPRING_VERSION, "compile"));
        dependencies.add(new Dependency(SPRING_GROUP_ID, "spring-web", SPRING_VERSION, "compile"));
        dependencies.add(new Dependency(SPRING_GROUP_ID, "spring-context-support", SPRING_VERSION, "compile"));
        dependencies.add(new Dependency(SPRING_GROUP_ID, "spring-oxm", "4.0.3.RELEASE", "compile"));
        dependencies.add(new Dependency("org.springframework.webflow", "spring-webflow", "2.3.3.RELEASE", "compile"));
        dependencies.add(new Dependency(SPRING_GROUP_ID, "spring-test", SPRING_VERSION, "compile"));
        dependencies.add(new Dependency(ASPECTJ_GROUP_ID, "aspectjrt", ASPECTJ_VERSION, "compile"));
        dependencies.add(new Dependency(ASPECTJ_GROUP_ID, "aspectjweaver", ASPECTJ_VERSION, "compile"));

        dependencies.add(new Dependency("javax.inject", "javax.inject", "1", "compile"));

        dependencies.add(new Dependency("org.slf4j", "jcl-over-slf4j", "1.7.13", "compile"));
        dependencies.add(new Dependency("ch.qos.logback", "logback-classic", "1.1.3", "compile"));

    }

    public SpringConfigType getSpringConfigType() {
        return springConfigType;
    }

    @XmlAttribute
    public void setSpringConfigType(SpringConfigType springConfigType) {
        this.springConfigType = springConfigType;
  //      ApplicationContext.getInstance().setSpringConfigType(springConfigType);
    }

    public SpringDIType getSpringDIType() {
        return springDIType;
    }

    @XmlAttribute
    public void setSpringDIType(SpringDIType springDIType) {
        this.springDIType = springDIType;
    }

    @Override
    protected void createDirectories() throws Exception {
        // Create commons package
        File localizationDirFile = new File(ApplicationContext.getInstance().getJavaWithPackagePrefixPath()
                + File.separator + "commons" + File.separator + "localization");
        localizationDirFile.mkdirs();

        File persistenceDirFile = new File(ApplicationContext.getInstance().getJavaWithPackagePrefixPath()
                + File.separator + "commons" + File.separator + "persistence");
        persistenceDirFile.mkdirs();

        File utilDirFile = new File(ApplicationContext.getInstance().getJavaWithPackagePrefixPath()
                + File.separator + "commons" + File.separator + "util");
        utilDirFile.mkdirs();
    }

    @Override
    protected Config createConfigFiles() throws Exception {
        Template springAppConfigTemplate = new Template("AppConfig", "architecture/springWebArchitecture/javaConfig/AppConfig.vm",
                ApplicationContext.getInstance().getJavaWithPackagePrefixPath() + File.separator + ApplicationContext.getInstance().getConfigPackage() + "/AppConfig.java");
        springAppConfigTemplate.putReference("packageName", ApplicationContext.getInstance().getPackagePrefix() + "." + ApplicationContext.getInstance().getConfigPackage()); // TODO: 5/20/2016
        //   springAppConfigTemplate.putReference("securityConfig", securityConfig.toString()); // TODO: 6/4/16 for now
        springAppConfigTemplate.putReference("configs", configs);
        springAppConfigTemplate.mergeTemplate();

        createCommonFiles();
        return null;
    }

    /**
     * Create commons files
     * */
    private void createCommonFiles() {
        /////////////////////////////////////
        Template localizedEnumTemplate = new Template("LocalizedEnum", "commons/localization/LocalizedEnum.vm",
                ApplicationContext.getInstance().getJavaWithPackagePrefixPath() + File.separator + "commons/localization/LocalizedEnum.java");
        localizedEnumTemplate.putReference("packagePrefix", ApplicationContext.getInstance().getPackagePrefix());
        localizedEnumTemplate.mergeTemplate();

        /////////////////////////////////////
        Template pagingDataListTemplate = new Template("PagingDataList", "commons/persistence/PagingDataList.vm",
                ApplicationContext.getInstance().getJavaWithPackagePrefixPath() + File.separator + "commons/persistence/PagingDataList.java");
        pagingDataListTemplate.putReference("packagePrefix", ApplicationContext.getInstance().getPackagePrefix());
        pagingDataListTemplate.mergeTemplate();

        ///////////////////////////////////////
        Template persistenceExceptionTemplate = new Template("PersistenceException", "commons/persistence/PersistenceException.vm",
                ApplicationContext.getInstance().getJavaWithPackagePrefixPath() + File.separator + "commons/persistence/PersistenceException.java");
        persistenceExceptionTemplate.putReference("packagePrefix", ApplicationContext.getInstance().getPackagePrefix());
        persistenceExceptionTemplate.mergeTemplate();

        ///////////////////////////////////////
        Template persistenceExceptionTypeTemplate = new Template("persistenceExceptionType", "commons/persistence/PersistenceExceptionType.vm",
                ApplicationContext.getInstance().getJavaWithPackagePrefixPath() + File.separator + "commons/persistence/PersistenceExceptionType.java");
        persistenceExceptionTypeTemplate.putReference("packagePrefix", ApplicationContext.getInstance().getPackagePrefix());
        persistenceExceptionTypeTemplate.mergeTemplate();

        ///////////////////////////////////////
        Template sortableDataListTemplate = new Template("SortableDataList", "commons/persistence/SortableDataList.vm",
                ApplicationContext.getInstance().getJavaWithPackagePrefixPath() + File.separator + "commons/persistence/SortableDataList.java");
        sortableDataListTemplate.putReference("packagePrefix", ApplicationContext.getInstance().getPackagePrefix());
        sortableDataListTemplate.mergeTemplate();

        ///////////////////////////////////////
        Template sortOrderTemplate = new Template("SortOrder", "commons/persistence/SortOrder.vm",
                ApplicationContext.getInstance().getJavaWithPackagePrefixPath() + File.separator + "commons/persistence/SortOrder.java");
        sortOrderTemplate.putReference("packagePrefix", ApplicationContext.getInstance().getPackagePrefix());
        sortOrderTemplate.mergeTemplate();
    }

    @Override
    protected void createBaseFiles() throws Exception {
        Template SpringAppConfigTemplate = new Template("SpringApplicationContext", "architecture/springWebArchitecture/SpringApplicationContext.vm",
                ApplicationContext.getInstance().getJavaWithPackagePrefixPath() + File.separator + ApplicationContext.getInstance().getConfigPackage() + "/SpringApplicationContext.java");
        SpringAppConfigTemplate.putReference("packageName", ApplicationContext.getInstance().getPackagePrefix() + "." + ApplicationContext.getInstance().getConfigPackage());
        SpringAppConfigTemplate.mergeTemplate();
    }

    /**
     * Add Configs with Spring IOC
    * */
    public void addTechnologiesConfig(List<Config> configs, Config securityConfig) {
        this.configs.addAll(configs);
        this.securityConfig = securityConfig;
    }


}
