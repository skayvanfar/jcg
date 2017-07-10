package ir.sk.jcg.jcgintellijpluginapp.ui.controller.impl;

import ir.sk.jcg.jcgengine.model.project.Package;
import ir.sk.jcg.jcgengine.model.project.View;
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
    public void createView(ViewDto viewDto, Package<View> viewPackage) {
        View view = viewDto.getViewType().createView();

        view.setName(viewDto.getName());
        view.setTargetEntity(viewDto.getTargetEntity());

        viewPackage.addElement(view);
    }
}
