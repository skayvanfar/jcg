package ir.sk.jcg.jcgengine.model.project;

import ir.sk.jcg.jcgcommon.PropertyView.ComponentType;
import ir.sk.jcg.jcgcommon.PropertyView.annotation.Editable;
import ir.sk.jcg.jcgcommon.PropertyView.annotation.Prop;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSeeAlso;
import java.io.Serializable;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 4/18/2016
 */
@XmlSeeAlso(Id.class)
@Editable
public class Property extends ModelElement implements Serializable {

    @Prop(label = "Label Name", editable = true, required = true)
    private String labelName;
    @Prop(label = "Type", componentType = ComponentType.EDITABLE_COMBO, values = {"int", "Integer", "short", "Short", "long", "Long", "String", "java.sql.Timestamp"}, editable = true, required = true)
    private String type;
//    @Prop(label = "Value", editable = true, required = true)
//    private String value;
    @Prop(label = "Column Name", editable = true, required = true)
    private String columnName;
    @Prop(label = "Is Unique", componentType = ComponentType.BOOLEAN_CHECKBOX, editable = true, required = true)
    private boolean unique;
    @Prop(label = "Is Nullable", componentType = ComponentType.BOOLEAN_CHECKBOX, editable = true, required = true)
    private boolean nullable;

  //  private List<ValidationRule> validationRules; // TODO: 5/12/2016

    public Property() {
    }

    /**
     * Copy constructor
     * */
    public Property(Property anotherProperty) {
        super(anotherProperty);
     //   this.value = anotherProperty.getValue();
    }

    public String getLabelName() {
        return labelName;
    }

    @XmlAttribute
    public void setLabelName(String labelName) {
        this.labelName = labelName;
    }

    public String getType() {
        return type;
    }

    @XmlAttribute
    public void setType(String type) {
        this.type = type;
    }

//    public String getValue() {
//        return value;
//    }
//
//    @XmlAttribute
//    public void setValue(String value) {
//        this.value = value;
//    }

    public String getColumnName() {
        return columnName;
    }

    @XmlAttribute
    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public boolean isUnique() {
        return unique;
    }

    @XmlAttribute
    public void setUnique(boolean unique) {
        this.unique = unique;
    }

    public boolean isNullable() {
        return nullable;
    }

    @XmlAttribute
    public void setNullable(boolean nullable) {
        this.nullable = nullable;
    }
}
