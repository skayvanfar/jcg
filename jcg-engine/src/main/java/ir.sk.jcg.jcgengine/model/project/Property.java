package ir.sk.jcg.jcgengine.model.project;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.io.Serializable;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 4/18/2016
 */
@XmlAccessorType(XmlAccessType.NONE)
public class Property extends Element implements Serializable {

    @Prop
    private String value;

    public Property() {}

    /**
     * Copy constructor
     * */
    public Property(Property anotherProperty) {
        super(anotherProperty);
        this.value = anotherProperty.getValue();
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Property{}";
    }

}
