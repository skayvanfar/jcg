package ir.sk.jcg.jcgengine.model.platform.technology.ormTechnology;

import ir.sk.jcg.jcgcommon.enums.EnumBase;
import ir.sk.jcg.jcgengine.model.platform.technology.TechnologyHandler;
import ir.sk.jcg.jcgengine.model.platform.technology.TechnologyHandlerEnumBase;
import ir.sk.jcg.jcgengine.model.platform.technology.ormTechnology.hibernate.HibernateHandler;
import ir.sk.jcg.jcgengine.model.platform.technology.ormTechnology.hibernate.element.EntityClass;
import ir.sk.jcg.jcgengine.model.project.Entity;
import ir.sk.jcg.jcgengine.model.project.ModelImplElement;

import javax.xml.bind.annotation.XmlSeeAlso;
import java.util.List;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 4/13/2016
 */
@XmlSeeAlso({HibernateHandler.class})
public abstract class ORMTechnologyHandler extends TechnologyHandler {

    public abstract EntityClass createEntityClass(Entity entity, String packagePath);

    public abstract List<ModelImplElement> createDao(Entity entity); // TODO: 5/8/2016 List<EntityElement>

    public enum ORMTechnologyHandlerType implements EnumBase, TechnologyHandlerEnumBase {

        HIBERNATE(0, "Hibernate"),
        IBATIS(1, "IBatis");

        private Integer value;
        private String desc;

        ORMTechnologyHandlerType(Integer value, String desc) {
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



//        public ORMTechnologyHandler architectureBuilder(File baseDir) {
//            ORMTechnologyHandler oRMTechnology = null;
//            switch (value) {
//                case 0:
//                    oRMTechnology = new HibernateHandler("", baseDir, null); // Todo: must redefine
//                    break;
//                case 1:
//                    //     architecture = new ThreeLayerArchitecture(); // Todo: must create
//                    break;
//            }
//            return oRMTechnology;
//        }

        public static ORMTechnologyHandlerType valueOf(Integer type) {
            for (ORMTechnologyHandlerType code : ORMTechnologyHandlerType.values()) {
                if (type == code.getValue()) {
                    return code;
                }
            }
            return null;
        }

        public static ORMTechnologyHandlerType valueOfs(String type) {
            for (ORMTechnologyHandlerType code : ORMTechnologyHandlerType.values()) {
                if (type == code.getDescription()) {
                    return code;
                }
            }
            return null;
        }

        @Override
        public TechnologyHandler technologyHandlerBuilder() {
            ORMTechnologyHandler ormTechnology = null;
            switch (value) {
                case 0:
                    ormTechnology = new HibernateHandler(); // Todo: must redefine
                    break;
                case 1:
              //      ormTechnology = new MavenHandler(, null, null); // Todo: must create
                    break;
            }
            return ormTechnology;
        }

        @Override
        public String toString() {
            return desc;
        }
    }

    public ORMTechnologyHandler() {
        super("ORM Technology");
    }

    public ORMTechnologyHandler(String name) {
        super(name);
    }

}
