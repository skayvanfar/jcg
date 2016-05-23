package ir.sk.jcg.jcgengine.model.platform.technology.SecurityTechnology.SpringSecurity;

import ir.sk.jcg.jcgcommon.PropertyView.ComponentType;
import ir.sk.jcg.jcgcommon.PropertyView.annotation.Prop;
import ir.sk.jcg.jcgengine.ApplicationContext;
import ir.sk.jcg.jcgengine.model.platform.Dependency;
import ir.sk.jcg.jcgengine.model.platform.Exclusion;
import ir.sk.jcg.jcgengine.model.platform.technology.SecurityTechnology.SecurityTechnologyHandler;
import ir.sk.jcg.jcgengine.model.platform.technology.SpringTechnology.Config;
import ir.sk.jcg.jcgengine.model.platform.technology.SpringTechnology.SpringConfigType;
import ir.sk.jcg.jcgengine.velocity.Template;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 5/21/2016
 */
public class SpringSecurityHandler extends SecurityTechnologyHandler {

    private static final String SPRING_SECURITY_GROUP_ID = "org.springframework.security";
    private static final String SPRING_SECURITY_VERSION = "4.0.3.RELEASE";

    private File securityDir;

    public SpringSecurityHandler() {
        super("Spring Security");
        Dependency springSecurityConfigDependency = new Dependency(SPRING_SECURITY_GROUP_ID, "spring-security-config", SPRING_SECURITY_VERSION, "compile");
        springSecurityConfigDependency.addExclusion(new Exclusion(SPRING_SECURITY_GROUP_ID, "spring-asm"));
        dependencies.add(springSecurityConfigDependency);
        dependencies.add(new Dependency(SPRING_SECURITY_GROUP_ID, "spring-security-web", SPRING_SECURITY_VERSION, "compile"));

    }

    @Override
    protected void createDirectories() throws Exception {
        securityDir = new File(ApplicationContext.getInstance().getJavaWithPackagePrefixPath() + File.separator + ApplicationContext.getInstance().getConfigPackage() + File.separator +"security");
        securityDir.mkdirs();
    }


    @Override
    protected Config createJavaConfig() {
        Template springSecurityConfigTemplate = new Template("Spring Security Config Initializer", "securityTechnology/springSecurity/config/SecurityConfig.vm",
                ApplicationContext.getInstance().getJavaWithPackagePrefixPath() + File.separator + ApplicationContext.getInstance().getConfigPackage() + File.separator  + "SecurityConfig.java");
        springSecurityConfigTemplate.putReference("packageName", ApplicationContext.getInstance().getPackagePrefix() + "." + ApplicationContext.getInstance().getConfigPackage());
        springSecurityConfigTemplate.mergeTemplate();
        Config springSecurityConfig = new Config("SecurityConfig");
        return springSecurityConfig;
    }

    @Override
    protected Config createXmlConfig() {
        return null; // TODO: 5/22/2016
    }

    @Override
    protected void createAnnotationDIBaseFiles() {
        //////////////////////
        Template springSecurityInitializerTemplate = new Template("Spring Security Initializer", "securityTechnology/springSecurity/SpringSecurityInitializer.vm",
                securityDir.getAbsolutePath() + File.separator  + "SpringSecurityInitializer.java");
        springSecurityInitializerTemplate.putReference("packageName", ApplicationContext.getInstance().getPackagePrefix() + "." + ApplicationContext.getInstance().getConfigPackage() + "." + "security");
        springSecurityInitializerTemplate.mergeTemplate();

        //////////////////////
        Template projectUserDetailsTemplate = new Template("ProjectUserDetails", "securityTechnology/springSecurity/ProjectUserDetails.vm",
                securityDir.getAbsolutePath() + File.separator  + "ProjectUserDetails.java");
        projectUserDetailsTemplate.putReference("packageName", ApplicationContext.getInstance().getPackagePrefix() + "." + ApplicationContext.getInstance().getConfigPackage() + "." + "security");
        projectUserDetailsTemplate.mergeTemplate();

        //////////////////////
        Template projectUserDetailsServiceTemplate = new Template("ProjectUserDetailsService", "securityTechnology/springSecurity/ProjectUserDetailsService.vm",
                securityDir.getAbsolutePath() + File.separator  + "ProjectUserDetailsService.java");
        projectUserDetailsServiceTemplate.putReference("packageName", ApplicationContext.getInstance().getPackagePrefix() + "." + ApplicationContext.getInstance().getConfigPackage() + "." + "security");
        projectUserDetailsServiceTemplate.mergeTemplate();
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
