package ir.sk.jcg.jcgengine.model.platform.technology.SpringTechnology;

import ir.sk.jcg.jcgcommon.PropertyView.annotation.Editable;
import ir.sk.jcg.jcgcommon.PropertyView.annotation.Prop;
import ir.sk.jcg.jcgengine.ApplicationContext;
import ir.sk.jcg.jcgengine.model.platform.Dependency;
import ir.sk.jcg.jcgengine.model.platform.technology.TechnologyHandler;
import ir.sk.jcg.jcgengine.velocity.Template;

import java.io.File;
import java.util.List;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 5/20/2016
 */
@Editable
public class SpringHandler extends TechnologyHandler {

    private static final String SPRING_GROUP_ID = "org.springframework";
    private static final String SPRING_VERSION = "4.3.7.Final";

    private SpringConfigType springConfigType;
    private SpringDIType springDIType;

    public SpringHandler() {
        super("Spring Handler");
        dependencies.add(new Dependency(SPRING_GROUP_ID, "spring-core", SPRING_VERSION, "compile")); // TODO: 5/20/2016 may better create SpringTechnologyHandler
        dependencies.add(new Dependency(SPRING_GROUP_ID, "spring-web", SPRING_VERSION, "compile"));
        dependencies.add(new Dependency(SPRING_GROUP_ID, "spring-context", SPRING_VERSION, "compile"));
    }

    public SpringConfigType getSpringConfigType() {
        return springConfigType;
    }

    public void setSpringConfigType(SpringConfigType springConfigType) {
        this.springConfigType = springConfigType;
        ApplicationContext.getInstance().setSpringConfigType(springConfigType);
    }

    public SpringDIType getSpringDIType() {
        return springDIType;
    }

    public void setSpringDIType(SpringDIType springDIType) {
        this.springDIType = springDIType;
        ApplicationContext.getInstance().setSpringDIType(springDIType);
    }

    @Override
    protected void createDirectories() throws Exception {

    }

    @Override
    protected List<Config> createConfigFiles() throws Exception {
        switch (springConfigType) {
            case JAVA:
                Template SpringConfigTemplate = new Template("oRMTechnology/hibernate/config/AppConfig.vm", "Spring Config",
                        baseDir + File.separator + ApplicationContext.getInstance().getConfigPackage() + "/AppConfig.java");
                SpringConfigTemplate.putReference("packageName", ApplicationContext.getInstance().getPackagePrefix() + "configuration"); // TODO: 5/20/2016
                SpringConfigTemplate.mergeTemplate();
                break;
            case XML_FILE:
                // must found main java config xml file and  add config name to that
                break;
            default:
        }
        return null;
    }

    @Override
    protected void createBaseFiles() throws Exception {

    }

    /**
     * Add Configs with Spring IOC
    * */
    public void addTechnologiesConfig(List<Config> configs) {
        switch (springConfigType) {
            case JAVA:
                // must found main java config file and  add config class name to that
                break;
            case XML_FILE:
                // must found main java config xml file and  add config name to that
                break;
            default:
        }
    }


}
