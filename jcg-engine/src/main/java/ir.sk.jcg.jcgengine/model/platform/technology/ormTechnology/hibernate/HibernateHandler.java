package ir.sk.jcg.jcgengine.model.platform.technology.ormTechnology.hibernate;

import ir.sk.jcg.jcgengine.model.platform.technology.ormTechnology.ORMTechnologyHandler;
import ir.sk.jcg.jcgengine.model.platform.technology.ormTechnology.hibernate.element.EntityClass;
import ir.sk.jcg.jcgengine.model.project.Entity;
import ir.sk.jcg.jcgengine.model.project.ModelImplElement;
import ir.sk.jcg.jcgengine.velocity.VelocityTemplate;
import ir.sk.jcg.jcgengine.model.platform.Dependency;

import org.apache.velocity.VelocityContext;

import java.io.File;
import java.util.List;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 4/13/2016
 */
public class HibernateHandler extends ORMTechnologyHandler {

    private static final String HIBERNATE_GROUP_ID = "org.hibernate";
    private static final String HIBERNATE_VERSION = "4.3.7.Final";

    private File interfaceDAODir;
    private File implDAODir;
    private File interfaceDAOCommonDir;
    private File implDAOCommonDir;

    private File entityMainPackage;

    @Override
    public EntityClass createEntityClass(Entity entity, String packagePath) {
        return null; // TODO: 5/8/2016
    }

    @Override
    public List<ModelImplElement> createDao(Entity entity) {
        return null; // TODO: 5/8/2016
    }

    public HibernateHandler() {
        dependencies.add(new Dependency(HIBERNATE_GROUP_ID, "hibernate-core", HIBERNATE_VERSION, "compile"));
        dependencies.add(new Dependency(HIBERNATE_GROUP_ID, "hibernate-entitymanager", HIBERNATE_VERSION, "compile"));
        dependencies.add(new Dependency(HIBERNATE_GROUP_ID, "hibernate-c3p0", HIBERNATE_VERSION, "compile"));
    }

//    public File getInterfaceDAODir() {
//        return interfaceDAODir;
//    }
//
//    @XmlAttribute(name = "interfaceDAODir", required = true)
//    public void setInterfaceDAODir(File interfaceDAODir) {
//        this.interfaceDAODir = interfaceDAODir;
//    }
//
//    public File getImplDAODir() {
//        return implDAODir;
//    }
//
//    @XmlAttribute(name = "implDAODir", required = true)
//    public void setImplDAODir(File implDAODir) {
//        this.implDAODir = implDAODir;
//    }
//
//    public File getInterfaceDAOCommonDir() {
//        return interfaceDAOCommonDir;
//    }
//
//    @XmlAttribute(name = "interfaceDAOCommonDir", required = true)
//    public void setInterfaceDAOCommonDir(File interfaceDAOCommonDir) {
//        this.interfaceDAOCommonDir = interfaceDAOCommonDir;
//    }
//
//    public File getImplDAOCommonDir() {
//        return implDAOCommonDir;
//    }
//
//    @XmlAttribute(name = "implDAOCommonDir", required = true)
//    public void setImplDAOCommonDir(File implDAOCommonDir) {
//        this.implDAOCommonDir = implDAOCommonDir;
//    }

    @Override
    protected void createDirectories() {
        String baseHibernateDir = getBaseDir() + File.separator + getBasePackageName().replace('.', '/');

        entityMainPackage = new File(baseHibernateDir + "model");

        interfaceDAODir = new File(baseHibernateDir + "/dao");
        implDAODir = new File(baseHibernateDir + "/dao/impl");
        interfaceDAOCommonDir = new File(baseHibernateDir + "/dao/common");
        implDAOCommonDir = new File(baseHibernateDir + "/dao/common/impl");

        interfaceDAODir.mkdirs();
        implDAODir.mkdirs();
        interfaceDAOCommonDir.mkdirs();
        implDAOCommonDir.mkdirs();
    }

    @Override
    protected void createBaseFiles() throws Exception {
        VelocityContext velocityContext = new VelocityContext();
        velocityContext.put("packageName", getBasePackageName() + ".dao.common");
        VelocityTemplate.mergeTemplate("oRMTechnology/hibernate/GenericDAO.vm", interfaceDAOCommonDir.getAbsolutePath() + "/GenericDAO.java", velocityContext);
        velocityContext.put("packageName", getBasePackageName() + ".dao.common.impl");
        VelocityTemplate.mergeTemplate("oRMTechnology/hibernate/HibernateGenericDAO.vm", implDAOCommonDir.getAbsolutePath() + "/HibernateGenericDAO.java", velocityContext);
    }

}
