package ir.sk.jcg.jcgintellijpluginapp.ui.action;

import com.intellij.openapi.actionSystem.AnActionEvent;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 4/25/2016
 */
public class JcgTreeRefreshAction extends NodeAction {

    public JcgTreeRefreshAction() {
        super("Refresh JcgTree");
    }

    @Override
    public void actionPerformed(AnActionEvent e) {
        super.actionPerformed(e);
        jcgProjectComponent.reloadJcgTree(jcgProjectComponent.getSelectionPath());
    }

}
