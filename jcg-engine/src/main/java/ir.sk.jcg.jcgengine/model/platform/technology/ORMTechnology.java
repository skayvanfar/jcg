package ir.sk.jcg.jcgengine.model.platform.technology;

import ir.sk.jcg.jcgcommon.enums.EnumBase;
import ir.sk.jcg.jcgengine.model.platform.Dependency;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSeeAlso;
import java.io.File;
import java.util.List;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 4/13/2016
 */
@XmlSeeAlso({Hibernate.class})
public abstract class ORMTechnology extends Technology {

    public enum ORMTechnologyType implements EnumBase, TechnologyEnumBase {

        HIBERNATE(0, "Hibernate"),
        IBATIS(1, "IBatis");

        private Integer value;
        private String desc;

        ORMTechnologyType(Integer value, String desc) {
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



//        public ORMTechnology architectureBuilder(File baseDir) {
//            ORMTechnology oRMTechnology = null;
//            switch (value) {
//                case 0:
//                    oRMTechnology = new Hibernate("", baseDir, null); // Todo: must redefine
//                    break;
//                case 1:
//                    //     architecture = new ThreeLayerArchitecture(); // Todo: must create
//                    break;
//            }
//            return oRMTechnology;
//        }

        public static ORMTechnologyType valueOf(Integer type) {
            for (ORMTechnologyType code : ORMTechnologyType.values()) {
                if (type == code.getValue()) {
                    return code;
                }
            }
            return null;
        }

        @Override
        public Technology technologyBuilder() {
            ORMTechnology ormTechnology = null;
            switch (value) {
                case 0:
                    ormTechnology = new Hibernate(); // Todo: must redefine
                    break;
                case 1:
              //      ormTechnology = new Maven(, null, null); // Todo: must create
                    break;
            }
            return ormTechnology;
        }
    }

    private String basePackageName;

    public ORMTechnology() {
    }


    public String getBasePackageName() {
        return basePackageName;
    }

    public void setBasePackageName(String basePackageName) {
        this.basePackageName = basePackageName;
    }

    @Override
    public void createBasePlatform() throws Exception {
        createDirectories();
        createBaseFiles();
    }

    protected abstract void createDirectories();

    protected abstract void createBaseFiles() throws Exception;

}
