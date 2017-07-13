package ir.sk.jcg.jcgengine.exception;

/**
 * Throws when can't export a content
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 7/13/2017.
 */
public class ExporterException extends Exception {
    public ExporterException(String message) {
        super(message);
    }

    public ExporterException(String message, Throwable cause) {
        super(message, cause);
    }
}
