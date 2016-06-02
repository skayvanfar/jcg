package ir.sk.jcg.jcgcommon.util;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.ObjectInput;
import java.net.URL;
import java.util.Set;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> 9/11/2015
 */
public class Utils {
    public static String getFileExtension(String name) {
        int pointIndex = name.lastIndexOf(".");

        if (pointIndex == -1) {
            return null;
        }

        if (pointIndex == name.length() - 1) {
            return null;
        }

        return name.substring(pointIndex + 1, name.length());
    }

    public static ImageIcon createIcon(String path) {
        URL url = System.class.getResource(path);

        if (url == null) {
            System.out.println("Unable to load image: " + path);
        }

        return new ImageIcon(url);
    }

    public static Font createFont(String path) {
        URL url = System.class.getResource(path);

        if (url == null) {
            System.out.println("Unable to load font: " + path);
        }

        Font font = null;
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, url.openStream());
        } catch (FontFormatException e) {
            System.err.println("Bad format in font file: " + path);
        } catch (IOException e) {
            System.out.println("Unable to read font file: " + path);
        }

        return font;
    }

    /**
     * Convert a array ofString to a String with separator
     * */
    public static String covertStringArrayToString(String[] array, char separator) {
        StringBuilder builder = new StringBuilder();
        for(String s : array) {
            builder.append(s).append(separator);
        }
        return (builder.deleteCharAt(builder.length() - 1)).toString();
    }

    /**
     * Convert a array of Objects to array of toString methods of those objects
     * */
    public static String[] convertObjectArrayToStringArray(Object[] objects) {
        String[] stringArray = new String[objects.length];
        for (int i = 0; i < objects.length; i++)
            stringArray[i] = objects[i].toString();
        return stringArray;
    }

    /**
     * Return a non repeated name
     * @param name
     * @param allNames
     * @return
     */
    public static String outputName(String name, Set<String> allNames) {
        if (!allNames.contains(name)) {
            return name;
        } else {
            if (name.lastIndexOf('-') != -1) {
                String firstPartName = name.substring(0, name.lastIndexOf('-'));
                int lastPartName = Integer.parseInt(name.substring(name.lastIndexOf('-') + 1)) + 1;
                return firstPartName + "-" + lastPartName;
            } else {
                return name + "-1";
            }

        }
    }

}
