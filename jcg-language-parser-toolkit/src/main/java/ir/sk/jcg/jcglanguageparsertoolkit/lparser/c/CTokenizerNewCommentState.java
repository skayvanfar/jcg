package ir.sk.jcg.jcglanguageparsertoolkit.lparser.c;

import ir.sk.jcg.jcglanguageparsertoolkit.lparser.tokenizer.CommentToken;

/**
 * State object for new style C comments (e.g. //)
 *
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 7/5/2017.
 */
public class CTokenizerNewCommentState extends CTokenizerState {

    private String text;

    public CTokenizerNewCommentState(CTokenizer cTokenizer) {
        super(cTokenizer);

        // Initialize the text buffer with the beginning //
        text = "//";
    }

    @Override
    public boolean next(char ch) {
        // Add the character to the comment text
        text += ch;

        if (ch == '\n') {
            cTokenizer.addToken(new CommentToken(text));
            cTokenizer.setState(new CTokenizerNormalState(cTokenizer));
        }

        // Proceed to the next character
        return true;
    }

}
