package ir.sk.jcg.jcgengine.model.platform.technology;

import ir.sk.jcg.jcgengine.model.platform.Dependency;
import ir.sk.jcg.jcgengine.model.platform.pattern.Pattern;
import ir.sk.jcg.jcgengine.model.project.Project;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSeeAlso;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 4/13/2016
 */
@XmlAccessorType(XmlAccessType.NONE)
@XmlSeeAlso({BuildTechnology.class, ORMTechnology.class, MVCTechnology.class})
public abstract class Technology {

    protected String baseDir;
//    protected String basePackageName; // TODO: 4/24/2016  may not hear
    protected List<Dependency> dependencies = new ArrayList<>();
  //  protected List<Pattern> patterns; // TODO: 4/22/2016 not for now
 //   protected Project project; // TODO: 4/27/2016 Maye be beter just use base and basePackagePerfix

    public Technology() {
    }
//
//    public Project getProject() {
//        return project;
//    }
//
//    public void setProject(Project project) {
//        this.project = project;
//    }

//    public String getBasePackageName() {
//        return basePackageName;
//    }
//
//    public void setBasePackageName(String basePackageName) {
//        this.basePackageName = basePackageName;
//    }

    public abstract void createBasePlatform() throws Exception;

    public String getBaseDir() {
        return baseDir;
    }

   // @XmlAttribute(name = "baseDir", required = true)
    public void setBaseDir(String baseDir) {
        this.baseDir = baseDir;
    }

    public List<Dependency> getDependencies() {
        return dependencies;
    }

//    public List<Pattern> getPatterns() {
//        return patterns;
//    }
//
//    public void setPatterns(List<Pattern> patterns) {
//        this.patterns = patterns;
//    }

}
