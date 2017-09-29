package ir.sk.jcg.jcgengine.model.project.component;

import ir.sk.jcg.jcgcommon.PropertyView.annotation.Editable;
import ir.sk.jcg.jcgcommon.PropertyView.annotation.Prop;
import ir.sk.jcg.jcgengine.model.project.Component;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 9/28/2017.
 */
@XmlAccessorType(XmlAccessType.NONE)
@Editable
public class Tag extends Component {

    @Prop(label = "Text", editable = true, required = true)
    private String text;

    public Tag() {
        super.name = "Tag";
    }

    public String getText() {
        return text;
    }

    @XmlAttribute(required = true)
    public void setText(String text) {
        this.text = text;
    }
}
