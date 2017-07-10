package ir.sk.jcg.jcglanguageparsertoolkit.lparser.java;

import ir.sk.jcg.jcglanguageparsertoolkit.lparser.scanner.*;
import ir.sk.jcg.jcglanguageparsertoolkit.lparser.tokenizer.CodeToken;
import ir.sk.jcg.jcglanguageparsertoolkit.lparser.tokenizer.CommentToken;
import ir.sk.jcg.jcglanguageparsertoolkit.lparser.tokenizer.Token;
import ir.sk.jcg.jcglanguageparsertoolkit.lparser.tokenizer.TokenStream;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/**
 * This is the JavaLanguageScanner specialized to read prototypes for Java
 * functions.
 *
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 7/5/2017.
 */
public class JavaLanguageScanner implements LanguageScanner {

    // An accessor for the array of classes found
    private List<LanguageClass> classes;

    // The prototype class to build
  //  private Prototype prototype;

    // The class to use when building variables
 //   private ClassVariable classVariable;

    // The class to use when building classes
  //  private LanguageClass languageClass;

    // The class to use when build JavaDoc objects
 //   private JavaDoc javaDoc;


    public JavaLanguageScanner() {
        classes = new ArrayList<>();
        /*prototype = new JavaPrototype();
        classVariable = new JavaVariable();
        languageClass = new JavaClass();
        javaDoc = new JavaDoc();*/
    }

    /**
     * This method reads the stream of tokens built by a Tokenizer
     * and fills the @prototypes array with the prototypes
     * that are found.
     * @param tokenStream An array of tokens built by a Tokenizer
     */

    @Override
    public void parse(TokenStream tokenStream) {
        for (int i = 0; i < tokenStream.size(); i++) {
            Token token = tokenStream.get(i);
            if (token.toString().equals("class") || token.toString().equals("interface")) {
                parseClass( tokenStream, i, null);
                break;
            }
        }
    }

    /**
     * Parses the class and adds it to the class list.
     *
     * @param tokenStream - The full array of tokens in the file
     * @param start - The start of our class definition
     * @param baseClass - The name of the base class (null if none)
    */
    protected int parseClass(TokenStream tokenStream, int start, String baseClass) {

        // Build the class and add it
        JavaClass javaClass = new JavaClass();
        // javaClass.setJavaDocClass(); // TODO: 8/2/2016
        classes.add(javaClass); // TODO: 8/2/2016 add the end

        // Backtrack to get the class comment
        int index = start;
        while (index >= 0) {
            if (tokenStream.get(index).toString().equals("public") ||
                    tokenStream.get(index).toString().equals("private") ||
                    tokenStream.get(index).toString().equals("class"))
                break;
            index --;
        }

        // Add the comments to the class
        String comments = tokenStream.getComments(index);
        javaClass.setComments(comments);
        javaClass.getComments().trim(); // TODO: 8/2/2016

        // Make sure the name is set to nil, we use nil as a marker in the state
        // machine
        javaClass.setName(null);

        // This is the code fragment leading up to the interior
        // of the function
        TokenStream codefrag = null;

        // 'level' is the level of bracket nesting.
        int level = 0;

        // This will be true if we are looking at precompiler tokens
        boolean holdUntilReturn = false;

        // Look through each token
        for (int i = start; i< tokenStream.size(); i++) {
            Token token = tokenStream.get(i);

            // Handles parsing the class name
            if (javaClass.getName() == null && token instanceof CodeToken && token.toString().equals("class")) {
                String className = token.toString();
                if (baseClass != null) {
                    className = baseClass + "." + className;
                }
                javaClass.setName(className);
                javaClass.setType(tokenStream.get(start).toString()); // TODO: 8/2/2016
            }

            // Handles parsing the parents of the class
            if (level == 0 && token instanceof CodeToken && javaClass.getName() != null) {
                if (token.toString() != "{" && token.toString() != javaClass.getName() &&
                        token.toString() != "implements" && token.toString() != "extends")
                    javaClass.addParent(token.toString());
            }

            // For imports we wait until a return
            if (holdUntilReturn) {
                if (token.toString().startsWith("\n"))
                    holdUntilReturn =false;
                continue;
            }

            if (token.toString().equals("class") || token.toString().equals("interface")) {
                // Waiting for the class name
                if (index != start)
                    index = parseClass(tokenStream, index, javaClass.getName());
                codefrag = new TokenStream();
            } else if (token.toString().equals("{")) {
                // If we are at level zero then we are
                // opening a function for code, so we
                // should interpret what we have
                // up until now.
                if (level == 1) {
                    parsePrototype(javaClass, codefrag);
                    codefrag = new TokenStream();
                }

                // Now increment the level
                level++;
            } else if (token.toString().equals("}")) {
                // Decrement the level
                level--;
                if (level == 0)
                    break;
            } else if (token.toString() == ";") {
                // If we see a ";" and we are at level
                // zero then we have a constant
                // declaration
                if (level == 1) {
                    parseVariable(javaClass, codefrag);
                    codefrag = new TokenStream();
                }
            } else {
                // Otherwise push the fragment
                if (level == 1)
                    codefrag.add(token);
            }
            index++;
        }
        return index;
    }

