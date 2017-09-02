package ir.sk.jcg.jcgengine.model.platform.technology.mvcTechnology.SpringMVC;

import ir.sk.jcg.jcgcommon.PropertyView.annotation.Editable;
import ir.sk.jcg.jcgcommon.PropertyView.annotation.Prop;
import ir.sk.jcg.jcgengine.ApplicationContext;
import ir.sk.jcg.jcgengine.model.platform.Dependency;
import ir.sk.jcg.jcgengine.model.platform.technology.SpringTechnology.Config;
import ir.sk.jcg.jcgengine.model.platform.technology.mvcTechnology.MVCTechnologyHandler;
import ir.sk.jcg.jcgengine.model.platform.technology.mvcTechnology.SpringMVC.element.ViewElement;
import ir.sk.jcg.jcgengine.model.platform.technology.ormTechnology.hibernate.element.EntityClass;
import ir.sk.jcg.jcgengine.model.project.*;
import ir.sk.jcg.jcgengine.velocity.GenerateTemplate;
import ir.sk.jcg.jcgengine.velocity.NewFileGenerateGenerateTemplate;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 4/13/2016
 */
@Editable
public class SpringMVCHandler extends MVCTechnologyHandler {

    private final ResourceBundle messagesBundle = java.util.ResourceBundle.getBundle("messages/messages");

    private static final String SPRING_GROUP_ID = "org.springframework";
    private static final String SPRING_VERSION = "4.0.3.RELEASE";

    @Prop(label = "Controller Directory", editableInWizard = true, required = true)
    private String controllerDir;

    @Prop(label = "Resources Directory", editableInWizard = true, required = true)
    private String resourcesDir;

    private File controllerDirFile;
    private File resourcesDirFile;
    private File tilesDefinitionsFile;
    private File tilesTemplateFile;

    @Override
    public ViewElement createView(View view, String packagePath) {
        String outputPath = ApplicationContext.getInstance().getMainWebPath() + File.separator + "WEB-INF" + File.separator + "views"
                 + File.separator + packagePath.replace('.', File.separatorChar) + File.separator + view.getName() + ".jsp";
     //   Path path = Paths.get("outputPath");
        ViewElement viewElement = new ViewElement();
        NewFileGenerateGenerateTemplate viewNewFileGenerateGenerateTemplate = null;
        if (view instanceof DisplayView)
            viewNewFileGenerateGenerateTemplate = new NewFileGenerateGenerateTemplate("Display View Element", "mvcTechnology/SpringMVC/view/DisplayView.vm", outputPath);
        else if (view instanceof SearchView)
            viewNewFileGenerateGenerateTemplate = new NewFileGenerateGenerateTemplate("Search View Element", "mvcTechnology/SpringMVC/view/SearchView.vm", outputPath);
        else if (view instanceof CreateEditView)
            viewNewFileGenerateGenerateTemplate = new NewFileGenerateGenerateTemplate("View Element", "mvcTechnology/SpringMVC/view/CreateEditView.vm", outputPath);
     /*   else if (view instanceof SearcE)
            viewNewFileGenerateGenerateTemplate = new NewFileGenerateGenerateTemplate("View Element", "mvcTechnology/SpringMVC/view/EntityWithAnnotation.vm", outputPath);*/

        viewNewFileGenerateGenerateTemplate.putReference("view", view);

        viewNewFileGenerateGenerateTemplate.mergeTemplate();
        viewElement.setName(view.getName() + ".java");
        return viewElement;
    }

    public SpringMVCHandler() {
        super("Spring Technology MVC");

        this.controllerDir = messagesBundle.getString("springMVCHandler.controllerDir");
        this.resourcesDir = messagesBundle.getString("springMVCHandler.resourcesDir");

        dependencies.add(new Dependency(SPRING_GROUP_ID, "spring-webmvc", SPRING_VERSION, "compile"));
        dependencies.add(new Dependency("org.apache.tiles", "tiles-extras", "3.0.3", "compile"));
    }

    @Override
    protected void createDirectories() {
        controllerDirFile = new File(ApplicationContext.getInstance().getJavaWithPackagePrefixPath() + File.separator + controllerDir);
        controllerDirFile.mkdirs();

        File flowsFile = new File(ApplicationContext.getInstance().getMainWebPath() + File.separator + "WEB-INF" + File.separator + "flows");
        flowsFile.mkdirs();
    }

