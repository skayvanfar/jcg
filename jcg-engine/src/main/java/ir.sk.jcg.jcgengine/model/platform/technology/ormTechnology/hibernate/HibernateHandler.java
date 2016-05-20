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
import java.util.List;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 4/13/2016
 */
@Editable
public class HibernateHandler extends ORMTechnologyHandler {

    private static final String HIBERNATE_GROUP_ID = "org.hibernate";
    private static final String HIBERNATE_VERSION = "4.3.7.Final";

    @Prop(label = "Interface DAO Directory")
    private String interfaceDAODir;
    @Prop(label = "Impl DAO Directory")
    private String implDAODir;
    private String interfaceDAOCommonDir;
    private String implDAOCommonDir;
    @Prop(label = "Model Directory")
    private String modelDir;

    @Prop(label = "Mapping Type", componentType = ComponentType.NON_EDITABLE_COMBO, editable = true, required = true)
    private MappingType mappingType;

    @Prop(label = "Hibernate Config Type", componentType = ComponentType.NON_EDITABLE_COMBO, editable = true, required = true)
    private HibernateConfigType hibernateConfigType;


    private File entityMainPackage;

    private File interfaceDAODirFile;
    private File implDAODirFile;
    private File interfaceDAOCommonDirFile;
    private File implDAOCommonDirFile;

    public HibernateHandler() {
        super("Hibernate");
        this.interfaceDAODir = "/dao";
        this.implDAODir = "/impl";
        this.interfaceDAOCommonDir = "/common";
        this.implDAOCommonDir = "/impl";
        this.modelDir = "/model";

        dependencies.add(new Dependency(HIBERNATE_GROUP_ID, "hibernate-core", HIBERNATE_VERSION, "compile"));
        dependencies.add(new Dependency(HIBERNATE_GROUP_ID, "hibernate-entitymanager", HIBERNATE_VERSION, "compile"));
        dependencies.add(new Dependency(HIBERNATE_GROUP_ID, "hibernate-c3p0", HIBERNATE_VERSION, "compile"));
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

    public void setHibernateConfigType(HibernateConfigType hibernateConfigType) {
        this.hibernateConfigType = hibernateConfigType;
    }

    @Override
    public EntityClass createEntityClass(Entity entity, String packagePath) {
        VelocityContext velocityContext = new VelocityContext(); // TODO: 5/12/2016
        velocityContext.put("packageName", ApplicationContext.getInstance().getPackagePrefix() + ".dao.common");
        VelocityTemplate.mergeTemplate("oRMTechnology/hibernate/GenericDAO.vm", interfaceDAOCommonDirFile.getAbsolutePath() + "/GenericDAO.java", velocityContext);
        return null; // TODO: 5/8/2016
    }

    @Override
    public List<ModelImplElement> createDao(Entity entity) {
        return null; // TODO: 5/8/2016
    }

    @Override
    protected void createDirectories() {
     //   String baseHibernateDir = getBaseDir() + File.separator + getBasePackageName().replace('.', '/');

        entityMainPackage = new File(baseDir + modelDir);

        interfaceDAODirFile = new File(baseDir + interfaceDAODir);
        implDAODirFile = new File(baseDir + interfaceDAODir + implDAODir);
        interfaceDAOCommonDirFile = new File(baseDir + interfaceDAODir + interfaceDAOCommonDir);
        implDAOCommonDirFile = new File(baseDir + interfaceDAODir + interfaceDAOCommonDir + implDAOCommonDir);

        interfaceDAODirFile.mkdirs();
        implDAODirFile.mkdirs();
        interfaceDAOCommonDirFile.mkdirs();
        implDAOCommonDirFile.mkdirs();
    }

    @Override
    protected List<Config> createConfigFiles() throws Exception {
        // common for all hibernateConfigTypes
        List<Config> configs = new ArrayList<>();
        Template propertiesConfigTemplate = new Template("Properties Config", "ormTechnology/hibernate/config/persistence.properties.vm", baseDir + File.separator + ApplicationContext.getInstance().getConfigPackage() + File.separator + "persistence.properties");
        propertiesConfigTemplate.mergeTemplate();
        switch (hibernateConfigType) {
            case SPRING_CONFIG:
                Template SpringConfigTemplate = new Template("Spring Config", "ormTechnology/hibernate/config/DataConfig.vm", baseDir + File.separator + ApplicationContext.getInstance().getConfigPackage() + File.separator  + "DataConfig.java");
                SpringConfigTemplate.putReference("packageName", ApplicationContext.getInstance().getPackagePrefix() + ".dao.common");
                SpringConfigTemplate.mergeTemplate();
                Config springConfig = new Config("DataConfig");
                configs.add(springConfig);
                break;
            case CFG_XML:
                // TODO: 5/20/2016
                break;
            case ORM:
                // TODO: 5/20/2016
                break;
            default:
        }
        return configs;
    }

    @Override
    protected void createBaseFiles() {
        VelocityContext velocityContext = new VelocityContext(); // TODO: 5/12/2016  
        velocityContext.put("packageName", ApplicationContext.getInstance().getPackagePrefix() + ".dao.common");
        VelocityTemplate.mergeTemplate("ormTechnology/hibernate/GenericDAO.vm", interfaceDAOCommonDirFile.getAbsolutePath() + "/GenericDAO.java", velocityContext);
        velocityContext.put("packageName", ApplicationContext.getInstance().getPackagePrefix() + ".dao.common.impl");
        VelocityTemplate.mergeTemplate("ormTechnology/hibernate/HibernateGenericDAO.vm", implDAOCommonDirFile.getAbsolutePath() + "/HibernateGenericDAO.java", velocityContext);
    }

}
