package ir.sk.jcg.lib.jcglibhibernatehandler.persistence;

import ir.sk.jcg.lib.jcglibhibernatehandler.localization.LocalizedEnum;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 1/6/2017.
 */
public enum PersistenceExceptionType implements LocalizedEnum {

    Fatal, ConnectionError, BadParameter, Repeated, IO, NotFound;

    @Override
    public String getString() {
        return toString();
    }

    @Override
    public int getOrdinal() {
        return ordinal();
    }

    @Override
    public String getCode() {
        switch (this) {
            case BadParameter :
                return "persistence.exception.bad.parameter";
            case ConnectionError :
                return "persistence.exception.connection.error";
            case Fatal :
                return "persistence.exception.fatal";
            case IO :
                return "persistence.exception.IO";
            case Repeated :
                return "persistence.exception.repeated";
            case NotFound:
                return "persistence.exception.not.found";
        }
        return null;
    }
}
