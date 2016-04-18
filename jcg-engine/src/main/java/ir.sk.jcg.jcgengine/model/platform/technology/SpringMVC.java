package ir.sk.jcg.jcgengine.model.platform.technology;

import ir.sk.jcg.jcgengine.model.platform.Dependency;

import java.io.File;
import java.util.List;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 4/13/2016
 */
public class SpringMVC extends MVCTechnology {

    public SpringMVC(String name, File baseDir, List<Dependency> dependenciesList) {
        super(name, baseDir, dependenciesList);
    }

    @Override
    public void createBasePlatform() {

    }

}
