package ir.sk.jcg.jcglibcommon.util;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 1/7/2017
 */
public class SpringUtils {
    public static HttpSession session() {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        return attr.getRequest().getSession(true); // true == allow create
    }

    public static Object session(String name) {
        return session().getAttribute(name);
    }

    public static void session(String name, Object value) {
        session().setAttribute(name, value);
    }

}
