package ir.sk.jcg.jcgengine.model.project;

import ir.sk.jcg.jcgcommon.PropertyView.annotation.Editable;
import ir.sk.jcg.jcgengine.model.project.exception.ElementBeforeExistException;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 7/8/2016
 */
@XmlAccessorType(XmlAccessType.NONE)
@Editable
public class DataGrid extends ModelElement {

    private List<Component> components;

    public DataGrid() {
        super.name = "Grid";
        components = new ArrayList<>();
    }

    public List<Component> getComponents() {
        return components;
    }

    @XmlElement(name = "component")
    public void setComponents(List<Component> components) {
        this.components = components;
    }

    public void addComponent(Component component) { // // TODO: 5/2/2016 repeated code(in Schema)
        if (components.contains(component))
            throw new ElementBeforeExistException(component);
        components.add(component);
    }

    public void addComponentAll(List<? extends Component> components) {
        for (Component component : components)
            addComponent(component);
    }

    public void removeComponent(Component component) {
        if (components.contains(component))
            components.remove(component);
    }

}
