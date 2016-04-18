package ir.sk.jcg.jcgengine.model.platform.technology;

import ir.sk.jcg.jcgengine.model.platform.Dependency;

import java.io.File;
import java.util.List;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 4/13/2016
 */
public abstract class BuildTechnology extends Technology {

    protected File mainJavaDir;
    protected File mainResourcesDir;
    protected File mainWebDir;
    protected File testJavaDir;
    protected File testResourcesDir;

    public BuildTechnology(String name, File baseDir, List<Dependency> dependenciesList) {
        super(name, baseDir, dependenciesList);
    }

    @Override
    public void createBasePlatform() {
        createDirectories();
        createBuildFile();
    }

    protected abstract void createDirectories();
    protected abstract void createBuildFile();

    public File getMainJavaDir() {
        return mainJavaDir;
    }

    public void setMainJavaDir(File mainJavaDir) {
        this.mainJavaDir = mainJavaDir;
    }

    public File getMainResourcesDir() {
        return mainResourcesDir;
    }

    public void setMainResourcesDir(File mainResourcesDir) {
        this.mainResourcesDir = mainResourcesDir;
    }

    public File getMainWebDir() {
        return mainWebDir;
    }

    public void setMainWebDir(File mainWebDir) {
        this.mainWebDir = mainWebDir;
    }

    public File getTestJavaDir() {
        return testJavaDir;
    }

    public void setTestJavaDir(File testJavaDir) {
        this.testJavaDir = testJavaDir;
    }

    public File getTestResourcesDir() {
        return testResourcesDir;
    }

    public void setTestResourcesDir(File testResourcesDir) {
        this.testResourcesDir = testResourcesDir;
    }

}
