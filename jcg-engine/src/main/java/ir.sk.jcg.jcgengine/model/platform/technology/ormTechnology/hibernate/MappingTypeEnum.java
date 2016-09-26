package ir.sk.jcg.jcgengine.model.platform.technology.ormTechnology.hibernate;

import ir.sk.jcg.jcgcommon.enums.EnumBase;

import java.util.Objects;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 5/7/2016
 */
//@XmlJavaTypeAdapter(MappingTypeEnum.Adapter.class)
enum MappingTypeEnum implements EnumBase {

    ANNOTATION(0, "Annotation"),
    HBM_XML_FILE(1, "hbm xml file");

    private Integer value;
    private String desc;

    MappingTypeEnum(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    @Override
    public Integer getValue() {
        return value;
    }

    @Override
    public String getDescription() {
        return desc;
    }

    public static MappingTypeEnum valueOf(Integer type) {
        for (MappingTypeEnum code : MappingTypeEnum.values()) {
            if (Objects.equals(type, code.getValue())) {
                return code;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return desc;
    }

    // for use custom mapping
    /*public static class Adapter extends EnumAdapter<MappingTypeEnum> {

        public Adapter() {
            super(MappingTypeEnum.class, ANNOTATION);
        }
    }*/
}

