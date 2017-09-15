package ir.sk.jcg.jcgcommon.util;

import com.google.common.base.Charsets;
import org.apache.commons.io.IOUtils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 7/5/2017.
 */
public class FileUtils {

    /**
     * Get content of inputStream
     * @param inputStream
     * @return - content of inputStream
     */
    public static String getFileContentByInputStream(InputStream inputStream) {

        String result = "";

        try {
            result = IOUtils.toString(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return result;

    }

    public static String getFileContentByPath(Path path) {
        String content = "";
        try {
            content = com.google.common.io.Files.toString(path.toFile(), Charsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }

    public static void writeToFile(String fileName, String content) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            bw.write(content);

            // no need to close it.
            //bw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
