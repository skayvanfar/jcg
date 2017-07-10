package ir.sk.jcg.jcglanguageparsertoolkit.lparser.c;

import ir.sk.jcg.jcglanguageparsertoolkit.lparser.tokenizer.WhitespaceToken;

/**
 * Handles whitespace in the character stream
 *
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 7/5/2017.
 */
public class CTokenizerWhitespaceState extends CTokenizerState {

    // Initialize the text buffer to blank
    private String text;

    public CTokenizerWhitespaceState(CTokenizer cTokenizer) {
        super(cTokenizer);
        text = "";
    }

    @Override
    public boolean next(char ch) {
        if (ch == ' ') {
            // If the character is whitespace add it to
            // the buffer
            text += ch;

            return true;
        } else {
            // Otherwise return to the normal state and
            // # add the token
            cTokenizer.addToken(new WhitespaceToken(text));
            cTokenizer.setState(new CTokenizerNormalState(cTokenizer));

            // Return false because we want the tokenizer
            // to re-run on the current character
            return false;
        }
    }

}
