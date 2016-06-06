package ir.sk.jcg.jcgengine.model.platform.technology.ormTechnology.hibernate;

import ir.sk.jcg.jcgcommon.PropertyView.ComponentType;
import ir.sk.jcg.jcgcommon.PropertyView.annotation.Editable;
import ir.sk.jcg.jcgengine.ApplicationContext;
import ir.sk.jcg.jcgengine.model.platform.technology.SpringTechnology.Config;
import ir.sk.jcg.jcgengine.model.platform.technology.ormTechnology.ORMTechnologyHandler;
import ir.sk.jcg.jcgengine.model.platform.technology.ormTechnology.hibernate.element.EntityClass;
import ir.sk.jcg.jcgengine.model.project.Entity;
import ir.sk.jcg.jcgengine.model.project.ModelImplElement;
import ir.sk.jcg.jcgcommon.PropertyView.annotation.Prop;
import ir.sk.jcg.jcgengine.model.project.enums.MappingType;
import ir.sk.jcg.jcgengine.velocity.Template;
import ir.sk.jcg.jcgengine.velocity.VelocityTemplate;
import ir.sk.jcg.jcgengine.model.platform.Dependency;

import org.apache.velocity.VelocityContext;

import javax.xml.bind.annotation.XmlAttribute;
import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 4/13/2016
 */
@Editable
public class HibernateHandler extends ORMTechnologyHandler {

    private static final String HIBERNATE_GROUP_ID = "org.hibernate";
    private static final String HIBERNATE_VERSION = "4.3.7.Final";

    @Prop(label = "Interface DAO Directory", editableInWizard = true, required = true)
    private String interfaceDAODir;
    @Prop(label = "Impl DAO Directory", editableInWizard = true, required = true)
    private String implDAODir;
    private String interfaceDAOCommonDir;
    private String implDAOCommonDir;
    @Prop(label = "Model Directory", editableInWizard = true, required = true)
    private String modelDir;

    @Prop(label = "Mapping Type", componentType = ComponentType.NON_EDITABLE_COMBO, editableInWizard = true, editable = true, required = true)
    private MappingType mappingType;

    @Prop(label = "Hibernate Config Type", componentType = ComponentType.NON_EDITABLE_COMBO, editableInWizard = true, editable = true, required = true)
    private HibernateConfigType hibernateConfigType;

    @Prop(label = "Interface Service Directory", editableInWizard = true, required = true)
    private String serviceDir;
    @Prop(label = "Impl Service Directory", editableInWizard = true, required = true)
    private String implServiceDir;

    private File serviceDirFile;
    private File implServiceDirFile;

    // private File entityMainPackage;

    private File interfaceDAODirFile;
    private File implDAODirFile;
    private File interfaceDAOCommonDirFile;
    private File implDAOCommonDirFile;

    public HibernateHandler() {
        super("Hibernate");
        this.interfaceDAODir = "dao";
        this.implDAODir = "dao.impl";
        this.interfaceDAOCommonDir = "dao.common";
        this.implDAOCommonDir = "dao.common.impl";
        this.modelDir = "model";

        this.serviceDir = "service";
        this.implServiceDir = "service.impl";

        dependencies.add(new Dependency(HIBERNATE_GROUP_ID, "hibernate-core", HIBERNATE_VERSION, "compile"));
        dependencies.add(new Dependency(HIBERNATE_GROUP_ID, "hibernate-entitymanager", HIBERNATE_VERSION, "compile"));
        dependencies.add(new Dependency(HIBERNATE_GROUP_ID, "hibernate-c3p0", HIBERNATE_VERSION, "compile"));
        dependencies.add(new Dependency("org.apache.commons", "commons-dbcp2", "2.0.1", "compile"));
        dependencies.add(new Dependency("mysql", "mysql-connector-java", "5.1.34", "compile"));
    }

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

    public String getModelDir() {
        return modelDir;
    }

    @XmlAttribute
    public void setModelDir(String modelDir) {
        this.modelDir = modelDir;
    }

    public MappingType getMappingType() {
        return mappingType;
    }

    @XmlAttribute
    public void setMappingType(MappingType mappingType) {
        this.mappingType = mappingType;
    }

    public HibernateConfigType getHibernateConfigType() {
        return hibernateConfigType;
    }

