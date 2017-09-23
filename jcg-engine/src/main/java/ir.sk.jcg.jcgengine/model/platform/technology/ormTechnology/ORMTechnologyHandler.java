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

    public abstract List<DomainImplElement> createDao(Entity entity, String packagePath); // TODO: 5/8/2016 List<EntityElement>

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

    @Prop(label = "Interface DAO Package", editableInWizard = true, required = true)
    protected String interfaceDAOPackage;
    @Prop(label = "Impl DAO Package", editableInWizard = true, required = true)
    protected String implDAOPackage;
    protected String interfaceDAOCommonPackage;
    protected String implDAOCommonPackage;
    @Prop(label = "Model Package", editableInWizard = true, required = true)
    protected String modelPackage;
    @Prop(label = "Interface Service Package", editableInWizard = true, required = true)
    protected String servicePackage;
    @Prop(label = "Impl Service Package", editableInWizard = true, required = true)
    protected String implServicePackage;
    protected String interfaceServiceCommonPackage;
    protected String implServiceCommonPackage;
    @Prop(label = "Database schema Name", editableInWizard = true, required = true)
    protected String databaseSchemaName;

    public String getInterfaceDAOPackage() {
        return interfaceDAOPackage;
    }

    @XmlAttribute
    public void setInterfaceDAOPackage(String interfaceDAOPackage) {
        this.interfaceDAOPackage = interfaceDAOPackage;
    }

    public String getImplDAOPackage() {
        return implDAOPackage;
    }

    @XmlAttribute
    public void setImplDAOPackage(String implDAOPackage) {
        this.implDAOPackage = implDAOPackage;
    }

    public String getInterfaceDAOCommonPackage() {
        return interfaceDAOCommonPackage;
    }

    public void setInterfaceDAOCommonPackage(String interfaceDAOCommonPackage) {
        this.interfaceDAOCommonPackage = interfaceDAOCommonPackage;
    }

    public String getImplDAOCommonPackage() {
        return implDAOCommonPackage;
    }

    public void setImplDAOCommonPackage(String implDAOCommonPackage) {
        this.implDAOCommonPackage = implDAOCommonPackage;
    }

    public String getModelPackage() {
        return modelPackage;
    }

    @XmlAttribute
    public void setModelPackage(String modelPackage) {
        this.modelPackage = modelPackage;
    }

    public String getServicePackage() {
        return servicePackage;
    }

    @XmlAttribute
    public void setServicePackage(String servicePackage) {
        this.servicePackage = servicePackage;
    }

    public String getImplServicePackage() {
        return implServicePackage;
    }

    @XmlAttribute
    public void setImplServicePackage(String implServicePackage) {
        this.implServicePackage = implServicePackage;
    }

    public String getInterfaceServiceCommonPackage() {
        return interfaceServiceCommonPackage;
    }

    public void setInterfaceServiceCommonPackage(String interfaceServiceCommonPackage) {
        this.interfaceServiceCommonPackage = interfaceServiceCommonPackage;
    }

    public String getImplServiceCommonPackage() {
        return implServiceCommonPackage;
    }

    public void setImplServiceCommonPackage(String implServiceCommonPackage) {
        this.implServiceCommonPackage = implServiceCommonPackage;
    }

    public String getDatabaseSchemaName() {
        return databaseSchemaName;
    }

    @XmlAttribute
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
