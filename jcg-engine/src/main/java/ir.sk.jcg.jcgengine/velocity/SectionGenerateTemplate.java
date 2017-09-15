package ir.sk.jcg.jcgengine.velocity;

import org.apache.velocity.VelocityContext;

import java.io.Serializable;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 9/2/2017.
 */
public class SectionGenerateTemplate extends GenerateTemplate implements Serializable, Comparable<SectionGenerateTemplate> {
    private VelocityContext velocityContext = new VelocityContext();

    public SectionGenerateTemplate(String name,String outfilePath) {
        super(name, outfilePath);
    }

    public void putReference(String key, Object value) {
        velocityContext.put(key, value);
    }

    @Override
    public int compareTo(SectionGenerateTemplate o) {
        return 0;
    }

    public void mergeTemplate() {
        //    VelocityTemplate.mergeTemplateInWriter(templateFilePath, writer, velocityContext);
    }

    @Override
    public String toString() {
        return name;
    }
    // TODO: 9/2/2017 not completed
}
