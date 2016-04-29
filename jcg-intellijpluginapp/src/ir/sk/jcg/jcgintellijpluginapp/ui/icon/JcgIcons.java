package ir.sk.jcg.jcgintellijpluginapp.ui.icon;

import com.intellij.openapi.util.IconLoader;

import javax.swing.*;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 4/18/2016
 */
public class JcgIcons {

    private static Icon load(String path) {
        return IconLoader.getIcon(path, JcgIcons.class);
    }

    public static final Icon JcgMedium = load("/images/jcgMedium.png");
    public static final Icon JcgLogo = load("/images/jcgSmall.png");
    public static final Icon JcgTreeToolWindow = load("/images/jcg13x13.png");

}
