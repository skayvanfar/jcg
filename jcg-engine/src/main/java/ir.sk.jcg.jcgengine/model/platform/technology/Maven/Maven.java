package ir.sk.jcg.jcgengine.model.platform.technology.Maven;

import ir.sk.jcg.jcgengine.velocity.VelocityTemplate;
import ir.sk.jcg.jcgengine.model.platform.Dependency;
import ir.sk.jcg.jcgengine.model.platform.technology.BuildTechnology;
import org.apache.velocity.VelocityContext;
//import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 4/13/2016
 */
public class Maven extends BuildTechnology {

    private MavenId mavenId;

    public Maven() {
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
