package ir.sk.jcg.jcgengine.model.project.enums;

import ir.sk.jcg.jcgcommon.enums.EnumBase;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 5/7/2016
 */
//@XmlJavaTypeAdapter(MappingType.Adapter.class)
public enum MappingType implements EnumBase {

    ANNOTATION(0, "Annotation"),
    HBM_XML_FILE(1, "hbm xml file");

    private Integer value;
    private String desc;

    MappingType(Integer value, String desc) {
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

    public static MappingType valueOf(Integer type) {
        for (MappingType code : MappingType.values()) {
            if (type == code.getValue()) {
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
    /*public static class Adapter extends EnumAdapter<MappingType> {

        public Adapter() {
            super(MappingType.class, ANNOTATION);
        }
    }*/
}

