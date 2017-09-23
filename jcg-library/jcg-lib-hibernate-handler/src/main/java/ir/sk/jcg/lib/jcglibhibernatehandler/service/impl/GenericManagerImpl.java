package ir.sk.jcg.lib.jcglibhibernatehandler.service.impl;

import ir.sk.jcg.jcglibcommon.persistence.PersistenceException;
import ir.sk.jcg.jcglibcommon.web.DisplayData;
import ir.sk.jcg.jcglibcommon.web.PagingDataList;
import ir.sk.jcg.jcglibcommon.web.SearchData;
import ir.sk.jcg.jcglibcommon.persistence.GenericDAO;
import ir.sk.jcg.jcglibcommon.persistence.GenericManager;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * This class serves as the Base class for all other Managers - namely to hold
 * common CRUD methods that they might all use. You should only need to extend
 * this class when your require custom CRUD logic.
 * <p/>
 * <p>To register this class in your Spring context file, use the following XML.
 * <pre>
 *     &lt;bean id="userManager" class="net.luna.service.impl.GenericManagerImpl"&gt;
 *         &lt;constructor-arg&gt;
 *             &lt;bean class="net.luna.dao.hibernateImpl.HibernateGenericDAO"&gt;
 *                 &lt;constructor-arg value="net.luna.model.User"/&gt;
 *                 &lt;property name="sessionFactory" ref="sessionFactory"/&gt;
 *             &lt;/bean&gt;
 *         &lt;/constructor-arg&gt;
 *     &lt;/bean&gt;
 * </pre>
 * <p/>
 * <p>If you're using iBATIS instead of Hibernate, use:
 * <pre>
 *     &lt;bean id="userManager" class="net.luna.service.impl.GenericManagerImpl"&gt;
 *         &lt;constructor-arg&gt;
 *             &lt;bean class="net.luna.dao.ibatis.GenericDaoiBatis"&gt;
 *                 &lt;constructor-arg value="net.luna.model.User"/&gt;
 *                 &lt;property name="dataSource" ref="dataSource"/&gt;
 *                 &lt;property name="sqlMapClient" ref="sqlMapClient"/&gt;
 *             &lt;/bean&gt;
 *         &lt;/constructor-arg&gt;
 *     &lt;/bean&gt;
 * </pre>
 *
 * @param <T>  a type variable
 * @param <PK> the primary key for that type
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 1/6/2017.
 */
@Transactional
public class GenericManagerImpl<T, PK extends Serializable> implements GenericManager<T, PK> {

    /**
     * Log variable for all child classes. Uses LogFactory.getLog(getClass()) from Commons Logging
     */
    protected final Log log = LogFactory.getLog(getClass());

    /**
     * GenericDAO instance, set by constructor of child classes
     */
    protected GenericDAO<T, PK> dao;


    public GenericManagerImpl() {
    }

