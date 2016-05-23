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

    @Prop(label = "Spring Config Type", componentType = ComponentType.NON_EDITABLE_COMBO, editableInWizard = true, required = true)
    private SpringConfigType springConfigType;

    @Prop(label = "Spring DI Type", componentType = ComponentType.NON_EDITABLE_COMBO, editableInWizard = true, required = true)
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
    //    ApplicationContext.getInstance().setSpringDIType(springDIType);
    }

    @Override
    protected void createDirectories() throws Exception {

    }

    @Override
    protected Config createJavaConfig() {
        Template SpringAppConfigTemplate = new Template("AppConfig", "architecture/springWebArchitecture/javaConfig/AppConfig.vm",
                ApplicationContext.getInstance().getJavaWithPackagePrefixPath() + File.separator + ApplicationContext.getInstance().getConfigPackage() + "/AppConfig.java");
        SpringAppConfigTemplate.putReference("packageName", ApplicationContext.getInstance().getPackagePrefix() + "." + ApplicationContext.getInstance().getConfigPackage()); // TODO: 5/20/2016
        SpringAppConfigTemplate.putReference("securityConfig", securityConfig.toString());
        SpringAppConfigTemplate.putReference("configs", configs);
        SpringAppConfigTemplate.mergeTemplate();
        return null;
    }

    @Override
    protected Config createXmlConfig() {
        return null; // TODO: 5/22/2016
    }

    @Override
    protected void createBaseFiles() throws Exception {
        Template SpringAppConfigTemplate = new Template("SpringApplicationContext", "architecture/springWebArchitecture/SpringApplicationContext.vm",
                ApplicationContext.getInstance().getJavaWithPackagePrefixPath() + File.separator + ApplicationContext.getInstance().getConfigPackage() + "/SpringApplicationContext.java");
        SpringAppConfigTemplate.putReference("packageName", ApplicationContext.getInstance().getPackagePrefix() + "." + ApplicationContext.getInstance().getConfigPackage());
        SpringAppConfigTemplate.mergeTemplate();
    }

    @Override
    protected void createAnnotationDIBaseFiles() {

    }

    @Override
    protected void createXmlDIBaseFiles() {

    }

    @Override
    protected void createJavaDIBaseFiles() {

    }

    /**
     * Add Configs with Spring IOC
    * */
    public void addTechnologiesConfig(List<Config> configs, Config securityConfig) {
        this.configs.addAll(configs);
        this.securityConfig = securityConfig;
    }


}
