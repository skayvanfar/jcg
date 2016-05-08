package ir.sk.jcg.jcgengine.model.platform.architecture;

import ir.sk.jcg.jcgengine.model.platform.technologyHandler.*;
import ir.sk.jcg.jcgengine.model.platform.technologyHandler.BuildTechnologyHandler;
import ir.sk.jcg.jcgengine.model.platform.technologyHandler.ORMTechnologyHandler;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 4/19/2016
 */
public abstract class TwoLayerArchitecture extends Architecture {

    private static TechnologyHandlerType[] technologyHandlerTypes = {TechnologyHandlerType.ORM_TECHNOLOGY, TechnologyHandlerType.BUILD_TECHNOLOGY};

    private BuildTechnologyHandler buildTechnology;
    private ORMTechnologyHandler ormTechnology;

    public BuildTechnologyHandler getBuildTechnology() {
        return buildTechnology;
    }

    public void setBuildTechnology(BuildTechnologyHandler buildTechnology) {
        this.buildTechnology = buildTechnology;
    }

    public ORMTechnologyHandler getOrmTechnology() {
        return ormTechnology;
    }

    public void setOrmTechnology(ORMTechnologyHandler ormTechnology) {
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
    public TechnologyHandlerType[] getTechnologyTypes() {
        return technologyHandlerTypes;
    }

//    @Override
//    public void setTechnologies(List<TechnologyHandler> technologies) {
//        for (TechnologyHandler technologyHandler : technologies) {
//            if (technologyHandler instanceof BuildTechnologyHandler)
//                buildTechnology = (BuildTechnologyHandler) technologyHandler;
//            else if (technologyHandler instanceof ORMTechnologyHandler)
//                ormTechnology = (ORMTechnologyHandler) technologyHandler;
//        }
//    }
//
//    @Override
//    public List<TechnologyHandler> getTechnologies() {
//        return null;
//    }

    @Override
    public void createView() {

    }

    @Override
    public void createBaseArchitecture() {

    }
}