    /**
     * This turns the set of tokens that represent a declaration
     * ("int *a[]") into a name ("a"), a type "int *", and an
     * array boolean (true).
     *
     * @param codefrag - The code token array
     * @return
     */
    protected Map<String, String> parseDeclaration(TokenStream codefrag) {
        // Get just the code
        TokenStream codeTokenStream = codefrag.getCodeOnly();

        // If we are an assignment declaration then parse that
        if (codeTokenStream.find(new CodeToken("=")) == 1) {
            Token value = codeTokenStream.get(0);
            codeTokenStream.get(0);
        }

        // See if we are private, public or protected
        String visibility = "";
        if (codeTokenStream.findAndRemove(new CodeToken("private")))
            visibility = "private";
        else if (codeTokenStream.findAndRemove(new CodeToken("public")))
            visibility = "public";
        else if (codeTokenStream.findAndRemove(new CodeToken("protected")))
            visibility = "protected";

        // Get the static and final value
        boolean staticc = codeTokenStream.findAndRemove(new CodeToken("static"));
        boolean finall = codeTokenStream.findAndRemove(new CodeToken("final"));

        // By default this will not be an array
        boolean array = false;

        // Here we are backtracking from the end to find the name
        // within the declaration
        while (codeTokenStream.size() > 0 ) {
            Token frag = codeTokenStream.get(codeTokenStream.size());
            if (frag.toString().equals("["))
                array = true;
            if (!(frag.toString().equals("[") || frag.toString().equals("]")))
                break;
            codeTokenStream.remove(codeTokenStream.size() - 1);
        }

        // We assume that the name is the last non-whitespace,
        // non-array token
        Token name = codeTokenStream.get(codeTokenStream.size());
        codeTokenStream.remove(codeTokenStream.size() - 1);

        // Then we build the type from the remainder
        // TODO: 7/6/2017  

        // Then we return a structure that contains the declaration data
        // TODO: 7/6/2017
        return null;
    }

    protected void parsePrototype(LanguageClass languageClass, TokenStream codefrag) {
        // This will be true when we have found the first code token
        boolean foundCode = false;

        // True when our iterator is within the arguments tokens
        boolean inArguments = false;

        // Start contains the tokens before the arguments
        TokenStream start = new TokenStream();

        // args contains the sets of arguments tokens
        TokenStream args = new TokenStream();

        // Scur_arg contains the tokens of the argument currently being parsed
        TokenStream curArg = new TokenStream();

        // Contains any comments found within the tokens
        TokenStream comments = new TokenStream();

        // Iterate through the codefrag tokens
        for (Token token : codefrag) {
            // Set found_code to true when we find a CodeToken
            if (token instanceof CodeToken)
                foundCode = true;

            // Add the comment if the token is a comment
            if (token instanceof CommentToken) {
                comments.add(token); // TODO: 7/6/2017
                continue;
            }

            // Go to the next token if we have not found code
            if (!foundCode)
                continue;

            if (token.toString().equals("(")) {
                // Look for the start of the arguments
                inArguments = true;
                curArg = new TokenStream(); // TODO: 7/6/2017
            } else if (token.toString().equals(")")) {
                // Look for the end of the arguments, when
                // we find it we dump out of the iterator
                if (curArg.size() > 0)
                 //   args.add(curArg); // TODO: 7/6/2017
                curArg = new TokenStream();
                break;
            } else if (!inArguments) {
                // If we are not in the arguments then
                // push these code tokens into the start
                // fragment list
                start.add(token);
            } else {
                // We are in the arguments, so look for
                // the comments that seperate the arguments
                if (token.toString() == ",") {
                 //   if (curArg.size() > 0)
                     //   args.add(curArg); // TODO: 7/6/2017
                    curArg = new TokenStream();
                } else
                    curArg.add(token);
            }
        }

        // Have the base class build the new prototype
        JavaPrototype proto = new JavaPrototype();
      //  proto.setJavadocClass(); // TODO: 7/6/2017

        // Parse the starting declaration and set the prototype
        Map<String, String> startDecl = parseDeclaration(start);

        proto.setClass_name(languageClass.getName());
        proto.setMethod_name(startDecl.get("name"));
        proto.setMethod_type(startDecl.get("type"));
        proto.setStatics(Boolean.valueOf(startDecl.get("static"))); // TODO: 7/6/2017
        proto.setVisibility(startDecl.get("visibility"));

        // Parse the arguments and add them to the prototype
        for (Token token : args) {
           // Map<String, String> argDecl = parseDeclaration(token); // TODO: 7/6/2017
          //  proto.addArgument(argDecl.get("name"), argDecl.get("type"));
        }

        // TODO: 7/6/2017
        // TODO: 7/6/2017
        // Add the comments
        languageClass.addMethod(proto);
    }

    /**
     * Parses a variable declaration and adds it to the class
     *
     * @param languageClass - The class to which we add the variable declaration
     * @param codefrag - The code fragmen that describes the variable
     */
    protected void parseVariable(LanguageClass languageClass, TokenStream codefrag) {
        // TODO: 7/8/2017
    }


    public List<LanguageClass> getClasses() {
        return classes;
    }

    @Override
    public String toString() {
        return "JavaLanguageScanner{" +
                "classes=" + classes +
                '}';
    }
}
