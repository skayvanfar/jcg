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

    @Prop(label = "Controller Directory", editableInWizard = true, required = true)
    private String controllerDir;

    @Prop(label = "Resources Directory", editableInWizard = true, required = true)
    private String resourcesDir;

    private File controllerDirFile;
    private File resourcesDirFile;

    public SpringMVCHandler() {
        super("Spring Technology MVC");

        this.controllerDir = "controller";
        this.resourcesDir = "resources";

        dependencies.add(new Dependency(SPRING_GROUP_ID, "spring-webmvc", SPRING_VERSION, "compile"));
        dependencies.add(new Dependency("org.apache.tiles", "tiles-extras", "3.0.3", "compile"));
    }

    @Override
    protected void createDirectories() {
      //  String baseSpringMVCDir = getBaseProjectPath() + File.separator + getBasePackageName().replace('.', '/');
        controllerDirFile = new File(ApplicationContext.getInstance().getJavaWithPackagePrefixPath() + File.separator + controllerDir);
        controllerDirFile.mkdirs();

        resourcesDirFile = new File(ApplicationContext.getInstance().getMainWebPath() + File.separator + resourcesDir);
        resourcesDirFile.mkdirs();

        File webInfFile = new File(ApplicationContext.getInstance().getMainWebPath() + File.separator + "WEB-INF");
        webInfFile.mkdirs();

        File flowsFile = new File(ApplicationContext.getInstance().getMainWebPath() + File.separator + "WEB-INF" + File.separator + "flows");
        flowsFile.mkdirs();
        File springFile = new File(ApplicationContext.getInstance().getMainWebPath() + File.separator + "WEB-INF" + File.separator + "spring" + File.separator + "webcontext");
        springFile.mkdirs();
        File tagsFile = new File(ApplicationContext.getInstance().getMainWebPath() + File.separator + "WEB-INF" + File.separator + "tags");
        tagsFile.mkdirs();

        File tilesDefinitionsFile = new File(ApplicationContext.getInstance().getMainWebPath() + File.separator + "WEB-INF" + File.separator + "tiles" + File.separator + "definitions");
        tilesDefinitionsFile.mkdirs();
        File tilesTemplateFile = new File(ApplicationContext.getInstance().getMainWebPath() + File.separator + "WEB-INF" + File.separator + "tiles" + File.separator + "template");
        tilesTemplateFile.mkdirs();

        File viewsFile = new File(ApplicationContext.getInstance().getMainWebPath() + File.separator + "WEB-INF" + File.separator + "views");
        tagsFile.mkdirs();
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
                controllerDirFile.getAbsolutePath() + File.separator + "BaseController.java");
        baseControllerTemplate.putReference("packageName", ApplicationContext.getInstance().getPackagePrefix() + "." + "controller");
        baseControllerTemplate.mergeTemplate();
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
