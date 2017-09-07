package ir.sk.jcg.lib.jcglibspringmvchandler.file.web;

import ir.sk.jcg.jcglibcommon.persistence.BaseEntity;
import ir.sk.jcg.jcglibcommon.persistence.PersistenceException;
import ir.sk.jcg.jcglibcommon.persistence.PersistenceExceptionType;
import ir.sk.jcg.jcglibcommon.util.Utils;
import ir.sk.jcg.lib.jcglibspringmvchandler.file.IFileAccess;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 1/9/2017.
 */
public class FileAccess implements IFileAccess {

    private static FileAccess instance;
    private String root;

    public FileAccess() {
        instance = this;
    }

    public static FileAccess i() {
        return instance;
    }

    public FileItem save(NamedInput input, boolean replace, BaseEntity entity, String... role) throws PersistenceException {
        if (entity.getId() < 1)
            throw new PersistenceException(
                    PersistenceExceptionType.Fatal,
                    "I can't set the file. "
                            + "Entity hasn't unique ID ! Save the entity before setting"
                            + " the file.");
        FileItem fileItem = new FileItem(root, new String[]{entity.getClass().getSimpleName(), String.valueOf(entity.getId())}, role, input.getName());
        try {
            if (!Files.exists(fileItem.getPath().getParent())) Files.createDirectories(fileItem.getPath().getParent());
            if (replace) {
                try (final DirectoryStream<Path> directoryStream = Files
                        .newDirectoryStream(fileItem.getPath().getParent())) {
                    for (Path path : directoryStream)
                        if (!Files.isDirectory(path))
                            Files.delete(path);
                    if (Files.exists(fileItem.getPath())) {
                        Files.walkFileTree(fileItem.getPath(), new SimpleFileVisitor<Path>() {
                            @Override
                            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                                Files.delete(file);
                                return FileVisitResult.CONTINUE;
                            }

                            @Override
                            public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                                Files.delete(dir);
                                return FileVisitResult.CONTINUE;
                            }

                        });
                    }
                }
            } else
                while (Files.exists(fileItem.getPath()))
                    fileItem = new FileItem(root, new String[]{entity.getClass().getSimpleName(),
                            String.valueOf(entity.getId())}, role, String.format("%d%s", System.currentTimeMillis(), input.getName()));
            Files.copy(input.getInput(), fileItem.getPath(), StandardCopyOption.REPLACE_EXISTING);
            return fileItem;
        } catch (IOException e) {
            throw new PersistenceException(PersistenceExceptionType.IO, e, "Can not move to " + fileItem);
        }
    }

    public FileItem read(BaseEntity entity, String... role)
            throws PersistenceException {
        if (entity.getId() < 1)
            return null;
        final Path recordPath = Paths.get(root, entity.getClass().getSimpleName(), String.valueOf(entity.getId()));
        final Path rolePath = Paths.get(recordPath.toString(), role);
        try {
            if (!Files.exists(rolePath) || !Files.isDirectory(rolePath))
                return null;
            try (final DirectoryStream<Path> directoryStream = Files
                    .newDirectoryStream(rolePath)) {
                for (Path path : directoryStream)
                    if (!Files.isDirectory(path))
                        return new FileItem(root, new String[]{entity.getClass().getSimpleName(),
                                String.valueOf(entity.getId())}, role, path.getFileName().toString());
            }
            return null;
        } catch (IOException e) {
            throw new PersistenceException(PersistenceExceptionType.IO, e, "Can not scan " + rolePath);
        }
    }

    public List<FileItem> list(BaseEntity entity, String... role)
            throws PersistenceException {
        if (entity.getId() < 1)
            throw new PersistenceException(
                    PersistenceExceptionType.Fatal,
                    "I can't set the file. "
                            + "Entity hasn't unique ID ! Save the entity before setting"
                            + " the file.");
        final List<FileItem> res = new ArrayList<>();
        final Path recordPath = Paths.get(root, entity.getClass().getSimpleName(), String.valueOf(entity.getId()));
        final Path rolePath = Paths.get(recordPath.toString(), role);
        try {
            if (!Files.exists(rolePath))
                return null;
            try (final DirectoryStream<Path> directoryStream = Files
                    .newDirectoryStream(rolePath)) {
                for (Path path : directoryStream)
                    if (!Files.isDirectory(path)) {
                        res.add(new FileItem(root, new String[]{entity.getClass().getSimpleName(), String.valueOf(entity.getId())}, role, path.getFileName().toString()));
                    }
            }
            return res;
        } catch (IOException e) {
            throw new PersistenceException(PersistenceExceptionType.IO, e, "Can not scan " + rolePath);
        }
    }

    public FileItem read(String... urn)
            throws PersistenceException {
        final FileItem fileItem = new FileItem(root, urn);
        if (Files.exists(fileItem.getPath()))
            return fileItem;
        return null;
    }

    public void remove(BaseEntity entity, String... role)
            throws PersistenceException {
        if (entity.getId() < 1)
            throw new PersistenceException(
                    PersistenceExceptionType.Fatal,
                    "I can't set the file. "
                            + "Entity hasn't unique ID ! Save the entity before setting"
                            + " the file.");
        final Path recordPath = Paths.get(root, entity.getClass().getSimpleName(), String.valueOf(entity.getId()));
        final Path rolePath = Paths.get(recordPath.toString(), role);
        try {
            try (final DirectoryStream<Path> directoryStream = Files
                    .newDirectoryStream(rolePath)) {
                for (Path path : directoryStream)
                    if (!Files.isDirectory(path)) {
                        Files.delete(path);
                    }
            }
        } catch (IOException e) {
            throw new PersistenceException(PersistenceExceptionType.IO, e, "Can not remove " + rolePath);
        }
    }

    public void remove(FileItem fileItem) throws PersistenceException {
        try {
            Files.delete(fileItem.getPath());
        } catch (IOException e) {
            throw new PersistenceException(PersistenceExceptionType.IO, "Can not remove to " + fileItem);
        }
    }

    public void clear(BaseEntity entity) throws PersistenceException {
        final Path recordPath = Paths.get(root, entity.getClass().getSimpleName(), String.valueOf(entity.getId()));
        try {
            Files.delete(recordPath);
        } catch (IOException e) {
            throw new PersistenceException(PersistenceExceptionType.IO, e, "Can not delete  " + recordPath);
        }
    }

    public String getRoot() {
        return root;
    }

    public void setRoot(String root) throws PersistenceException {
        try {
            this.root = Utils.createRoot(root).toAbsolutePath().toString();
        } catch (IOException e) {
            throw new PersistenceException(
                    PersistenceExceptionType.Fatal,
                    "can not create root directory " + root);
        }
    }
}
