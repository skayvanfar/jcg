package ir.sk.jcg.jcglanguageparsertoolkit.lparser.tokenizer;

/**
 * This represents a comment token
 *
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 7/5/2017.
 */
public class CommentToken extends Token {

    /**
     * Initialieze the token with it's text
     *
     * @param text - The text of the token
     */
    public CommentToken(String text) {
        super(text);
    }
}
