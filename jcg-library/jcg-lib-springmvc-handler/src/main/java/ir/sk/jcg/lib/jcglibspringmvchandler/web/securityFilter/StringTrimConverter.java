package ir.sk.jcg.lib.jcglibspringmvchandler.web.securityFilter;

import org.springframework.core.convert.converter.Converter;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 1/7/2017
 */
public class StringTrimConverter implements Converter<String, String> {
    @Override
    public String convert(String s) {
        s = s.trim();
        return s.isEmpty() ? null : s;
    }
}
