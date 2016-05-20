package ir.sk.jcg.jcgengine;

import ir.sk.jcg.jcgengine.model.platform.technology.SpringTechnology.SpringConfigType;
import ir.sk.jcg.jcgengine.model.platform.technology.SpringTechnology.SpringDIType;

import java.util.HashMap;
import java.util.Map;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 5/11/2016
 */
public class ApplicationContext {

    private static final ApplicationContext INSTANCE = new ApplicationContext();

    private String baseDir;

    private String packagePrefix;

    private String configPackage;

    private SpringConfigType springConfigType;

    private SpringDIType springDIType;

    private Map<String, Object> map = new HashMap<>();

    public static ApplicationContext getInstance() { return INSTANCE; }


    public String getBaseDir() {
        return baseDir;
    }

    public void setBaseDir(String baseDir) {
        this.baseDir = baseDir;
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

    public void setAttribute(String key , Object value) {
        map.put(key, value);
    }

    public Object getAttribute(String key) {
        return map.get(key);
    }
}
