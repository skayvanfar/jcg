package ir.sk.jcg.jcgengine.model.platform.technology.mvcTechnology.SpringMVC;

import ir.sk.jcg.jcgcommon.PropertyView.annotation.Editable;
import ir.sk.jcg.jcgengine.ApplicationContext;
import ir.sk.jcg.jcgengine.model.platform.Dependency;
import ir.sk.jcg.jcgengine.model.platform.technology.SpringTechnology.Config;
import ir.sk.jcg.jcgengine.model.platform.technology.mvcTechnology.MVCTechnologyHandler;
import ir.sk.jcg.jcgengine.velocity.Template;
import ir.sk.jcg.jcgengine.velocity.VelocityTemplate;
import org.apache.velocity.VelocityContext;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 4/13/2016
 */
@Editable
public class SpringMVCHandler extends MVCTechnologyHandler {

    private static final String SPRING_GROUP_ID = "org.springframework";
    private static final String SPRING_VERSION = "4.0.3.RELEASE";

    private File controllerDir;

    public SpringMVCHandler() {
        super("SpringTechnology MVC");
        dependencies.add(new Dependency(SPRING_GROUP_ID, "spring-webmvc", SPRING_VERSION, "compile"));
        dependencies.add(new Dependency("org.apache.tiles", "tiles-extras", "3.0.3", "compile"));
    }

    @Override
    protected void createDirectories() {
      //  String baseSpringMVCDir = getBaseDir() + File.separator + getBasePackageName().replace('.', '/');

        controllerDir = new File(baseDir + File.separator + "controller");
        controllerDir.mkdirs();
    }

    @Override
    protected Config createJavaConfig() {
        Template SpringWebConfigTemplate = new Template("WebConfig.java", "mvcTechnology/SpringMVC/config/WebConfig.vm",
                baseDir + File.separator + ApplicationContext.getInstance().getConfigPackage() + File.separator +"WebConfig.java");
        SpringWebConfigTemplate.putReference("packageName", ApplicationContext.getInstance().getPackagePrefix() + "." + ApplicationContext.getInstance().getConfigPackage()); // TODO: 5/20/2016
        SpringWebConfigTemplate.mergeTemplate();
        return new Config("WebConfig");
    }

    @Override
    protected Config createXmlConfig() {
        return null; // TODO: 5/22/2016
    }

    @Override
    protected void createAnnotationDIBaseFiles() {
        Template baseControllerTemplate = new Template("BaseController", "mVCTechnology/SpringMVC/BaseController.vm",
                controllerDir.getAbsolutePath() + File.separator + "BaseController.java");
        baseControllerTemplate.putReference("packageName", ApplicationContext.getInstance().getPackagePrefix() + "." + "controller" + "." + "controller");
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
