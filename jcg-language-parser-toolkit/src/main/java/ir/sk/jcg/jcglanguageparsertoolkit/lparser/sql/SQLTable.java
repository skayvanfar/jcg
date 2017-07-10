package ir.sk.jcg.jcglanguageparsertoolkit.lparser.sql;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents an SQL table definition
 *
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 7/5/2017.
 */
public class SQLTable {

    // The name of the table
    private String name;

    // The fields of the table
    private List<SQLField> fields;

    // True if the field is unique
    private Map<String, SQLField> fieldHash;

    // The comment associated with the table
    private String comment;

    public SQLTable() {
        name = "";
        fields = new ArrayList<>();
        fieldHash = new HashMap<>();
        comment = "";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<SQLField> getFields() {
        return fields;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * Adds a field to the table
     * @param sqlField - The field object
     */
    public void addField(SQLField sqlField) {
        fields.add(sqlField);
        fieldHash.put(sqlField.getName().toLowerCase(), sqlField);
    }

    /**
     * Fetchs a field object based on it's name
     * @param name - The name of the field
     */
    public SQLField getField(String name) {
        return fieldHash.get(name);
    }

}
