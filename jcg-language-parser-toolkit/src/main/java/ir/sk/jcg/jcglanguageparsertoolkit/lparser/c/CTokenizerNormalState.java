package ir.sk.jcg.jcglanguageparsertoolkit.lparser.c;

import ir.sk.jcg.jcglanguageparsertoolkit.lparser.tokenizer.CodeToken;

/**
 * The default state machine to which all of the other states return.
 *
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 7/5/2017.
 */
public class CTokenizerNormalState extends CTokenizerState {

    // This normal state handles adding CodeTokens in the
    // basic stream (e.g. not in a string). So we have a
    // text buffer.
    private String text;

    public CTokenizerNormalState(CTokenizer cTokenizer) {
        super(cTokenizer);
        text = "";
    }

    @Override
    public boolean next(char ch) {
        switch (ch) {
            // If this is a special character (e.g. ;,*,+, etc.)
            // then dump the current token and add the special
            // characer token
            case ';':
            case ',':
            case ':':
            case '{':
            case '}':
            case '(':
            case ')':
            case '[':
            case ']':
            case '%':
            case '+':
            case '-':
            case '*':
            case '.':
                cTokenizer.addToken(new CodeToken(text));
                text = "";
                cTokenizer.addToken(new CodeToken(ch + "")); // TODO: 8/1/2016
                break;
            case '\"':
                // Start the double quote state if we see a
                // double quote
                cTokenizer.addToken(new CodeToken(text));
                cTokenizer.setState(new CTokenizerDoubleQuateState(cTokenizer));
                break;
            case '/':
                // Start the double quote state if we see a
                // double quote
                cTokenizer.addToken(new CodeToken(text));
                cTokenizer.setState(new CTokenizerWaitingForCommentState(cTokenizer));
                break;
            case ' ':
                // Move into the whitespace state if we
                // see whitespace.  Return true to re-run
                // the parser on this character.
                cTokenizer.addToken(new CodeToken(text));
                cTokenizer.setState(new CTokenizerWhitespaceState(cTokenizer));
                return false;
            // break;
            default:
                // Otherwise add this character to the buffer
                text += ch;
        }
        // Continue onto the next character
        return true;
    }
}
