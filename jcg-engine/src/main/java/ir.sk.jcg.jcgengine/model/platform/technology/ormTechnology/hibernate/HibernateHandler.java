package ir.sk.jcg.jcgengine.model.platform.technology.ormTechnology.hibernate;

import ir.sk.jcg.jcgcommon.PropertyView.ComponentType;
import ir.sk.jcg.jcgengine.model.platform.technology.ormTechnology.ORMTechnologyHandler;
import ir.sk.jcg.jcgengine.model.platform.technology.ormTechnology.hibernate.element.EntityClass;
import ir.sk.jcg.jcgengine.model.project.Entity;
import ir.sk.jcg.jcgengine.model.project.ModelImplElement;
import ir.sk.jcg.jcgcommon.PropertyView.annotation.Prop;
import ir.sk.jcg.jcgengine.model.project.enums.ModelInfoType;
import ir.sk.jcg.jcgengine.velocity.VelocityTemplate;
import ir.sk.jcg.jcgengine.model.platform.Dependency;

import org.apache.velocity.VelocityContext;

import javax.xml.bind.annotation.XmlAttribute;
import java.io.File;
import java.util.List;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 4/13/2016
 */
public class HibernateHandler extends ORMTechnologyHandler {

    private static final String HIBERNATE_GROUP_ID = "org.hibernate";
    private static final String HIBERNATE_VERSION = "4.3.7.Final";

    @Prop
    private String interfaceDAODir;
    @Prop
    private String implDAODir;
    private String interfaceDAOCommonDir;
    private String implDAOCommonDir;
    @Prop
    private String modelDir;

    @Prop(label = "Model info type", componentType = ComponentType.NON_EDITABLE_COMBO, isRequired = true)
    private ModelInfoType modelInfoType;


    private File entityMainPackage;

    private File interfaceDAODirFile;
    private File implDAODirFile;
    private File interfaceDAOCommonDirFile;
    private File implDAOCommonDirFile;

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

    public ModelInfoType getModelInfoType() {
        return modelInfoType;
    }

    @XmlAttribute
    public void setModelInfoType(ModelInfoType modelInfoType) {
        this.modelInfoType = modelInfoType;
    }

    @Override
    public EntityClass createEntityClass(Entity entity, String packagePath) {
        return null; // TODO: 5/8/2016
    }

    @Override
    public List<ModelImplElement> createDao(Entity entity) {
        return null; // TODO: 5/8/2016
    }

    public HibernateHandler() {
        this.interfaceDAODir = "/dao";
        this.implDAODir = "/impl";
        this.interfaceDAOCommonDir = "/common";
        this.implDAOCommonDir = "/impl";
        this.modelDir = "model";

        dependencies.add(new Dependency(HIBERNATE_GROUP_ID, "hibernate-core", HIBERNATE_VERSION, "compile"));
        dependencies.add(new Dependency(HIBERNATE_GROUP_ID, "hibernate-entitymanager", HIBERNATE_VERSION, "compile"));
        dependencies.add(new Dependency(HIBERNATE_GROUP_ID, "hibernate-c3p0", HIBERNATE_VERSION, "compile"));
    }

    @Override
    protected void createDirectories() {
        String baseHibernateDir = getBaseDir() + File.separator + getBasePackageName().replace('.', '/');

        entityMainPackage = new File(baseHibernateDir + modelDir);

        interfaceDAODirFile = new File(baseHibernateDir + interfaceDAODir);
        implDAODirFile = new File(baseHibernateDir + interfaceDAODir + implDAODir);
        interfaceDAOCommonDirFile = new File(baseHibernateDir + interfaceDAODir + interfaceDAOCommonDir);
        implDAOCommonDirFile = new File(baseHibernateDir + interfaceDAODir + interfaceDAOCommonDir + implDAOCommonDir);

        interfaceDAODirFile.mkdirs();
        implDAODirFile.mkdirs();
        interfaceDAOCommonDirFile.mkdirs();
        implDAOCommonDirFile.mkdirs();
    }

    @Override
    protected void createBaseFiles() throws Exception {
        VelocityContext velocityContext = new VelocityContext();
        velocityContext.put("packageName", getBasePackageName() + ".dao.common");
        VelocityTemplate.mergeTemplate("oRMTechnology/hibernate/GenericDAO.vm", interfaceDAOCommonDirFile.getAbsolutePath() + "/GenericDAO.java", velocityContext);
        velocityContext.put("packageName", getBasePackageName() + ".dao.common.impl");
        VelocityTemplate.mergeTemplate("oRMTechnology/hibernate/HibernateGenericDAO.vm", implDAOCommonDirFile.getAbsolutePath() + "/HibernateGenericDAO.java", velocityContext);
    }

}
