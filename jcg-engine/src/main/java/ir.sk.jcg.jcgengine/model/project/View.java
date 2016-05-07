package ir.sk.jcg.jcgengine.model.project;

import ir.sk.jcg.jcgengine.model.project.annotation.Editable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.io.Serializable;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 4/13/2016
 */
@XmlAccessorType(XmlAccessType.NONE)
@Editable
public class View extends ModelElement implements Serializable {

    public View() {}

    /**
     * Copy constructor
     * */
    public View(View anotherView) {
        super(anotherView);
    }

    @Override
    public String toString() {
        return "View";
    }

}
