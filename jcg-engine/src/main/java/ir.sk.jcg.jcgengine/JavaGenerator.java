package ir.sk.jcg.jcgengine;

import ir.sk.jcg.jcgengine.model.platform.architecture.Architecture;
import ir.sk.jcg.jcgengine.model.project.Entity;
import ir.sk.jcg.jcgengine.model.project.Project;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 4/13/2016
 */
public class JavaGenerator implements Generator {

    private Project project;

    @Override
    public boolean createBaseProject(Project project) {
        Architecture architecture = project.getArchitecture();
        architecture.createBaseArchitecture();

        return false;
    }

    public boolean addEntity(Entity entity) {
        return false;
    }
}
