package ir.sk.jcg.jcgengine.model.platform.technology;

import ir.sk.jcg.jcgengine.model.platform.Dependency;

import java.io.File;
import java.util.List;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 4/13/2016
 */
public abstract class ORMTechnology extends Technology {

    public ORMTechnology(String name, File baseDir, List<Dependency> dependenciesList) {
        super(name, baseDir, dependenciesList);
    }

    @Override
    public void createBasePlatform() throws Exception {
        createDirectories();
        createBaseFiles();
    }

    protected abstract void createDirectories();

    protected abstract void createBaseFiles() throws Exception;

}
