package ir.sk.jcg.jcglanguageparsertoolkit.lparser.tokenizer;

/**
 * This represents a string of whitespace within a source file

 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 7/5/2017.
 */
public class WhitespaceToken extends Token {

    /**
     * Initialieze the token with it's text
     *
     * @param text - The text of the token
     */
    public WhitespaceToken(String text) {
        super(text);
    }

}
