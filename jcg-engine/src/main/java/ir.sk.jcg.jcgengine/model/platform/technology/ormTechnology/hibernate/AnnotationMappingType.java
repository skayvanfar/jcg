package ir.sk.jcg.jcgengine.model.platform.technology.ormTechnology.hibernate;

import ir.sk.jcg.jcgengine.ApplicationContext;
import ir.sk.jcg.jcgengine.model.platform.technology.ormTechnology.hibernate.element.EntityClass;
import ir.sk.jcg.jcgengine.model.project.DomainImplElement;
import ir.sk.jcg.jcgengine.model.project.Entity;
import ir.sk.jcg.jcgengine.velocity.Template;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 9/21/2016
 */
public class AnnotationMappingType extends MappingType {

    @Override
    EntityClass createEntityClass(HibernateHandler hibernateHandler, Entity entity, String packagePath) {
        EntityClass entityClass = new EntityClass();
        Template SpringConfigTemplate = new Template("Entity Class", "ormTechnology/hibernate/EntityWithAnnotation.vm", ApplicationContext.getInstance().getJavaWithPackagePrefixPath()
                + File.separator + hibernateHandler.getModelDir() + File.separator + entity.getName() + ".java");
        SpringConfigTemplate.putReference("packageName", ApplicationContext.getInstance().getPackagePrefix() + ".model");
        SpringConfigTemplate.putReference("schema", hibernateHandler.getDatabaseSchemaName());
        SpringConfigTemplate.putReference("entity", entity);

        SpringConfigTemplate.mergeTemplate();
        entityClass.setName(entity.getName() + ".java");

        return entityClass; // TODO: 5/8/2016
    }

    @Override
    List<DomainImplElement> createDao(HibernateHandler hibernateHandler, Entity entity) {
        List<DomainImplElement> domainImplElements = new ArrayList<>();

        /////////////////////////////////////////////
        Template daoTemplate = new Template("Dao", "ormTechnology/hibernate/dao/DAO.vm", ApplicationContext.getInstance().getJavaWithPackagePrefixPath()
                + File.separator + hibernateHandler.getInterfaceDAODir() + File.separator + entity.getName() + "DAO.java");
        daoTemplate.putReference("packageName", ApplicationContext.getInstance().getPackagePrefix() + "." + hibernateHandler.getInterfaceDAODir());
        daoTemplate.putReference("entity", entity);
        // imports
        Set<String> daoImportSet = new HashSet<>();
        daoImportSet.add(ApplicationContext.getInstance().getPackagePrefix() + "." + hibernateHandler.getInterfaceDAOCommonDir() + ".GenericDAO");
        daoImportSet.add(ApplicationContext.getInstance().getPackagePrefix() + "." + hibernateHandler.getModelDir() + "." + entity.getName());
        daoTemplate.putReference("imports", daoImportSet);
        daoTemplate.mergeTemplate();

        EntityClass daoClass = new EntityClass();
        daoClass.setName(entity.getName() + "DAO.java");
        domainImplElements.add(daoClass);
        /////////////////////////////////////////////
        Template hibernateDaoTemplate = new Template("Hibernate Dao", "ormTechnology/hibernate/dao/HibernateDAO.vm", ApplicationContext.getInstance().getJavaWithPackagePrefixPath()
                + File.separator + hibernateHandler.getImplDAODir().replace('.', '/') + File.separator + "Hibernate" + entity.getName() + "DAO.java");
        hibernateDaoTemplate.putReference("packageName", ApplicationContext.getInstance().getPackagePrefix() + "." + hibernateHandler.getImplDAODir());
        hibernateDaoTemplate.putReference("entity", entity);
        // imports
        Set<String> hibernateDaoImportSet = new HashSet<>();
        hibernateDaoImportSet.add(ApplicationContext.getInstance().getPackagePrefix() + "." + hibernateHandler.getImplDAOCommonDir() + ".HibernateGenericDAO");
        hibernateDaoImportSet.add(ApplicationContext.getInstance().getPackagePrefix() + "." + hibernateHandler.getModelDir() + "." + entity.getName());
        hibernateDaoImportSet.add(ApplicationContext.getInstance().getPackagePrefix() + "." + hibernateHandler.getInterfaceDAODir() + "." + entity.getName() + "DAO");
        hibernateDaoTemplate.putReference("imports", hibernateDaoImportSet);
        hibernateDaoTemplate.mergeTemplate();

        EntityClass hibernateDaoClass = new EntityClass();
        hibernateDaoClass.setName("Hibernate" + entity.getName() + "DAO.java");
        domainImplElements.add(hibernateDaoClass);

        ///////////////////////////////////////////////////////////////////////////////// Service
        /////////////////////////////////////////////
        Template serviceTemplate = new Template("Service", "ormTechnology/hibernate/service/Service.vm", ApplicationContext.getInstance().getJavaWithPackagePrefixPath()
                + File.separator + hibernateHandler.getServiceDir() + File.separator + entity.getName() + "Service.java");
        serviceTemplate.putReference("packageName", ApplicationContext.getInstance().getPackagePrefix() + ".service");
        serviceTemplate.putReference("entity", entity);
        // imports
        Set<String> serviceImportSet = new HashSet<>();
        //   serviceImportSet.add(ApplicationContext.getInstance().getPackagePrefix() + ".service.GenericManager");
        serviceImportSet.add(ApplicationContext.getInstance().getPackagePrefix() + "." + hibernateHandler.getModelDir() + "." + entity.getName());
        serviceTemplate.putReference("imports", serviceImportSet);
        serviceTemplate.mergeTemplate();

        EntityClass serviceClass = new EntityClass();
        serviceClass.setName(entity.getName() + "Service.java");
        domainImplElements.add(serviceClass);
        /////////////////////////////////////////////
        Template serviceImplTemplate = new Template("Hibernate Dao", "ormTechnology/hibernate/service/ServiceImpl.vm", ApplicationContext.getInstance().getJavaWithPackagePrefixPath()
                + File.separator + hibernateHandler.getImplServiceDir().replace('.', '/') + File.separator + entity.getName() + "ServiceImpl.java");
        serviceImplTemplate.putReference("packageName", ApplicationContext.getInstance().getPackagePrefix() + "." + hibernateHandler.getImplServiceDir());
        serviceImplTemplate.putReference("entity", entity);
        // imports
        Set<String> serviceImplImportSet = new HashSet<>();
        //    serviceImplImportSet.add(ApplicationContext.getInstance().getPackagePrefix() + ".service.impl.GenericManagerImpl");
        serviceImplImportSet.add(ApplicationContext.getInstance().getPackagePrefix() + "." + hibernateHandler.getModelDir() + "." + entity.getName());
        serviceImplImportSet.add(ApplicationContext.getInstance().getPackagePrefix() + ".service." + entity.getName() + "Service");
        serviceImplImportSet.add(ApplicationContext.getInstance().getPackagePrefix() + "." + hibernateHandler.getInterfaceDAODir() + "." + entity.getName() + "DAO");

        serviceImplTemplate.putReference("imports", serviceImplImportSet);
        serviceImplTemplate.mergeTemplate();

        EntityClass serviceImplClass = new EntityClass();
        serviceImplClass.setName(entity.getName() + "ServiceImpl.java");
        domainImplElements.add(serviceImplClass);

        return domainImplElements; // TODO: 5/8/2016
    }
}
