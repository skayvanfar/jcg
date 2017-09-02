package ir.sk.jcg.jcgengine.exception;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 9/2/2017.
 */
public class ModelElementAlreadyExistException  extends Exception {
    public ModelElementAlreadyExistException() {
    }

    public ModelElementAlreadyExistException(String message) {
        super(message);
    }
}
