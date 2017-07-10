package ir.sk.jcg.jcgcommon.util;

import ir.sk.jcg.jcgcommon.PropertyView.annotation.Prop;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 5/6/2016
 */
public class SerializationUtil {

    /**
     * This method makes a "deep clone" of any Java object it is given.
     */
    public static Object deepClone(Object object) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(object);
            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bais);
            return ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Create a shallow clone on object that and fill properties that has specified annotation
     *
     * @param object          real Object
     * @param annotationClass specified annotation
     */
    public static Object shallowCloneByAnnotation(Object object, Class<? extends Annotation> annotationClass) throws IllegalAccessException, InstantiationException, NoSuchFieldException {
        Object clonedObject = object.getClass().newInstance();
        java.util.List<Field> objectFields = ReflectionUtil.findFields(object.getClass(), Prop.class);
        for (Field field : objectFields) {
            field.setAccessible(true);
            field.set(clonedObject, field.get(object));
        }
        return clonedObject;
    }
}
