package ir.sk.jcg.jcglanguageparsertoolkit.lparser.c;

import ir.sk.jcg.jcglanguageparsertoolkit.lparser.tokenizer.CommentToken;

/**
 * Handles parsing an old-style C comment (e.g. /* ... *\/ )
 *
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 7/5/2017.
 */
public class CTokenizerOldCommentState extends CTokenizerState {

    // Initialize the text with the beginning
    private String text;

    // True if the last character was a star
    private boolean lastWasStar;

    public CTokenizerOldCommentState(CTokenizer cTokenizer) {
        super(cTokenizer);
        text = "/*";
        lastWasStar = false;
    }

    @Override
    public boolean next(char ch) {
        // Add this character to the comment
        text += ch;

        // See if we have a '/' if the last character was a star.
        // If that is the case then return to normal parsing
        // and add the comment token to the token array.
        if (ch == '/' && lastWasStar) {
            cTokenizer.addToken(new CommentToken(text));
            cTokenizer.setState(new CTokenizerNormalState(cTokenizer));
        }

        // Set the last_was_star to true if we see a star
        lastWasStar = (ch == '*');

        // Continue onto the next character
        return true;
    }

}
