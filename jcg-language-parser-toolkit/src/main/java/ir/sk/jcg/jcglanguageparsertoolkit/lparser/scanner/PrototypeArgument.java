package ir.sk.jcg.jcglanguageparsertoolkit.lparser.scanner;

/**
 * This class represents arguments on the prototype.
 *
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 7/5/2017.
 */
public class PrototypeArgument {

    // The argument name
    private String name;

    // The argument type
    private String type;

    public PrototypeArgument() {
        name = null;
        type = null;
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

    @Override
    public String toString() {
        return "PrototypeArgument{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                '}';
    }

}
