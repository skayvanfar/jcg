package ir.sk.jcg.jcgengine.model.platform.technology;

import ir.sk.jcg.jcgcommon.velocity.VelocityTemplate;
import ir.sk.jcg.jcgengine.model.platform.Dependency;

import org.apache.velocity.VelocityContext;

import java.io.File;
import java.util.List;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 4/13/2016
 */
public class Hibernate extends ORMTechnology {

    private VelocityContext velocityContext;

    private File interfaceDAODir;
    private File implDAODir;
    private File interfaceDAOCommonDir;
    private File implDAOCommonDir;

    public Hibernate(String name, File baseDir, List<Dependency> dependenciesList) {
        super(name, baseDir, dependenciesList);
    }

    @Override
    protected void createDirectories() {
        interfaceDAODir = new File(baseDir, "/dao");
        implDAODir = new File(baseDir, "/dao/impl");
        interfaceDAOCommonDir = new File(baseDir, "/dao/common");
        implDAOCommonDir = new File(baseDir, "/dao/common/impl");

        interfaceDAODir.mkdirs();
        implDAODir.mkdirs();
        interfaceDAOCommonDir.mkdirs();
        implDAOCommonDir.mkdirs();
    }

    @Override
    protected void createBaseFiles() throws Exception {
        VelocityTemplate.mergeTemplate("template/oRMTechnology/hibernate/DAO.vm", interfaceDAOCommonDir.getAbsolutePath() + "/DAO.java", velocityContext);
        VelocityTemplate.mergeTemplate("template/oRMTechnology/hibernate/AbstractHibernateDao.vm", implDAOCommonDir.getAbsolutePath() + "/AbstractHibernateDao.java", velocityContext);
    }

}
