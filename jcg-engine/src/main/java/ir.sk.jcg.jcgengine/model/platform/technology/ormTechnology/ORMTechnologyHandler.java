package ir.sk.jcg.jcgengine.model.platform.technology.ormTechnology;

import ir.sk.jcg.jcgcommon.PropertyView.annotation.Prop;
import ir.sk.jcg.jcgcommon.enums.EnumBase;
import ir.sk.jcg.jcgengine.ApplicationContext;
import ir.sk.jcg.jcgengine.model.platform.technology.TechnologyHandler;
import ir.sk.jcg.jcgengine.model.platform.technology.TechnologyHandlerEnumBase;
import ir.sk.jcg.jcgengine.model.platform.technology.ormTechnology.hibernate.HibernateHandler;
import ir.sk.jcg.jcgengine.model.platform.technology.ormTechnology.hibernate.element.EntityClass;
import ir.sk.jcg.jcgengine.model.project.DomainImplElement;
import ir.sk.jcg.jcgengine.model.project.Entity;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSeeAlso;
import java.util.List;
import java.util.Objects;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 4/13/2016
 */
@XmlSeeAlso({HibernateHandler.class})
public abstract class ORMTechnologyHandler extends TechnologyHandler {

    public abstract EntityClass createEntityClass(Entity entity, String packagePath);

    public abstract List<DomainImplElement> createDao(Entity entity); // TODO: 5/8/2016 List<EntityElement>

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
//                    //     architecture = new SpringWebArchitecture(); // Todo: must create
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
                if (Objects.equals(type, code.getDescription())) {
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

    @Prop(label = "Interface DAO Directory", editableInWizard = true, required = true)
    protected String interfaceDAODir;
    @Prop(label = "Impl DAO Directory", editableInWizard = true, required = true)
    protected String implDAODir;
    protected String interfaceDAOCommonDir;
    protected String implDAOCommonDir;
    @Prop(label = "Model Directory", editableInWizard = true, required = true)
    protected String modelDir;
    @Prop(label = "Interface Service Directory", editableInWizard = true, required = true)
    protected String serviceDir;
    @Prop(label = "Impl Service Directory", editableInWizard = true, required = true)
    protected String implServiceDir;
    @Prop(label = "Database schema Name", editableInWizard = true, required = true)
    protected String databaseSchemaName;

    public String getInterfaceDAODir() {
        return interfaceDAODir;
    }

    @XmlAttribute
    public void setInterfaceDAODir(String interfaceDAODir) {
        this.interfaceDAODir = interfaceDAODir;
    }

    public String getImplDAODir() {
        return implDAODir;
    }

    @XmlAttribute
    public void setImplDAODir(String implDAODir) {
        this.implDAODir = implDAODir;
    }

    public String getInterfaceDAOCommonDir() {
        return interfaceDAOCommonDir;
    }

    public void setInterfaceDAOCommonDir(String interfaceDAOCommonDir) {
        this.interfaceDAOCommonDir = interfaceDAOCommonDir;
    }

    public String getImplDAOCommonDir() {
        return implDAOCommonDir;
    }

    public void setImplDAOCommonDir(String implDAOCommonDir) {
        this.implDAOCommonDir = implDAOCommonDir;
    }

    public String getModelDir() {
        return modelDir;
    }

    @XmlAttribute
    public void setModelDir(String modelDir) {
        this.modelDir = modelDir;
    }

    public String getServiceDir() {
        return serviceDir;
    }

    @XmlAttribute
    public void setServiceDir(String serviceDir) {
        this.serviceDir = serviceDir;
    }

    public String getImplServiceDir() {
        return implServiceDir;
    }

    @XmlAttribute
    public void setImplServiceDir(String implServiceDir) {
        this.implServiceDir = implServiceDir;
    }

    public String getDatabaseSchemaName() {
        return databaseSchemaName;
    }

    public void setDatabaseSchemaName(String databaseSchemaName) {
        this.databaseSchemaName = databaseSchemaName;
    }

    public ORMTechnologyHandler() {
        super("ORM Technology");
    }

    public ORMTechnologyHandler(String name) {
        super(name);
        databaseSchemaName = ApplicationContext.getInstance().getProjectName();

    }

}
