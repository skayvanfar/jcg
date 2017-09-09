package ir.sk.jcg.jcgengine.model.project;

import ir.sk.jcg.jcgcommon.PropertyView.annotation.Editable;
import ir.sk.jcg.jcgcommon.PropertyView.annotation.Prop;
import ir.sk.jcg.jcgengine.model.project.exception.ElementBeforeExistException;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Present an View in domain
 *
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 4/13/2016
 */
@XmlAccessorType(XmlAccessType.NONE)
@XmlSeeAlso({DisplayView.class, SearchView.class, CreateEditView.class})
@Editable
public abstract class View extends SchemaItem implements Serializable {


    /**
     * Name of view file
     */
    @Prop(label = "View File Name", required = true, editable = true)
    private String viewFileName;

    /**
     * Counts of components in each row
     */
    @Prop(label = "Span", required = true, editable = true)
    private String span = "3";

    @Prop(label = "Target Entity", required = true)
    private Entity targetEntity;

    // List of components that is used by subclasses
    private List<Component> components;

    public View() {
        components = new ArrayList<>();
    }

    public String getViewFileName() {
        return viewFileName;
    }

    @XmlAttribute
    public void setViewFileName(String viewFileName) {
        this.viewFileName = viewFileName;
    }

    public String getSpan() {
        return span;
    }

    @XmlAttribute
    public void setSpan(String span) {
        this.span = span;
    }

    public Entity getTargetEntity() {
        return targetEntity;
    }

    @XmlAttribute
    @XmlIDREF
    public void setTargetEntity(Entity targetEntity) {
        this.targetEntity = targetEntity;
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

    public void removeComponent(Component component) {
        if (components.contains(component))
            components.remove(component);
    }

}
