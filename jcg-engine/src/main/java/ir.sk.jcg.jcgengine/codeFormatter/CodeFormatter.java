package ir.sk.jcg.jcgengine.codeFormatter;

import ir.sk.jcg.jcgengine.exception.ExporterException;

import java.io.File;

/**
 * Format the code
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 7/13/2017.
 */
public interface CodeFormatter {
    /**
     * @param source - unformatted code
     * @return - formatted code
     */
    String format(String source);

    /**
     * Throws exception if not possible to read or write the file.
     * @param file
     * @return - Returns true if formatting went ok and returns false if the formatting could not finish because of errors in the input.
     */
    boolean formatFile(File file) throws ExporterException;
}
