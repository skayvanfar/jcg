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
import ir.sk.jcg.jcgengine.regexp.GeneratedCodeType;
import ir.sk.jcg.jcgengine.velocity.AddElementToSectionGenerateTemplate;
import ir.sk.jcg.jcgengine.velocity.GenerateTemplate;
import ir.sk.jcg.jcgengine.velocity.NewFileGenerateGenerateTemplate;

import java.io.File;
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

    @Override
    public ViewElement createView(View view, String packagePath) {

        String vmPath = "";
        GeneratedCodeType generatedCodeType = null;
        String dataName = "";
        if (view instanceof DisplayView) { // TODO: 9/23/2017 Refactor by replace Type code
            vmPath = "mvcTechnology/SpringMVC/view/DisplayView.vm";
            dataName = "DisplayData";
            generatedCodeType = GeneratedCodeType.DISPLAY_CONTROL;
        } else if (view instanceof SearchView) {
            vmPath = "mvcTechnology/SpringMVC/view/SearchView.vm";
            dataName = "SearchData";
            generatedCodeType = GeneratedCodeType.SEARCH_CONTROL;
        } else if (view instanceof CreateEditView) {
            vmPath = "mvcTechnology/SpringMVC/view/CreateEditView.vm";
            dataName = "CreateData";
            generatedCodeType = GeneratedCodeType.CREATE_CONTROL;
        }

        GenerateTemplate searchDataNewFileGenerateTemplate = new NewFileGenerateGenerateTemplate(dataName, ApplicationContext.getInstance().getJavaWithPackagePrefixPath()
                + File.separator + "dto" + File.separator + packagePath + File.separator + view.getViewFileName() + dataName + ".java", "mvcTechnology/SpringMVC/controller/" + dataName + ".vm");
        searchDataNewFileGenerateTemplate.putReference("packageName", ApplicationContext.getInstance().getPackagePrefix() + ".dto" + "." + packagePath);
        searchDataNewFileGenerateTemplate.putReference("view", view);
        searchDataNewFileGenerateTemplate.mergeTemplate();

        ViewElement viewElement = new ViewElement();
        NewFileGenerateGenerateTemplate viewNewFileGenerateGenerateTemplate = null;

        String outputPath = ApplicationContext.getInstance().getMainWebPath() + File.separator + "WEB-INF" + File.separator + "views"
                + File.separator + packagePath.replace('.', File.separatorChar) + File.separator + view.getTargetEntity().getName().toLowerCase() + File.separator + view.getViewFileName() + ".jsp";

        viewNewFileGenerateGenerateTemplate = new NewFileGenerateGenerateTemplate("View", outputPath, vmPath);

        viewNewFileGenerateGenerateTemplate.putReference("view", view);

        viewNewFileGenerateGenerateTemplate.mergeTemplate();
        viewElement.setName(view.getName() + ".java");

        // add controller method to controller class
        String filePath = ApplicationContext.getInstance().getJavaWithPackagePrefixPath() + File.separator + controllerDir
                + File.separator + packagePath.replace('.', File.separatorChar) + File.separator + view.getTargetEntity().getName() + "Controller.java";

        GenerateTemplate addControllerMethodGenerateTemplate = new AddElementToSectionGenerateTemplate("Add Controller Method", filePath, generatedCodeType);
        addControllerMethodGenerateTemplate.putReference("view", view);
        // TODO: 9/15/2017 better to place all related filed to a model go to specific model
        addControllerMethodGenerateTemplate.putReference("dataPackage", ApplicationContext.getInstance().getPackagePrefix() + ".dto." + packagePath);

        addControllerMethodGenerateTemplate.mergeTemplate();

        // add tiles definition
        String fileDefinitionPath = ApplicationContext.getInstance().getMainWebPath() + File.separator + "WEB-INF" + File.separator + "tiles\\definition\\tile-definition.xml";

        GenerateTemplate addTileDefinitionMethodGenerateTemplate = new AddElementToSectionGenerateTemplate("Add tile definition", fileDefinitionPath, GeneratedCodeType.TILES_DEFINITION);
        addTileDefinitionMethodGenerateTemplate.putReference("view", view);
        addTileDefinitionMethodGenerateTemplate.putReference("packagePath", packagePath);
        //   addTileDefinitionMethodGenerateTemplate.putReference("searchDataPackage", ApplicationContext.getInstance().getPackagePrefix() + ".dto." + packagePath);
        addTileDefinitionMethodGenerateTemplate.mergeTemplate();

        return viewElement;
    }

    public SpringMVCHandler() {
        super("Spring Technology MVC");

        this.controllerDir = messagesBundle.getString("springMVCHandler.controllerDir");
        this.resourcesDir = messagesBundle.getString("springMVCHandler.resourcesDir");

        dependencies.add(new Dependency(SPRING_GROUP_ID, "spring-webmvc", SPRING_VERSION, "compile"));
        dependencies.add(new Dependency("org.apache.tiles", "tiles-extras", "3.0.3", "compile"));
        dependencies.add(new Dependency("ir.sk.jcg", "jcg-lib-springmvc-handler", "1.0-SNAPSHOT", "compile"));
    }

    @Override
    protected void createDirectories() {
        /*controllerDirFile = new File(ApplicationContext.getInstance().getJavaWithPackagePrefixPath() + File.separator + controllerDir);
        controllerDirFile.mkdirs();*/
        // add hooks
        GenerateTemplate tagsREADMENewFileGenerateTemplate = new NewFileGenerateGenerateTemplate("tagsREADME",
                ApplicationContext.getInstance().getMainWebPath() + File.separator + "WEB-INF" + File.separator + "tags" + File.separator
                        + "README.txt", "mvcTechnology/SpringMVC/hook/README.vm");
        tagsREADMENewFileGenerateTemplate.putReference("description", "Put your tags files here.");
        tagsREADMENewFileGenerateTemplate.mergeTemplate();

        GenerateTemplate flowsREADMENewFileGenerateTemplate = new NewFileGenerateGenerateTemplate("flowsREADME",
                ApplicationContext.getInstance().getMainWebPath() + File.separator + "WEB-INF" + File.separator + "flows" + File.separator
                        + "README.txt", "mvcTechnology/SpringMVC/hook/README.vm");
        flowsREADMENewFileGenerateTemplate.putReference("description", "Put your flows files here.");
        flowsREADMENewFileGenerateTemplate.mergeTemplate();

        GenerateTemplate tilesDefinitionREADMENewFileGenerateTemplate = new NewFileGenerateGenerateTemplate("tileDefinitionsREADME",
                ApplicationContext.getInstance().getMainWebPath() + File.separator + "WEB-INF" + File.separator + "tiles\\definition" + File.separator
                        + "README.txt", "mvcTechnology/SpringMVC/hook/README.vm");
        tilesDefinitionREADMENewFileGenerateTemplate.putReference("description", "Put your tiles Definition files here.");
        tilesDefinitionREADMENewFileGenerateTemplate.mergeTemplate();

        /*File source = new File("E:\\template\\mvcTechnology\\SpringMVC\\view\\tiles\\definition"); // TODO: 7/10/2016 must not be hard code
        File dest = new File(ApplicationContext.getInstance().getMainWebPath() + File.separator + "WEB-INF" + File.separator + "tiles\\definition");
        try {
            FileUtils.copyDirectory(source, dest);
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        GenerateTemplate tilesTemplatesREADMENewFileGenerateTemplate = new NewFileGenerateGenerateTemplate("tilesTemplatesREADME",
                ApplicationContext.getInstance().getMainWebPath() + File.separator + "WEB-INF" + File.separator + "tiles\\template" + File.separator
                        + "README.txt", "mvcTechnology/SpringMVC/hook/README.vm");
        tilesTemplatesREADMENewFileGenerateTemplate.putReference("description", "Put your tiles Template files here.");
        tilesTemplatesREADMENewFileGenerateTemplate.mergeTemplate();

        GenerateTemplate viewsREADMENewFileGenerateTemplate = new NewFileGenerateGenerateTemplate("viewsREADME",
                ApplicationContext.getInstance().getMainWebPath() + File.separator + "WEB-INF" + File.separator + "views" + File.separator
                        + "README.txt", "mvcTechnology/SpringMVC/hook/README.vm");
        viewsREADMENewFileGenerateTemplate.putReference("description", "Put your view files here.");
        viewsREADMENewFileGenerateTemplate.mergeTemplate();

        GenerateTemplate resourcesREADMENewFileGenerateTemplate = new NewFileGenerateGenerateTemplate("resourcesREADME",
                ApplicationContext.getInstance().getMainWebPath() + File.separator + "resources" + File.separator
                        + "README.txt", "mvcTechnology/SpringMVC/hook/README.vm");
        resourcesREADMENewFileGenerateTemplate.putReference("description", "Put your resource files here.");
        resourcesREADMENewFileGenerateTemplate.mergeTemplate();
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
        GenerateTemplate tilesDefinitionNewFileGenerateTemplate = new NewFileGenerateGenerateTemplate("TilesDefinition",
                ApplicationContext.getInstance().getMainWebPath() + File.separator + "WEB-INF" + File.separator + "tiles\\definition\\tile-definition.xml", "mvcTechnology/SpringMVC/view/tiles/definition/tile-definition.vm");
        tilesDefinitionNewFileGenerateTemplate.mergeTemplate();
    }

    @Override
    public List<DomainImplElement> createController(Entity entity, String packagePath) {
        List<DomainImplElement> domainImplElements = new ArrayList<>();

        /////////////////////////////////////////////
        GenerateTemplate controllerNewFileGenerateTemplate = new NewFileGenerateGenerateTemplate("Controller", ApplicationContext.getInstance().getJavaWithPackagePrefixPath()
                + File.separator + controllerDir + File.separator + packagePath.replace('.', File.separatorChar) + File.separator + entity.getName() + "Controller.java", "mvcTechnology/SpringMVC/controller/Controller.vm");
        controllerNewFileGenerateTemplate.putReference("packageName", ApplicationContext.getInstance().getPackagePrefix() + "." + controllerDir + "." + packagePath);
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
