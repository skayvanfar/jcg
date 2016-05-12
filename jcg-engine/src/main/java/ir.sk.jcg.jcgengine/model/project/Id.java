package ir.sk.jcg.jcgengine.model.project;

import ir.sk.jcg.jcgcommon.PropertyView.annotation.Prop;
import ir.sk.jcg.jcgengine.model.project.enums.IdGeneratorType;

import java.io.Serializable;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 5/12/2016
 */
public class Id extends Property implements Serializable {

    @Prop(label = "Id Generator Type", required = true)
    IdGeneratorType idGeneratorType;

    public IdGeneratorType getIdGeneratorType() {
        return idGeneratorType;
    }

    public void setIdGeneratorType(IdGeneratorType idGeneratorType) {
        this.idGeneratorType = idGeneratorType;
    }
}
