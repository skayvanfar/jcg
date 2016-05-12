package ir.sk.jcg.jcgengine.model.platform.technology.buildTechnology.Maven;

import ir.sk.jcg.jcgengine.model.platform.technology.buildTechnology.BuildTechnologyHandler;
import ir.sk.jcg.jcgcommon.PropertyView.annotation.Prop;
import ir.sk.jcg.jcgengine.velocity.VelocityTemplate;
import org.apache.velocity.VelocityContext;
//import org.jetbrains.annotations.Nullable;

import javax.xml.bind.annotation.XmlAttribute;
import java.io.File;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 4/13/2016
 */
public class MavenHandler extends BuildTechnologyHandler {

   // @Prop
   // private MavenId mavenId; // TODO: 5/10/2016 better use later

    @Prop(label = "Group Id", isRequired = true)
    private String groupId;
    @Prop(label = "Artifact Id", isRequired = true)
    private String artifactId;
    @Prop(label = "Version Id", isRequired = true)
    private String versionId;

    public MavenHandler() {
        mainJavaDir = "/src/main/java";
        mainResourcesDir = "/src/main/resources";
        mainWebDir = "/src/main/web";
        testJavaDir = "/src/test/java";
        testResourcesDir = "/src/test/resources";
    }

//    public MavenId getMavenId() {
//        return mavenId;
//    }
//
//    public void setMavenId(MavenId mavenId) {
//        this.mavenId = mavenId;
//    }


    public String getGroupId() {
        return groupId;
    }

    @XmlAttribute
    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getArtifactId() {
        return artifactId;
    }

    @XmlAttribute
    public void setArtifactId(String artifactId) {
        this.artifactId = artifactId;
    }

    public String getVersionId() {
        return versionId;
    }

    @XmlAttribute
    public void setVersionId(String versionId) {
        this.versionId = versionId;
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
