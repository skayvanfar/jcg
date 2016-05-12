package ir.sk.jcg.jcgengine.model.project;

import javax.xml.bind.annotation.XmlSeeAlso;
import java.io.Serializable;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 5/2/2016
 */
@XmlSeeAlso({Entity.class, View.class})
public abstract class SchemaItem extends ModelElement implements Serializable {

    /**
     * Copy constructor
     * */
    public SchemaItem(SchemaItem anotherSchemaItem) {
        super(anotherSchemaItem);
    }

    protected SchemaItem() {
    }

}
