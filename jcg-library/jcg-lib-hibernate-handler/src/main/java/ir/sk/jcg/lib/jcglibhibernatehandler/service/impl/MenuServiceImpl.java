package ir.sk.jcg.lib.jcglibhibernatehandler.service.impl;

import ir.sk.jcg.jcglibcommon.persistence.PersistenceException;
import ir.sk.jcg.jcglibcommon.persistence.entity.Menu;
import ir.sk.jcg.jcglibcommon.persistence.entity.MenuDAO;
import ir.sk.jcg.jcglibcommon.persistence.entity.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 9/21/2017.
 */
@Service
public class MenuServiceImpl extends GenericManagerImpl<Menu, Long> implements MenuService {

    private MenuDAO menuDAO;

    @Autowired
    public MenuServiceImpl(MenuDAO menuDAO) {
        super(menuDAO);
        this.menuDAO = menuDAO;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = PersistenceException.class, readOnly = true)
    public List<Menu> getHierarchyMenus(boolean fetchChildren) throws PersistenceException {
        return menuDAO.getHierarchyMenus(fetchChildren);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = PersistenceException.class, readOnly = true)
    public void refreshChildren(Menu menu) throws PersistenceException {
        menuDAO.refreshChildren(menu);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = PersistenceException.class, readOnly = true)
    public List<Menu> getChildMenus(long parentId) throws PersistenceException {
        return menuDAO.getChildMenus(parentId);
    }

}
