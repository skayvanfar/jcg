package ir.sk.jcg.jcgengine.model.project.component;

import ir.sk.jcg.jcgcommon.PropertyView.annotation.Editable;
import ir.sk.jcg.jcgcommon.PropertyView.annotation.Prop;
import ir.sk.jcg.jcgengine.model.project.Component;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 7/8/2016
 */
@XmlAccessorType(XmlAccessType.NONE)
@XmlSeeAlso({PasswordTextField.class, TextArea.class})
@Editable
public class TextField extends Component {

    public TextField() {
        super.name = "Text Field";
    }

    public TextField(String maxLength) {
        this.maxLength = maxLength;
    }

    @Prop(label = "Max Length", editable = true, required = true)
    private String maxLength;

    public String getMaxLength() {
        return maxLength;
    }

    @XmlAttribute
    public void setMaxLength(String maxLength) {
        this.maxLength = maxLength;
    }
}
