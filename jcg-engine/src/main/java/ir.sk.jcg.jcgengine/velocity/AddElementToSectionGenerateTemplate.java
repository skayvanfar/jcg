package ir.sk.jcg.jcgengine.velocity;

import java.io.StringWriter;
import java.io.Writer;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 7/15/2017
 */
public class AddElementToSectionGenerateTemplate extends GenerateTemplate {

    public AddElementToSectionGenerateTemplate(String name, String outfilePath, String... templateFilePath) {
        super(name, outfilePath, templateFilePath);
    }

    @Override
    public void mergeTemplate() {
        Writer writer = new StringWriter();
        VelocityTemplate.mergeTemplateInWriter(templateFilePath[0], writer, velocityContext);
        System.out.println(writer.toString());
    }
}
