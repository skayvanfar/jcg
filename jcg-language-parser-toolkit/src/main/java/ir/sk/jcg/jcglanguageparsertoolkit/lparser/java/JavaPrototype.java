package ir.sk.jcg.jcglanguageparsertoolkit.lparser.java;

import ir.sk.jcg.jcglanguageparsertoolkit.lparser.scanner.Prototype;

/**
 * A derived class of Prototype to account for the JavaDoc attached to the
 * prototype
 *
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 7/5/2017.
 */
public class JavaPrototype extends Prototype {

    // The JavaDoc attached to the method
    private JavaDoc javaDoc;

    // The class to build the JavaDoc object
    private Class javadocClass; // TODO: 7/5/2017 perhaps doesn't needed

    public JavaPrototype() {
        javaDoc = null;
        javadocClass = JavaDoc.class;
    }

    public JavaDoc getJavaDoc() {
        return javaDoc;
    }

    public Class getJavadocClass() {
        return javadocClass;
    }

    public void setJavadocClass(Class javadocClass) {
        this.javadocClass = javadocClass;
    }

    /**
     * Overrides the add_comment method to account for the JavaDoc as well as
     * adding the comment.
     *
     * @param text - The text of the comment
     */
    @Override
    public void addComment(String text) {
        super.addComment(text);
        if (javaDoc != null)
            javaDoc = new JavaDoc();
        javaDoc.parse(text);
    }
}
