package ir.sk.jcg.jcgengine.velocity;

import java.io.File;
import java.io.Serializable;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 5/12/2016
 */
public class NewFileGenerateGenerateTemplate extends GenerateTemplate implements Serializable, Comparable<NewFileGenerateGenerateTemplate> {

    protected final String[] templateFilePath;

    public NewFileGenerateGenerateTemplate(String name, String  outfilePath, String ... templateFilePath) {
        super(name, outfilePath);
        this.templateFilePath = templateFilePath;
    }

    public NewFileGenerateGenerateTemplate(String name, File pathFile, File outfileName) {
        this(name, pathFile.getPath(), outfileName.getPath());
    }

    public String[] getTemplateFilePath() {
        return templateFilePath;
    }

    @Override
    public int compareTo(NewFileGenerateGenerateTemplate o) {
        return 0;
    }

    @Override
    public void mergeTemplate() {
        VelocityTemplate.mergeTemplate(templateFilePath[0], outfilePath, velocityContext);
    }

    @Override
    public String toString() {
        return name;
    }
    // TODO: 9/2/2017 not completed
}
