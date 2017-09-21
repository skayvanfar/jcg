package ir.sk.jcg.lib.jcglibspringmvchandler.controller;

import ir.sk.jcg.jcglibcommon.persistence.PersistenceException;
import ir.sk.jcg.jcglibcommon.persistence.entity.Menu;
import ir.sk.jcg.jcglibcommon.persistence.entity.MenuService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 9/15/2017.
 */
public class BaseController {

    @Autowired
    private MenuService menuService;

    /**
     * Log variable for all child classes. Uses LogFactory.getLog(getClass()) from Commons Logging
     */
    private static final Logger logger = LoggerFactory.getLogger(GenericController.class);

    @ModelAttribute("menus")
    public List<Menu> getCategories() throws PersistenceException {
        List<Menu> menus = menuService.getHierarchyMenus(true);
        return menus;
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST,  reason="Illegal request, please verify your payload")
    public void handleClientErrors(Exception ex) { }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason="Internal server error")
    public void handleServerErrors(Exception ex) {	}
}
