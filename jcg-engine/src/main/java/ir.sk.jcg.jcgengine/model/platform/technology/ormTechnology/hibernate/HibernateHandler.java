package ir.sk.jcg.jcgengine.model.platform.technology.ormTechnology.hibernate;

import ir.sk.jcg.jcgcommon.PropertyView.ComponentType;
import ir.sk.jcg.jcgcommon.PropertyView.annotation.Editable;
import ir.sk.jcg.jcgcommon.PropertyView.annotation.Prop;
import ir.sk.jcg.jcgengine.ApplicationContext;
import ir.sk.jcg.jcgengine.model.platform.Dependency;
import ir.sk.jcg.jcgengine.model.platform.technology.SpringTechnology.Config;
import ir.sk.jcg.jcgengine.model.platform.technology.ormTechnology.ORMTechnologyHandler;
import ir.sk.jcg.jcgengine.model.platform.technology.ormTechnology.hibernate.element.EntityClass;
import ir.sk.jcg.jcgengine.model.project.DomainImplElement;
import ir.sk.jcg.jcgengine.model.project.Entity;
import ir.sk.jcg.jcgengine.velocity.GenerateTemplate;
import ir.sk.jcg.jcgengine.velocity.NewFileGenerateGenerateTemplate;

import javax.xml.bind.annotation.XmlAttribute;
import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 4/13/2016
 */
@Editable
public class HibernateHandler extends ORMTechnologyHandler {

    private final ResourceBundle messagesBundle = java.util.ResourceBundle.getBundle("messages/messages");

    private static final String HIBERNATE_GROUP_ID = "org.hibernate";
    private static final String HIBERNATE_VERSION = "4.3.7.Final";

    @Prop(label = "Mapping Type", componentType = ComponentType.NON_EDITABLE_COMBO, required = true)
    private MappingTypeEnum mappingTypeEnum;

    // Replace type code with state/strategy
    private MappingType mappingType;

    @Prop(label = "Hibernate Config Type", componentType = ComponentType.NON_EDITABLE_COMBO, required = true)
    private HibernateConfigType hibernateConfigType;

    public HibernateHandler() {
        super("Hibernate");
        this.interfaceDAOPackage = messagesBundle.getString("hibernateHandler.interfaceDAOPackage");
        this.implDAOPackage = messagesBundle.getString("hibernateHandler.implDAOPackage");
        this.interfaceDAOCommonPackage = messagesBundle.getString("hibernateHandler.interfaceDAOCommonPackage");
        this.implDAOCommonPackage = messagesBundle.getString("hibernateHandler.implDAOCommonPackage");
        this.modelPackage = messagesBundle.getString("hibernateHandler.modelPackage");

        this.servicePackage = messagesBundle.getString("hibernateHandler.servicePackage");
        this.implServicePackage = messagesBundle.getString("hibernateHandler.implServicePackage");
        this.interfaceServiceCommonPackage = messagesBundle.getString("hibernateHandler.interfaceServiceCommonPackage");
        this.implServiceCommonPackage = messagesBundle.getString("hibernateHandler.implServiceCommonPackage");

        dependencies.add(new Dependency(HIBERNATE_GROUP_ID, "hibernate-core", HIBERNATE_VERSION, "compile"));
        dependencies.add(new Dependency(HIBERNATE_GROUP_ID, "hibernate-entitymanager", HIBERNATE_VERSION, "compile"));
        dependencies.add(new Dependency(HIBERNATE_GROUP_ID, "hibernate-c3p0", HIBERNATE_VERSION, "compile"));
        dependencies.add(new Dependency("org.apache.commons", "commons-dbcp2", "2.0.1", "compile"));
        dependencies.add(new Dependency("mysql", "mysql-connector-java", "5.1.34", "compile"));
    }

    public MappingTypeEnum getMappingTypeEnum() {
        return mappingTypeEnum;
    }

    @XmlAttribute
    public void setMappingTypeEnum(MappingTypeEnum mappingTypeEnum) {
        this.mappingTypeEnum = mappingTypeEnum;
        mappingType = MappingType.newInstance(mappingTypeEnum); // TODO: 9/21/2016 may be mappingTypeEnum not needed
    }

    public HibernateConfigType getHibernateConfigType() {
        return hibernateConfigType;
    }

    @XmlAttribute
    public void setHibernateConfigType(HibernateConfigType hibernateConfigType) {
        this.hibernateConfigType = hibernateConfigType;
    }

    @Override
    public EntityClass createEntityClass(Entity entity, String packagePath) {
        return mappingType.createEntityClass(this, entity, packagePath);
    }

    @Override
    public List<DomainImplElement> createDao(Entity entity, String packagePath) {
        return mappingType.createDao(this, entity, packagePath);
    }

    @Override
    protected void createDirectories() {

    }

