package ir.sk.jcg.lib.jcglibhibernatehandler.persistence.jpa.hibernateImpl;

import ir.sk.jcg.jcglibcommon.persistence.PersistenceException;
import ir.sk.jcg.jcglibcommon.persistence.entity.Menu;
import ir.sk.jcg.jcglibcommon.persistence.entity.MenuDAO;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 9/21/2017.
 */
@Repository
public class HibernateMenuDAO extends HibernateGenericDAO<Menu, Long> implements MenuDAO {

    public HibernateMenuDAO() {
        super(Menu.class);
    }

    @Override
    public List<Menu> getHierarchyMenus(boolean fetchChildren) throws PersistenceException {
        Criteria criteria = getSession().createCriteria(Menu.class).add(
                Restrictions.isNull("parentId"));
        List<Menu> retValue = (List<Menu>) criteria.list();
        if (fetchChildren)
            for (Menu menu : retValue)
            refreshChildren(menu);
        return retValue;
    }

    @Override
    public void refreshChildren(Menu menu) throws PersistenceException {
        if (menu.getChildren() != null) return;
        List<Menu> children = getChildMenus(menu.getId());
        if (!children.isEmpty())
            for (Menu child : children)
                refreshChildren(child);
        menu.setChildren(children);
    }

    @Override
    @Transactional(value = "db", propagation = Propagation.REQUIRED, rollbackFor = PersistenceException.class, readOnly = true)
    public List<Menu> getChildMenus(long parentId) throws PersistenceException {
        Criteria criteria = getSession().createCriteria(Menu.class).add(
                Restrictions.eq("parentId", parentId));
        return (List<Menu>) criteria.list();
    }
}
