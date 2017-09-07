package ir.sk.jcg.lib.jcglibspringmvchandler.file.simple;

import ir.sk.jcg.jcglibcommon.persistence.BaseEntity;
import ir.sk.jcg.jcglibcommon.persistence.PersistenceException;
import ir.sk.jcg.jcglibcommon.persistence.PersistenceExceptionType;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 1/9/2017.
 */
public class SimpleFileOrganizer {

    private static SimpleFileOrganizer instance;

    private String root;

    public SimpleFileOrganizer() {
        SimpleFileOrganizer.instance = this;
    }

    public static SimpleFileOrganizer i() {
        return instance;
    }

    public String getRoot() {
        return root;
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public void setRoot(String root) {
        if (File.separator.equals("\\") && root.startsWith("/"))
            root = "C://" + root.substring(1);
        if (root.startsWith("~")) {
            String homeFolder = System.getProperty("user.home");
            root = homeFolder + root.substring(1);
        }
        if (File.separator.equals("\\"))
            root = root.replace('/', '\\');
        File rootFile = new File(root);
        rootFile.mkdirs();
        this.root = root;
    }

    public void save(BaseEntity entity, String fileName, InputStream stream)
            throws PersistenceException {
        if ((entity == null) || (entity.getId() == -1) || (fileName == null)
                || (fileName.length() == 0) || (stream == null))
            throw new PersistenceException(PersistenceExceptionType.BadParameter,
                    "parameters for file saving are not completed...");
        Path path = Paths.get(root, entity.getClass().getSimpleName(), String
                .valueOf(entity.getId()), fileName);
        try {
            Files.createDirectories(path.getParent());
            Files.copy(stream, path, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new PersistenceException(PersistenceExceptionType.Repeated, "Can not move to " + path);
        }
    }

    public String load(BaseEntity entity, String fileName) throws PersistenceException {
        if ((entity == null) || (entity.getId() <= 0) || (fileName == null)
                || (fileName.length() == 0))
            throw new PersistenceException(PersistenceExceptionType.BadParameter,
                    "parameters for file saving are not completed...");
        Path path = Paths.get(root, entity.getClass().getSimpleName(), String
                .valueOf(entity.getId()), fileName);
        if (path.toFile().exists())
            return path.toFile().getAbsolutePath();
        else
            return null;
    }

    /**
     * list all files which are saved for an entity
     *
     * @param entity entity
     * @return never returns null
     * @throws PersistenceException
     */
    public List<Path> list(BaseEntity entity) throws PersistenceException {
        return list(entity, null);
    }

    /**
     * list all files which are saved for an entity
     *
     * @param entity entity
     * @return never returns null
     * @throws PersistenceException
     */
    public List<Path> list(BaseEntity entity, String startWith) throws PersistenceException {
        if ((entity == null) || (entity.getId() <= 0))
            throw new PersistenceException(PersistenceExceptionType.BadParameter,
                    "parameters for file listing are not completed...");
        Path path = Paths.get(root, entity.getClass().getSimpleName(), String
                .valueOf(entity.getId()));
        if (path.toFile().exists()) {
            List<Path> fileNames = new ArrayList<>();
            try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(path)) {
                for (Path p : directoryStream) {
                    if (startWith != null) {
                        if (p.getFileName().toString().startsWith(startWith)) fileNames.add(p);
                    } else fileNames.add(p);
                }
            } catch (IOException ignored) {
            }
            return fileNames;
        } else
            return new ArrayList<>();
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public void clear(BaseEntity entity) throws PersistenceException {
        if ((entity == null) || (entity.getId() <= 0))
            throw new PersistenceException(PersistenceExceptionType.BadParameter,
                    "parameters for file saving are not completed...");
        Path path = Paths.get(root, entity.getClass().getSimpleName(), String
                .valueOf(entity.getId()));
        if (!path.toFile().exists())
            return;
        if (path.toFile().isDirectory()) {
            File[] files = path.toFile().listFiles();
            if (files != null) {
                for (File file : files)
                    file.delete();
            }
            path.toFile().delete();
        } else
            throw new PersistenceException(PersistenceExceptionType.Fatal,
                    "wrong file address generated for entity");
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public void clear(BaseEntity entity, String startOfFileName)
            throws PersistenceException {
        if ((entity == null) || (entity.getId() <= 0))
            throw new PersistenceException(PersistenceExceptionType.BadParameter,
                    "parameters for file saving are not completed...");
        Path path = Paths.get(root, entity.getClass().getSimpleName(), String
                .valueOf(entity.getId()));
        if (path.toFile().isDirectory()) {
            File[] files = path.toFile().listFiles();
            if (files != null) {
                for (File file : files)
                    if (file.getName().startsWith(startOfFileName))
                        file.delete();
            }
        } else
            throw new PersistenceException(PersistenceExceptionType.Fatal,
                    "wrong file address generated for entity");
    }
}
