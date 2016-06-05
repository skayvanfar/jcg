package ir.sk.jcg.jcgengine.model.platform.technology.buildTechnology.Maven;

import ir.sk.jcg.jcgcommon.PropertyView.annotation.Editable;
import ir.sk.jcg.jcgengine.ApplicationContext;
import ir.sk.jcg.jcgengine.model.platform.technology.SpringTechnology.Config;
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
@Editable
public class MavenHandler extends BuildTechnologyHandler {

   // @Prop
   // private MavenId mavenId; // TODO: 5/10/2016 better use later

    @Prop(label = "Group Id", editableInWizard = true, required = true)
    private String groupId;
    @Prop(label = "Artifact Id", editableInWizard = true, required = true)
    private String artifactId;
    @Prop(label = "Version", editableInWizard = true, required = true)
    private String version;

    public MavenHandler() {
        super("Maven");
        version = "0.0.1-SNAPSHOT";

        mainJavaPath = "/src/main/java";
        mainResourcesPath = "/src/main/resources";
        mainWebPath = "/src/main/webapp";
        testJavaPath = "/src/test/java";
        testResourcesPath = "/src/test/resources";
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

    public String getVersion() {
        return version;
    }

    @XmlAttribute
    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    protected void createDirectories() {
        ApplicationContext applicationContext = ApplicationContext.getInstance();
        (new File(applicationContext.getBaseProjectPath() + mainJavaPath)).mkdirs();
        (new File(applicationContext.getBaseProjectPath() + mainResourcesPath)).mkdirs();
        (new File(applicationContext.getBaseProjectPath() + mainWebPath)).mkdirs();
        (new File(applicationContext.getBaseProjectPath() + testJavaPath)).mkdirs();
        (new File(applicationContext.getBaseProjectPath() + testResourcesPath)).mkdirs();
    }

    @Override
    protected Config createJavaConfig() {
        return null;
    }

    @Override
    protected Config createXmlConfig() {
        return null;
    }

    @Override
    protected void createAnnotationDIBaseFiles() {

    }

    @Override
    protected void createXmlDIBaseFiles() {
        createBuildFile();
    }

    @Override
    protected void createJavaDIBaseFiles() {
        createBuildFile();
    }

    @Override
    protected void createBuildFile() {
        VelocityContext velocityContext = new VelocityContext();
        velocityContext.put("groupId", groupId); // TODO: 5/12/2016 create another method
        velocityContext.put("artifactId", artifactId);
        velocityContext.put("version", version);
        velocityContext.put("packaging", "war");
        velocityContext.put("dependencies", dependencies);
        VelocityTemplate.mergeTemplate("buildTechnology/maven/mavenBuild.vm", ApplicationContext.getInstance().getBaseProjectPath() + "/pom.xml", velocityContext);
    }

    /**
     * set Package prefix of project into group id and artifact id of maven
     * */
    public void setGroupIdAndArtifactIdWithPackagePrefix(String packagePrefix) {
        int lastIndexOf = packagePrefix.lastIndexOf('.');
        if (lastIndexOf != -1) { // When packagePrefix include '.' character
            groupId = packagePrefix.substring(0, lastIndexOf);
            artifactId = packagePrefix.substring(lastIndexOf + 1);
        } else {
            groupId = packagePrefix;
        }
    }
}
