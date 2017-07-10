package ir.sk.jcg.jcglanguageparsertoolkit.lparser.tokenizer;

import java.util.ArrayList;

/**
 * An array of tokens with many useful helper methods
 *
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 7/5/2017.
 */
public class TokenStream extends ArrayList<Token> {

    public TokenStream() {
    }

    /**
     * Deletes any leading or trailing whitespace
     */
    public void strip() {
        Token firstToken = get(0);
        Token lastToken = get(size() - 1);

        if (firstToken != null && firstToken instanceof WhitespaceToken)
            remove(0); // Shift
        if (lastToken != null && lastToken instanceof WhitespaceToken)
            remove(size() - 1); // Pop
    }

    /**
     * Returns a new token stream with the code tokens only
     *
     * @return
     */
    public TokenStream getCodeOnly() { // TODO: 7/8/2017 This method and below method must be generic.
        TokenStream codeTokenStream = new TokenStream();
        for (Token token : this) {
            if (token instanceof CodeToken)
                codeTokenStream.add(token);
        }
        return codeTokenStream;
    }

    /**
     * Returns a new token stream with the comments only
     *
     * @return
     */
    public TokenStream getCommentsOnly() {
        TokenStream commentTokenStream = new TokenStream();
        for (Token token : this) {
            if (token instanceof CommentToken)
                commentTokenStream.add(token);
        }
        return commentTokenStream;
    }

    /**
     * find_pattern( pattern )
     * <p>
     * pattern - A pattern array
     * <p>
     * This searches the set of tokens for a pattern of strings.  The array should contain
     * a series of strings and lambdas.  In the first pass the pattern finder looks for the
     * strings.  If a match is found the lambdas are called with the token in the original
     * string for that position.
     * <p>
     * For example:
     * <p>
     * find_pattern( "primary", "key", "(", lambda { |name| print name }, ")" )
     * <p>
     * Would print "myname" if the original sequence was "primary key ( myname )"
     *
     * @param pattern
     * @return
     */
    public String findPattern(String[] pattern) {
        TokenStream codeTokenStream = getCodeOnly();
        int delta = (codeTokenStream.size() - pattern.length) + 1;

        for (int i = 0; i < delta; i++) { // TODO: 7/8/2017 Use Stream java 8
            boolean found = true;

            // TODO: 7/8/2017
        }

        return null; // TODO: 7/31/2016
    }


    /**
     * This method looks backwards from the starting index to find all of the
     * comments and to put them together into a comment stream.  It stops if it
     * finds new CodeTokens.
     *
     * @param start - The start index
     * @return
     */
    public String getComments(int start) {
        TokenStream commentTokenStream = new TokenStream();
        int index = start - 1;

        while (index > -1) {
            Token token = get(index);
            if (get(index) instanceof CodeToken)
                break;
            commentTokenStream.unShift(token);
            index--;
        }


        String comments = "";
        for (int i = 0; i < commentTokenStream.size(); i++) {
            comments += commentTokenStream.get(i).toString(); // TODO: 8/2/2016
        }

        return comments;
    }

    /**
     * This finds a CodeToken with the same text as the input (case insensitive)
     * and returns the index.
     *
     * @param codeToken - The code token text as a string
     * @return
     */
    public long find(CodeToken codeToken) {
        // TODO: 7/8/2017
        return 0;
    }

    /**
     * This is the same as find, but it removes the item.  It returns true if it
     * found something and false if not.
     *
     * @param codeToken - The code token text as a string
     * @return
     */
    public boolean findAndRemove(CodeToken codeToken) {
        // TODO: 7/8/2017
        return false;
    }

    /**
     * Prepends objects to the front of +self+, moving other elements upwards.
     * a = [ "b", "c", "d" ]
     * a.unshift("a")   #=> ["a", "b", "c", "d"]
     *
     * @param token - Added
     */
    // Faced
    public void unShift(Token token) {
        add(0, token);
    }


    /**
     * Converts all of the tokens back to text
     *
     * @return
     */
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("");
        for (Token token : this) {
            stringBuilder.append(token.toString());
        }
        return stringBuilder.toString();
    }
}
