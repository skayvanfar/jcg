package ir.sk.jcg.jcglibcommon.web;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 9/8/2017.
 */
public class SearchData<T> {

    protected T id;

    private int page;
    private int pageSize;

    public SearchData() {
        pageSize = 10;
    }

    public SearchData(int pageSize) {
        this.pageSize = pageSize;
    }

    public T getId() {
        return id;
    }

    public void setId(T id) {
        this.id = id;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public Map<String, String> getProperties(){
        try {
            return BeanUtils.describe(this);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Map<String, Object> getSearchParams() {
        Map<String, Object> fieldsMap = new HashMap<>();
        // Get all declared fields.
        Field[] fields = this.getClass().getDeclaredFields();
        for(Field field: fields){
            Object fieldValue = null;
            try {
                fieldValue = PropertyUtils.getProperty(this, field.getName());
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
            // If the field is annotated by @SearchParam
            if(field.isAnnotationPresent(SearchParam.class) && !(fieldValue instanceof Collection)) {
                fieldsMap.put(field.getName(), fieldValue);
                /*// Recursive call to check the nested fields of this field object.
                getSearchParams(
                        // We actually get the field object here.
                        field.get(someObject)
                );*/
            }
        }
        return fieldsMap;
    }

    public Map<String, Collection<SuggestionData>> getSearchParamsCollections() {
        Map<String, Collection<SuggestionData>> fieldsMap = new HashMap<>();
        // Get all declared fields.
        Field[] fields = this.getClass().getDeclaredFields();
        for(Field field: fields){
            Object fieldValue = null;
            try {
                fieldValue = PropertyUtils.getProperty(this, field.getName());
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
            // If the field is annotated by @SearchParam
            if(field.isAnnotationPresent(SearchParam.class) && fieldValue instanceof Collection) {
                fieldsMap.put(field.getName(), (Collection<SuggestionData>) fieldValue);
                /*// Recursive call to check the nested fields of this field object.
                getSearchParams(
                        // We actually get the field object here.
                        field.get(someObject)
                );*/
            }
        }
        return fieldsMap;
    }

}
