package ir.sk.jcg.jcgintellijpluginapp.ui.controller.impl;

import ir.sk.jcg.jcgengine.model.project.Component;
import ir.sk.jcg.jcgengine.model.project.ComponentContainer;
import ir.sk.jcg.jcgengine.model.project.DataGrid;
import ir.sk.jcg.jcgengine.model.project.View;
import ir.sk.jcg.jcgengine.model.project.component.Link;
import ir.sk.jcg.jcgintellijpluginapp.ui.controller.ComponentController;
import ir.sk.jcg.jcgintellijpluginapp.ui.dto.ComponentDto;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 7/8/2016
 */
public class ComponentControllerImpl implements ComponentController {

    private static final ComponentController INSTANCE = new ComponentControllerImpl();

    public static ComponentController getInstance() {
        return INSTANCE;
    }

    @Override
    public Component createInputComponent(ComponentDto componentDto, View view) {
        Component component = componentDto.getInputComponentType().createComponent();

        component.setName(componentDto.getName());
        component.setTargetEntityElement(componentDto.getTargetEntityElement());

        view.addComponent(component);
        return component;
    }

    @Override
    public Component createOutputComponent(ComponentDto componentDto, ComponentContainer componentContainer) {
        Component component = componentDto.getOutputComponentType().createComponent();

        component.setName(componentDto.getName());
        component.setTargetEntityElement(componentDto.getTargetEntityElement());

        if (component instanceof Link) // TODO: 9/25/2017
            ((Link) component).setTargetView(componentDto.getTargetView());

        componentContainer.addComponent(component);
        return component;
    }
}
