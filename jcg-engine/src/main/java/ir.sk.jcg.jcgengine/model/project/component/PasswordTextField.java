package ir.sk.jcg.jcgengine.model.project.component;

import ir.sk.jcg.jcgcommon.PropertyView.annotation.Editable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 7/9/2016
 */
@XmlAccessorType(XmlAccessType.NONE)
@Editable
public class PasswordTextField extends TextField {

    public PasswordTextField() {
        super.name = "Password Text Field";
    }
}