    public GenericManagerImpl(GenericDAO<T, PK> genericDAO) {
        this.dao = genericDAO;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T get(PK id) {
        return dao.get(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<T> getAll() {
        return dao.getAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<T> getAllDistinct() {
        return dao.getAllDistinct();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<T> getByIds(String property, String[] ids) {
        return dao.getByIds(property, ids);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <V extends Object> T getObjectByPropertyEqualTo(String propertyName, V propertyValue) {
        return dao.getObjectByPropertyEqualTo(propertyName, propertyValue);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <V extends Object> List<T> getByPropertyEqualTo(String propertyName, V propertyValue) {
        return dao.getByPropertyEqualTo(propertyName, propertyValue);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <V extends Object> List<T> getByProperties(String propertyName, V[] propertyValue) {
        return dao.getByProperties(propertyName, propertyValue);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<T> getByProperties(String propertyName, String[] propertyValue) {
        return dao.getByProperties(propertyName, propertyValue);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<T> getByProperties(String[] propertyName, String[] propertyValue) {
        return dao.getByProperties(propertyName, propertyValue);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T getObjectByProperties(String[] propertyName, String[] propertyValue) {
        return dao.getObjectByProperties(propertyName, propertyValue);
    }
//

    /**
     * {@inheritDoc}
     */
    @Override
    public <V> List<T> getByPropertiesLikeExact(String[] propertyName, V[] propertyValue) {
        return dao.getByPropertiesLikeExact(propertyName, propertyValue);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<T> getByPropertiesLikeExact(String[] propertyName, String propertyValue) {
        return dao.getByPropertiesLikeExact(propertyName, propertyValue);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <V extends Object> boolean existByProperty(String propertyName, V propertyValue) {
        return dao.existByProperty(propertyName, propertyValue);
    }

    @Override
    public <V extends Object> List<T> getByAscOrder(String propertyForOrder) {
        return dao.getByAscOrder(propertyForOrder);
    }

    @Override
    public <V extends Object> List<T> getByDescOrder(String propertyForOrder) {
        return dao.getByDescOrder(propertyForOrder);
    }

    @Override
    public <V extends Object> List<T> getByPageAndRow(int page, int row) {
        return dao.getByPageAndRow(page, row);
    }
    //


    /**
     * {@inheritDoc}
     */
    @Override
    public <V extends Object> List<T> getByPropertyOfPropertyEqualTo(String property, String propertyOfProperty, V value) {
        return dao.getByPropertyOfPropertyEqualTo(property, propertyOfProperty, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <V extends Object> T getObjectByPropertyOfPropertyEqualTo(String property, String propertyOfProperty, V value) {
        return dao.getObjectByPropertyOfPropertyEqualTo(property, propertyOfProperty, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <V extends Object> List<T> getByPropertyEqualToIgnoreCase(String propertyName, V propertyValue) {
        return dao.getByPropertyEqualToIgnoreCase(propertyName, propertyValue);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <V extends Object> List<T> getByPropertyLessThanOrEqualTo(String propertyName, V propertyValue) {
        return dao.getByPropertyLessThanOrEqualTo(propertyName, propertyValue);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <V extends Object> List<T> getByPropertyGreaterThanOrEqualTo(String propertyName, V propertyValue) {
        return dao.getByPropertyGreaterThanOrEqualTo(propertyName, propertyValue);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <V extends Object> List<T> getByPropertyLessThan(String propertyName, V propertyValue) {
        return dao.getByPropertyLessThan(propertyName, propertyValue);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <V extends Object> List<T> getByPropertyGreaterThan(String propertyName, V propertyValue) {
        return dao.getByPropertyGreaterThan(propertyName, propertyValue);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<T> getByPropertyLikeAnyWhereMode(String propertyName, String propertyValue) {
        return dao.getByPropertyLikeAnyWhereMode(propertyName, propertyValue);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<T> getByPropertyLikeAnyWhereModeIgnoreCase(String propertyName, String propertyValue) {
        return dao.getByPropertyLikeAnyWhereModeIgnoreCase(propertyName, propertyValue);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <V extends Object> T getObjectByPropertyEqualToIgnoreCase(String propertyName, V propertyValue) {
        return dao.getObjectByPropertyEqualToIgnoreCase(propertyName, propertyValue);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <V extends Object> List<T> getByProperties(String[] propertyName, V[] propertyValue) {
        return dao.getByProperties(propertyName, propertyValue);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <V extends Object> List<T> getByPropertiesWithDescOrder(String[] propertyName, V[] propertyValue, String propertyForOrder) {
        return dao.getByPropertiesWithDescOrder(propertyName, propertyValue, propertyForOrder);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <V extends Object> List<T> getByPropertiesWithAscOrder(String[] propertyName, V[] propertyValue, String propertyForOrder) {
        return dao.getByPropertiesWithAscOrder(propertyName, propertyValue, propertyForOrder);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <V extends Object> List<T> getByPropertyNotEqualTo(String propertyName, V propertyValue) {
        return dao.getByPropertyNotEqualTo(propertyName, propertyValue);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<T> getByPropertyLikeExact(String propertyName, String propertyValue) {
        return dao.getByPropertyLikeExact(propertyName, propertyValue);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T getByPropertyLikeExactModeIgnoreCase(String propertyName, String propertyValue) {
        return dao.getByPropertyLikeExactModeIgnoreCase(propertyName, propertyValue);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T getByPropertyLikeModeIgnoreCase(String propertyName, String propertyValue){
        return dao.getByPropertyLikeModeIgnoreCase(propertyName, propertyValue);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<T> getByPropertyLikeAnyWhereModeIgnoreCasePartially(String propertyName, String propertyValue) {
        return dao.getByPropertyLikeAnyWhereModeIgnoreCasePartially(propertyName, propertyValue);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<T> getByAssociatedProperty(String associatedProperty, String propertyName, Integer propertyValue) {
        return dao.getByAssociatedProperty(associatedProperty, propertyName, propertyValue);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T save(T object) {
        return dao.save(object);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void reattachToSession(T object) {
        dao.reattachToSession(object);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(T object) {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void saveOrUpdate(T object) {
        dao.saveOrUpdate(object);
    }

//    void saveOrUpdateAll(Collection<T> coll);

    /**
     * {@inheritDoc}
     */
    @Override
    public void remove(T object) {
        dao.remove(object);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void remove(PK id) {
        dao.remove(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteAll() {
        dao.deleteAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<T> findByNamedQuery(String queryName, Map<String, Object> queryParams) {
        return dao.findByNamedQuery(queryName, queryParams);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public long count() {
        return dao.count();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean exists(PK id) {
        return dao.exists(id);
    }

    @Override
    public PagingDataList<T> search(SearchData searchData) throws PersistenceException {
        return dao.search(searchData);
    }

    @Override
    public DisplayData getByOutputClass(String idName, PK id, DisplayData displayData) {
        return dao.getByOutputClass(idName, id, displayData);
    }
}
