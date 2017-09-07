package ir.sk.jcg.lib.jcglibspringmvchandler.file;

import ir.sk.jcg.jcglibcommon.persistence.BaseEntity;
import ir.sk.jcg.jcglibcommon.persistence.PersistenceException;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 1/9/2017.
 */
public interface IFileAccess {
    void clear(BaseEntity entity) throws PersistenceException;
}
