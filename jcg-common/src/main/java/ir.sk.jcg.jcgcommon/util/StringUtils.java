package ir.sk.jcg.jcgcommon.util;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 5/12/2016
 */
public class StringUtils {

    public static String toCamelCase(String str) {
        return Character.toLowerCase(str.charAt(0)) + str.substring(1);
    }
}
