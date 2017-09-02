package ir.sk.jcg.jcgengine.regexp;

import ir.sk.jcg.jcgengine.exception.ModelElementAlreadyExistException;

import java.util.List;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 9/2/2017.
 */
public abstract class RegExSrc {
    private String src;

    public RegExSrc(String src) {
        this.src = src;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    /**
     * Return All generated code sections
     * @param generatedCodeType
     * @return
     */
    public abstract List<String> getGeneratedCode(GeneratedCodeType generatedCodeType);

    /**
     * Specify code already exist in generated code section
     * @param generatedCodeType
     * @param code - code of element like property, control, ...
     * @return
     */
    public abstract boolean hasModelElement(GeneratedCodeType generatedCodeType, String code);

    /**
     * Add the code at the end of generated code section
     * @param generatedCodeType
     * @param code
     */
    public abstract void addModelElement(GeneratedCodeType generatedCodeType, String code) throws ModelElementAlreadyExistException;

}
