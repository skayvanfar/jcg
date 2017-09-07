package ir.sk.jcg.lib.jcglibspringmvchandler.file.pair;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 1/9/2017.
 */
public class FileItem {

    protected Path path;
    private String clazz;
    private long id;
    private String role;
    private String name;

    public FileItem(String clazz, long id, String role, String name) {
        path = Paths.get(FileQueryProvider.root, clazz, String.valueOf(id),
                role, name);
        this.clazz = clazz;
        this.id = id;
        this.role = role;
        this.name = name;
        if (name.isEmpty())
            this.name = path.getFileName().toString();
    }

    @Override
    public String toString() {
        return path.toString();
    }

    @Override
    protected Object clone() {
        return new FileItem(clazz, id, role, name);
    }

    public boolean isFile() {
        return true;
    }

    public boolean exists() {
        return Files.exists(path);
    }

    public Path getPath() {
        return path;
    }

    public String getClazz() {
        return clazz;
    }

    public long getId() {
        return id;
    }

    public String getRole() {
        return role;
    }

    public String getName() {
        return name;
    }

    public FileItem getSibling(String siblingName) {
        return new FileItem(clazz, id, role, siblingName);
    }

}
