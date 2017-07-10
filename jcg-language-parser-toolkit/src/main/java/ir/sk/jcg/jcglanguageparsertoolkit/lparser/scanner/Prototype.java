package ir.sk.jcg.jcglanguageparsertoolkit.lparser.scanner;

import java.util.ArrayList;
import java.util.List;

/**
 * This class stores all of the information about a prototype

 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 7/5/2017.
 */
public class Prototype {

    // The class name
    private String class_name;

    // The name of the method
    private String method_name;

    // The return type of the method
    private String method_type;

    // The method arguments
    private List<PrototypeArgument> arguments;

    // Comments associated with the method
    private List<PrototypeComment> comments;

    // True if the method is static
    private boolean statics;

    // Visibility of the method (public, private, etc.)
    private String visibility;

    public Prototype() {
        class_name = null;
        method_name = null;
        method_type = null;
        arguments = new ArrayList<>();
        comments = new ArrayList<>();
        statics = false;
        visibility = null;
    }

    public String getClass_name() {
        return class_name;
    }

    public void setClass_name(String class_name) {
        this.class_name = class_name;
    }

    public String getMethod_name() {
        return method_name;
    }

    public void setMethod_name(String method_name) {
        this.method_name = method_name;
    }

    public String getMethod_type() {
        return method_type;
    }

    public void setMethod_type(String method_type) {
        this.method_type = method_type;
    }

    public List<PrototypeArgument> getArguments() {
        return arguments;
    }

    public List<PrototypeComment> getComments() {
        return comments;
    }

    public boolean isStatics() {
        return statics;
    }

    public void setStatics(boolean statics) {
        this.statics = statics;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    /**
     * Adds an argument to the ordered argument list
     * @param name - The name of the argument
     * @param type - The type of the argument
     */
    public void addArgument(String name, String type) {
        PrototypeArgument argument = new PrototypeArgument();
        argument.setName(name);
        argument.setType(type);
        arguments.add(argument);
    }

    /**
     * Adds a comment to the prototype
     * @param text - The text of the comment
     */
    public void addComment(String text) {
        PrototypeComment comment = new PrototypeComment();
        comment.setText(text);
        comments.add(comment);
    }

    @Override
    public String toString() {
        return "Prototype{" +
                "class_name='" + class_name + '\'' +
                ", method_name='" + method_name + '\'' +
                ", method_type='" + method_type + '\'' +
                ", arguments=" + arguments +
                ", comments=" + comments +
                ", statics=" + statics +
                ", visibility='" + visibility + '\'' +
                '}';
    }

}
