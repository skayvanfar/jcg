package ir.sk.jcg.jcgengine.model.platform.technology;

import ir.sk.jcg.jcgcommon.enums.EnumBase;

import java.io.File;
import java.util.EnumSet;

import static ir.sk.jcg.jcgengine.model.platform.technology.BuildTechnology.*;
import static ir.sk.jcg.jcgengine.model.platform.technology.ORMTechnology.*;
import static ir.sk.jcg.jcgengine.model.platform.technology.MVCTechnology.*;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 4/19/2016
 */
public enum TechnologyType implements EnumBase, TechnologyEnumBase {

    BUILD_TECHNOLOGY(0, "Build Technology", BuildTechnologyType.values()), // todo: must define
    ORM_TECHNOLOGY(1, "ORM Technology", ORMTechnologyType.values()),
    MVC_TECHNOLOGY(2, "MVC Technology", MVCTechnologyType.values());
 //   CLIENT_VIEW_TECHNOLOGY(1, "Client view Technology", ORMTechnologyType.values()); // todo: must define

    private Integer value;
    private String description;
    private TechnologyEnumBase[] subTechnologyTypes;

    private TechnologyType(Integer value, String description, TechnologyEnumBase[] subTechnologyTypes) {
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

    public TechnologyEnumBase[] getSubTechnologyTypes() {
        return subTechnologyTypes;
    }

    public static TechnologyType valueOf(Integer type) {
        for (TechnologyType code : TechnologyType.values()) {
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
    public Technology technologyBuilder() {
        return null; // todo
    }
}
