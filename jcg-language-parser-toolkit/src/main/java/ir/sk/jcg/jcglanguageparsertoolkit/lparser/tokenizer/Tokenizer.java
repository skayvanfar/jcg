package ir.sk.jcg.jcglanguageparsertoolkit.lparser.tokenizer;

/**
 * The base class for all Tokenizers. The Tokenizers are meant to be
 * specialized to parse the text of various languages into token streams
 * that consist of CodeToken, WhitespaceToken, and CommentToken objects
 *
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 7/5/2017.
 */
public abstract class Tokenizer {

    // The ordered array of tokens
    protected TokenStream tokenStream;

    public Tokenizer() {
        tokenStream = new TokenStream();
    }

    public TokenStream getTokenStream() {
        return tokenStream;
    }


    /**
     * parse( text )
     *
     * text - The string of code to be turned into tokens
     *
     * This should be overridden by all derived classes. It is
     * meant to parse the text into tokens. Which should be stored
     * in @tokens.
     * @param text
     */
    public abstract void parse(String text);
}
