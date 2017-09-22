package ir.sk.jcg.jcgengine.databaseAccess;

import java.net.MalformedURLException;
import java.sql.SQLException;
import java.util.List;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 9/21/2017.
 */
public interface DatabaseDao {
    boolean connect() throws SQLException;
    boolean disconnect();
    void save(Menu download) throws SQLException;
    void delete(int id) throws SQLException;
}
