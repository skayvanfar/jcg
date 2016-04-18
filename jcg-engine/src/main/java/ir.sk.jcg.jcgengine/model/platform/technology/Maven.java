package ir.sk.jcg.jcgengine.model.platform.technology;

import ir.sk.jcg.jcgcommon.velocity.VelocityTemplate;
import ir.sk.jcg.jcgengine.model.platform.Dependency;
import org.apache.velocity.VelocityContext;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 4/13/2016
 */
public class Maven extends BuildTechnology {

    private VelocityContext velocityContext;

    public Maven(String name, File baseDir, List<Dependency> dependenciesList) {
        super(name, baseDir, dependenciesList);
    }

    @Override
    protected void createDirectories() {
        mainJavaDir = new File(baseDir, "/src/main/java");
        mainResourcesDir = new File(baseDir, "/src/main/resources");
        mainWebDir = new File(baseDir, "/src/main/web");
        testJavaDir = new File(baseDir, "/src/test/java");
        testResourcesDir = new File(baseDir, "/src/test/resources");

        mainJavaDir.mkdirs();
        mainResourcesDir.mkdirs();
        mainWebDir.mkdirs();
        testJavaDir.mkdirs();
        testResourcesDir.mkdirs();
    }

    @Override
    protected void createBuildFile() {

        List list = new ArrayList();

        VelocityContext context = new VelocityContext();
        context.put("dependenciesList", dependenciesList);

        VelocityTemplate.mergeTemplate("template/buildTechnology/maven/mavenBuild.vm", "/src/pom.xml", velocityContext);
    }
}
