package ir.sk.jcg.jcglanguageparsertoolkit.lparser.c;

import ir.sk.jcg.jcglanguageparsertoolkit.lparser.tokenizer.CodeToken;

/**
 * Handles switching between old comments, new comments, and slashes.
 *
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 7/5/2017.
 */
public class CTokenizerWaitingForCommentState extends CTokenizerState {

    public CTokenizerWaitingForCommentState(CTokenizer cTokenizer) {
        super(cTokenizer);
    }

    @Override
    public boolean next(char ch) {
        switch (ch) {
            // Check to see if we are looking at a new or old
            // style comment
            case '*':
                cTokenizer.setState(new CTokenizerOldCommentState(cTokenizer));
                break;
            case '/':
                cTokenizer.setState(new CTokenizerNewCommentState(cTokenizer));
                break;
            default:
                // Or if it was just a slash
                cTokenizer.addToken(new CodeToken("/"));
                cTokenizer.setState(new CTokenizerNormalState(cTokenizer));
        }
        return true;
    }

}
