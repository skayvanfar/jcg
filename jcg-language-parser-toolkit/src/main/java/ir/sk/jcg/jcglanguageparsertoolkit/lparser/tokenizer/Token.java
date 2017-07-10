package ir.sk.jcg.jcglanguageparsertoolkit.lparser.tokenizer;

/**
 * The base type for all tokens
 *
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 7/5/2017.
 */
public class Token {

    private String text;


    /**
     * Initialieze the token with it's text
     * @param text - The text of the token
     */
    public Token(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
