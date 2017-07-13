package ir.sk.jcg.jcgcommon.util;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 7/5/2017.
 */
public class FileUtils {

    /**
     * Get content of inputStream
     * @param inputStream
     * @return - content of inputStream
     */
    public static String getFile(InputStream inputStream) {

        String result = "";

        try {
            result = IOUtils.toString(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;

    }
}
