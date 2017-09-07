package ir.sk.jcg.lib.jcglibspringmvchandler.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 1/7/2017
 */
public class BaseController {

/*
    @Autowired
    MenuService menuService;

    @ModelAttribute("allMenus")
    public List<Menu> getMenus() throws PersistenceException {
        List<Menu> mainMenus = menuService.getMainMenus(true);
  //      List<Menu> filledCategories = new ArrayList<>();
//        for (Menu category : mainMenus) {
//          //  if (category.isActive() > 0) {
//          //      filledCategories.add(category);
//          //      continue;
//          //  }
//            ArtMartAccessCenter.getInstance().c().refreshChildren(category);
//            for (Category child : category.getChildren()) {
//                if (child.getCount() > 0) {
//                    filledCategories.add(category);
//                    break;
//                }
//            }
//        }
        return mainMenus;
    }
*/

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST,  reason="Illegal request, please verify your payload")
    public void handleClientErrors(Exception ex) { }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason="Internal server error")
    public void handleServerErrors(Exception ex) {	}
}
