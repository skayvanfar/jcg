package ir.sk.jcg.jcgengine.model.project;

import ir.sk.jcg.jcgengine.model.project.annotation.Editable;
import ir.sk.jcg.jcgengine.model.project.annotation.Prop;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import java.io.Serializable;
import java.util.List;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 4/18/2016
 */
@XmlAccessorType(XmlAccessType.NONE)
@Editable
public class Property extends Element implements Serializable {

    @Prop(isRequired = true)
    private String type;
    @Prop(isRequired = true)
    private String value;
    @Prop
    private String columnName;
    @Prop(isRequired = true)
    private String isUnique;



  //  private List<ValidationRule> validationRules;



    public Property() {}

    /**
     * Copy constructor
     * */
    public Property(Property anotherProperty) {
        super(anotherProperty);
        this.value = anotherProperty.getValue();
    }

    public String getType() {
        return type;
    }

    @XmlAttribute
    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    @XmlAttribute
    public void setValue(String value) {
        this.value = value;
    }

    public String getColumnName() {
        return columnName;
    }

    @XmlAttribute
    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getIsUnique() {
        return isUnique;
    }

    @XmlAttribute
    public void setIsUnique(String isUnique) {
        this.isUnique = isUnique;
    }
}
