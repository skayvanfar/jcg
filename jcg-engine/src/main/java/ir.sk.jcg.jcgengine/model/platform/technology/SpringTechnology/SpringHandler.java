package ir.sk.jcg.jcgengine.model.platform.technology.SpringTechnology;

import ir.sk.jcg.jcgcommon.PropertyView.annotation.Editable;
import ir.sk.jcg.jcgengine.model.platform.Dependency;
import ir.sk.jcg.jcgengine.model.platform.technology.TechnologyHandler;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 5/20/2016
 */
@Editable
public class SpringHandler extends TechnologyHandler {

    private static final String SPRING_GROUP_ID = "org.springframework";
    private static final String SPRING_VERSION = "4.3.7.Final";

    private SpringConfigType springConfigType;

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
    }

    @Override
    protected void createDirectories() throws Exception {

    }

    @Override
    protected void createBaseFiles() throws Exception {

    }


}
