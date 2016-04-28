package ir.sk.jcg.jcgintellijpluginapp.ui.action;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.ui.treeStructure.Tree;
import ir.sk.jcg.jcgintellijpluginapp.ui.treeToolWindow.JcgProjectComponent;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 4/25/2016
 */
public class JcgTreeRefreshAction extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent anActionEvent) {
        JcgProjectComponent jcgProjectComponent = JcgProjectComponent.getInstance(anActionEvent.getProject());
//        Tree jcgTree = jcgProjectComponent.getJcgTree();
//        TreePath treePath = zkTree.getSelectionPath();
//        jcgTree.updateUI();
//        if (treePath != null) {
//            jcgTree.expandPath(treePath);
//        }

    }

}
