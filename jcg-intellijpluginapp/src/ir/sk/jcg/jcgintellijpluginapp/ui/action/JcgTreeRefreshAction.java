package ir.sk.jcg.jcgintellijpluginapp.ui.action;

import com.intellij.openapi.actionSystem.AnActionEvent;
import ir.sk.jcg.jcgintellijpluginapp.ui.toolwindow.JcgProjectComponent;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 4/25/2016
 */
public class JcgTreeRefreshAction extends NodeAction {

    @Override
    public void actionPerformed(AnActionEvent anActionEvent) {
        JcgProjectComponent jcgProjectComponent = JcgProjectComponent.getInstance(anActionEvent.getProject());
        jcgProjectComponent.reloadJcgTree(jcgProjectComponent.getSelectionPath());
    }

}
