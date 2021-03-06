package ir.sk.jcg.lib.jcglibhibernatehandler.persistence.jpa.hibernateImpl;



import ir.sk.jcg.jcglibcommon.persistence.GenericDAO;
import ir.sk.jcg.jcglibcommon.persistence.PersistenceException;
import ir.sk.jcg.jcglibcommon.web.Data;
import ir.sk.jcg.jcglibcommon.web.PagingDataList;
import ir.sk.jcg.jcglibcommon.web.SearchData;
import ir.sk.jcg.jcglibcommon.web.SuggestionData;
import ir.sk.jcg.lib.jcglibhibernatehandler.persistence.jpa.PersistenceUtil;

import org.hibernate.*;
import org.hibernate.criterion.*;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.*;

/**
 * This class serves as the Base class for all other DAOs - namely to hold
 * common CRUD methods that they might all use. You should only need to extend
 * this class when your require custom CRUD logic.
 * <p/>
 * <p>To register this class in your Spring context file, use the following XML.
 * <pre>
 *      &lt;bean id="fooDao" class="ir.sk.dao.hibernateImpl.HibernateGenericDAO"&gt;
 *          &lt;constructor-arg value="ir.sk.model.Foo"/&gt;
 *      &lt;/bean&gt;
 * </pre>
 *
 * @param <T>  a type variable
 * @param <PK> the primary key for that type
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 1/6/2017
 *         Updated by jgarcia: update hibernate3 to hibernate4
 */
public class HibernateGenericDAO<T, PK extends Serializable> extends PersistenceUtil implements GenericDAO<T, PK> {

    /**
     * Log variable for all child classes. Uses LogFactory.getLog(getClass()) from Commons Logging
     */

    //  private static final Logger logger = LoggerFactory.getLogger(HibernateGenericDAO.class);

    private Class<T> persistentClass;

    @Resource
    //  @Autowired
    private SessionFactory sessionFactory;

    /**
     * Constructor that takes in a class to see which type of entity to persist.
     * Use this constructor when subclassing.
     *
     * @param persistentClass the class type you'd like to persist
     */
    public HibernateGenericDAO(final Class<T> persistentClass) {
        this.persistentClass = persistentClass;
    }

    /**
     * Constructor that takes in a class and sessionFactory for easy creation of DAO.
     *
     //   * @param persistentClass the class type you'd like to persist
     //   * @param sessionFactory  the pre-configured Hibernate SessionFactory
     */
//    public HibernateGenericDAO(final Class<T> persistentClass, SessionFactory sessionFactory) {
//        this.persistentClass = persistentClass;
//        this.sessionFactory = sessionFactory;
//    }

    public SessionFactory getSessionFactory() {
        return this.sessionFactory;
    }

    public Session getSession() throws HibernateException {
        Session session = getSessionFactory().getCurrentSession();
        if (session == null) {
            session = getSessionFactory().openSession();
        }
        return session;
    }

    @Autowired
    @Required
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * Returns domain class
     *
     * @return Class<T>
     */
    @SuppressWarnings("unchecked")
    private Class<T> getPersistentClass() {
        if (persistentClass == null) {
            ParameterizedType thisType
                    = (ParameterizedType) getClass().getGenericSuperclass();
            this.persistentClass
                    = (Class<T>) thisType.getActualTypeArguments()[0];
        }
        return persistentClass;
    }

