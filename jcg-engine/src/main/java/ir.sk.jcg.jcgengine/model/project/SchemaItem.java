package ir.sk.jcg.jcgengine.model.project;

import javax.xml.bind.annotation.XmlSeeAlso;
import java.io.Serializable;

/**
 * Items that can save in Schema
 *
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 5/2/2016
 */
@XmlSeeAlso({Entity.class, View.class})
public abstract class SchemaItem extends ModelElement implements Serializable {

    protected SchemaItem() {
    }
}
