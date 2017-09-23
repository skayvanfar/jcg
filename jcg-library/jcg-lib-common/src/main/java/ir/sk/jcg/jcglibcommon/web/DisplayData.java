package ir.sk.jcg.jcglibcommon.web;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 9/23/2017.
 */
public class DisplayData {

    public Set<String> getDisplayParamNames() {
        Set<String> fieldNames = new HashSet<>();
        // Get all declared fields.
        Field[] fields = this.getClass().getDeclaredFields();
        for(Field field: fields){
            // If the field is annotated by @SearchParam
            if(field.isAnnotationPresent(SearchParam.class)) {
                fieldNames.add(field.getName());
                /*// Recursive call to check the nested fields of this field object.
                getSearchParams(
                        // We actually get the field object here.
                        field.get(someObject)
                );*/
            }
        }
        return fieldNames;
    }
}
