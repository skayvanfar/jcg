package ir.sk.jcg.jcgcommon.enums;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * A generic adapter for use custom mapping enums to xml
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 9/20/2016
 */
public class EnumAdapter<T extends Enum> extends XmlAdapter<String, T> {

    private Class<T> clazz;
    private T defaultValue;

    public EnumAdapter(Class<T> clazz) {
        this(clazz, null);
    }

    public EnumAdapter(Class<T> clazz, T defaultValue) {
        this.clazz = clazz;
        this.defaultValue = defaultValue;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T unmarshal(String v) throws Exception {

        if(v == null || v.isEmpty())
            return defaultValue;

        return (T) Enum.valueOf(clazz, v);
    }

    @Override
    public String marshal(T v) throws Exception {
        if(v == defaultValue)
            return null;
        return v.name();
    }

}
