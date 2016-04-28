package ir.sk.jcg.jcgengine.model.platform.architecture;

import ir.sk.jcg.jcgengine.model.platform.technology.BuildTechnology;
import ir.sk.jcg.jcgengine.model.platform.technology.ORMTechnology;
import ir.sk.jcg.jcgengine.model.platform.technology.Technology;
import ir.sk.jcg.jcgengine.model.platform.technology.TechnologyType;

import java.io.File;
import java.util.List;
import java.util.Set;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 4/19/2016
 */
public abstract class TwoLayerArchitecture extends Architecture {

    private static TechnologyType[] technologyTypes = {TechnologyType.ORM_TECHNOLOGY, TechnologyType.BUILD_TECHNOLOGY};

    private BuildTechnology buildTechnology;
    private ORMTechnology ormTechnology;

    public BuildTechnology getBuildTechnology() {
        return buildTechnology;
    }

    public void setBuildTechnology(BuildTechnology buildTechnology) {
        this.buildTechnology = buildTechnology;
    }

    public ORMTechnology getOrmTechnology() {
        return ormTechnology;
    }

    public void setOrmTechnology(ORMTechnology ormTechnology) {
        this.ormTechnology = ormTechnology;
    }


//    @Override
//    protected void setBaseDirOfTechnologies(File baseDir) {
//
//    }
//
//    @Override
//    protected void setBasePackageNameOfTechnologies(String baseDir) {
//
//    }

    @Override
    public TechnologyType[] getTechnologyTypes() {
        return technologyTypes;
    }

//    @Override
//    public void setTechnologies(List<Technology> technologies) {
//        for (Technology technology : technologies) {
//            if (technology instanceof BuildTechnology)
//                buildTechnology = (BuildTechnology) technology;
//            else if (technology instanceof ORMTechnology)
//                ormTechnology = (ORMTechnology) technology;
//        }
//    }
//
//    @Override
//    public List<Technology> getTechnologies() {
//        return null;
//    }

    @Override
    public void createView() {

    }

    @Override
    public void createBaseArchitecture() {

    }
}
