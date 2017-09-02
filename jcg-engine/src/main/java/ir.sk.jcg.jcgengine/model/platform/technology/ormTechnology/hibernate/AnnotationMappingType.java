package ir.sk.jcg.jcgengine.model.platform.technology.ormTechnology.hibernate;

import ir.sk.jcg.jcgcommon.util.FileUtils;
import ir.sk.jcg.jcgengine.ApplicationContext;
import ir.sk.jcg.jcgengine.model.platform.technology.ormTechnology.hibernate.element.EntityClass;
import ir.sk.jcg.jcgengine.model.project.DomainImplElement;
import ir.sk.jcg.jcgengine.model.project.Entity;
import ir.sk.jcg.jcgengine.velocity.GenerateTemplate;
import ir.sk.jcg.jcgengine.velocity.NewFileGenerateGenerateTemplate;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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

        Path path = Paths.get("outputPath");
        EntityClass entityClass = new EntityClass();
        // For the first time
        if (Files.notExists(path)){
            GenerateTemplate springConfigNewFileGenerateTemplate = new NewFileGenerateGenerateTemplate("Entity Class", outputPath, "ormTechnology/hibernate/EntityWithAnnotation.vm");
            springConfigNewFileGenerateTemplate.putReference("packageName", ApplicationContext.getInstance().getPackagePrefix() + '.' + hibernateHandler.getModelPackage()+ "." + packagePath);
           // springConfigNewFileGenerateTemplate.putReference("schema", hibernateHandler.getDatabaseSchemaName());
            springConfigNewFileGenerateTemplate.putReference("databaseSchemaName", hibernateHandler.getDatabaseSchemaName());
            springConfigNewFileGenerateTemplate.putReference("entity", entity);

            springConfigNewFileGenerateTemplate.mergeTemplate();
            entityClass.setName(entity.getName() + ".java");
        } else {
            //     String content = FileUtils.getFileContentByPath(path);
            //     RegExSrc regExSrc = new JavaRegExSrc(content);
            //     List<String> propertyGeneratedCodeSections = regExSrc.getGeneratedCode(GeneratedCodeType.PROPERTY); // TODO: 7/14/2017 must complete
            //////////////////////////////////////////
            //     SectionGenerateTemplate propetiesTemplate = new SectionGenerateTemplate("Hibernate Generic DAO", "ormTechnology/hibernate/dao/HibernateGenericDAO.vm", ApplicationContext.getInstance().getJavaWithPackagePrefixPath() + File.separator + implDAOCommonPackage.replace('.', File.separatorChar) + File.separator +"HibernateGenericDAO.java");
            //     Set<String> importSet = new HashSet<>();
            /*importSet.add(ApplicationContext.getInstance().getPackagePrefix() + "." + interfaceDAOCommonPackage + ".GenericDAO");
            hibernateGenericDAOTemplate.putReference("imports", importSet);
            hibernateGenericDAOTemplate.putReference("packageName", ApplicationContext.getInstance().getPackagePrefix() + "." + implDAOCommonPackage);
            hibernateGenericDAOTemplate.mergeTemplate();*/
        }

        return entityClass; // TODO: 5/8/2016
    }

    @Override
    List<DomainImplElement> createDao(HibernateHandler hibernateHandler, Entity entity, String packagePath) {
        List<DomainImplElement> domainImplElements = new ArrayList<>();

        /////////////////////////////////////////////
        String outputPath = ApplicationContext.getInstance().getJavaWithPackagePrefixPath()
                + File.separator + hibernateHandler.getInterfaceDAOPackage() + File.separator + packagePath.replace('.', File.separatorChar) + File.separator + entity.getName() + "DAO.java";
        GenerateTemplate daoNewFileGenerateTemplate = new NewFileGenerateGenerateTemplate("Dao", outputPath, "ormTechnology/hibernate/dao/DAO.vm");
        daoNewFileGenerateTemplate.putReference("packageName", ApplicationContext.getInstance().getPackagePrefix() + "." + hibernateHandler.getInterfaceDAOPackage() + "." + packagePath);
        daoNewFileGenerateTemplate.putReference("entity", entity);
        // imports
        Set<String> daoImportSet = new HashSet<>();
        daoImportSet.add(ApplicationContext.getInstance().getPackagePrefix() + "." + hibernateHandler.getInterfaceDAOCommonPackage() + ".GenericDAO");
        daoImportSet.add(ApplicationContext.getInstance().getPackagePrefix() + "." + hibernateHandler.getModelPackage() + "." + packagePath + "." + entity.getName());
        daoNewFileGenerateTemplate.putReference("imports", daoImportSet);

        daoNewFileGenerateTemplate.mergeTemplate();

        EntityClass daoClass = new EntityClass();
        daoClass.setName(entity.getName() + "DAO.java");
        domainImplElements.add(daoClass);
        /////////////////////////////////////////////
        outputPath = ApplicationContext.getInstance().getJavaWithPackagePrefixPath()
                + File.separator + hibernateHandler.getImplDAOPackage().replace('.', File.separatorChar) + File.separator + packagePath.replace('.', File.separatorChar) + File.separator + "Hibernate" + entity.getName() + "DAO.java";
        GenerateTemplate hibernateDaoNewFileGenerateTemplate = new NewFileGenerateGenerateTemplate("Hibernate Dao", outputPath, "ormTechnology/hibernate/dao/HibernateDAO.vm");
        hibernateDaoNewFileGenerateTemplate.putReference("packageName", ApplicationContext.getInstance().getPackagePrefix() + "." + hibernateHandler.getImplDAOPackage() + "." + packagePath);
        hibernateDaoNewFileGenerateTemplate.putReference("entity", entity);
        // imports
        Set<String> hibernateDaoImportSet = new HashSet<>();
        hibernateDaoImportSet.add(ApplicationContext.getInstance().getPackagePrefix() + "." + hibernateHandler.getImplDAOCommonPackage() + ".HibernateGenericDAO");
        hibernateDaoImportSet.add(ApplicationContext.getInstance().getPackagePrefix() + "." + hibernateHandler.getModelPackage() + "." + packagePath + "." + entity.getName());
        hibernateDaoImportSet.add(ApplicationContext.getInstance().getPackagePrefix() + "." + hibernateHandler.getInterfaceDAOPackage() + "." + packagePath + "." + entity.getName() + "DAO");
        hibernateDaoNewFileGenerateTemplate.putReference("imports", hibernateDaoImportSet);

        hibernateDaoNewFileGenerateTemplate.mergeTemplate();

        EntityClass hibernateDaoClass = new EntityClass();
        hibernateDaoClass.setName("Hibernate" + entity.getName() + "DAO.java");
        domainImplElements.add(hibernateDaoClass);

        ///////////////////////////////////////////////////////////////////////////////// Service
        /////////////////////////////////////////////
        outputPath = ApplicationContext.getInstance().getJavaWithPackagePrefixPath()
                + File.separator + hibernateHandler.getServicePackage() + File.separator + packagePath.replace('.', File.separatorChar) + File.separator + entity.getName() + "Service.java";
        GenerateTemplate serviceNewFileGenerateTemplate = new NewFileGenerateGenerateTemplate("Service", outputPath, "ormTechnology/hibernate/service/Service.vm");
        serviceNewFileGenerateTemplate.putReference("packageName", ApplicationContext.getInstance().getPackagePrefix() + "." + hibernateHandler.getServicePackage() + "." + packagePath);
        serviceNewFileGenerateTemplate.putReference("entity", entity);
        // imports
        Set<String> serviceImportSet = new HashSet<>();
        serviceImportSet.add(ApplicationContext.getInstance().getPackagePrefix() + "." + hibernateHandler.getInterfaceServiceCommonPackage() + ".GenericManager");
        serviceImportSet.add(ApplicationContext.getInstance().getPackagePrefix() + "." + hibernateHandler.getModelPackage() + "." + packagePath + "." + entity.getName());
        serviceNewFileGenerateTemplate.putReference("imports", serviceImportSet);

        serviceNewFileGenerateTemplate.mergeTemplate();

        EntityClass serviceClass = new EntityClass();
        serviceClass.setName(entity.getName() + "Service.java");
        domainImplElements.add(serviceClass);
        /////////////////////////////////////////////
        outputPath = ApplicationContext.getInstance().getJavaWithPackagePrefixPath()
                + File.separator + hibernateHandler.getImplServicePackage().replace('.', File.separatorChar) + File.separator + packagePath.replace('.', File.separatorChar) + File.separator + entity.getName() + "ServiceImpl.java";
        GenerateTemplate serviceImplNewFileGenerateTemplate = new NewFileGenerateGenerateTemplate("Hibernate Dao", outputPath, "ormTechnology/hibernate/service/ServiceImpl.vm");
        serviceImplNewFileGenerateTemplate.putReference("packageName", ApplicationContext.getInstance().getPackagePrefix() + "." + hibernateHandler.getImplServicePackage() + "." + packagePath);
        serviceImplNewFileGenerateTemplate.putReference("entity", entity);
        // imports
        Set<String> serviceImplImportSet = new HashSet<>();
        serviceImplImportSet.add(ApplicationContext.getInstance().getPackagePrefix() + "." + hibernateHandler.getImplServiceCommonPackage() + ".GenericManagerImpl");
        serviceImplImportSet.add(ApplicationContext.getInstance().getPackagePrefix() + "." + hibernateHandler.getModelPackage() + "." + packagePath + "." + entity.getName());
        serviceImplImportSet.add(ApplicationContext.getInstance().getPackagePrefix() + "." + hibernateHandler.getServicePackage() +  "." + packagePath + "." + entity.getName() + "Service");
        serviceImplImportSet.add(ApplicationContext.getInstance().getPackagePrefix() + "." + hibernateHandler.getInterfaceDAOPackage() + "." + packagePath + "." + entity.getName() + "DAO");

        serviceImplNewFileGenerateTemplate.putReference("imports", serviceImplImportSet);

        serviceImplNewFileGenerateTemplate.mergeTemplate();

        EntityClass serviceImplClass = new EntityClass();
        serviceImplClass.setName(entity.getName() + "ServiceImpl.java");
        domainImplElements.add(serviceImplClass);

        return domainImplElements; // TODO: 5/8/2016
    }
}
