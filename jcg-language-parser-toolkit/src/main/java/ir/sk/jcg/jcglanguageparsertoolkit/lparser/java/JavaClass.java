package ir.sk.jcg.jcglanguageparsertoolkit.lparser.java;

import ir.sk.jcg.jcglanguageparsertoolkit.lparser.scanner.LanguageClass;

/**
 * A specialization for LanguageClass to handle the JavaDoc markup on a Java
 * class declaration
 *
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 7/5/2017.
 */
public class JavaClass extends LanguageClass {

    // The JavaDoc attached to the class
    private JavaDoc javaDoc;

    // The class to use to build the JavaDoc object
    private Class javaDocClass; // TODO: 7/5/2017 perhaps doesn't needed

    public JavaClass() {
        javaDocClass = javaDoc.getClass();
    }

    public JavaDoc getJavaDoc() {
        return javaDoc;
    }

    public Class getJavaDocClass() {
        return javaDocClass;
    }

    public void setJavaDocClass(Class javaDocClass) {
        this.javaDocClass = javaDocClass;
    }

    /**
     * Overrides the comment text setter to build the JavaDoc as well as setting
     * the comment
     *
     * @param comments - The comment text
     */
    @Override
    public void setComments(String comments) {
        super.setComments(comments);
        if (javaDoc != null)
            javaDoc = new JavaDoc();
        javaDoc.parse(comments);
    }
}
