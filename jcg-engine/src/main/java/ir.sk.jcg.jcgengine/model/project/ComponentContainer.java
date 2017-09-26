package ir.sk.jcg.jcgengine.model.project;

import java.util.List;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 9/26/2017.
 */
public interface ComponentContainer {
    List<Component> getComponents();
    void setComponents(List<Component> components);
    void addComponent(Component component);
}
