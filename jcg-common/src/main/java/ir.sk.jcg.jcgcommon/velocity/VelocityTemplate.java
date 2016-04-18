package ir.sk.jcg.jcgcommon.velocity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

import java.io.BufferedWriter;
import java.io.FileWriter;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> 4/13/2016
 */
public class VelocityTemplate {

    private static final Logger logger = LoggerFactory.getLogger(VelocityTemplate.class);

    /**
     *
     * */
    static {
        try {
            //       VelocityEngine velocityEngine = new VelocityEngine();
            //    String velocityConf = ClassLoader.getSystemResource("velocity.properties").getPath();
            Velocity.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
            Velocity.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
            Velocity.init();
            //   Velocity.init(velocityConf);
            //      logger.info("Velocity init success", velocityConf);
            logger.info("Velocity init success");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Velocity init error", new Exception("Velocity init error"));
        }
    }

    /**
     * mergeTemplate
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
     * @param templateFile
     * @return template
     * @throws Exception
     */
    private static Template buildTemplate(String templateFile) {
        Template template = null;
        try {
            template = Velocity.getTemplate(templateFile);
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
     * @param templateName
     * @param outfileName
     * @param context
     * @throws Exception
     */
    public static void mergeTemplate(String templateName, String outfileName,
                                     VelocityContext context) {
        Template t = buildTemplate(templateName);
        try {
            mergeTemplate(t, outfileName, context);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("mergeTemplate[" + outfileName + "] error in template " + templateName + ":" + e);
        }
    }

}
