package ir.sk.jcg.lib.jcglibspringmvchandler.file.web;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 1/6/2017.
 */
public class FileItem {
    @JsonIgnore
    private String root;
    private String[] urn;
    @JsonIgnore
    private Path path;

    public FileItem(String root, String[] urn) {
        this.root = root;
        this.urn = urn;
        this.path = Paths.get(root, urn);
    }

    public FileItem(String root, String[] record, String[] role, String name) {
        this.root = root;
        this.urn = new String[record.length + role.length + 1];
        System.arraycopy(record, 0, urn, 0, record.length);
        System.arraycopy(role, 0, urn, record.length, role.length);
        urn[urn.length - 1] = name;
        this.path = Paths.get(root, urn);
    }

    public String[] getUrn() {
        return urn;
    }

    public Path getPath() {
        return path;
    }

    public String getRoot() {
        return root;
    }

    @Override
    public String toString() {
        return "FileItem{" +
                "root='" + root + '\'' +
                ", urn=" + Arrays.toString(urn) +
                ", path=" + path +
                '}';
    }
}
