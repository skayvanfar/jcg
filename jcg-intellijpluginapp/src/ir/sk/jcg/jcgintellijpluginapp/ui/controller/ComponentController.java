package ir.sk.jcg.jcgintellijpluginapp.ui.controller;

import ir.sk.jcg.jcgengine.model.project.Component;
import ir.sk.jcg.jcgengine.model.project.ComponentContainer;
import ir.sk.jcg.jcgengine.model.project.DataGrid;
import ir.sk.jcg.jcgengine.model.project.View;
import ir.sk.jcg.jcgintellijpluginapp.ui.dto.ComponentDto;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 7/8/2016
 */
public interface ComponentController {

    /**
     * Create Component
     *
     * @param componentDto
     * @param view         that is parent
     */
    Component createInputComponent(ComponentDto componentDto, View view);

    Component createOutputComponent(ComponentDto componentDto, ComponentContainer componentContainer);
}
