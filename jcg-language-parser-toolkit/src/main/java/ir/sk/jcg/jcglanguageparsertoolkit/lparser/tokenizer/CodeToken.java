package ir.sk.jcg.jcglanguageparsertoolkit.lparser.tokenizer;

/**
 * This represents a code fragment within a source file
 *
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 7/5/2017.
 */
public class CodeToken extends Token {

    /**
     * Initialieze the token with it's text
     *
     * @param text - The text of the token
     */
    public CodeToken(String text) {
        super(text);
    }
}
