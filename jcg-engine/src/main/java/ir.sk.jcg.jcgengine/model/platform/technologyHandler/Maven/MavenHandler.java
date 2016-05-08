package ir.sk.jcg.jcgengine.model.platform.technologyHandler.Maven;

import ir.sk.jcg.jcgengine.model.platform.technologyHandler.BuildTechnologyHandler;
import ir.sk.jcg.jcgengine.velocity.VelocityTemplate;
import org.apache.velocity.VelocityContext;
//import org.jetbrains.annotations.Nullable;

import java.io.File;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 4/13/2016
 */
public class MavenHandler extends BuildTechnologyHandler {

    private MavenId mavenId;

    public MavenHandler() {
        mainJavaDir = "/src/main/java";
        mainResourcesDir = "/src/main/resources";
        mainWebDir = "/src/main/web";
        testJavaDir = "/src/test/java";
        testResourcesDir = "/src/test/resources";
    }

    public MavenId getMavenId() {
        return mavenId;
    }

    public void setMavenId(MavenId mavenId) {
        this.mavenId = mavenId;
    }

    @Override
    protected void createDirectories() {
        (new File(baseDir + mainJavaDir)).mkdirs();
        (new File(baseDir + mainResourcesDir)).mkdirs();
        (new File(baseDir + mainWebDir)).mkdirs();
        (new File(baseDir + testJavaDir)).mkdirs();
        (new File(baseDir + testResourcesDir)).mkdirs();
    }

    @Override
    protected void createBuildFile() {
        VelocityContext velocityContext = new VelocityContext();
        velocityContext.put("dependencies", dependencies);
        VelocityTemplate.mergeTemplate("buildTechnology/maven/mavenBuild.vm", baseDir + "/pom.xml", velocityContext);
    }
}
