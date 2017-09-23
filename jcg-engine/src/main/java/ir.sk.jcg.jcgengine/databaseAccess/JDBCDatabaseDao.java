package ir.sk.jcg.jcgengine.databaseAccess;

import ir.sk.jcg.jcgengine.exception.DriverNotFoundException;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 9/21/2017.
 */
public class JDBCDatabaseDao implements DatabaseDao {

    // Logger
  //  private final Logger logger = Logger.getLogger(this.getClass().getName());

    private Connection con;

    private String connectionUrl;
    private String driver;
    private int port; //todo ?
    private String userName;
    private String password;

    public JDBCDatabaseDao(String driver, String connectionURL, int port, String userName, String password) {
        this.driver = driver;
        this.connectionUrl = connectionURL;
        this.port = port;
        this.userName = userName;
        this.password = password;

        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            throw new DriverNotFoundException("Driver not found");
        }

    }

    @Override
    public boolean connect() throws SQLException {
        if (con != null) return false;

        con = DriverManager.getConnection(connectionUrl, userName, password);

      //  logger.info("Connected: " + con);
        System.out.println("Connected: " + con);

        return true;
    }

    @Override
    public boolean disconnect() {
        boolean result = false;
        if (con != null) {
            try {
                con.close();
           //     logger.info("Disconnected");
                System.out.println("Disconnected");
                con = null;
                result = true;
            } catch (SQLException e) {
                System.out.println("Can't close connection");
           //     logger.error("Can't close connection");
            }
        }
        return result;
    }

    private boolean isTablesExist() throws SQLException {
        Statement existStatement = con.createStatement();
        String downloadCreateSql = "SELECT name FROM sqlite_master WHERE type='table' AND name='TBL_BIS_MENU'";

        ResultSet checkResult = existStatement.executeQuery(downloadCreateSql);
        return checkResult.next();
    }

    @Override
    public void save(Menu menu) throws SQLException {
        connect();
        String checkSql = "SELECT COUNT(*) AS count FROM TBL_BIS_MENU WHERE ID = ?";
        PreparedStatement checkStatement = con.prepareStatement(checkSql);

        String insertMenuSql = "INSERT INTO TBL_BIS_MENU (ID, TITLE, IMAGENAME, PRIORITY, URL, PARENTID) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement insertMenuStatement = con.prepareStatement(insertMenuSql);

        String updateMenuSql = "UPDATE TBL_BIS_MENU SET TITLE=?, IMAGENAME=?, PRIORITY=?, URL=?, PARENTID=? WHERE ID = ?";
        PreparedStatement updateMenuStatement = con.prepareStatement(updateMenuSql);


        // download info
        Long id = menu.getId();
        String title = menu.getTitle();
        String imageName = menu.getImageName();
        long priority = menu.getPriority();
        String url = menu.getUrl();
        Long parentId = menu.getParentId();
        int count = 0;
        if (id != null) {
            checkStatement.setLong(1, id);

            ResultSet checkResult = checkStatement.executeQuery();
            checkResult.next();

            count = checkResult.getInt(1); //
        }

        con.setAutoCommit(false);

        if (count == 0) {
        //    logger.info("Inserting download with ID " + id);

            int col = 1;
            insertMenuStatement.setLong(col++, getNextId(con));
            insertMenuStatement.setString(col++, title);
            insertMenuStatement.setString(col++, imageName);
            insertMenuStatement.setLong(col++, priority);
            insertMenuStatement.setString(col++, url);
            if (parentId != null)
                insertMenuStatement.setString(col++, parentId + "");
            else
                insertMenuStatement.setNull(col++, Types.LONGVARCHAR);

            insertMenuStatement.executeUpdate();
        } else {
         //   logger.info("Updating download with ID " + id);

            int col = 1;
            updateMenuStatement.setString(col++, title);
            updateMenuStatement.setString(col++, imageName);
            updateMenuStatement.setLong(col++, priority);
            updateMenuStatement.setString(col++, url);
            updateMenuStatement.setLong(col++, parentId);

            updateMenuStatement.setLong(col++, id);

            updateMenuStatement.executeUpdate();
        }
        con.commit();
        insertMenuStatement.close();
        updateMenuStatement.close();
        checkStatement.close();
        disconnect();
    }

    private long getNextId(Connection con) throws SQLException {
        String checkSql = "SELECT MAX(id) AS maxId FROM TBL_BIS_MENU";
        PreparedStatement checkStatement = con.prepareStatement(checkSql);
        ResultSet checkResult = checkStatement.executeQuery();
        checkResult.next();

        long maxId = checkResult.getLong(1);
        return maxId + 1;
    }

    public void delete(int id) throws SQLException {
        connect();
        String cascadeSql = "PRAGMA foreign_keys = ON";
        //    con.setAutoCommit(false);
        Statement statement = con.createStatement();
        statement.execute(cascadeSql);

        String deleteSql = "DELETE FROM TBL_BIS_MENU WHERE ID = ?";
        PreparedStatement deleteStatement = con.prepareStatement(deleteSql);

        deleteStatement.setInt(1, id);
        deleteStatement.executeUpdate();
        //    con.commit();
        disconnect();
    }

    @Override
    public Long loadMenuIdByTitleName(String titleName) throws SQLException {
        connect();
        String loadSql = "SELECT ID FROM TBL_BIS_MENU WHERE TITLE = ?";
        PreparedStatement loadStatement = con.prepareStatement(loadSql);
        loadStatement.setString(1, titleName);
        ResultSet menuResultSet = loadStatement.executeQuery();
        menuResultSet.next();

        Long mainMenuId = menuResultSet.getLong("ID");

        loadStatement.close();
        disconnect();

        return mainMenuId;
    }
}
