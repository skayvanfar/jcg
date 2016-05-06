package ir.sk.jcg.jcgengine.model.project;

import javax.swing.*;
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
    CellType editor() default CellType.DEFAULT;
    String[] values() default "";
}