    @Override
    protected Config createConfigFiles() throws Exception {
        GenerateTemplate springWebConfigNewFileGenerateTemplate = new NewFileGenerateGenerateTemplate("WebConfig.java",
                ApplicationContext.getInstance().getJavaWithPackagePrefixPath() + File.separator + ApplicationContext.getInstance().getConfigPackage() + File.separator + "WebConfig.java", "mvcTechnology/SpringMVC/config/WebConfig.vm");
        springWebConfigNewFileGenerateTemplate.putReference("packageName", ApplicationContext.getInstance().getPackagePrefix() + "." + ApplicationContext.getInstance().getConfigPackage()); // TODO: 5/20/2016
        springWebConfigNewFileGenerateTemplate.putReference("basePackage", ApplicationContext.getInstance().getPackagePrefix()); // TODO: 5/20/2016

        springWebConfigNewFileGenerateTemplate.mergeTemplate();
        return new Config("WebConfig");
    }

    @Override
    protected void createBaseFiles() throws Exception {
        ///////////////////////////////////////////
        GenerateTemplate baseControllerNewFileGenerateTemplate = new NewFileGenerateGenerateTemplate("BaseController",
                controllerDirFile.getAbsolutePath() + File.separator + "BaseController.java", "mvcTechnology/SpringMVC/controller/BaseController.vm");
        baseControllerNewFileGenerateTemplate.putReference("packageName", ApplicationContext.getInstance().getPackagePrefix() + "." + "controller");
        Set<String> baseControllerImportSet = new HashSet<>();
        baseControllerImportSet.add(ApplicationContext.getInstance().getPackagePrefix() + ".commons.persistence.PersistenceException");
        baseControllerNewFileGenerateTemplate.putReference("imports", baseControllerImportSet);
        baseControllerNewFileGenerateTemplate.mergeTemplate();

        File source = new File("E:\\template\\mvcTechnology\\SpringMVC\\view\\resources"); // TODO: 7/10/2016 must not be hard code
        File dest = new File(ApplicationContext.getInstance().getMainWebPath() + File.separator + resourcesDir);
        try {
            FileUtils.copyDirectory(source, dest);
        } catch (IOException e) {
            e.printStackTrace();
        }

        source = new File("E:\\template\\mvcTechnology\\SpringMVC\\view\\tags\\form\\foundation");
        dest = new File(ApplicationContext.getInstance().getMainWebPath() + File.separator + "WEB-INF" + File.separator + File.separator + "tags" + File.separator + "zurb-foundation-form");
        try {
            FileUtils.copyDirectory(source, dest);
        } catch (IOException e) {
            e.printStackTrace();
        }

        source = new File("E:\\template\\mvcTechnology\\SpringMVC\\view\\tags\\utils");
        dest = new File(ApplicationContext.getInstance().getMainWebPath() + File.separator + "WEB-INF" + File.separator + File.separator + "tags" + File.separator + "utils");
        try {
            FileUtils.copyDirectory(source, dest);
        } catch (IOException e) {
            e.printStackTrace();
        }

        source = new File("E:\\template\\mvcTechnology\\SpringMVC\\view\\tiles");
        dest = new File(ApplicationContext.getInstance().getMainWebPath() + File.separator + "WEB-INF" + File.separator + File.separator + "tiles");
        try {
            FileUtils.copyDirectory(source, dest);
        } catch (IOException e) {
            e.printStackTrace();
        }

        source = new File("E:\\template\\mvcTechnology\\SpringMVC\\view\\web-inf");
        dest = new File(ApplicationContext.getInstance().getMainWebPath() + File.separator + "WEB-INF" + File.separator);
        try {
            FileUtils.copyDirectory(source, dest);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<DomainImplElement> createController(Entity entity, String packagePath) {
        List<DomainImplElement> domainImplElements = new ArrayList<>();

        /////////////////////////////////////////////
        GenerateTemplate controllerNewFileGenerateTemplate = new NewFileGenerateGenerateTemplate("Controller", ApplicationContext.getInstance().getJavaWithPackagePrefixPath()
                + File.separator + controllerDir + File.separator + entity.getName() + "Controller.java", "mvcTechnology/SpringMVC/controller/Controller.vm");
        controllerNewFileGenerateTemplate.putReference("packageName", ApplicationContext.getInstance().getPackagePrefix() + "." + controllerDir);
        controllerNewFileGenerateTemplate.putReference("entity", entity);
        // imports
        Set<String> controllerImportSet = new HashSet<>();
        controllerImportSet.add(ApplicationContext.getInstance().getPackagePrefix() + "." + ApplicationContext.getInstance().getOrmTechnologyHandler().getServicePackage() + "." + packagePath + "." + entity.getName() + "Service");
        controllerImportSet.add(ApplicationContext.getInstance().getPackagePrefix() + "." + ApplicationContext.getInstance().getOrmTechnologyHandler().getModelPackage() + "." + packagePath + "." + entity.getName());
        controllerNewFileGenerateTemplate.putReference("imports", controllerImportSet);
        controllerNewFileGenerateTemplate.mergeTemplate();

        EntityClass controllerClass = new EntityClass();
        controllerClass.setName(entity.getName() + "Controller.java");
        domainImplElements.add(controllerClass);


        return domainImplElements; // TODO: 5/8/2016
    }
}
