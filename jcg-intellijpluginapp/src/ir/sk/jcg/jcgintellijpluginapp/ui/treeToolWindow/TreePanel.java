package ir.sk.jcg.jcgintellijpluginapp.ui.treeToolWindow;

import com.intellij.ide.ui.customization.CustomizationUtil;
import com.intellij.openapi.actionSystem.ActionGroup;
import com.intellij.openapi.actionSystem.ActionManager;
import com.intellij.openapi.actionSystem.ActionPlaces;
import com.intellij.openapi.fileTypes.FileTypes;
import com.intellij.openapi.ui.SimpleToolWindowPanel;
import com.intellij.ui.components.JBScrollPane;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentManager;
import com.intellij.ui.treeStructure.Tree;
import ir.sk.jcg.jcgengine.model.project.Entity;
import ir.sk.jcg.jcgengine.model.project.Project;
import ir.sk.jcg.jcgengine.model.project.View;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;
import java.awt.*;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 4/24/2016
 */
public class TreePanel extends SimpleToolWindowPanel {

    private Tree jcgTree;
    private Project jcgProject;

    public TreePanel(Project jcgProject) {
        super(true);
        this.jcgProject = jcgProject;

        initJcgTree();

        ToolTipManager.sharedInstance().registerComponent(jcgTree);
        jcgTree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
     //   CustomizationUtil.installPopupHandler(jcgTree, "JCG.OperationMenu", ActionPlaces.UNKNOWN);
         jcgTree.setCellRenderer(new JcgTreeRenderer());

        jcgTree.addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                switch (e.getPath().getLastPathComponent().toString()) {
                    case  "Entities":
                        CustomizationUtil.installPopupHandler(jcgTree, "JCG.EntityOperationMenu", ActionPlaces.UNKNOWN);
                        break;
                    case "Views":
                        CustomizationUtil.installPopupHandler(jcgTree, "JCG.ViewOperationMenu", ActionPlaces.UNKNOWN);
                        break;
                    // TODO: 4/28/2016 others
                    default:
                        CustomizationUtil.installPopupHandler(jcgTree, "JCG.ProjectOperationMenu", ActionPlaces.UNKNOWN);

                }
            }
        });

        JBScrollPane jbScrollPane = new JBScrollPane(jcgTree);
        add(jbScrollPane);
        setToolbar(createToolBar());

    }

    public Tree getJcgTree() {
        return jcgTree;
    }
//    private final Icon rootIcon = IconLoader.findIcon("/icons/zookeeper_small.png");

    private JComponent createToolBar() {
        ActionGroup actionGroup = (ActionGroup) ActionManager.getInstance().getAction("JCG.Toolbar");
        String place = ActionPlaces.EDITOR_TOOLBAR;
        JPanel toolBarPanel = new JPanel(new GridLayout());
        toolBarPanel.add(ActionManager.getInstance().createActionToolbar(place, actionGroup, true).getComponent());
        return toolBarPanel;
    }

    /**
     * Full a tree of node from jcgProject
     * */
    public void initJcgTree() {
        DefaultMutableTreeNode projectNode = new DefaultMutableTreeNode(jcgProject);
        DefaultMutableTreeNode entitiesNode = new DefaultMutableTreeNode("Entities");
        DefaultMutableTreeNode viewsNode = new DefaultMutableTreeNode("Views");

        for (Entity entity : jcgProject.getEntities()) {
            entitiesNode.add(new DefaultMutableTreeNode(entity));
        }

        for (View view : jcgProject.getViews()) {
            entitiesNode.add(new DefaultMutableTreeNode(view));
        }

        projectNode.add(entitiesNode);
        projectNode.add(viewsNode);

        jcgTree = new Tree(projectNode);

    }

}
