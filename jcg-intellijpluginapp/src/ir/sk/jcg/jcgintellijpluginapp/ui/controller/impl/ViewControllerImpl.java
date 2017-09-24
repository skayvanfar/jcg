package ir.sk.jcg.jcgintellijpluginapp.ui.controller.impl;

import ir.sk.jcg.jcgengine.model.project.*;
import ir.sk.jcg.jcgengine.model.project.Package;
import ir.sk.jcg.jcgengine.model.project.component.Label;
import ir.sk.jcg.jcgengine.model.project.component.TextField;
import ir.sk.jcg.jcgintellijpluginapp.ui.controller.ViewController;
import ir.sk.jcg.jcgintellijpluginapp.ui.dto.ViewDto;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 7/7/2016
 */
public class ViewControllerImpl implements ViewController {

    private static final ViewController INSTANCE = new ViewControllerImpl();

    public static ViewController getInstance() {
        return INSTANCE;
    }


    @Override
    public View createView(ViewDto viewDto, Package<View> viewPackage) {
        View view = viewDto.getViewType().createView();

        view.setName(viewDto.getName());
        view.setViewFileName(viewDto.getViewFileName());
     //   view.setTargetEntity(viewDto.getTargetEntity());
        viewDto.getTargetEntity().addView(view);

        if (view instanceof SearchView) { // TODO: 9/9/2017
            addDefaultComponents((SearchView) view);
        }

        viewPackage.addElement(view);
        return view;
    }

    private void addDefaultComponents(SearchView searchView) {
        for (Property property : searchView.getTargetEntity().getProperties()) {
            // TODO: 9/9/2017 must create a map between components and propety types like textField for String
            Component component = new TextField("20");
            component.setName(property.getLabelName());
            component.setTargetEntityElement(property);
            searchView.addComponent(component);
            // TODO: 9/9/2017 must add Label for DataGrid
            Component gridComponent = new Label();
            gridComponent.setName(property.getLabelName());
            gridComponent.setTargetEntityElement(property);
            searchView.getDataGrid().addComponent(gridComponent);
        }
    }
}
