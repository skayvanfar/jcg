package ir.sk.jcg.jcgengine.model.platform.technology;

import ir.sk.jcg.jcgcommon.enums.EnumBase;
import ir.sk.jcg.jcgengine.model.platform.technology.buildTechnology.BuildTechnologyHandler;
import ir.sk.jcg.jcgengine.model.platform.technology.mvcTechnology.MVCTechnologyHandler;
import ir.sk.jcg.jcgengine.model.platform.technology.ormTechnology.ORMTechnologyHandler;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 4/19/2016
 */
public enum TechnologyHandlerType implements EnumBase, TechnologyHandlerEnumBase {

    BUILD_TECHNOLOGY(0, "Build Technology Handler", BuildTechnologyHandler.BuildTechnologyHandlerType.values()), // todo: must define
    ORM_TECHNOLOGY(1, "ORM Technology Handler", ORMTechnologyHandler.ORMTechnologyHandlerType.values()),
    MVC_TECHNOLOGY(2, "MVC Technology Handler", MVCTechnologyHandler.MVCTechnologyHandlerType.values());
 //   CLIENT_VIEW_TECHNOLOGY(1, "Client view TechnologyHandler", ORMTechnologyType.values()); // todo: must define

    private Integer value;
    private String description;
    private TechnologyHandlerEnumBase[] subTechnologyTypes;

    private TechnologyHandlerType(Integer value, String description, TechnologyHandlerEnumBase[] subTechnologyTypes) {
        this.value = value;
        this.description = description;
        this.subTechnologyTypes = subTechnologyTypes;
    }

    @Override
    public Integer getValue() {
        return value;
    }

    @Override
    public String getDescription() {
        return description;
    }

    public TechnologyHandlerEnumBase[] getSubTechnologyTypes() {
        return subTechnologyTypes;
    }

    public static TechnologyHandlerType valueOf(Integer type) {
        for (TechnologyHandlerType code : TechnologyHandlerType.values()) {
            if (type == code.getValue()) {
                return code;
            }
        }
        return null;
    }


//
//    public EnumSet<?> getSubTechnologyTypes() {
//        if (value == 0)
//            return EnumSet.allOf(BuildTechnologyType.class);
//        else if (value == 1)
//            return EnumSet.allOf(ORMTechnologyType.class);
//        else if (value == 2)
//            return EnumSet.allOf(MVCTechnologyType.class);
//
//        return null;
//    }

    @Override
    public TechnologyHandler technologyHandlerBuilder() {
        return null; // todo
    }
}
