package ir.sk.jcg.lib.jcglibspringmvchandler.file.pair;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 1/9/2017.
 */
public class Container extends FileItem {

    private ArrayList<FileItem> files = new ArrayList<FileItem>();

    Container(String clazz, long id) {
        super(clazz, id, "", "");
    }

    Container(String clazz, long id, String role) {
        super(clazz, id, role, "");
    }

    public int getNumberOfFiles() {
        return files.size();
    }

    public FileItem getFile(int pos) {
        return files.get(pos);
    }

    @Override
    public boolean isFile() {
        return false;
    }

    public boolean rebuild() {
        if (!Files.exists(path))
            return false;
        if (!Files.isDirectory(path))
            return false;
        this.path = path;
        scan();
        return true;
    }

    public void scan() {
        try {
            try (DirectoryStream<Path> directoryStream = Files
                    .newDirectoryStream(path)) {
                files.clear();
                for (Path path : directoryStream) {
                    String name = path.getFileName().toString();
                    if (Files.isHidden(path))
                        continue;
                    else if (Files.isDirectory(path)) {
                        Container container = new Container(getClazz(), getId(),
                                getRole() + "/" + name);
                        container.scan();
                        files.add(container);
                    } else {
                        FileItem item = new FileItem(getClazz(), getId(),
                                getRole(), name);
                        files.add(item);
                    }
                }
            }
        } catch (IOException ignored) {
        }
    }

    ArrayList<FileItem> query(String filter) {
        return query(filter, true);
    }

    ArrayList<FileItem> query(String filter, boolean exactDepth) {
        String[] splits = ("*/" + filter).split("/");
        ArrayList<FileItem> result = new ArrayList<FileItem>();
        filter(this, splits, 0, result, exactDepth);
        return result;
    }

    private void filter(FileItem currentNode, String[] filterString, int depth,
                        ArrayList<FileItem> queue, boolean exactDepth) {
        if (filterString.length <= depth)
            return;
        String cr = filterString[depth];
        if (!cr.equals("*") && (!cr.equals(currentNode.getName())))
            return;
        if (currentNode.isFile()) {
            if (exactDepth && (depth != filterString.length - 1))
                return;
            FileItem fCopy = (FileItem) currentNode.clone();
            queue.add(fCopy);
        } else if (depth < filterString.length - 1) {
            Container container = (Container) currentNode;
            for (int i = 0; i < container.files.size(); i++) {
                FileItem f = container.files.get(i);
                filter(f, filterString, depth + 1, queue, exactDepth);
            }
        }
    }
}
