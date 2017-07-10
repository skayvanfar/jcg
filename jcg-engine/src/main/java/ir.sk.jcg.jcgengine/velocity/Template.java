package ir.sk.jcg.jcgengine.velocity;

import org.apache.velocity.VelocityContext;

import java.io.File;
import java.io.Serializable;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 5/12/2016
 */
public class Template implements Serializable, Comparable<Template> {

    private VelocityContext velocityContext = new VelocityContext(); // TODO: 5/12/2016

    private String name;

    private final String templateFilePath;

    private final String outfilePath;

    public Template(String name, String templateFilePath, String outfilePath) {
        if (name == null || name.equals("")) {
            this.name = name; // TODO: 5/12/2016 use ladt sectin of path
        }
        this.name = name;
        this.templateFilePath = templateFilePath;
        this.outfilePath = outfilePath;
    }

    public Template(String name, File pathFile, File outfileName) {
        this(name, pathFile.getPath(), outfileName.getPath());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTemplateFilePath() {
        return templateFilePath;
    }

    public String getOutfilePath() {
        return outfilePath;
    }

    public VelocityContext getVelocityContext() {
        return velocityContext;
    }

    public void setVelocityContext(VelocityContext velocityContext) {
        this.velocityContext = velocityContext;
    }

    public void putReference(String key, Object value) {
        velocityContext.put(key, value);
    }

    @Override
    public int compareTo(Template o) {
        return 0;
    }

    public void mergeTemplate() {
        VelocityTemplate.mergeTemplate(templateFilePath, outfilePath, velocityContext);
    }

    @Override
    public String toString() {
        return name;
    }
}
