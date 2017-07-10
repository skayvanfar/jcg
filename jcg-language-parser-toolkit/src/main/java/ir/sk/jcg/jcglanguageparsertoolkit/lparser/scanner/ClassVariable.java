package ir.sk.jcg.jcglanguageparsertoolkit.lparser.scanner;

/**
 * A data structure class to store all of the information about the variables
 * associated with a class
 *
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 7/5/2017.
 */
public class ClassVariable {

    // The name of the variable
    private String name;

    // The type of the variable
    private String type;

    // True if it's static
    private boolean staticc;

    // True if it's const
    private boolean constt;

    // "public", "private" or "protected" // TODO: 7/31/2016 Use Enum
    private String visibility;

    // The value of the variable
    private String value;

    // The comment surrounding the variable
    private String comment;

    public ClassVariable() {
        name = null;
        type = null;
        staticc = false;
        constt = false;
        visibility = null;
        value = null;
        comment = "";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isStaticc() {
        return staticc;
    }

    public void setStaticc(boolean staticc) {
        this.staticc = staticc;
    }

    public boolean isConstt() {
        return constt;
    }

    public void setConstt(boolean constt) {
        this.constt = constt;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "ClassVariable{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", staticc=" + staticc +
                ", constt=" + constt +
                ", visibility='" + visibility + '\'' +
                ", value='" + value + '\'' +
                ", comment='" + comment + '\'' +
                '}';
    }

}
