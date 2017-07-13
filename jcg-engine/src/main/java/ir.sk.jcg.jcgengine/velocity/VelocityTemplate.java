package ir.sk.jcg.jcgengine.velocity;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.runtime.RuntimeConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> 4/13/2016
 */
public class VelocityTemplate {

    private static final Logger logger = LoggerFactory.getLogger(VelocityTemplate.class);
    private static VelocityEngine velocityEngine = null;

    /**
     *
     * */
    static {
        try {
            velocityEngine = new VelocityEngine();
            //   String velocityConf = ClassLoader.getSystemResource("/media/saeed/win/E/template/velocity.properties").getPath();
            //   System.out.printf("ve  " + velocityConf);
            velocityEngine.setProperty("runtime.log.logsystem.class", "org.apache.velocity.runtime.log.NullLogSystem");
            velocityEngine.setProperty("directive.set.null.allowed", "true");

            //    velocityEngine.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
            //     velocityEngine.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
            //    velocityEngine.setProperty("resource.loader","file,class,jar");
            //    File file = new File("/resources/template");
            //    URL res = VelocityTemplate.class.getClassLoader().getResource("/template.oRMTechnology");
            //  File f = new File(res.getFile());

            //  URL resource = VelocityTemplate.class.getResource("../mavenBuild.vm");
            //  File f = Paths.get(resource.toURI()).toFile();


            //      System.out.println("absolutePath:::::::::::::          "     + "E:/template/buildTechnology/maven");
            velocityEngine.setProperty(RuntimeConstants.FILE_RESOURCE_LOADER_PATH, "E:\\template"); // TODO: 4/22/2016 must chnage to relative
            velocityEngine.setProperty(RuntimeConstants.VM_LIBRARY_AUTORELOAD, true);


            velocityEngine.init();
            //       Velocity.init(velocityConf);
            //      logger.info("Velocity init success", velocityConf);
            logger.info("Velocity init success");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Velocity init error", new Exception("Velocity init error"));
        }
    }

    /**
     * mergeTemplate
     *
     * @param template
     * @param outfileName
     * @param context
     * @throws Exception
     */
    private static void mergeTemplate(Template template, String outfileName,
                                      VelocityContext context) throws Exception {
  /*
   * Now have the template engine process your template using the data
   * placed into the context. Think of it as a 'merge' of the template and
   * the data to produce the output stream.
   */
        BufferedWriter writer = new BufferedWriter(new FileWriter(outfileName));

        if (template != null)
            template.merge(context, writer);

  /*
   * flush and cleanup
   */
        writer.flush();
        writer.close();
    }

    /**
     * buildTemplate
     *
     * @param templateFile
     * @return template
     * @throws Exception
     */
    private static Template buildTemplate(String templateFile) {
        Template template = null;
        try {
            template = velocityEngine.getTemplate(templateFile);
        } catch (ResourceNotFoundException rnfe) {
            rnfe.printStackTrace();
            logger.error("buildTemplate error : cannot find template " + templateFile);
        } catch (ParseErrorException pee) {
            pee.printStackTrace();
            logger.error("buildTemplate error in template " + templateFile + ":" + pee);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("buildTemplate error in template " + templateFile + ":" + e);
        }
        return template;
    }

    /**
     * mergeTemplate
     *
     * @param templateName
     * @param outfileName
     * @param context
     * @throws Exception
     */
    public static void mergeTemplate(String templateName, String outfileName,
                                     VelocityContext context) {
        Template t = buildTemplate(templateName);
        Path path = Paths.get(outfileName).getParent();
        try {
            Files.createDirectories(path);
        } catch (IOException e) {
            logger.error("Cannot create directories - " + e);
        }
        try {
            mergeTemplate(t, outfileName, context);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("mergeTemplate[" + outfileName + "] error in template " + templateName + ":" + e);
        }
    }

}
