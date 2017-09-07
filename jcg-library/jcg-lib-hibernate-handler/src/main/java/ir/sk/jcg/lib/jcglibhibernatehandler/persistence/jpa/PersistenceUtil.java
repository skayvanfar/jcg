package ir.sk.jcg.lib.jcglibhibernatehandler.persistence.jpa;

import ir.sk.jcg.jcglibcommon.persistence.BaseEntity;

import ir.sk.jcg.jcglibcommon.persistence.PersistenceException;
import ir.sk.jcg.jcglibcommon.persistence.PersistenceExceptionType;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.internal.CriteriaImpl;
import org.hibernate.transform.ResultTransformer;

import java.util.List;
import java.util.Set;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 1/13/2017.
 */
public class PersistenceUtil {

    /**
     * Retrieve data with specified offset and limit
     *
     * @param criteria basic criteria that has entity
     * @param startPage start page
     * @param pageSize count of records in a page
     * @param orders array from order columns
     * */
    protected <T> PagingDataList<T> paging(Criteria criteria, int startPage,
                                           int pageSize, Order... orders) {
        if (orders != null)
            for (Order order : orders)
                criteria.addOrder(order);
        if (pageSize > 0) {
            //retain old projections. we are overwriting that in row count calculations.
            ResultTransformer oldTransformer = null;
            Projection oldProjection = null;
            if (criteria instanceof CriteriaImpl) {
                oldTransformer = ((CriteriaImpl) criteria)
                        .getResultTransformer();
                oldProjection = ((CriteriaImpl) criteria).getProjection();
            }
            Number rowCount = (Number) criteria.setProjection(
                    Projections.rowCount()).uniqueResult();
            if (startPage * pageSize > rowCount.intValue())
                startPage = rowCount.intValue() / pageSize;
            criteria.setProjection(null);
            if (oldTransformer != null)
                criteria.setResultTransformer(oldTransformer);
            if (oldProjection != null)
                criteria.setProjection(oldProjection);
            criteria.setFirstResult(startPage * pageSize);
            criteria.setMaxResults(pageSize);
            List<T> cs = (List<T>) criteria.list();
            setDataAccess(cs);
            return new PagingDataList<T>(cs, rowCount.intValue(), startPage,
                    pageSize);
        } else { // get all
            List<T> cs = (List<T>) criteria.list();
            return new PagingDataList<T>(cs, cs.size(), 0, cs.size());
        }
    }

    @SuppressWarnings("rawtypes")
    protected void setDataAccess(List entities) {
    }

    protected void setDataAccess(Set entities) {
    }

    protected static String convertToSearch(String pattern,
                                            boolean replaceWhiteSpace) {
        if (pattern == null)
            return null;
        if (replaceWhiteSpace)
            pattern = pattern.trim().replaceAll("\\s+", "*");
        pattern = String.format("*%s*", pattern);
        pattern = pattern.replace('*', '%');
        pattern = pattern.replace('?', '_');
        pattern = pattern.replaceAll("%+", "%");
        return pattern;
    }

    /**
     * Check that entity has invalid property.
     *
     * @param entities
     * @exception PersistenceException
     */
    protected static void checkIsValid(BaseEntity ... entities)
            throws PersistenceException {
        if (entities == null || entities.length == 0) {
            throw new PersistenceException(PersistenceExceptionType.BadParameter);
        }
        for (BaseEntity e : entities) {
            String args = e.getInValidProperty();
            if (args != null) {
                throw new PersistenceException(PersistenceExceptionType.BadParameter, args);
            }
        }
    }
}
