package ir.sk.jcg.jcglanguageparsertoolkit.lparser.java;

import ir.sk.jcg.jcglanguageparsertoolkit.lparser.scanner.ClassVariable;

/**
 * A specialized ClassVariable that includes the JavaDoc for the class
 *
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 7/5/2017.
 */
public class JavaVariable extends ClassVariable {

    // The JavaDoc object
    private JavaDoc javaDoc;

    // The class to use to create the JavaDoc object
    private Class javadocClass; // TODO: 7/5/2017 perhaps doesn't needed

    public JavaVariable() {
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
     * Override of the comment set method to not only set the comment but also
     * to parse it into the JavaDoc
     */
    @Override
    public void setComment(String comment) {
        super.setComment(comment);
        if (javaDoc != null)
            javaDoc = new JavaDoc();
        javaDoc.parse(comment);

    }
}
