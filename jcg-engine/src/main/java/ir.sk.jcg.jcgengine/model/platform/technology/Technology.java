package ir.sk.jcg.jcgengine.model.platform.technology;

import ir.sk.jcg.jcgengine.model.platform.Dependency;

import java.io.File;
import java.util.List;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 4/13/2016
 */
public abstract class Technology {

    protected String name;
    protected File baseDir;
    protected List<Dependency> dependenciesList;

    public Technology(String name, File baseDir, List<Dependency> dependenciesList) {
        this.name = name;
        this.baseDir = baseDir;
        this.dependenciesList = dependenciesList;
    }

    public abstract void createBasePlatform() throws Exception;

    public File getBaseDir() {
        return baseDir;
    }

    public void setBaseDir(File baseDir) {
        this.baseDir = baseDir;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Dependency> getDependenciesList() {
        return dependenciesList;
    }

    public void setDependenciesList(List<Dependency> dependenciesList) {
        this.dependenciesList = dependenciesList;
    }

}
