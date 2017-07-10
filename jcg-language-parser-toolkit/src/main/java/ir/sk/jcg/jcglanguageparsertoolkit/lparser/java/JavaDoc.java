package ir.sk.jcg.jcglanguageparsertoolkit.lparser.java;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Simple JavaDoc interpreter.  This class holds JavaDoc tokens for a series of related JavaDoc
 * comments. You should use one JavaDoc object per method or class. The JavaDoc object parses the
 * comments, and stores the JavaDoc data in structured holders for easy access.
 *
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 7/5/2017.
 */
public class JavaDoc {
    // The tag contents
    private Map<String, String> tags;

    // The @param contents
    private List<String> params;

    public JavaDoc() {
        tags = new HashMap<>();
        // Hash to hold the @name tags. The default text goes into a tag named '@desc'.
        tags.put("@desc", "");

        // Special holder for the @param values
        params = new ArrayList<>();
    }

    public Map<String, String> getTags() {
        return tags;
    }

    public List<String> getParams() {
        return params;
    }

    /**
     * Parses the JavaDoc comment and stores the contents
     *
     * @param text - The JavaDoc comment
     */
    public void parse(String text) { // TODO: 7/6/2017 must be tested
        // Strip leading and trailing space
        text = text.trim();

        // Return unless we see the distinctive '/**' JavaDoc beginning marker
        if (!text.startsWith("/**") && !text.matches("/*"))
            return;

        // This section removes all of the leading stars, the comment lines
        // and anything around the text

        StringBuilder cleanedText = new StringBuilder("");
        String[] lines = text.split("\n");
        for (String line : lines) {
            line = line.trim();
            line = line.replaceFirst("^/\\*\\*", "");    // "/**" // TODO: 7/6/2017
            line = line.replaceFirst("^\\*/", "");       // "*/"
            line = line.replaceFirst("^\\*\\s*", "");   // "*"
            line = line.trim();

            if (line.length() < 1)
                continue;
            cleanedText.append(line).append("\n"); // System.getProperty("line.separator")
        }

        // This section is a mini-tokenizer that splits the content of the string
        // into an array.  The delimiter is whitespace.  The whitespace is stored
        // in the array as well as it may be important to some tags.
        boolean inWhitespace = false;
        String str = "";
        List<String> tokens = new ArrayList<>();
        for (char ch : cleanedText.toString().toCharArray()) {
            if (ch == ' ' || ch == '\n') { // TODO: 7/5/2017
                if (inWhitespace)
                    str += ch;
                else {
                    if (str.length() > 0)
                        tokens.add(str.toString());
                    str = String.valueOf(ch);
                    inWhitespace = true;
                }
            } else {
                if (inWhitespace) {
                    if (str.length() > 0)
                        tokens.add(str.toString());
                    str = String.valueOf(ch);
                    inWhitespace = false;
                } else
                    str += ch;
            }

        }

        if (str.length() > 0)
            tokens.add(str);

        // Now we use our 'tokenized' array and search for the '@tag' items. As
        // we go through the tokens we are building a buffer.  When we reach a
        // new tag we send the buffer on to add_to_tag.
        String curName = "@desc";
        StringBuilder buffer = new StringBuilder("");

        for (String token : tokens) {
            if (token.startsWith("@")) { // // TODO: 7/6/2017 must replace with regex to ignore @@somthing
                if (buffer.length() > 0)
                    addToTags(curName, buffer.toString());
                buffer = new StringBuilder("");
                curName = token;
            } else
                buffer.append(token);
        }

        // Make sure we get the contents of the buffer at the end
        if (buffer.length() > 0)
            addToTags(curName, buffer.toString());
        cleanTags();
    }

    /**
     * This adds some more text to the tag. This is the method you would
     * override if you wanted to add your own JavaDoc tag to a parser, but
     * only if that tag had special parsing requirements.
     *
     * @param key  - The tag name (e.g. '@see' )
     * @param text - The text of the tag
     */
    protected void addToTags(String key, String text) {

        if (key.equals("@param")) {
            // If this is a param then we handle it seperately
            // by storing it's contents into a hash
            text = text.trim();
            // TODO: 7/6/2017
        } else {
            // Otherwise we just append it to the tag text in the tag hash
            if (tags.containsKey(key)) {
                // TODO: 7/6/2017  
            }
        }
    }

    /**
     * A process step method that cleans up the tags after they are read in.
     */
    protected void cleanTags() {
        for (Map.Entry<String, String> entry : (tags.entrySet())) {
            String trimmed = entry.getValue().trim();
            if (!trimmed.equals(entry.getValue())) {
                tags.remove(entry.getKey());
                tags.put(entry.getKey(), trimmed);
            }
        }
    }

    @Override
    public String toString() {
        return "JavaDoc{" +
                "tags=" + tags +
                ", params=" + params +
                '}';
    }
}
