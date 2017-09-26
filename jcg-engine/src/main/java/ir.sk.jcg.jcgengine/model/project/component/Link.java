package ir.sk.jcg.jcgengine.model.project.component;

import ir.sk.jcg.jcgcommon.PropertyView.annotation.Editable;
import ir.sk.jcg.jcgengine.model.project.Component;
import ir.sk.jcg.jcgengine.model.project.View;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlIDREF;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 9/23/2017.
 */
@XmlAccessorType(XmlAccessType.NONE)
@Editable
public class Link extends Component {

    private View targetView;

    public Link() {
        super.name = "Link";
    }

    public View getTargetView() {
        return targetView;
    }

    @XmlElement(name = "targetView")
    @XmlIDREF
    public void setTargetView(View targetView) {
        this.targetView = targetView;
    }

}
