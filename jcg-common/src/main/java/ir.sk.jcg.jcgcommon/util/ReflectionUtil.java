package ir.sk.jcg.jcgcommon.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.*;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 5/5/2016
 */
public class ReflectionUtil {

    /**
     * @return null safe set
     */
    public static List<Field> findFields(Class<?> classs, Class<? extends Annotation> ann) {
        List<Field> list = new ArrayList<>();
        Class<?> c = classs;
        while (c != null) {
            for (Field field : c.getDeclaredFields()) {
                if (field.isAnnotationPresent(ann)) {
                    list.add(field);
                }
            }
            c = c.getSuperclass();
        }
        return list;
    }

    public static Field getFieldByName(String name, Object o) throws NoSuchFieldException {
        Class<?> c = o.getClass();
        while (c != null) {
            for (Field field : c.getDeclaredFields()) {
                if (field.getName().equals(name))
                    return field;
            }
            c = c.getSuperclass();
        }
        return null;
    }

    public static void printFieldNames(Object obj) {
        for(Field field : obj.getClass().getFields()) {
            System.out.println(field.getName());
        }
    }

//    public static Object runGetter(Field field, Object o)
//    {
//        // MZ: Find the correct method
//        for (Method method : o.getClass().getMethods())
//        {
//            if ((method.getName().startsWith("get")) && (method.getName().length() == (field.getName().length() + 3)))
//            {
//                if (method.getName().toLowerCase().endsWith(field.getName().toLowerCase()))
//                {
//                    // MZ: Method found, run it
//                    try
//                    {
//                        return method.invoke(o);
//                    }
//                    catch (IllegalAccessException e)
//                    {
//                        Logger.fatal("Could not determine method: " + method.getName());
//                    }
//                    catch (InvocationTargetException e)
//                    {
//                        Logger.fatal("Could not determine method: " + method.getName());
//                    } catch (InvocationTargetException e) {
//                        e.printStackTrace();
//                    }
//
//                }
//            }
//        }
//
//
//        return null;
//    }
}
