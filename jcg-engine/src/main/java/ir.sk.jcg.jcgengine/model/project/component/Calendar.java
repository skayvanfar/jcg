package ir.sk.jcg.jcgengine.model.project.component;

import ir.sk.jcg.jcgcommon.PropertyView.annotation.Editable;
import ir.sk.jcg.jcgengine.model.project.Component;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 9/23/2017.
 */
@XmlAccessorType(XmlAccessType.NONE)
@Editable
public class Calendar extends Component {

    public Calendar() {
        super.name = "Calendar";
    }

    private boolean showTime;

    private boolean ShowAsFromTo;

    public boolean isShowTime() {
        return showTime;
    }

    public void setShowTime(boolean showTime) {
        this.showTime = showTime;
    }

    public boolean isShowAsFromTo() {
        return ShowAsFromTo;
    }

    public void setShowAsFromTo(boolean showAsFromTo) {
        ShowAsFromTo = showAsFromTo;
    }
}
