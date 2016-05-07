package ir.sk.jcg.jcgintellijpluginapp.ui.action;

import com.intellij.openapi.actionSystem.AnAction;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 5/4/2016
 */
public abstract class NodeAction extends AnAction {

    protected String correctName(String nodeName) {
        if (nodeName.startsWith("/")) {
            nodeName = nodeName.substring(1);
        }
        if (nodeName.endsWith("/")) {
            nodeName = nodeName.substring(0, nodeName.length() - 1);
        }
        return nodeName;
    }
}
