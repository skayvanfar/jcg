package ir.sk.jcg.jcgengine.model.platform.technology.mvcTechnology.SpringMVC;

import ir.sk.jcg.jcgcommon.PropertyView.annotation.Editable;
import ir.sk.jcg.jcgcommon.PropertyView.annotation.Prop;
import ir.sk.jcg.jcgengine.ApplicationContext;
import ir.sk.jcg.jcgengine.model.platform.Dependency;
import ir.sk.jcg.jcgengine.model.platform.technology.SpringTechnology.Config;
import ir.sk.jcg.jcgengine.model.platform.technology.mvcTechnology.MVCTechnologyHandler;
import ir.sk.jcg.jcgengine.model.platform.technology.ormTechnology.hibernate.element.EntityClass;
import ir.sk.jcg.jcgengine.model.project.DomainImplElement;
import ir.sk.jcg.jcgengine.model.project.Entity;
import ir.sk.jcg.jcgengine.velocity.Template;
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

    public SpringMVCHandler() {
        super("Spring Technology MVC");

        this.controllerDir = messagesBundle.getString("springMVCHandler.controllerDir");
        this.resourcesDir = messagesBundle.getString("springMVCHandler.resourcesDir");

        dependencies.add(new Dependency(SPRING_GROUP_ID, "spring-webmvc", SPRING_VERSION, "compile"));
        dependencies.add(new Dependency("org.apache.tiles", "tiles-extras", "3.0.3", "compile"));
    }

    @Override
    protected void createDirectories() {
        //  String baseSpringMVCDir = getBaseProjectPath() + File.separator + getBasePackageName().replace('.', '/');
        controllerDirFile = new File(ApplicationContext.getInstance().getJavaWithPackagePrefixPath() + File.separator + controllerDir);
        controllerDirFile.mkdirs();

        // resources
        resourcesDirFile = new File(ApplicationContext.getInstance().getMainWebPath() + File.separator + resourcesDir);
        resourcesDirFile.mkdirs();

        File source = new File("E:\\template\\mvcTechnology\\SpringMVC\\view\\resources"); // TODO: 7/10/2016 must not be hard code
        File dest = new File(ApplicationContext.getInstance().getMainWebPath() + File.separator + resourcesDir);
        try {
            FileUtils.copyDirectory(source, dest);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // WEB-INF
        File webInfFile = new File(ApplicationContext.getInstance().getMainWebPath() + File.separator + "WEB-INF");
        webInfFile.mkdirs();

        File flowsFile = new File(ApplicationContext.getInstance().getMainWebPath() + File.separator + "WEB-INF" + File.separator + "flows");
        flowsFile.mkdirs();
        File springFile = new File(ApplicationContext.getInstance().getMainWebPath() + File.separator + "WEB-INF" + File.separator + "spring" + File.separator + "webcontext");
        springFile.mkdirs();
        File tagsFile = new File(ApplicationContext.getInstance().getMainWebPath() + File.separator + "WEB-INF" + File.separator + "tags");
        tagsFile.mkdirs();

        tilesDefinitionsFile = new File(ApplicationContext.getInstance().getMainWebPath() + File.separator + "WEB-INF" + File.separator + "tiles" + File.separator + "definitions");
        tilesDefinitionsFile.mkdirs();
        tilesTemplateFile = new File(ApplicationContext.getInstance().getMainWebPath() + File.separator + "WEB-INF" + File.separator + "tiles" + File.separator + "template");
        tilesTemplateFile.mkdirs();

        File viewsFile = new File(ApplicationContext.getInstance().getMainWebPath() + File.separator + "WEB-INF" + File.separator + "views");
        viewsFile.mkdirs();
    }

    @Override
    protected Config createConfigFiles() throws Exception {
        Template SpringWebConfigTemplate = new Template("WebConfig.java", "mvcTechnology/SpringMVC/config/WebConfig.vm",
                ApplicationContext.getInstance().getJavaWithPackagePrefixPath() + File.separator + ApplicationContext.getInstance().getConfigPackage() + File.separator + "WebConfig.java");
        SpringWebConfigTemplate.putReference("packageName", ApplicationContext.getInstance().getPackagePrefix() + "." + ApplicationContext.getInstance().getConfigPackage()); // TODO: 5/20/2016
        SpringWebConfigTemplate.putReference("basePackage", ApplicationContext.getInstance().getPackagePrefix()); // TODO: 5/20/2016

        SpringWebConfigTemplate.mergeTemplate();
        return new Config("WebConfig");
    }

    @Override
    protected void createBaseFiles() throws Exception {
        ///////////////////////////////////////////
        Template baseControllerTemplate = new Template("BaseController", "mvcTechnology/SpringMVC/controller/BaseController.vm",
                controllerDirFile.getAbsolutePath() + File.separator + "BaseController.java");
        baseControllerTemplate.putReference("packageName", ApplicationContext.getInstance().getPackagePrefix() + "." + "controller");
        Set<String> baseControllerImportSet = new HashSet<>();
        baseControllerImportSet.add(ApplicationContext.getInstance().getPackagePrefix() + ".commons.persistence.PersistenceException");
        baseControllerTemplate.putReference("imports", baseControllerImportSet);
        baseControllerTemplate.mergeTemplate();

        // Definitions
        ///////////////////////////////////////////
        Template layoutTileDefinitionTemplate = new Template("LayoutTileDefinition", "mvcTechnology/SpringMVC/view/tiles/definitions/layout-tile-definition.vm",
                tilesDefinitionsFile.getAbsolutePath() + File.separator + "layout-tile-definition.xml");
        layoutTileDefinitionTemplate.mergeTemplate();

        ///////////////////////////////////////////
        Template pagesTileDefinitionTemplate = new Template("PagesTileDefinition", "mvcTechnology/SpringMVC/view/tiles/definitions/pages-tile-definition.vm",
                tilesDefinitionsFile.getAbsolutePath() + File.separator + "pages-tile-definition.xml");
        pagesTileDefinitionTemplate.mergeTemplate();

        // Templates
        ///////////////////////////////////////////
        Template baseLayoutTemplate = new Template("BaseLayout", "mvcTechnology/SpringMVC/view/tiles/templates/baseLayout.vm",
                tilesTemplateFile.getAbsolutePath() + File.separator + "baseLayout.jsp");
        baseLayoutTemplate.putReference("resourcesDir", resourcesDir);
        baseLayoutTemplate.mergeTemplate();

        ///////////////////////////////////////////
        Template headerTemplate = new Template("Header", "mvcTechnology/SpringMVC/view/tiles/templates/header.vm",
                tilesTemplateFile.getAbsolutePath() + File.separator + "header.jsp");
        headerTemplate.mergeTemplate();

        ///////////////////////////////////////////
        Template navigationTemplate = new Template("Navigation", "mvcTechnology/SpringMVC/view/tiles/templates/navigation.vm",
                tilesTemplateFile.getAbsolutePath() + File.separator + "navigation.jsp");
        navigationTemplate.mergeTemplate();

        ///////////////////////////////////////////
        Template footerTemplate = new Template("Footer", "mvcTechnology/SpringMVC/view/tiles/templates/footer.vm",
                tilesTemplateFile.getAbsolutePath() + File.separator + "footer.jsp");
        footerTemplate.mergeTemplate();

        ///////////////////////////////////////////
        Template logoutTemplate = new Template("Logout", "mvcTechnology/SpringMVC/view/tiles/templates/logout.vm",
                tilesTemplateFile.getAbsolutePath() + File.separator + "logout.jsp");
        logoutTemplate.mergeTemplate();
    }

    @Override
    public List<DomainImplElement> createController(Entity entity) {
        List<DomainImplElement> domainImplElements = new ArrayList<>();

        /////////////////////////////////////////////
        Template controllerTemplate = new Template("Controller", "mvcTechnology/SpringMVC/controller/Controller.vm", ApplicationContext.getInstance().getJavaWithPackagePrefixPath()
                + File.separator + controllerDir + File.separator + entity.getName() + "Controller.java");
        controllerTemplate.putReference("packageName", ApplicationContext.getInstance().getPackagePrefix() + "." + controllerDir);
        controllerTemplate.putReference("entity", entity);
        // imports
        Set<String> controllerImportSet = new HashSet<>();
        controllerImportSet.add(ApplicationContext.getInstance().getPackagePrefix() + "." + ApplicationContext.getInstance().getOrmTechnologyHandler().getServicePackage() + "." + entity.getName() + "Service");
        controllerImportSet.add(ApplicationContext.getInstance().getPackagePrefix() + "." + ApplicationContext.getInstance().getOrmTechnologyHandler().getModelPackage() + "." + entity.getName());
        controllerTemplate.putReference("imports", controllerImportSet);
        controllerTemplate.mergeTemplate();

        EntityClass controllerClass = new EntityClass();
        controllerClass.setName(entity.getName() + "Controller.java");
        domainImplElements.add(controllerClass);


        return domainImplElements; // TODO: 5/8/2016
    }
}
