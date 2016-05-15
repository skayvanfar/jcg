package ir.sk.jcg.jcgengine.model.platform.technology.buildTechnology;

import ir.sk.jcg.jcgcommon.PropertyView.annotation.Prop;
import ir.sk.jcg.jcgcommon.enums.EnumBase;
import ir.sk.jcg.jcgengine.model.platform.Dependency;
import ir.sk.jcg.jcgengine.model.platform.technology.buildTechnology.Maven.MavenHandler;
import ir.sk.jcg.jcgengine.model.platform.technology.TechnologyHandler;
import ir.sk.jcg.jcgengine.model.platform.technology.TechnologyHandlerEnumBase;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import java.util.List;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 4/13/2016
 */
@XmlSeeAlso({MavenHandler.class})
public abstract class BuildTechnologyHandler extends TechnologyHandler {

    public enum BuildTechnologyHandlerType implements EnumBase, TechnologyHandlerEnumBase {

        ANT(0, "ANT"),
        MAVEN(1, "Maven"),
        GRADLE(2, "Gradle");

        private Integer value;
        private String desc;

        BuildTechnologyHandlerType(Integer value, String desc) {
            this.value = value;
            this.desc = desc;
        }

        @Override
        public Integer getValue() {
            return value;
        }

        @Override
        public String getDescription() {
            return desc;
        }

        public static BuildTechnologyHandlerType valueOf(Integer type) {
            for (BuildTechnologyHandlerType code : BuildTechnologyHandlerType.values()) {
                if (type == code.getValue()) {
                    return code;
                }
            }
            return null;
        }

        public static BuildTechnologyHandlerType valueOfs(String type) {
            for (BuildTechnologyHandlerType code : BuildTechnologyHandlerType.values()) {
                if (type == code.getDescription()) {
                    return code;
                }
            }
            return null;
        }

        @Override
        public TechnologyHandler technologyHandlerBuilder() {
            BuildTechnologyHandler buildTechnology = null;
            switch (value) {
                case 0:
                    //    buildTechnology = new Ant("", baseDir, null); // Todo: must redefine
                    break;
                case 1:
                    buildTechnology = new MavenHandler(); // Todo: must create
                    break;
                case 2:
                    //     architecture = new ThreeLayerArchitecture(); // Todo: must create
                    break;
            }
            return buildTechnology;
        }

        @Override
        public String toString() {
            return desc;
        }
    }

    @Prop(label = "Main Java Directory")
    protected String mainJavaDir;
    @Prop(label = "Main Resources Directory")
    protected String mainResourcesDir;
    @Prop(label = "Main Web Directory")
    protected String mainWebDir;
    @Prop(label = "Test Java Directory")
    protected String testJavaDir;
    @Prop(label = "Test Resources Directory")
    protected String testResourcesDir;

    public BuildTechnologyHandler() {
        super("Build Technology");
    }

    public BuildTechnologyHandler(String name) {
        super(name);
    }

    public String getMainJavaDir() {
        return mainJavaDir;
    }

    @XmlAttribute(name = "mainJavaDir", required = true)
    public void setMainJavaDir(String mainJavaDir) {
        this.mainJavaDir = mainJavaDir;
    }

    public String getMainResourcesDir() {
        return mainResourcesDir;
    }

    @XmlAttribute(name = "mainResourcesDir", required = true)
    public void setMainResourcesDir(String mainResourcesDir) {
        this.mainResourcesDir = mainResourcesDir;
    }

    public String getMainWebDir() {
        return mainWebDir;
    }

    @XmlAttribute(name = "mainWebDir", required = true)
    public void setMainWebDir(String mainWebDir) {
        this.mainWebDir = mainWebDir;
    }

    public String getTestJavaDir() {
        return testJavaDir;
    }

    @XmlAttribute(name = "testJavaDir", required = true)
    public void setTestJavaDir(String testJavaDir) {
        this.testJavaDir = testJavaDir;
    }

    public String getTestResourcesDir() {
        return testResourcesDir;
    }

    @XmlAttribute(name = "testResourcesDir", required = true)
    public void setTestResourcesDir(String testResourcesDir) {
        this.testResourcesDir = testResourcesDir;
    }
    
    public void addDependency(Dependency dependency) {
        if (!dependencies.contains(dependency)) // TODO: 4/24/2016 must throws exception in other way
            dependencies.add(dependency);
    }
    
    public void addDependencies(List<Dependency> dependencies) {
        for (Dependency dependency : dependencies)
            if(!this.dependencies.contains(dependency))
                this.dependencies.add(dependency); // TODO: 4/24/2016 must throws exception in other way
    }

    @Override
    public List<Dependency> getDependencies() {
        return super.getDependencies();
    }

    @XmlElement(name = "dependencies")
    public void setDependencies(List<Dependency> dependencies) {
        this.dependencies = dependencies;
    }


    @Override
    protected void createBaseFiles() {
        createBuildFile();
    }

    protected abstract void createBuildFile();
}
