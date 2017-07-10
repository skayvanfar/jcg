package ir.sk.jcg.jcgcommon.util;

import java.awt.*;
import java.io.IOException;
import java.net.URI;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> 11/27/2015
 */

public class DesktopUtil {

    public static void openDefaultBrowser(URI uri) throws IOException {
        if (Desktop.isDesktopSupported()) {
            Desktop.getDesktop().browse(uri);
        } else {
        }
    }

    public static void openDefaultMailClient(URI uri) throws IOException {
        if (Desktop.isDesktopSupported()) {
            Desktop.getDesktop().mail(uri);
        } else {
        }
    }
}
