package ir.sk.jcg.lib.jcglibhibernatehandler.persistence;

import java.util.Arrays;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 1/6/2017.
 */
public class PersistenceException extends Exception {

    private final String[] messages;
    private PersistenceExceptionType type;

    public PersistenceException(PersistenceExceptionType type) {
        this(type, null, (String[]) null);
    }

    public PersistenceException(PersistenceExceptionType type,
                                String... messages) {
        this(type, null, messages);
    }

    public PersistenceException(PersistenceExceptionType type,
                                Throwable detail, String... messages) {
        super(Arrays.toString(messages), detail);
        this.type = type;
        this.messages = messages;
    }

    public String[] getMessages() {
        return messages;
    }

    public PersistenceExceptionType getType() {
        return type;
    }
}
