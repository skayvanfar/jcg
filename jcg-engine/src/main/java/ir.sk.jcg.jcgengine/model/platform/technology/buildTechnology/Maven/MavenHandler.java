package ir.sk.jcg.jcgengine.model.platform.technology.buildTechnology.Maven;

import ir.sk.jcg.jcgcommon.PropertyView.annotation.Editable;
import ir.sk.jcg.jcgcommon.PropertyView.annotation.Prop;
import ir.sk.jcg.jcgengine.ApplicationContext;
import ir.sk.jcg.jcgengine.model.platform.technology.SpringTechnology.Config;
import ir.sk.jcg.jcgengine.model.platform.technology.buildTechnology.BuildTechnologyHandler;
import ir.sk.jcg.jcgengine.velocity.VelocityTemplate;
import org.apache.velocity.VelocityContext;

import javax.xml.bind.annotation.XmlAttribute;
import java.io.File;
import java.util.ResourceBundle;

//import org.jetbrains.annotations.Nullable;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 4/13/2016
 */
@Editable
public class MavenHandler extends BuildTechnologyHandler {

    private final ResourceBundle messagesBundle = java.util.ResourceBundle.getBundle("messages/messages");

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

        mainJavaPath = messagesBundle.getString("mavenHandler.mainJavaPath");
        mainResourcesPath = messagesBundle.getString("mavenHandler.mainResourcesPath");
        mainWebPath = messagesBundle.getString("mavenHandler.mainWebPath");
        testJavaPath = messagesBundle.getString("mavenHandler.testJavaPath");
        testResourcesPath = messagesBundle.getString("mavenHandler.testResourcesPath");
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
    protected Config createConfigFiles() throws Exception {
        return null; // TODO: 6/30/2017 must return null object
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
     */
    public void setGroupIdAndArtifactIdWithPackagePrefix(String packagePrefix, String artifactName) {
        /*int lastIndexOf = packagePrefix.lastIndexOf('.');
        if (lastIndexOf != -1) { // When packagePrefix include '.' character
            groupId = packagePrefix.substring(0, lastIndexOf);
            artifactId = packagePrefix.substring(lastIndexOf + 1);
        } else {
            groupId = packagePrefix;
        }*/
        groupId = packagePrefix;
        artifactId = artifactName;
    }
}
