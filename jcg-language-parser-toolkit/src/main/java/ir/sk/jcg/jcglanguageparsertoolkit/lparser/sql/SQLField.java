package ir.sk.jcg.jcglanguageparsertoolkit.lparser.sql;

/**
 * Represents a field in a table.
 *
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 7/5/2017.
 */
public class SQLField {

    // The name of the field
    private String name;

    // The type of the field
    private String type;

    // True if the field is unique
    private boolean notNull;

    // True if the field is non-null
    private boolean unique;

    // True if the field is the primary key
    private boolean primaryKey;

    // Any comment associated with the field
    private String comment;

    public SQLField() {
        name = "";
        type = "";
        notNull = false;
        unique = false;
        primaryKey = false;
        comment = "";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isNotNull() {
        return notNull;
    }

    public void setNotNull(boolean notNull) {
        this.notNull = notNull;
    }

    public boolean isUnique() {
        return unique;
    }

    public void setUnique(boolean unique) {
        this.unique = unique;
    }

    public boolean isPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(boolean primaryKey) {
        this.primaryKey = primaryKey;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }


    @Override
    public String toString() {
        return "SQLField{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", notNull=" + notNull +
                ", unique=" + unique +
                ", primaryKey=" + primaryKey +
                ", comment='" + comment + '\'' +
                '}';
    }

}
