package ir.sk.jcg.jcglibcommon.persistence.entity;

import ir.sk.jcg.jcglibcommon.persistence.GenericManager;
import ir.sk.jcg.jcglibcommon.persistence.PersistenceException;

import java.util.List;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 9/21/2017.
 */
public interface MenuService extends GenericManager<Menu, Long> {
    List<Menu> getHierarchyMenus(boolean fetchChildren) throws PersistenceException;
    void refreshChildren(Menu category) throws PersistenceException;
    List<Menu> getChildMenus(long parentId) throws PersistenceException;
}
