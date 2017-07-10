package ir.sk.jcg.jcglanguageparsertoolkit.lparser.c;

import ir.sk.jcg.jcglanguageparsertoolkit.lparser.tokenizer.Token;
import ir.sk.jcg.jcglanguageparsertoolkit.lparser.tokenizer.Tokenizer;

/**
 * CTokenizer handles C, C++, and Java
 *
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 7/5/2017.
 */
public class CTokenizer extends Tokenizer {

    private CTokenizerState state;

    public CTokenizerState getState() {
        return state;
    }

    public void setState(CTokenizerState state) {
        this.state = state;
    }

    @Override
    public void parse(String text) {
        state = new CTokenizerNormalState(this);

        // Iterate through the text
        int index = 0;
        while ( index < text.length()) {
            // Dispatch the character to the current state
            if (state.next(text.charAt(index)))
                index++;
        }
    }

    /**
     * newstate( classref )
     *
     * classref - The new static class type
     *
     * Called when we are requesting a change of state.  This method creates the
     * new state from the class reference that is passed in.
     */
  /*  public void newState(CTokenizerState cTokenizerState) {

    }*/

    /**
     # This adds a token to the token list.
     * @param token - The new token
     */
    public void addToken(Token token) {
        // Adds a token to the stack. If the token text is empty
        // then ignore it
        if (token.toString().length() < 1)
            return;

        // Add the token to the array
        tokenStream.add(token);
    }

}
