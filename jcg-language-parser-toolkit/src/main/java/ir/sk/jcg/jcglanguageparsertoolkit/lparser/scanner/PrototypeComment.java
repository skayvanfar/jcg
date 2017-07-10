package ir.sk.jcg.jcglanguageparsertoolkit.lparser.scanner;

/**
 * The class represents a comment in the prototype.
 *
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 7/5/2017.
 */
public class PrototypeComment {

    // The text of the comment
    private String text;

    public PrototypeComment() {
        this.text = "";
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "PrototypeComment{" +
                "text='" + text + '\'' +
                '}';
    }

}
