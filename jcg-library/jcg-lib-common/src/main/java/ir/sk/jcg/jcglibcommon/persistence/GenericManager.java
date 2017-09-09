package ir.sk.jcg.jcglibcommon.persistence;

import ir.sk.jcg.jcglibcommon.persistence.PersistenceException;
import ir.sk.jcg.jcglibcommon.web.PagingDataList;
import ir.sk.jcg.jcglibcommon.web.SearchData;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Generic Manager that talks to GenericDAO to CRUD POJOs.
 *
 * <p>Extend this interface if you want typesafe (no casting necessary) managers
 * for your domain objects.
 *
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 1/6/2017.
 * @param <T> a type variable
 * @param <PK> the primary key for that type
 */
public interface GenericManager<T, PK extends Serializable> {

    /**
     * Generic method to get an object based on class and identifier. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if
     * nothing is found.
     *
     * @param id the identifier (primary key) of the object to get
     * @return a populated object
     * @see org.springframework.orm.ObjectRetrievalFailureException
     */
    T get(PK id);

    /**
     * Generic method used to get all objects of a particular type. This
     * is the same as lookup up all rows in a table.
     * @return List of populated objects
     */
    List<T> getAll();

    /**
     * Gets all records without duplicates.
     * <p>Note that if you use this method, it is imperative that your model
     * classes correctly implement the hashcode/equals methods</p>
     * @return List of populated objects
     */
    List<T> getAllDistinct();

    /**
     * Generic method to get all object based on class and identifiers. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if
     * nothing is found.
     *
     * @param property
     * @param ids the identifiers (primary keys) of the object to get
     * @return List of populated objects
     * @see org.springframework.orm.ObjectRetrievalFailureException
     */
    List<T> getByIds(String property, String[] ids);

    <V extends Object> T getObjectByPropertyEqualTo(String propertyName, V propertyValue) throws PersistenceException;

    <V extends Object> List<T> getByPropertyEqualTo(String propertyName, V propertyValue);

    <V extends Object> List<T> getByProperties(String propertyName, V[] propertyValue);

    List<T> getByProperties(String propertyName, String[] propertyValue);

    List<T> getByProperties(String[] propertyName, String[] propertyValue);

    T getObjectByProperties(String[] propertyName, String[] propertyValue);

    //
    <V> List<T> getByPropertiesLikeExact(String[] propertyName, V[] propertyValue);

    List<T> getByPropertiesLikeExact(String[] propertyName, String propertyValue);

    <V extends Object> boolean existByProperty(String propertyName, V propertyValue);

    <V extends Object> List<T> getByAscOrder(String propertyForOrder);
    <V extends Object> List<T> getByDescOrder(String propertyForOrder);
    <V extends Object> List<T> getByPageAndRow(int page, int row);
    //



    <V extends Object> List<T> getByPropertyOfPropertyEqualTo(String property, String propertyOfProperty, V value);

    <V extends Object> T getObjectByPropertyOfPropertyEqualTo(String property, String propertyOfProperty, V value);

    <V extends Object> List<T> getByPropertyEqualToIgnoreCase(String propertyName, V propertyValue);

    <V extends Object> List<T> getByPropertyLessThanOrEqualTo(String propertyName, V propertyValue);

    <V extends Object> List<T> getByPropertyGreaterThanOrEqualTo(String propertyName, V propertyValue);

    <V extends Object> List<T> getByPropertyLessThan(String propertyName, V propertyValue);

    <V extends Object> List<T> getByPropertyGreaterThan(String propertyName, V propertyValue);

    List<T> getByPropertyLikeAnyWhereMode(String propertyName, String propertyValue);

    List<T> getByPropertyLikeAnyWhereModeIgnoreCase(String propertyName, String propertyValue);

    <V extends Object> T getObjectByPropertyEqualToIgnoreCase(String propertyName, V propertyValue);

    <V extends Object> List<T> getByProperties(String[] propertyName, V[] propertyValue);

    <V extends Object> List<T> getByPropertiesWithDescOrder(String[] propertyName, V[] propertyValue, String propertyForOrder);

    <V extends Object> List<T> getByPropertiesWithAscOrder(String[] propertyName, V[] propertyValue, String propertyForOrder);

    <V extends Object> List<T> getByPropertyNotEqualTo(String propertyName, V propertyValue);

    List<T> getByPropertyLikeExact(String propertyName, String propertyValue);

    T getByPropertyLikeExactModeIgnoreCase(String propertyName, String propertyValue);

    T getByPropertyLikeModeIgnoreCase(String propertyName, String propertyValue);

    List<T> getByPropertyLikeAnyWhereModeIgnoreCasePartially(String propertyName, String propertyValue);

    List<T> getByAssociatedProperty(String associatedProperty, String propertyName, Integer propertyValue);

    /**
     * Generic method to save an object - handles both update and insert.
     * @param object the object to save
     * @return the persisted object
     */
    T save(T object);

    void reattachToSession(T object);

    void update(T object);

    void saveOrUpdate(T object);

//    void saveOrUpdateAll(Collection<T> coll);

    /**
     * Generic method to delete an object
     * @param object the object to remove
     */
    void remove(T object);

    /**
     * Generic method to delete an object
     * @param id the identifier (primary key) of the object to remove
     */
    void remove(PK id);

    void deleteAll();

    /**
     * Find a list of records by using a named query
     * @param queryName query name of the named query
     * @param queryParams a map of the query names and the values
     * @return a list of the records found
     */
    List<T> findByNamedQuery(String queryName, Map<String, Object> queryParams);

    long count();

    /**
     * Checks for existence of an object of type T using the id arg.
     * @param id the id of the entity
     * @return - true if it exists, false if it doesn't
     */
    boolean exists(PK id);

    PagingDataList<T> search(SearchData searchData) throws PersistenceException;
}
