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
import ir.sk.jcg.jcgengine.velocity.Template;
import ir.sk.jcg.jcgengine.model.platform.Dependency;

import javax.xml.bind.annotation.XmlAttribute;
import java.io.File;
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

    @Prop(label = "Mapping Type", componentType = ComponentType.NON_EDITABLE_COMBO, editableInWizard = true, editable = true, required = true)
    private MappingTypeEnum mappingTypeEnum;

    // Replace type code with state/strategy
    private MappingType mappingType;

    @Prop(label = "Hibernate Config Type", componentType = ComponentType.NON_EDITABLE_COMBO, editableInWizard = true, editable = true, required = true)
    private HibernateConfigType hibernateConfigType;

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
    public List<ModelImplElement> createDao(Entity entity) {
        return mappingType.createDao(this, entity);
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