    private String getPersistentClassName() {
        return getPersistentClass().getName();
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public T get(PK id) {
        IdentifierLoadAccess byId = getSession().byId(persistentClass);
        T entity = (T) byId.load(id);

        if (entity == null) {
            //     logger.warn("Uh oh, '" + this.persistentClass + "' object with id '" + id + "' not found...");
            throw new ObjectRetrievalFailureException(this.persistentClass, id);
        }

        return entity;
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<T> getAll() {
        return getSession().createCriteria(persistentClass).list();
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<T> getAllDistinct() {
        Collection<T> result = new LinkedHashSet<>(getAll());
        return new ArrayList<>(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<T> getByIds(String property, String[] ids) {
        Criteria criteria = getSession().createCriteria(persistentClass);

        Criterion criterion = null;
        for (String id : ids) {
            if (criterion == null) {
                criterion = Restrictions.eq(property, Integer.valueOf(id));
            } else {
                criterion = Restrictions.or(criterion, Restrictions.eq(property, Integer.valueOf(id)));
            }
        }

        if (criterion != null) {
            criteria.add(criterion);
        }
        return criteria.list();
    }

    @Override
    public <V> T getObjectByPropertyEqualTo(String propertyName, V propertyValue) {
        return (T) getSession().createCriteria(persistentClass)
                .add(Restrictions.eq(propertyName, propertyValue))
                .setMaxResults(1)
                .uniqueResult();
    }

    @Override
    public <V> List<T> getByPropertyEqualTo(String propertyName, V propertyValue) {
        return getSession().createCriteria(persistentClass)
                .add(Restrictions.eq(propertyName, propertyValue))
                .list();
    }

    @Override
    public <V> List<T> getByProperties(String propertyName, V[] propertyValue) {
        Criteria criteria = getSession().createCriteria(persistentClass);
        Criterion criterion = null;
        for (Object aPropertyValue : propertyValue) {
            if (criterion == null) {
                criterion = Restrictions.eq(propertyName, aPropertyValue);
            } else {
                criterion = Restrictions.or(criterion, Restrictions.eq(propertyName, aPropertyValue));
            }
        }
        return criteria.add(criterion).list();
    }

    @Override
    public List<T> getByProperties(String propertyName, String[] propertyValue) {
        Criteria criteria = getSession().createCriteria(persistentClass);
        Criterion criterion = null;
        for (String aPropertyValue : propertyValue) {
            if (criterion == null) {
                criterion = Restrictions.eq(propertyName, aPropertyValue);
            } else {
                criterion = Restrictions.or(criterion, Restrictions.eq(propertyName, aPropertyValue));
            }
        }
        return criteria.add(criterion).list();
    }

    @Override
    public List<T> getByProperties(String[] propertyName, String[] propertyValue) {
        Criteria criteria = getSession().createCriteria(persistentClass);
        for (int i = 0; i < propertyName.length; ++i) {
            criteria.add(Restrictions.eq(propertyName[i], propertyValue[i]));
        }
        return criteria.list();
    }

    //////////////////////////////////////////////////
    @Override
    public <V> List<T> getByPropertiesLikeExact(String[] propertyName, V[] propertyValue) {
        Criteria criteria = getSession().createCriteria(persistentClass);
        for (int i = 0; i < propertyName.length; ++i) {
            criteria.add(Restrictions.like(propertyName[i], propertyValue[i]));
        }
        return criteria.list();
    }

    @Override
    public List<T> getByPropertiesLikeExact(String[] propertyName, String propertyValue) {
        Criteria criteria = getSession().createCriteria(persistentClass);
        for (int i = 0; i < propertyName.length; ++i) {
            criteria.add(Restrictions.like(propertyName[i], propertyValue));
        }
        return criteria.list();
    }

    @Override
    public <V> boolean existByProperty(String propertyName, V propertyValue) {
        return !Objects.isNull(getSession().createCriteria(persistentClass)
                .add(Restrictions.eq(propertyName, propertyValue))
                .setMaxResults(1)
                .uniqueResult());
    }

    @Override
    public <V extends Object> List<T> getByAscOrder(String propertyForOrder) {
        Criteria criteria = getSession().createCriteria(persistentClass);
        return criteria.addOrder(Order.asc(propertyForOrder)).list();
    }

    @Override
    public <V extends Object> List<T> getByDescOrder(String propertyForOrder) {
        Criteria criteria = getSession().createCriteria(persistentClass);
        return criteria.addOrder(Order.desc(propertyForOrder)).list();
    }

    @Override
    public <V extends Object> List<T> getByPageAndRow(int page, int row) {
        int firstRow = (page - 1) * row;
        Criteria criteria = getSession().createCriteria(persistentClass);
        criteria.setFirstResult(firstRow);
        criteria.setMaxResults(row);
        return criteria.list();
    }
//////////////////////////////////////////////////

    @Override
    public T getObjectByProperties(String[] propertyName, String[] propertyValue) {
        Criteria criteria = getSession().createCriteria(persistentClass);

        Criterion firstCriterion = Restrictions.eq(propertyName[0], propertyValue[0]);
        List<Criterion> criterions = new ArrayList<>();
        for (int i = 1; i < propertyName.length; i++) {
            Criterion criterion = Restrictions.eq(propertyName[i], propertyValue[i]);
            criterions.add(criterion);
        }

        for (Criterion aCriterion : criterions) {
            firstCriterion = Restrictions.or(firstCriterion, aCriterion);
        }

        criteria.add(firstCriterion);
        return (T) criteria.setMaxResults(1).uniqueResult();
    }

    @Override
    public <V> List<T> getByPropertyOfPropertyEqualTo(String property, String propertyOfProperty, V value) {
        return getSession().createCriteria(persistentClass)
                .createCriteria(property)
                .add(Restrictions.eq(propertyOfProperty, value))
                .list();
    }

    @Override
    public <V> T getObjectByPropertyOfPropertyEqualTo(String property, String propertyOfProperty, V value) {
        return (T) getSession().createCriteria(persistentClass)
                .createCriteria(property)
                .add(Restrictions.eq(propertyOfProperty, value))
                .setMaxResults(1)
                .uniqueResult();
    }

    @Override
    public <V> List<T> getByPropertyEqualToIgnoreCase(String propertyName, V propertyValue) {
        return getSession().createCriteria(persistentClass)
                .add(Restrictions.eq(propertyName, propertyValue).ignoreCase())
                .list();
    }

    @Override
    public <V> List<T> getByPropertyLessThanOrEqualTo(String propertyName, V propertyValue) {
        return getSession().createCriteria(persistentClass)
                .add(Restrictions.le(propertyName, propertyValue))
                .list();
    }

    @Override
    public <V> List<T> getByPropertyGreaterThanOrEqualTo(String propertyName, V propertyValue) {
        return getSession().createCriteria(persistentClass)
                .add(Restrictions.ge(propertyName, propertyValue))
                .list();
    }

    @Override
    public <V> List<T> getByPropertyLessThan(String propertyName, V propertyValue) {
        return getSession().createCriteria(persistentClass)
                .add(Restrictions.lt(propertyName, propertyValue))
                .list();
    }

    @Override
    public <V> List<T> getByPropertyGreaterThan(String propertyName, V propertyValue) {
        return getSession().createCriteria(persistentClass)
                .add(Restrictions.gt(propertyName, propertyValue))
                .list();
    }

    @Override
    public List<T> getByPropertyLikeAnyWhereMode(String propertyName, String propertyValue) {
        return getSession().createCriteria(persistentClass)
                .add(Restrictions.like(propertyName, propertyValue, MatchMode.ANYWHERE))
                .list();
    }

    @Override
    public List<T> getByPropertyLikeAnyWhereModeIgnoreCase(String propertyName, String propertyValue) {
        return getSession().createCriteria(persistentClass)
                .add(Restrictions.like(propertyName, propertyValue, MatchMode.ANYWHERE).ignoreCase())
                .list();
    }

    @Override
    public <V> T getObjectByPropertyEqualToIgnoreCase(String propertyName, V propertyValue) {
        return (T) getSession().createCriteria(persistentClass)
                .add(Restrictions.eq(propertyName, propertyValue).ignoreCase())
                .setMaxResults(1)
                .uniqueResult();
    }

    @Override
    public <V> List<T> getByProperties(String[] propertyName, V[] propertyValue) {
        Criteria criteria = getSession().createCriteria(persistentClass);
        for (int i = 0; i < propertyName.length; ++i) {
            criteria.add(Restrictions.eq(propertyName[i], propertyValue[i]));
        }
        return criteria.list();
    }

    @Override
    public <V> List<T> getByPropertiesWithDescOrder(String[] propertyName, V[] propertyValue, String propertyForOrder) {
        Criteria criteria = getSession().createCriteria(persistentClass);
        for (int i = 0; i < propertyName.length; ++i) {
            criteria.add(Restrictions.eq(propertyName[i], propertyValue[i]))
                    .addOrder(Order.desc(propertyForOrder));
        }
        return criteria.list();
    }

    @Override
    public <V> List<T> getByPropertiesWithAscOrder(String[] propertyName, V[] propertyValue, String propertyForOrder) {
        Criteria criteria = getSession().createCriteria(persistentClass);
        for (int i = 0; i < propertyName.length; ++i) {
            criteria.add(Restrictions.eq(propertyName[i], propertyValue[i]))
                    .addOrder(Order.asc(propertyForOrder));
        }
        return criteria.list();
    }

    @Override
    public <V> List<T> getByPropertyNotEqualTo(String propertyName, V propertyValue) {
        return getSession().createCriteria(persistentClass)
                .add(Restrictions.ne(propertyName, propertyValue))
                .list();
    }

    @Override
    public List<T> getByPropertyLikeExact(String propertyName, String propertyValue) {
        return getSession().createCriteria(persistentClass)
                .add(Restrictions.ilike(propertyName, propertyValue, MatchMode.EXACT))
                .list();
    }

    @Override
    public T getByPropertyLikeExactModeIgnoreCase(String propertyName, String propertyValue) {
        return (T) getSession().createCriteria(persistentClass)
                .add(Restrictions.like(propertyName, propertyValue, MatchMode.EXACT).ignoreCase())
                .setMaxResults(1)
                .uniqueResult();
    }

    @Override
    public T getByPropertyLikeModeIgnoreCase(String propertyName, String propertyValue) {
        return (T) getSession().createCriteria(persistentClass)
                .add(Restrictions.like(propertyName, propertyValue).ignoreCase())
                .setMaxResults(1)
                .uniqueResult();
    }

    @Override
    public List<T> getByPropertyLikeAnyWhereModeIgnoreCasePartially(String propertyName, String propertyValue) {
        return getSession().createCriteria(persistentClass)
                .add(Restrictions.like(propertyName, "%" + propertyValue + "%", MatchMode.ANYWHERE).ignoreCase())
                .list();
    }

    @Override
    public List<T> getByAssociatedProperty(String associatedProperty, String propertyName, Integer propertyValue) {
        return getSession().createCriteria(persistentClass)
                .createCriteria(associatedProperty)
                .add(Restrictions.eq(propertyName, propertyValue))
                .list();
    }

    @Override
    public void reattachToSession(T object) {
        Session session = getSession();

        try {
            session.merge(object);
            session.refresh(object, LockMode.NONE);

        } catch (HibernateException e) {

        }

    }

    @Override
    public void update(T object) {
        getSession().update(object);
    }

    @Override
    public void saveOrUpdate(T object) {
        getSession().saveOrUpdate(object);
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public T save(T object) {
        return (T) getSession().merge(object);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void remove(T object) {
        getSession().delete(object);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void remove(PK id) {
        Session session = getSession();
        IdentifierLoadAccess byId = session.byId(persistentClass);
        T entity = (T) byId.load(id);
        session.delete(entity);
    }

    @Override
    public void deleteAll() {
        getSession().createQuery("delete " + persistentClass)
                .executeUpdate();
    }

    @Override
    public long count() {
        return (Long) getSession()
                .createQuery("select count(*) from " + persistentClass)
                .uniqueResult();
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<T> findByNamedQuery(String queryName, Map<String, Object> queryParams) {
        Session sess = getSession();
        Query namedQuery = sess.getNamedQuery(queryName);
        for (String s : queryParams.keySet()) {
            Object val = queryParams.get(s);
            if (val instanceof Collection) {
                namedQuery.setParameterList(s, (Collection) val);
            } else {
                namedQuery.setParameter(s, val);
            }
        }
        return namedQuery.list();
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public boolean exists(PK id) {
        IdentifierLoadAccess byId = getSession().byId(persistentClass);
        T entity = (T) byId.load(id);
        return entity != null;
    }

    @Override
    public PagingDataList<T> search(SearchData searchData) throws PersistenceException {
        Map<String, Object> properties = searchData.getSearchParams();
        Criteria criteria = getSession().createCriteria(persistentClass);

        for (Map.Entry<String, Object> propertyEntry : properties.entrySet()) {
            if (propertyEntry.getValue() != null && !propertyEntry.getValue().equals(""))
                criteria.add(Restrictions.like(propertyEntry.getKey(), propertyEntry.getValue()));
        }

        Map<String, Object> SuggestionDataCollections = searchData.getSearchParamsCollections();
        for (Map.Entry<String, Object> propertyEntry : SuggestionDataCollections.entrySet()) {
            Collection<SuggestionData> suggestionDataCollection = ((Collection<SuggestionData>) propertyEntry.getValue());
            if (propertyEntry.getValue() != null && !(suggestionDataCollection.size() != 0)) {
                for (SuggestionData suggestionData : suggestionDataCollection) {
                    criteria.add(Restrictions.eq(persistentClass.getSimpleName() + '.' + propertyEntry.getKey(), suggestionData.getValue()));
                }
            }
        }

        return paging(criteria, searchData.getPage(), searchData.getPageSize(), Order.asc("id"));
    }

    @Override
    public Data getByOutputClass(String idName, PK idValue, Data data) {
        Set<String> propertyNames = data.getDisplayParamNames();
        ProjectionList projectionList = Projections.projectionList();
        propertyNames.stream().forEach(s -> projectionList.add(Projections.property(s), s));
        Criteria criteria = getSession().createCriteria(persistentClass)
                .setProjection(projectionList).setResultTransformer(Transformers.aliasToBean(data.getClass()));

        criteria.add(Restrictions.eq(idName, idValue));

        return (Data) criteria.uniqueResult();
    }

    @Override
    public PagingDataList<T> searchFilter(String propertyName, String propertyValue, int page, int pageSize)
            throws PersistenceException {
        Criteria criteria = getSession().createCriteria(persistentClass);
        if (propertyValue != null) {
            String search = convertToSearch(propertyValue, true);
            criteria.add(Restrictions.like(propertyName, search));
        }
        return paging(criteria, page, pageSize);
    }
}
