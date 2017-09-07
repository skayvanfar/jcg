package ir.sk.jcg.lib.jcglibspringmvchandler.web.securityFilter;

import org.springframework.core.convert.converter.Converter;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 1/7/2017
 */
public class HashedConverter <H extends Hashed> implements Converter<String, H> {

    private Class<H> clazz;

    public HashedConverter(Class<H> clazz)
    {
        this.clazz = clazz;
    }


    @Override
    public H convert(String s) {
        if(s == null || s.isEmpty())
            return null;
        try {
            H h = clazz.newInstance();
            h.setEncrypted(s);
            return h;
        }catch (Throwable th) {
            return null;
        }
    }
}
