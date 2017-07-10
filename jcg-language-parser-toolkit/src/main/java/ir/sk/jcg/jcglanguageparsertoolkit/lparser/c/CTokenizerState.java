package ir.sk.jcg.jcglanguageparsertoolkit.lparser.c;

/**
 * The base class state object for the C-Tokenizer state machine.
 *
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 7/5/2017.
 */
public abstract class CTokenizerState {

    // Special characters that are found as delineators in C
    public static final String[] specials = {";",",",":","{","}","(",")","[","]","%","+","-","*","."};

    protected CTokenizer cTokenizer;

    public CTokenizerState(CTokenizer cTokenizer) {
        this.cTokenizer = cTokenizer;
    }

    /**
     * All states should override this method.  This handles a
     * character from the stream.  Returning true means that the
     * parsing should continue to the next character.  Returning false
     * means the parser should stay on the current character.
     * @param ch - The character
     * @return
     */
    public abstract boolean next(char ch);
}
