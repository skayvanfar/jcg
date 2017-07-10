package ir.sk.jcg.jcglanguageparsertoolkit.lparser.c;

import ir.sk.jcg.jcglanguageparsertoolkit.lparser.tokenizer.CodeToken;

/**
 * Handles parsing strings
 *
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 7/5/2017.
 */
public class CTokenizerDoubleQuateState extends CTokenizerState {

    // Start the text buffer with the beginning double quote
    private String text;

    // Set the escaped flag to false.  This will go true when
    // we see a '\'
    private boolean escaped;

    public CTokenizerDoubleQuateState(CTokenizer cTokenizer) {
        super(cTokenizer);
        text = "\"";
        escaped = false;
    }

    @Override
    public boolean next(char ch) {
        // Add this character to the text buffer
        text += ch;

        // If the character is a double qoute and we are not
        // escape then go back to the normal state and add
        // the string token to the array
        if (ch == '\"' && !escaped) {
            cTokenizer.addToken(new CodeToken(text));
            cTokenizer.setState(new CTokenizerNormalState(cTokenizer));
        }

        // Set escaped to true if we see a \
        escaped = (ch == '\\') ? true : false;

        // Proceed to the next character
        return true;
    }

}
