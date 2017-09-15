package ir.sk.jcg.jcgengine.velocity;

import org.apache.velocity.VelocityContext;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 7/15/2017
 */
public abstract class GenerateTemplate {

    protected final VelocityContext velocityContext = new VelocityContext();

    protected String name;



    protected final String outfilePath;

    public GenerateTemplate(String name, String outfilePath) {
        this.name = name;
        this.outfilePath = outfilePath;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public String getOutfilePath() {
        return outfilePath;
    }

    /*public static GenerateTemplate create(GenerateTemplateType generateTemplateType, String name,String outfilePath, String ... templateFilePath) {
        switch (generateTemplateType) {
            case NEW_FILE_GENERATE_TEMPLATE:
                return new NewFileGenerateGenerateTemplate(name, outfilePath, templateFilePath[0]);
            case SECTION_GENERATE_TEMPLATE:
                return new SectionGenerateTemplate(name, outfilePath, templateFilePath);
            case ADD_ELEMENT_TO_SECTION_GENERATE_TEMPLATE:
                return new AddElementToSectionGenerateTemplate(name, outfilePath);
            default:
                throw new IllegalArgumentException("Incorrect type code value");
        }
    }*/

    public void putReference(String key, Object value) {
        velocityContext.put(key, value);
    }

    public abstract void mergeTemplate();
}
