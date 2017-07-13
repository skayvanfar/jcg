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
        String outputPath = ApplicationContext.getInstance().getJavaWithPackagePrefixPath()
                + File.separator + hibernateHandler.getModelPackage() + File.separator + packagePath.replace('.', File.separatorChar) + File.separator + entity.getName() + ".java";

        EntityClass entityClass = new EntityClass();
        Template SpringConfigTemplate = new Template("Entity Class", "ormTechnology/hibernate/EntityWithAnnotation.vm", outputPath);
        SpringConfigTemplate.putReference("packageName", ApplicationContext.getInstance().getPackagePrefix() + '.' + hibernateHandler.getModelPackage()+ "." + packagePath);
        SpringConfigTemplate.putReference("schema", hibernateHandler.getDatabaseSchemaName());
        SpringConfigTemplate.putReference("entity", entity);

        SpringConfigTemplate.mergeTemplate();
        entityClass.setName(entity.getName() + ".java");

        return entityClass; // TODO: 5/8/2016
    }

    @Override
    List<DomainImplElement> createDao(HibernateHandler hibernateHandler, Entity entity, String packagePath) {
        List<DomainImplElement> domainImplElements = new ArrayList<>();

        /////////////////////////////////////////////
        String outputPath = ApplicationContext.getInstance().getJavaWithPackagePrefixPath()
                + File.separator + hibernateHandler.getInterfaceDAOPackage() + File.separator + packagePath.replace('.', File.separatorChar) + File.separator + entity.getName() + "DAO.java";
        Template daoTemplate = new Template("Dao", "ormTechnology/hibernate/dao/DAO.vm", outputPath);
        daoTemplate.putReference("packageName", ApplicationContext.getInstance().getPackagePrefix() + "." + hibernateHandler.getInterfaceDAOPackage() + "." + packagePath);
        daoTemplate.putReference("entity", entity);
        // imports
        Set<String> daoImportSet = new HashSet<>();
        daoImportSet.add(ApplicationContext.getInstance().getPackagePrefix() + "." + hibernateHandler.getInterfaceDAOCommonPackage() + ".GenericDAO");
        daoImportSet.add(ApplicationContext.getInstance().getPackagePrefix() + "." + hibernateHandler.getModelPackage() + "." + packagePath + "." + entity.getName());
        daoTemplate.putReference("imports", daoImportSet);

        daoTemplate.mergeTemplate();

        EntityClass daoClass = new EntityClass();
        daoClass.setName(entity.getName() + "DAO.java");
        domainImplElements.add(daoClass);
        /////////////////////////////////////////////
        outputPath = ApplicationContext.getInstance().getJavaWithPackagePrefixPath()
                + File.separator + hibernateHandler.getImplDAOPackage().replace('.', File.separatorChar) + File.separator + packagePath.replace('.', File.separatorChar) + File.separator + "Hibernate" + entity.getName() + "DAO.java";
        Template hibernateDaoTemplate = new Template("Hibernate Dao", "ormTechnology/hibernate/dao/HibernateDAO.vm", outputPath);
        hibernateDaoTemplate.putReference("packageName", ApplicationContext.getInstance().getPackagePrefix() + "." + hibernateHandler.getImplDAOPackage() + "." + packagePath);
        hibernateDaoTemplate.putReference("entity", entity);
        // imports
        Set<String> hibernateDaoImportSet = new HashSet<>();
        hibernateDaoImportSet.add(ApplicationContext.getInstance().getPackagePrefix() + "." + hibernateHandler.getImplDAOCommonPackage() + ".HibernateGenericDAO");
        hibernateDaoImportSet.add(ApplicationContext.getInstance().getPackagePrefix() + "." + hibernateHandler.getModelPackage() + "." + packagePath + "." + entity.getName());
        hibernateDaoImportSet.add(ApplicationContext.getInstance().getPackagePrefix() + "." + hibernateHandler.getInterfaceDAOPackage() + "." + packagePath + "." + entity.getName() + "DAO");
        hibernateDaoTemplate.putReference("imports", hibernateDaoImportSet);

        hibernateDaoTemplate.mergeTemplate();

        EntityClass hibernateDaoClass = new EntityClass();
        hibernateDaoClass.setName("Hibernate" + entity.getName() + "DAO.java");
        domainImplElements.add(hibernateDaoClass);

        ///////////////////////////////////////////////////////////////////////////////// Service
        /////////////////////////////////////////////
        outputPath = ApplicationContext.getInstance().getJavaWithPackagePrefixPath()
                + File.separator + hibernateHandler.getServicePackage() + File.separator + packagePath.replace('.', File.separatorChar) + File.separator + entity.getName() + "Service.java";
        Template serviceTemplate = new Template("Service", "ormTechnology/hibernate/service/Service.vm", outputPath);
        serviceTemplate.putReference("packageName", ApplicationContext.getInstance().getPackagePrefix() + "." + hibernateHandler.getServicePackage() + "." + packagePath);
        serviceTemplate.putReference("entity", entity);
        // imports
        Set<String> serviceImportSet = new HashSet<>();
        serviceImportSet.add(ApplicationContext.getInstance().getPackagePrefix() + "." + hibernateHandler.getInterfaceServiceCommonPackage() + ".GenericManager");
        serviceImportSet.add(ApplicationContext.getInstance().getPackagePrefix() + "." + hibernateHandler.getModelPackage() + "." + packagePath + "." + entity.getName());
        serviceTemplate.putReference("imports", serviceImportSet);

        serviceTemplate.mergeTemplate();

        EntityClass serviceClass = new EntityClass();
        serviceClass.setName(entity.getName() + "Service.java");
        domainImplElements.add(serviceClass);
        /////////////////////////////////////////////
        outputPath = ApplicationContext.getInstance().getJavaWithPackagePrefixPath()
                + File.separator + hibernateHandler.getImplServicePackage().replace('.', File.separatorChar) + File.separator + packagePath.replace('.', File.separatorChar) + File.separator + entity.getName() + "ServiceImpl.java";
        Template serviceImplTemplate = new Template("Hibernate Dao", "ormTechnology/hibernate/service/ServiceImpl.vm", outputPath);
        serviceImplTemplate.putReference("packageName", ApplicationContext.getInstance().getPackagePrefix() + "." + hibernateHandler.getImplServicePackage() + "." + packagePath);
        serviceImplTemplate.putReference("entity", entity);
        // imports
        Set<String> serviceImplImportSet = new HashSet<>();
        serviceImplImportSet.add(ApplicationContext.getInstance().getPackagePrefix() + "." + hibernateHandler.getImplServiceCommonPackage() + ".GenericManagerImpl");
        serviceImplImportSet.add(ApplicationContext.getInstance().getPackagePrefix() + "." + hibernateHandler.getModelPackage() + "." + packagePath + "." + entity.getName());
        serviceImplImportSet.add(ApplicationContext.getInstance().getPackagePrefix() + "." + hibernateHandler.getServicePackage() +  "." + packagePath + "." + entity.getName() + "Service");
        serviceImplImportSet.add(ApplicationContext.getInstance().getPackagePrefix() + "." + hibernateHandler.getInterfaceDAOPackage() + "." + packagePath + "." + entity.getName() + "DAO");

        serviceImplTemplate.putReference("imports", serviceImplImportSet);

        serviceImplTemplate.mergeTemplate();

        EntityClass serviceImplClass = new EntityClass();
        serviceImplClass.setName(entity.getName() + "ServiceImpl.java");
        domainImplElements.add(serviceImplClass);

        return domainImplElements; // TODO: 5/8/2016
    }
}