    @XmlAttribute
    public void setHibernateConfigType(HibernateConfigType hibernateConfigType) {
        this.hibernateConfigType = hibernateConfigType;
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

    @Override
    public EntityClass createEntityClass(Entity entity, String packagePath) {
        EntityClass entityClass = new EntityClass();
        if (mappingType == MappingType.ANNOTATION) {
            Template SpringConfigTemplate = new Template("Entity Class", "ormTechnology/hibernate/EntityWithAnnotation.vm", ApplicationContext.getInstance().getJavaWithPackagePrefixPath()
                    + File.separator + modelDir + File.separator  + entity.getName() + ".java");
            SpringConfigTemplate.putReference("packageName", ApplicationContext.getInstance().getPackagePrefix() + ".model");
            SpringConfigTemplate.putReference("schema", databaseSchemaName);
            SpringConfigTemplate.putReference("entity", entity);

            SpringConfigTemplate.mergeTemplate();
            entityClass.setName(entity.getName() + ".java");
        } else if (mappingType == MappingType.HBM_XML_FILE) {

        }

        return entityClass; // TODO: 5/8/2016
    }

    @Override
    public List<ModelImplElement> createDao(Entity entity) {
        List<ModelImplElement> modelImplElements = new ArrayList<>();
        if (mappingType == MappingType.ANNOTATION) {
            /////////////////////////////////////////////
            Template daoTemplate = new Template("Dao", "ormTechnology/hibernate/dao/DAO.vm", ApplicationContext.getInstance().getJavaWithPackagePrefixPath()
                    + File.separator + interfaceDAODir + File.separator  + entity.getName() + "DAO.java");
            daoTemplate.putReference("packageName", ApplicationContext.getInstance().getPackagePrefix() + "." + interfaceDAODir);
            daoTemplate.putReference("entity", entity);
            // imports
            Set<String> daoImportSet = new HashSet<>();
            daoImportSet.add(ApplicationContext.getInstance().getPackagePrefix() + "." + interfaceDAOCommonDir + ".GenericDAO");
            daoImportSet.add(ApplicationContext.getInstance().getPackagePrefix() + "." + modelDir + "." + entity.getName());
            daoTemplate.putReference("imports", daoImportSet);
            daoTemplate.mergeTemplate();

            EntityClass daoClass = new EntityClass();
            daoClass.setName(entity.getName() + "DAO.java");
            modelImplElements.add(daoClass);
            /////////////////////////////////////////////
            Template hibernateDaoTemplate = new Template("Hibernate Dao", "ormTechnology/hibernate/dao/HibernateDAO.vm", ApplicationContext.getInstance().getJavaWithPackagePrefixPath()
                    + File.separator + implDAODir.replace('.', '/') + File.separator + "Hibernate" + entity.getName() + "DAO.java");
            hibernateDaoTemplate.putReference("packageName", ApplicationContext.getInstance().getPackagePrefix() + "." + implDAODir);
            hibernateDaoTemplate.putReference("entity", entity);
            // imports
            Set<String> hibernateDaoImportSet = new HashSet<>();
            hibernateDaoImportSet.add(ApplicationContext.getInstance().getPackagePrefix() + "." + implDAOCommonDir + ".HibernateGenericDAO");
            hibernateDaoImportSet.add(ApplicationContext.getInstance().getPackagePrefix() + "." + modelDir + "." + entity.getName());
            hibernateDaoImportSet.add(ApplicationContext.getInstance().getPackagePrefix() + "." + interfaceDAODir + "." +  entity.getName() + "DAO");
            hibernateDaoTemplate.putReference("imports", hibernateDaoImportSet);
            hibernateDaoTemplate.mergeTemplate();

            EntityClass hibernateDaoClass = new EntityClass();
            hibernateDaoClass.setName("Hibernate" + entity.getName() + "DAO.java");
            modelImplElements.add(hibernateDaoClass);

            ///////////////////////////////////////////////////////////////////////////////// Service
            /////////////////////////////////////////////
            Template serviceTemplate = new Template("Service", "ormTechnology/hibernate/service/Service.vm", ApplicationContext.getInstance().getJavaWithPackagePrefixPath()
                    + File.separator + serviceDir + File.separator  + entity.getName() + "Service.java");
            serviceTemplate.putReference("packageName", ApplicationContext.getInstance().getPackagePrefix() + ".service");
            serviceTemplate.putReference("entity", entity);
            // imports
            Set<String> serviceImportSet = new HashSet<>();
            //   serviceImportSet.add(ApplicationContext.getInstance().getPackagePrefix() + ".service.GenericManager");
            serviceImportSet.add(ApplicationContext.getInstance().getPackagePrefix() + "." + modelDir + "." + entity.getName());
            serviceTemplate.putReference("imports", serviceImportSet);
            serviceTemplate.mergeTemplate();

            EntityClass serviceClass = new EntityClass();
            serviceClass.setName(entity.getName() + "Service.java");
            modelImplElements.add(serviceClass);
            /////////////////////////////////////////////
            Template serviceImplTemplate = new Template("Hibernate Dao", "ormTechnology/hibernate/service/ServiceImpl.vm", ApplicationContext.getInstance().getJavaWithPackagePrefixPath()
                    + File.separator + implServiceDir.replace('.', '/') + File.separator + entity.getName() + "ServiceImpl.java");
            serviceImplTemplate.putReference("packageName", ApplicationContext.getInstance().getPackagePrefix() + "." + implServiceDir);
            serviceImplTemplate.putReference("entity", entity);
            // imports
            Set<String> serviceImplImportSet = new HashSet<>();
            //    serviceImplImportSet.add(ApplicationContext.getInstance().getPackagePrefix() + ".service.impl.GenericManagerImpl");
            serviceImplImportSet.add(ApplicationContext.getInstance().getPackagePrefix() + "." + modelDir + "." + entity.getName());
            serviceImplImportSet.add(ApplicationContext.getInstance().getPackagePrefix() + ".service." +  entity.getName() + "Service");
            serviceImplImportSet.add(ApplicationContext.getInstance().getPackagePrefix() + "." + interfaceDAODir + "." + entity.getName() + "DAO");

            serviceImplTemplate.putReference("imports", serviceImplImportSet);
            serviceImplTemplate.mergeTemplate();

            EntityClass serviceImplClass = new EntityClass();
            serviceImplClass.setName(entity.getName() + "ServiceImpl.java");
            modelImplElements.add(serviceImplClass);
        } else if (mappingType == MappingType.HBM_XML_FILE) {

        }
        return modelImplElements; // TODO: 5/8/2016
    }

    @Override
    protected void createDirectories() {
     //   String baseHibernateDir = getBaseProjectPath() + File.separator + getBasePackageName().replace('.', '/');

     //   entityMainPackage = new File(baseDir + modelDir);

        File modelDirFile = new File(ApplicationContext.getInstance().getJavaWithPackagePrefixPath() + File.separator + modelDir);
        modelDirFile.mkdirs();

        interfaceDAODirFile = new File(ApplicationContext.getInstance().getJavaWithPackagePrefixPath() + File.separator + interfaceDAODir);
        implDAODirFile = new File(ApplicationContext.getInstance().getJavaWithPackagePrefixPath() + File.separator + implDAODir.replace('.', '/'));
        interfaceDAOCommonDirFile = new File(ApplicationContext.getInstance().getJavaWithPackagePrefixPath() + File.separator + interfaceDAOCommonDir.replace('.', '/'));
        implDAOCommonDirFile = new File(ApplicationContext.getInstance().getJavaWithPackagePrefixPath() + File.separator + implDAOCommonDir.replace('.', '/'));

        serviceDirFile = new File(ApplicationContext.getInstance().getJavaWithPackagePrefixPath() + File.separator + serviceDir);
        implServiceDirFile = new File(ApplicationContext.getInstance().getJavaWithPackagePrefixPath() + File.separator + implServiceDir.replace('.', '/'));

        interfaceDAODirFile.mkdirs();
        implDAODirFile.mkdirs();
        interfaceDAOCommonDirFile.mkdirs();
        implDAOCommonDirFile.mkdirs();

        serviceDirFile.mkdirs();
        implServiceDirFile.mkdirs();
    }

    @Override
    protected Config createJavaConfig() {
        Config config = null;

        Template propertiesConfigTemplate = new Template("Properties Config", "ormTechnology/hibernate/config/persistence.properties.vm",
                ApplicationContext.getInstance().getMainResourcesPath() + File.separator + "persistence.properties");
        propertiesConfigTemplate.putReference("schemaName", ApplicationContext.getInstance().getProjectName());
        propertiesConfigTemplate.mergeTemplate();
        switch (hibernateConfigType) {
            case SPRING_CONFIG:
                Template SpringConfigTemplate = new Template("Spring Config", "ormTechnology/hibernate/config/DataConfig.vm", ApplicationContext.getInstance().getJavaWithPackagePrefixPath()
                        + File.separator + ApplicationContext.getInstance().getConfigPackage() + File.separator  + "DataConfig.java");
                SpringConfigTemplate.putReference("packageName", ApplicationContext.getInstance().getPackagePrefix() + "." + ApplicationContext.getInstance().getConfigPackage());
                SpringConfigTemplate.putReference("modelPackage", ApplicationContext.getInstance().getPackagePrefix() + "." + modelDir); // TODO: 6/4/2016 model
                SpringConfigTemplate.mergeTemplate();
                config = new Config("DataConfig");
                break;
            case CFG_XML:
                // TODO: 5/20/2016
                break;
            case ORM:
                // TODO: 5/20/2016
                break;
            default:
        }
        return config;
    }

    @Override
    protected Config createXmlConfig() {
        return null; // TODO: 5/20/2016
    }

    @Override
    protected void createAnnotationDIBaseFiles() {

        Template genericDAOTemplate = new Template("Generic DAO", "ormTechnology/hibernate/dao/GenericDAO.vm", interfaceDAOCommonDirFile.getAbsolutePath() + "/GenericDAO.java");
        genericDAOTemplate.putReference("packageName", ApplicationContext.getInstance().getPackagePrefix() + "." + interfaceDAOCommonDir);
        genericDAOTemplate.mergeTemplate();

        Template hibernateGenericDAOTemplate = new Template("Hibernate Generic DAO", "ormTechnology/hibernate/dao/HibernateGenericDAO.vm", implDAOCommonDirFile.getAbsolutePath() + "/HibernateGenericDAO.java");
        Set<String> importSet = new HashSet<>();
        importSet.add(ApplicationContext.getInstance().getPackagePrefix() + "." + interfaceDAOCommonDir + ".GenericDAO");
        hibernateGenericDAOTemplate.putReference("imports", importSet);
        hibernateGenericDAOTemplate.putReference("packageName", ApplicationContext.getInstance().getPackagePrefix() + "." + implDAOCommonDir);
        hibernateGenericDAOTemplate.mergeTemplate();


        ///////////////////////////////////////////
        Template genericManagerTemplate = new Template("Generic Manager", "ormTechnology/hibernate/service/GenericManager.vm", serviceDirFile.getAbsolutePath() + "/GenericManager.java");
        Set<String> genericDAOImportSet = new HashSet<>();
        genericDAOImportSet.add(ApplicationContext.getInstance().getPackagePrefix() + ".commons.persistence.PersistenceException");
        genericManagerTemplate.putReference("imports", genericDAOImportSet);
        genericManagerTemplate.putReference("packageName", ApplicationContext.getInstance().getPackagePrefix() + ".service");
        genericManagerTemplate.mergeTemplate();

        //////////////////////////////////////////
        Template genericManagerImplTemplate = new Template("Generic Manager Impl", "ormTechnology/hibernate/service/GenericManagerImpl.vm", implServiceDirFile.getAbsolutePath() + "/GenericManagerImpl.java");
        Set<String> genericManagerImplTemplateImportSet = new HashSet<>();
        genericManagerImplTemplateImportSet.add(ApplicationContext.getInstance().getPackagePrefix() + ".service.GenericManager");
        genericManagerImplTemplateImportSet.add(ApplicationContext.getInstance().getPackagePrefix() + "." + interfaceDAOCommonDir + ".GenericDAO");
        genericManagerImplTemplate.putReference("imports", genericManagerImplTemplateImportSet);
        genericManagerImplTemplate.putReference("packageName", ApplicationContext.getInstance().getPackagePrefix() + "." + implServiceDir);
        genericManagerImplTemplate.mergeTemplate();
    }

    @Override
    protected void createXmlDIBaseFiles() {

    }

    @Override
    protected void createJavaDIBaseFiles() {

    }

}
