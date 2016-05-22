package ir.sk.jcg.jcgcommon.PropertyView.annotation;

import ir.sk.jcg.jcgcommon.PropertyView.ComponentType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Save info of editor for field
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 5/5/2016
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Prop {

    /**
     * label of property. if not specified default name of property selected
     * */
    String label() default "";

    /**
     * values for value of property that show in combobox
     * */
    String[] values() default "";

    /**
     * type of property
     * */
    ComponentType componentType() default ComponentType.DEFAULT;

    /**
     * is property editable
     * */
    boolean editable() default false;

    /**
     * is property editable in wizard
     * */
    boolean editableInWizard() default false;

    /**
     * Required or not required
     * */
    boolean required() default false;
}
