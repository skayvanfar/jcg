package ir.sk.jcg.jcgintellijpluginapp;

import java.util.HashMap;
import java.util.Map;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 5/11/2016
 */
public class ApplicationContext {

    private static final ApplicationContext INSTANCE = new ApplicationContext();

    private Map<String, Object> map = new HashMap<>();

    public static ApplicationContext getInstance() { return INSTANCE; }

    public Map<String, Object> map() {
        return map;
    }

    public void setAttribute(String key , Object value) {
        map.put(key, value);
    }

    public Object getAttribute(String key) {
        return map.get(key);
    }
}
