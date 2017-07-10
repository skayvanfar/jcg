package ir.sk.jcg.jcglanguageparsertoolkit.lparser.scanner;

import java.util.ArrayList;
import java.util.List;

/**
 * This is a simple data structure that defines what we know about a given
 * class.
 *
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 7/5/2017.
 */
public class LanguageClass {

    // The type of class (could be interface, or struct)
    private String type = "class";

    // The name of the class
    private String name = "";

    // The text of any comments
    private String comments = "";

    // An array of parent class names
    private List<String> parents;

    // The array of methods
    private List<Prototype> methods;

    // The array of instance variables (or constants)
    private List<ClassVariable> variables;

    public LanguageClass() {
        type = "class";
        name = "";
        comments = "";
        parents = new ArrayList<>();
        methods = new ArrayList<>();
        variables = new ArrayList<>();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public List<String> getParents() {
        return parents;
    }

    public List<Prototype> getMethods() {
        return methods;
    }

    public List<ClassVariable> getVariables() {
        return variables;
    }


    /**
     * Adds a method
     * @param method - The method object
     */
    public void addMethod(Prototype method) {
        methods.add(method);
    }

    /**
     * Adds a parent class
     * @param parent - The parent name as text
     */
    public void addParent(String parent) {
        parents.add(parent);
    }

    /**
     * Adds a variable to the class definition
     * @param variable - The variable object
     */
    public void addVariable(ClassVariable variable) {
        variables.add(variable);
    }

    @Override
    public String toString() { // TODO: 7/31/2016 Must change
        return "LanguageClass{" +
                "type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", comments='" + comments + '\'' +
                ", parents=" + parents +
                ", methods=" + methods +
                ", variables=" + variables +
                '}';
    }
}
