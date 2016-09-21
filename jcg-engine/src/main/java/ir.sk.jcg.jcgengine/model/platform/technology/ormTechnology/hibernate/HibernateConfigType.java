package ir.sk.jcg.jcgengine.model.platform.technology.ormTechnology.hibernate;

import ir.sk.jcg.jcgcommon.enums.EnumBase;
import ir.sk.jcg.jcgengine.model.platform.technology.SpringTechnology.SpringConfigType;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 5/20/2016
 */
//@XmlJavaTypeAdapter(HibernateConfigType.Adapter.class)
public enum HibernateConfigType implements EnumBase {

    SPRING_CONFIG(0, "SpringConfig"),
    CFG_XML(1, "cfg.xml file"),
    ORM(2, "orm file");

    private Integer value;
    private String desc;

    HibernateConfigType(Integer value, String desc) {
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

    public static HibernateConfigType valueOf(Integer type) {
        for (HibernateConfigType code : HibernateConfigType.values()) {
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
    /*public static class Adapter extends EnumAdapter<HibernateConfigType> {

        public Adapter() {
            super(MappingTypeEnum.class, ANNOTATION);
        }
    }*/
}
