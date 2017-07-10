package ir.sk.jcg.jcgengine.model.platform.technology.buildTechnology;

import ir.sk.jcg.jcgcommon.PropertyView.annotation.Prop;
import ir.sk.jcg.jcgcommon.enums.EnumBase;
import ir.sk.jcg.jcgengine.model.platform.Dependency;
import ir.sk.jcg.jcgengine.model.platform.technology.TechnologyHandler;
import ir.sk.jcg.jcgengine.model.platform.technology.TechnologyHandlerEnumBase;
import ir.sk.jcg.jcgengine.model.platform.technology.buildTechnology.Maven.MavenHandler;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import java.util.List;
import java.util.Objects;

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
                if (Objects.equals(type, code.getValue())) {
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
                    //     architecture = new SpringWebArchitecture(); // Todo: must create
                    break;
            }
            return buildTechnology;
        }

        @Override
        public String toString() {
            return desc;
        }
    }

    @Prop(label = "Main Java Path", required = true)
    protected String mainJavaPath;
    @Prop(label = "Main Resources Path", required = true)
    protected String mainResourcesPath;
    @Prop(label = "Main Web Path", required = true)
    protected String mainWebPath;
    @Prop(label = "Test Java Path", required = true)
    protected String testJavaPath;
    @Prop(label = "Test Resources Path", required = true)
    protected String testResourcesPath;

    public BuildTechnologyHandler() {
        super("Build Technology");
    }

    public BuildTechnologyHandler(String name) {
        super(name);
    }

    public String getMainJavaPath() {
        return mainJavaPath;
    }

    @XmlAttribute(name = "mainJavaPath", required = true)
    public void setMainJavaPath(String mainJavaPath) {
        this.mainJavaPath = mainJavaPath;
    }

    public String getMainResourcesPath() {
        return mainResourcesPath;
    }

    @XmlAttribute(name = "mainResourcesPath", required = true)
    public void setMainResourcesPath(String mainResourcesPath) {
        this.mainResourcesPath = mainResourcesPath;
    }

    public String getMainWebPath() {
        return mainWebPath;
    }

    @XmlAttribute(name = "mainWebPath", required = true)
    public void setMainWebPath(String mainWebPath) {
        this.mainWebPath = mainWebPath;
    }

    public String getTestJavaPath() {
        return testJavaPath;
    }

    @XmlAttribute(name = "testJavaPath", required = true)
    public void setTestJavaPath(String testJavaPath) {
        this.testJavaPath = testJavaPath;
    }

    public String getTestResourcesPath() {
        return testResourcesPath;
    }

    @XmlAttribute(name = "testResourcesPath", required = true)
    public void setTestResourcesPath(String testResourcesPath) {
        this.testResourcesPath = testResourcesPath;
    }

    public void addDependency(Dependency dependency) {
        if (!dependencies.contains(dependency)) // TODO: 4/24/2016 must throws exception in other way
            dependencies.add(dependency);
    }

    public void addDependencies(List<Dependency> dependencies) {
        for (Dependency dependency : dependencies)
            if (!this.dependencies.contains(dependency))
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
