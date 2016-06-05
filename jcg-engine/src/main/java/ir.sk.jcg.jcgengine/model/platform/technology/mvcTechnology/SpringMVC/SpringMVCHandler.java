package ir.sk.jcg.jcgengine.model.platform.technology.mvcTechnology.SpringMVC;

import ir.sk.jcg.jcgcommon.PropertyView.annotation.Editable;
import ir.sk.jcg.jcgcommon.PropertyView.annotation.Prop;
import ir.sk.jcg.jcgengine.ApplicationContext;
import ir.sk.jcg.jcgengine.model.platform.Dependency;
import ir.sk.jcg.jcgengine.model.platform.technology.SpringTechnology.Config;
import ir.sk.jcg.jcgengine.model.platform.technology.mvcTechnology.MVCTechnologyHandler;
import ir.sk.jcg.jcgengine.velocity.Template;
import ir.sk.jcg.jcgengine.velocity.VelocityTemplate;
import org.apache.velocity.VelocityContext;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 4/13/2016
 */
@Editable
public class SpringMVCHandler extends MVCTechnologyHandler {

    private static final String SPRING_GROUP_ID = "org.springframework";
    private static final String SPRING_VERSION = "4.0.3.RELEASE";

    private File controllerDir;

    @Prop(label = "Interface Service Directory", editableInWizard = true, required = true)
    private String serviceDir;
    @Prop(label = "Impl Service Directory", editableInWizard = true, required = true)
    private String implServiceDir;

    private File serviceDirFile;
    private File implServiceDirFile;

    public SpringMVCHandler() {
        super("Spring Technology MVC");

        this.serviceDir = "/service";
        this.implServiceDir = "/service/impl";

        dependencies.add(new Dependency(SPRING_GROUP_ID, "spring-webmvc", SPRING_VERSION, "compile"));
        dependencies.add(new Dependency("org.apache.tiles", "tiles-extras", "3.0.3", "compile"));
    }

    @Override
    protected void createDirectories() {
      //  String baseSpringMVCDir = getBaseProjectPath() + File.separator + getBasePackageName().replace('.', '/');

        controllerDir = new File(ApplicationContext.getInstance().getJavaWithPackagePrefixPath() + File.separator + "controller");
        serviceDirFile = new File(ApplicationContext.getInstance().getJavaWithPackagePrefixPath() + File.separator + serviceDir);
        implServiceDirFile = new File(ApplicationContext.getInstance().getJavaWithPackagePrefixPath() + File.separator + implServiceDir);

        controllerDir.mkdirs();
        serviceDirFile.mkdirs();
        implServiceDirFile.mkdirs();
    }

    @Override
    protected Config createJavaConfig() {
        Template SpringWebConfigTemplate = new Template("WebConfig.java", "mvcTechnology/SpringMVC/config/WebConfig.vm",
                ApplicationContext.getInstance().getJavaWithPackagePrefixPath() + File.separator + ApplicationContext.getInstance().getConfigPackage() + File.separator +"WebConfig.java");
        SpringWebConfigTemplate.putReference("packageName", ApplicationContext.getInstance().getPackagePrefix() + "." + ApplicationContext.getInstance().getConfigPackage()); // TODO: 5/20/2016
        SpringWebConfigTemplate.putReference("basePackage", ApplicationContext.getInstance().getPackagePrefix()); // TODO: 5/20/2016

        SpringWebConfigTemplate.mergeTemplate();
        return new Config("WebConfig");
    }

    @Override
    protected Config createXmlConfig() {
        return null; // TODO: 5/22/2016
    }

    @Override
    protected void createAnnotationDIBaseFiles() {
        ///////////////////////////////////////////
        Template baseControllerTemplate = new Template("BaseController", "mvcTechnology/SpringMVC/BaseController.vm",
                controllerDir.getAbsolutePath() + File.separator + "BaseController.java");
        baseControllerTemplate.putReference("packageName", ApplicationContext.getInstance().getPackagePrefix() + "." + "controller");
        baseControllerTemplate.mergeTemplate();

        ///////////////////////////////////////////
        Template genericDAOTemplate = new Template("Generic Manager", "mvcTechnology/SpringMVC/service/GenericManager.vm", serviceDirFile.getAbsolutePath() + "/GenericManager.java");
        Set<String> genericDAOImportSet = new HashSet<>();
        genericDAOImportSet.add(ApplicationContext.getInstance().getPackagePrefix() + ".commons.persistence.PersistenceException");
        genericDAOTemplate.putReference("imports", genericDAOImportSet);
        genericDAOTemplate.putReference("packageName", ApplicationContext.getInstance().getPackagePrefix() + ".service");
        genericDAOTemplate.mergeTemplate();

        //////////////////////////////////////////
        Template hibernateGenericDAOTemplate = new Template("Generic Manager Impl", "mvcTechnology/SpringMVC/service/GenericManagerImpl.vm", implServiceDirFile.getAbsolutePath() + "/GenericManagerImpl.java");
        Set<String> importSet = new HashSet<>();
        importSet.add(ApplicationContext.getInstance().getPackagePrefix() + ".service.GenericManager");
        hibernateGenericDAOTemplate.putReference("imports", importSet);
        hibernateGenericDAOTemplate.putReference("packageName", ApplicationContext.getInstance().getPackagePrefix() + ".service.impl");
        hibernateGenericDAOTemplate.mergeTemplate();
    }

    @Override
    protected void createXmlDIBaseFiles() {
        // TODO: 5/22/2016
    }

    @Override
    protected void createJavaDIBaseFiles() {
        // TODO: 5/22/2016
    }


}
