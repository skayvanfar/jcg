package ir.sk.jcg.jcgengine.codeFormatter;

import ir.sk.jcg.jcgengine.exception.ExporterException;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.ToolFactory;
import org.eclipse.jdt.core.formatter.DefaultCodeFormatterConstants;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.IDocument;
import org.eclipse.text.edits.MalformedTreeException;
import org.eclipse.text.edits.TextEdit;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

/**
 * By use of org.eclipse.jdt lib
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 7/13/2017.
 */
public class JavaCodeFormatter implements CodeFormatter {

    static Map options = null;

    // instantiate the default code formatter with the given options
    static final org.eclipse.jdt.core.formatter.CodeFormatter codeFormatter;

    static {
        // take default Eclipse formatting options
        options = DefaultCodeFormatterConstants.getEclipseDefaultSettings();

        // initialize the compiler settings to be able to format 1.7 code
        options.put(JavaCore.COMPILER_COMPLIANCE, JavaCore.VERSION_1_7);
        options.put(JavaCore.COMPILER_CODEGEN_TARGET_PLATFORM, JavaCore.VERSION_1_7);
        options.put(JavaCore.COMPILER_SOURCE, JavaCore.VERSION_1_7);

        // change the option to wrap each enum constant on a new line
        options.put(DefaultCodeFormatterConstants.FORMATTER_ALIGNMENT_FOR_ENUM_CONSTANTS,
                DefaultCodeFormatterConstants.createAlignmentValue(
                        true,
                        DefaultCodeFormatterConstants.WRAP_ONE_PER_LINE,
                        DefaultCodeFormatterConstants.INDENT_ON_COLUMN));
        codeFormatter = ToolFactory.createCodeFormatter(options);
    }

    @Override
    public String format(String source) {
       // TextEdit edit=codeFormatter.format(org.eclipse.jdt.core.formatter.CodeFormatter.K_COMPILATION_UNIT,source,0,source.length(),0,null);

        final TextEdit edit = codeFormatter.format(
                org.eclipse.jdt.core.formatter.CodeFormatter.K_COMPILATION_UNIT, // format a compilation unit
                source, // source to format
                0, // starting position
                source.length(), // length
                0, // initial indentation
                System.getProperty("line.separator") // line separator
        );

        IDocument document = new Document(source);
        try {
            edit.apply(document);
        } catch (MalformedTreeException e) {
            e.printStackTrace();
        } catch (BadLocationException e) {
            e.printStackTrace();
        }

        // return the formatted string
        return document.get();
    }

    @Override
    public boolean formatFile(File file) throws ExporterException {
        IDocument doc=new Document();
        try {
            String contents=new String(org.eclipse.jdt.internal.compiler.util.Util.getFileCharContent(file,null));
            doc.set(contents);
            TextEdit edit = codeFormatter.format(org.eclipse.jdt.core.formatter.CodeFormatter.K_COMPILATION_UNIT,contents,0,contents.length(),0,null);
            if (edit != null) {
                edit.apply(doc);
            }
            else {
                return false;
            }
            final BufferedWriter out=new BufferedWriter(new FileWriter(file));
            try {
                out.write(doc.get());
                out.flush();
            }
            finally {
                try {
                    out.close();
                }
                catch (IOException e) {
                }
            }
            return true;
        }
        catch (  IOException e) {
            throw new ExporterException("Could not format " + file, e);
        }
        catch (  BadLocationException e) {
            throw new ExporterException("Could not format " + file, e);
        }
    }
}
