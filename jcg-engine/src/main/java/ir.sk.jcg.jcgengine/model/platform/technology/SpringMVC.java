package ir.sk.jcg.jcgengine.model.platform.technology;

import ir.sk.jcg.jcgengine.model.platform.Dependency;
import ir.sk.jcg.jcgengine.velocity.VelocityTemplate;
import org.apache.velocity.VelocityContext;

import java.io.File;
import java.util.List;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 4/13/2016
 */
public class SpringMVC extends MVCTechnology {

    private static final String SPRING_GROUP_ID = "org.springframework";
    private static final String SPRING_VERSION = "4.3.7.Final";

    private VelocityContext velocityContext = new VelocityContext(); // TODO: 4/21/2016

    private File controllerDir;

    public SpringMVC() {
        dependencies.add(new Dependency(SPRING_GROUP_ID, "spring-core", SPRING_VERSION, "compile"));
        dependencies.add(new Dependency(SPRING_GROUP_ID, "spring-web", SPRING_VERSION, "compile"));
        dependencies.add(new Dependency(SPRING_GROUP_ID, "spring-webmvc", SPRING_VERSION, "compile"));
        dependencies.add(new Dependency(SPRING_GROUP_ID, "spring-context", SPRING_VERSION, "compile"));
        dependencies.add(new Dependency(SPRING_GROUP_ID, "spring-web", SPRING_VERSION, "compile"));
    }

    @Override
    protected void createDirectories() {
        controllerDir = new File(baseDir, "/controller");
        controllerDir.mkdirs();
    }

    @Override
    protected void createBaseFiles() throws Exception {
        VelocityTemplate.mergeTemplate("mVCTechnology/SpringMVC/BaseController.vm", controllerDir.getAbsolutePath() + "/BaseController.java", velocityContext);
    }


}
