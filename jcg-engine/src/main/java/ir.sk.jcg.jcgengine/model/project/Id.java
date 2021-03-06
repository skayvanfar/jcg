package ir.sk.jcg.jcgengine.model.project;

import ir.sk.jcg.jcgcommon.PropertyView.ComponentType;
import ir.sk.jcg.jcgcommon.PropertyView.annotation.Editable;
import ir.sk.jcg.jcgcommon.PropertyView.annotation.Prop;
import ir.sk.jcg.jcgengine.model.project.enums.IdGeneratorType;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlIDREF;
import java.io.Serializable;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 5/12/2016
 */
@Editable
public class Id extends Property implements Serializable {

    @Prop(label = "Id Generator Type", componentType = ComponentType.NON_EDITABLE_COMBO, editable = true, required = true)
    private IdGeneratorType idGeneratorType;

    // TODO: 9/30/2017 Bidirectional
    private Entity entity;

    public Id() {
        idGeneratorType = IdGeneratorType.NATIVE;
        name = "id";
    }

    public Entity getEntity() {
        return entity;
    }

    @XmlElement(name = "entity")
    @XmlIDREF
    public void setEntity(Entity entity) {
        this.entity = entity;
    }

    public IdGeneratorType getIdGeneratorType() {
        return idGeneratorType;
    }

    @XmlAttribute
    public void setIdGeneratorType(IdGeneratorType idGeneratorType) {
        this.idGeneratorType = idGeneratorType;
    }
}
