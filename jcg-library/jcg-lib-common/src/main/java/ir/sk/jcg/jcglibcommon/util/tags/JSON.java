package ir.sk.jcg.jcglibcommon.util.tags;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 1/7/2017
 */
public class JSON {
    public static String json(Object object) {
        try {
            return new ObjectMapper().writeValueAsString(object);
        } catch (IOException ignored) {
            return "";
        }
    }
}
