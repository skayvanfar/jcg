package ir.sk.jcg.jcgintellijpluginapp.ui.controller.impl;

import ir.sk.jcg.jcgengine.model.project.Component;
import ir.sk.jcg.jcgengine.model.project.View;
import ir.sk.jcg.jcgintellijpluginapp.ui.controller.ComponentController;
import ir.sk.jcg.jcgintellijpluginapp.ui.dto.ComponentDto;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 7/8/2016
 */
public class ComponentControllerImpl implements ComponentController {

    private static final ComponentController INSTANCE = new ComponentControllerImpl();

    public static ComponentController getInstance() { return INSTANCE; }
    @Override
    public void createComponent(ComponentDto componentDto, View view) {
        Component component = componentDto.getComponentType().createComponent();

        component.setName(componentDto.getTargetProperty().getLabelName());
        component.setTargetProperty(componentDto.getTargetProperty());

        view.addComponent(component);
    }

}
