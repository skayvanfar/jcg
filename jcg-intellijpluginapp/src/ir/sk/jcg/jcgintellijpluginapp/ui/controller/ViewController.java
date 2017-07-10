package ir.sk.jcg.jcgintellijpluginapp.ui.controller;

import ir.sk.jcg.jcgengine.model.project.Package;
import ir.sk.jcg.jcgengine.model.project.View;
import ir.sk.jcg.jcgintellijpluginapp.ui.dto.ViewDto;


/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 7/7/2016
 */
public interface ViewController {

    /**
     * Create View of a ViewDto
     *
     * @param viewDto
     * @param viewPackage that is parent
     */
    void createView(ViewDto viewDto, Package<View> viewPackage);

}
