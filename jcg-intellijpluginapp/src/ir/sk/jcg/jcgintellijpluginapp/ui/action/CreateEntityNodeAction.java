package ir.sk.jcg.jcgintellijpluginapp.ui.action;

import com.intellij.ide.ui.customization.CustomizationUtil;
import com.intellij.openapi.actionSystem.ActionPlaces;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.ui.DialogBuilder;
import com.intellij.ui.treeStructure.Tree;
import ir.sk.jcg.jcgengine.Generator;
import ir.sk.jcg.jcgengine.model.project.Entity;
import ir.sk.jcg.jcgintellijpluginapp.ui.treeToolWindow.JcgProjectComponent;
import ir.sk.jcg.jcgintellijpluginapp.ui.treeToolWindow.TreePanel;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 4/28/2016
 */
public class CreateEntityNodeAction extends AnAction {
    @Override
    public void actionPerformed(AnActionEvent anActionEvent) {
        final DialogBuilder builder = new DialogBuilder(anActionEvent.getProject());
        builder.setTitle("Create Entity");


        builder.setOkOperation(new Runnable() {
            @Override
            public void run() {
                JcgProjectComponent jcgProjectComponent = JcgProjectComponent.getInstance(anActionEvent.getProject());
                TreePanel treePanel =jcgProjectComponent.getTreePanel(); // TODO: 4/28/2016 may beeter call a method on JcgProjectComponent
                Tree jcgTree = treePanel.getJcgTree();

                Generator generator = jcgProjectComponent.getGenerator();

                TreePath treePath = jcgTree.getSelectionPath();
                DefaultMutableTreeNode currentNode = (DefaultMutableTreeNode) treePath.getLastPathComponent();
                Object userObject = currentNode.getUserObject();

            }
        });

        builder.showModal(true);
    }

}
