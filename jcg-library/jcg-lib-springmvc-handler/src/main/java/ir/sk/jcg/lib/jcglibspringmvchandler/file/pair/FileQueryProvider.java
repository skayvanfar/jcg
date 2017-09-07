package ir.sk.jcg.lib.jcglibspringmvchandler.file.pair;

import ir.sk.jcg.jcglibcommon.persistence.BaseEntity;
import ir.sk.jcg.jcglibcommon.persistence.PersistenceException;
import ir.sk.jcg.jcglibcommon.persistence.PersistenceExceptionType;
import ir.sk.jcg.jcglibcommon.util.Utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 1/9/2017.
 */
public class FileQueryProvider {

    protected static String root;
    private static FileQueryProvider instance;

    public FileQueryProvider() {
        instance = this;
    }

    public static FileQueryProvider instance() {
        return instance;
    }

    public void setRoot(String root) {
        if (File.separator.equals("\\") && root.startsWith("/"))
            root = "C://" + root.substring(1);
        if (root.startsWith("~")) {
            String homeFolder = System.getProperty("user.home");
            root = homeFolder + root.substring(1);
        }
        if (File.separator.equals("\\"))
            root = root.replace('/', '\\');
        this.root = root;
    }

    public void save(BaseEntity entity, String resourcePath, String objectName,
                     InputStream inputStream) throws PersistenceException {
        if (entity.getId() < 1)
            throw new PersistenceException(
                    PersistenceExceptionType.Fatal,
                    "I can't set the file. "
                            + "Entity hasn't unique ID ! Save the entity before setting"
                            + " the file.");
        if ((resourcePath.startsWith("/")) || (resourcePath.endsWith("/")))
            throw new PersistenceException(PersistenceExceptionType.BadParameter, "wrong resource path!"
                    + " path must not start with / and must not end with /");
        if (objectName == null || objectName.length() == 0)
            throw new PersistenceException(PersistenceExceptionType.BadParameter, "wrong object name");
        Path path = Paths.get(root, entity.getClass().getSimpleName(), String
                .valueOf(entity.getId()), resourcePath, objectName);
        try {
            Files.createDirectories(path.getParent());
            Files.copy(inputStream, path, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new PersistenceException(PersistenceExceptionType.Repeated, "Can not move to " + path);
        }
    }

    public ArrayList<FileItem> read(BaseEntity entity, String queryString)
            throws PersistenceException {
        if ((queryString.startsWith("/")) || (queryString.endsWith("/")))
            throw new PersistenceException(PersistenceExceptionType.BadParameter, "wrong resource path!"
                    + " path must not start with / and must not end with /");

        Container container = new Container(entity.getClass().getSimpleName(),
                entity.getId());
        container.rebuild();
        return container.query(queryString);
    }

    public FileItem uniqueResult(BaseEntity entity, String queryString)
            throws PersistenceException {
        ArrayList<FileItem> result = read(entity, queryString);
        if (result.isEmpty())
            return null;
        else if (result.size() != 1)
            throw new PersistenceException(PersistenceExceptionType.Repeated,
                    "result is not unique. size in " + result.size());
        return result.get(0);
    }

    public void clearPath(BaseEntity entity) throws PersistenceException {
        if (entity.getId() < 1)
            throw new PersistenceException(
                    PersistenceExceptionType.Fatal,
                    "I can't set the file. "
                            + "Entity hasn't unique ID ! Save the entity before setting"
                            + " the file.");
        Path path = Paths.get(root, entity.getClass().getSimpleName(), String
                .valueOf(entity.getId()));
        if (!path.toFile().exists())
            return;
        try {
            Utils.removePath(path);
        } catch (IOException ignored) {
        }
    }

    public void remove(BaseEntity entity, String resourcePath, String objectName)
            throws PersistenceException {
        if (entity.getId() < 1)
            throw new PersistenceException(
                    PersistenceExceptionType.Fatal,
                    "I can't set the file. "
                            + "Entity hasn't unique ID ! Save the entity before setting"
                            + " the file.");
        Path path = Paths.get(root, entity.getClass().getSimpleName(), String
                .valueOf(entity.getId()), resourcePath, objectName);
        if (path.toFile().exists()) {
            try {
                path.toFile().delete();
            } catch (Exception exp) {
                throw new PersistenceException(PersistenceExceptionType.Fatal, "could not delete path: "
                        + path);
            }
        }
    }
}
