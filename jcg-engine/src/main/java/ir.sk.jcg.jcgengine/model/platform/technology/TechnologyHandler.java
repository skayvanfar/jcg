package ir.sk.jcg.jcgengine.model.platform.technology;

import ir.sk.jcg.jcgcommon.PropertyView.annotation.Prop;
import ir.sk.jcg.jcgengine.model.Presentable;
import ir.sk.jcg.jcgengine.model.platform.Dependency;
import ir.sk.jcg.jcgengine.model.platform.technology.buildTechnology.BuildTechnologyHandler;
import ir.sk.jcg.jcgengine.model.platform.technology.mvcTechnology.MVCTechnologyHandler;
import ir.sk.jcg.jcgengine.model.platform.technology.ormTechnology.ORMTechnologyHandler;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 4/13/2016
 */
@XmlAccessorType(XmlAccessType.NONE)
@XmlSeeAlso({BuildTechnologyHandler.class, ORMTechnologyHandler.class, MVCTechnologyHandler.class})
public abstract class TechnologyHandler implements Presentable {

    @Prop(label = "Name", required = true)
    private String name;

    protected String baseDir;
    protected String basePackageName;

    protected List<Dependency> dependencies = new ArrayList<>();
  //  protected List<Pattern> patterns; // TODO: 4/22/2016 not for now

    public TechnologyHandler() {
        name = "Technology";
    }

    public TechnologyHandler(String name) {
        this.name = name;
    }

    public void createBasePlatform() throws Exception {
        createDirectories();
        createBaseFiles();
    }

    protected abstract void createDirectories() throws Exception;
    protected abstract void createBaseFiles() throws Exception;

    public String getBaseDir() {
        return baseDir;
    }

    public void setBaseDir(String baseDir) {
        this.baseDir = baseDir;
    }

    public String getBasePackageName() {
        return basePackageName;
    }

    public void setBasePackageName(String basePackageName) {
        this.basePackageName = basePackageName;
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

    @Override
    public String toString() {
        return name;
    }
}