    @Override
    protected Config createConfigFiles() throws Exception {
        Config config = null;

        GenerateTemplate propertiesConfigNewFileGenerateTemplate = new NewFileGenerateGenerateTemplate("Properties Config",
                ApplicationContext.getInstance().getMainResourcesPath() + File.separator + "persistence.properties", "ormTechnology/hibernate/config/persistence.properties.vm");
        propertiesConfigNewFileGenerateTemplate.putReference("schemaName", ApplicationContext.getInstance().getProjectName());
        propertiesConfigNewFileGenerateTemplate.mergeTemplate();

        GenerateTemplate springConfigNewFileGenerateTemplate = new NewFileGenerateGenerateTemplate("Spring Config", ApplicationContext.getInstance().getJavaWithPackagePrefixPath()
                + File.separator + ApplicationContext.getInstance().getConfigPackage() + File.separator + "DataConfig.java", "ormTechnology/hibernate/config/DataConfig.vm");
        springConfigNewFileGenerateTemplate.putReference("packageName", ApplicationContext.getInstance().getPackagePrefix() + "." + ApplicationContext.getInstance().getConfigPackage());
        springConfigNewFileGenerateTemplate.putReference("modelPackage", ApplicationContext.getInstance().getPackagePrefix() + "." + modelPackage); // TODO: 6/4/2016 model
        springConfigNewFileGenerateTemplate.mergeTemplate();
        config = new Config("DataConfig");

        return config;
    }

    @Override
    protected void createBaseFiles() throws Exception {
        /////////////////////////////////////////// interfaceDAOCommonPackage
        GenerateTemplate genericDAONewFileGenerateTemplate = new NewFileGenerateGenerateTemplate("Generic DAO",
                ApplicationContext.getInstance().getJavaWithPackagePrefixPath() + File.separator + interfaceDAOCommonPackage.replace('.', File.separatorChar) + File.separator +"GenericDAO.java", "ormTechnology/hibernate/dao/GenericDAO.vm");
        genericDAONewFileGenerateTemplate.putReference("packageName", ApplicationContext.getInstance().getPackagePrefix() + "." + interfaceDAOCommonPackage);
        genericDAONewFileGenerateTemplate.mergeTemplate();

        ////////////////////////////////////////// implDAOCommonPackage
        GenerateTemplate hibernateGenericDAONewFileGenerateTemplate = new NewFileGenerateGenerateTemplate("Hibernate Generic DAO",
                ApplicationContext.getInstance().getJavaWithPackagePrefixPath() + File.separator + implDAOCommonPackage.replace('.', File.separatorChar) + File.separator +"HibernateGenericDAO.java", "ormTechnology/hibernate/dao/HibernateGenericDAO.vm");
        Set<String> importSet = new HashSet<>();
        importSet.add(ApplicationContext.getInstance().getPackagePrefix() + "." + interfaceDAOCommonPackage + ".GenericDAO");
        hibernateGenericDAONewFileGenerateTemplate.putReference("imports", importSet);
        hibernateGenericDAONewFileGenerateTemplate.putReference("packageName", ApplicationContext.getInstance().getPackagePrefix() + "." + implDAOCommonPackage);
        hibernateGenericDAONewFileGenerateTemplate.mergeTemplate();


        /////////////////////////////////////////// serviceDirFile
        GenerateTemplate genericManagerNewFileGenerateTemplate = new NewFileGenerateGenerateTemplate("Generic Manager",
                ApplicationContext.getInstance().getJavaWithPackagePrefixPath() + File.separator + interfaceServiceCommonPackage.replace('.', File.separatorChar) + File.separator + "GenericManager.java", "ormTechnology/hibernate/service/GenericManager.vm");
        Set<String> genericDAOImportSet = new HashSet<>();
        genericDAOImportSet.add(ApplicationContext.getInstance().getPackagePrefix() + ".commons.persistence.PersistenceException");
        genericManagerNewFileGenerateTemplate.putReference("imports", genericDAOImportSet);
        genericManagerNewFileGenerateTemplate.putReference("packageName", ApplicationContext.getInstance().getPackagePrefix() + "." + interfaceServiceCommonPackage);
        genericManagerNewFileGenerateTemplate.mergeTemplate();

        ////////////////////////////////////////// implServiceDirFile
        GenerateTemplate genericManagerImplNewFileGenerateTemplate = new NewFileGenerateGenerateTemplate("Generic Manager Impl",
                ApplicationContext.getInstance().getJavaWithPackagePrefixPath() + File.separator + implServiceCommonPackage.replace('.', File.separatorChar) + File.separator + "GenericManagerImpl.java", "ormTechnology/hibernate/service/GenericManagerImpl.vm");
        Set<String> genericManagerImplTemplateImportSet = new HashSet<>();
        genericManagerImplTemplateImportSet.add(ApplicationContext.getInstance().getPackagePrefix() + "." + interfaceServiceCommonPackage + ".GenericManager");
        genericManagerImplTemplateImportSet.add(ApplicationContext.getInstance().getPackagePrefix() + "." + interfaceDAOCommonPackage + ".GenericDAO");
        genericManagerImplNewFileGenerateTemplate.putReference("imports", genericManagerImplTemplateImportSet);
        genericManagerImplNewFileGenerateTemplate.putReference("packageName", ApplicationContext.getInstance().getPackagePrefix() + "." + implServiceCommonPackage);
        genericManagerImplNewFileGenerateTemplate.mergeTemplate();
    }

}
