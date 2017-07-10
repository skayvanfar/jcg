package ir.sk.jcg.jcgengine;

import ir.sk.jcg.jcgengine.model.platform.technology.SecurityTechnology.SecurityTechnologyHandler;
import ir.sk.jcg.jcgengine.model.platform.technology.SpringTechnology.SpringConfigType;
import ir.sk.jcg.jcgengine.model.platform.technology.SpringTechnology.SpringDIType;
import ir.sk.jcg.jcgengine.model.platform.technology.SpringTechnology.SpringHandler;
import ir.sk.jcg.jcgengine.model.platform.technology.buildTechnology.BuildTechnologyHandler;
import ir.sk.jcg.jcgengine.model.platform.technology.mvcTechnology.MVCTechnologyHandler;
import ir.sk.jcg.jcgengine.model.platform.technology.ormTechnology.ORMTechnologyHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 5/11/2016
 */
public class ApplicationContext {

    private static final ApplicationContext INSTANCE = new ApplicationContext();

    private String projectName;

    /**
     * Absolute Path of project
     */
    private String baseProjectPath;

    /**
     * Absolute Path of java source of project
     */
    private String mainJavaPath;

    /**
     * Absolute Path of Resources of project
     */
    private String mainResourcesPath;

    /**
     * Absolute Path of web source of project
     */
    private String mainWebPath;

    /**
     * Absolute Path of test source of project
     */
    private String testJavaPath;

    /**
     * Path of java test resources of project
     */
    private String testResourcesPath;

    /**
     * Absolute Java source with package prefix of project
     */
    private String JavaWithPackagePrefixPath;

    private String packagePrefix;

    private String configPackage;

    private SpringConfigType springConfigType;

    private SpringDIType springDIType;

    private Map<String, Object> map = new HashMap<>();

    private BuildTechnologyHandler buildTechnologyHandler;

    private ORMTechnologyHandler ormTechnologyHandler;

    private MVCTechnologyHandler mvcTechnologyHandler;

    private SpringHandler springHandler;

    private SecurityTechnologyHandler securityTechnologyHandler;

    public static ApplicationContext getInstance() {
        return INSTANCE;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getBaseProjectPath() {
        return baseProjectPath;
    }

    public void setBaseProjectPath(String baseProjectPath) {
        this.baseProjectPath = baseProjectPath;
    }

    public String getMainJavaPath() {
        return mainJavaPath;
    }

    public void setMainJavaPath(String mainJavaPath) {
        this.mainJavaPath = mainJavaPath;
    }

    public String getMainResourcesPath() {
        return mainResourcesPath;
    }

    public void setMainResourcesPath(String mainResourcesPath) {
        this.mainResourcesPath = mainResourcesPath;
    }

    public String getMainWebPath() {
        return mainWebPath;
    }

    public void setMainWebPath(String mainWebPath) {
        this.mainWebPath = mainWebPath;
    }

    public String getTestJavaPath() {
        return testJavaPath;
    }

    public void setTestJavaPath(String testJavaPath) {
        this.testJavaPath = testJavaPath;
    }

    public String getTestResourcesPath() {
        return testResourcesPath;
    }

    public void setTestResourcesPath(String testResourcesPath) {
        this.testResourcesPath = testResourcesPath;
    }

    public String getJavaWithPackagePrefixPath() {
        return JavaWithPackagePrefixPath;
    }

    public void setJavaWithPackagePrefixPath(String javaWithPackagePrefixPath) {
        JavaWithPackagePrefixPath = javaWithPackagePrefixPath;
    }

    public String getPackagePrefix() {
        return packagePrefix;
    }

    public void setPackagePrefix(String packagePrefix) {
        this.packagePrefix = packagePrefix;
    }

    public String getConfigPackage() {
        return configPackage;
    }

    public void setConfigPackage(String configPackage) {
        this.configPackage = configPackage;
    }

    public SpringConfigType getSpringConfigType() {
        return springConfigType;
    }

    public void setSpringConfigType(SpringConfigType springConfigType) {
        this.springConfigType = springConfigType;
    }

    public SpringDIType getSpringDIType() {
        return springDIType;
    }

    public void setSpringDIType(SpringDIType springDIType) {
        this.springDIType = springDIType;
    }

    public Map<String, Object> map() {
        return map;
    }

    public void setAttribute(String key, Object value) {
        map.put(key, value);
    }

    public Object getAttribute(String key) {
        return map.get(key);
    }

    public BuildTechnologyHandler getBuildTechnologyHandler() {
        return buildTechnologyHandler;
    }

    public void setBuildTechnologyHandler(BuildTechnologyHandler buildTechnologyHandler) {
        this.buildTechnologyHandler = buildTechnologyHandler;
    }

    public ORMTechnologyHandler getOrmTechnologyHandler() {
        return ormTechnologyHandler;
    }

    public void setOrmTechnologyHandler(ORMTechnologyHandler ormTechnologyHandler) {
        this.ormTechnologyHandler = ormTechnologyHandler;
    }

    public MVCTechnologyHandler getMvcTechnologyHandler() {
        return mvcTechnologyHandler;
    }

    public void setMvcTechnologyHandler(MVCTechnologyHandler mvcTechnologyHandler) {
        this.mvcTechnologyHandler = mvcTechnologyHandler;
    }

    public SpringHandler getSpringHandler() {
        return springHandler;
    }

    public void setSpringHandler(SpringHandler springHandler) {
        this.springHandler = springHandler;
    }

    public SecurityTechnologyHandler getSecurityTechnologyHandler() {
        return securityTechnologyHandler;
    }

    public void setSecurityTechnologyHandler(SecurityTechnologyHandler securityTechnologyHandler) {
        this.securityTechnologyHandler = securityTechnologyHandler;
    }
}
