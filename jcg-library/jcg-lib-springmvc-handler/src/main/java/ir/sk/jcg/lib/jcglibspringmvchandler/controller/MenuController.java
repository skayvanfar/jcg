package ir.sk.jcg.lib.jcglibspringmvchandler.controller;

import ir.sk.jcg.jcglibcommon.persistence.entity.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 9/21/2017.
 */
@Controller
@RequestMapping("/menus")
public class MenuController {
    @Autowired
    private MenuService menuService;
}
