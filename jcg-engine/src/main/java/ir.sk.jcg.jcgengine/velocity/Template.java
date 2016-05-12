package ir.sk.jcg.jcgengine.velocity;

import java.io.File;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 5/12/2016
 */
public class Template implements Serializable, Comparable<Template> {

    private String name;

    private final String path;

    private Map<String, Object> referencesMap;

    public Template(String name, String path) {
        if (name == null || name.equals("")) {
            name = path; // TODO: 5/12/2016 use ladt sectin of path
        }
        this.name = name;
        this.path = path;
    }

    public Template(String name, File pathFile) {
        this(name, pathFile.getPath());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public Map<String, Object> getReferencesMap() {
        return referencesMap;
    }

    public void setReferencesMap(Map<String, Object> referencesMap) {
        this.referencesMap = referencesMap;
    }

    public void putReference(String key, Object value) {
        referencesMap.put(key, value);
    }

    @Override
    public int compareTo(Template o) {
        return 0;
    }


}
