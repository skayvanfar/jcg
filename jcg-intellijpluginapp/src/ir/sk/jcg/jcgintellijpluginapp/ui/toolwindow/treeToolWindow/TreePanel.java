package ir.sk.jcg.jcgintellijpluginapp.ui.toolwindow.treeToolWindow;

import com.intellij.ide.ui.customization.CustomizationUtil;
import com.intellij.openapi.actionSystem.ActionGroup;
import com.intellij.openapi.actionSystem.ActionManager;
import com.intellij.openapi.actionSystem.ActionPlaces;
import com.intellij.openapi.ui.SimpleToolWindowPanel;
import com.intellij.ui.components.JBScrollPane;
import com.intellij.ui.treeStructure.Tree;
import ir.sk.jcg.jcgcommon.util.SimpleNavigatorTreeUtil;
import ir.sk.jcg.jcgengine.model.project.*;
import ir.sk.jcg.jcgengine.model.project.Package;
import ir.sk.jcg.jcgintellijpluginapp.ui.listener.TreeSelectionChangedListener;

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

    private TreeSelectionChangedListener treeSelectionChangedListener;

    public TreePanel(Project jcgProject) {
        super(true);
        this.jcgProject = jcgProject;

        initJcgTree();

        ToolTipManager.sharedInstance().registerComponent(jcgTree);
        jcgTree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
     //   CustomizationUtil.installPopupHandler(jcgTree, "JCG.OperationMenu", ActionPlaces.UNKNOWN);
         jcgTree.setCellRenderer(new JcgTreeRenderer()); // TODO: 4/29/2016

        jcgTree.addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {

                DefaultMutableTreeNode treeNode = (DefaultMutableTreeNode) e.getPath().getLastPathComponent();
                if (treeSelectionChangedListener != null) {
                    treeSelectionChangedListener.selectionChanged(treeNode.getUserObject());
                }
                if (treeNode.isRoot()) {
                    CustomizationUtil.installPopupHandler(jcgTree, "JCG.ProjectOperationMenu", ActionPlaces.UNKNOWN);
                } else if (treeNode.getUserObject() instanceof Model) {
                    CustomizationUtil.installPopupHandler(jcgTree, "JCG.ModelOperationMenu", ActionPlaces.UNKNOWN);
                } else if (treeNode.getUserObject() instanceof Package) {
                    CustomizationUtil.installPopupHandler(jcgTree, "JCG.PackageOperationMenu", ActionPlaces.UNKNOWN);
                } else if (treeNode.getUserObject() instanceof Entity) {
                    CustomizationUtil.installPopupHandler(jcgTree, "JCG.EntityOperationMenu", ActionPlaces.UNKNOWN);
                } else if (treeNode.getUserObject() instanceof View) {
                    CustomizationUtil.installPopupHandler(jcgTree, "JCG.ViewOperationMenu", ActionPlaces.UNKNOWN);
                }
            }
        });

        JBScrollPane jbScrollPane = new JBScrollPane(jcgTree);
        add(jbScrollPane);
        setToolbar(createToolBar());

    }

    public void setTreeSelectionChangedListener(TreeSelectionChangedListener treeSelectionChangedListener) {
        this.treeSelectionChangedListener = treeSelectionChangedListener;
    }

    /**
     * update UI and expand selected node
     * */
    public void updateAndExpandUI(TreePath treePath) {


        jcgTree.updateUI();
        if (treePath != null) { // TODO: 5/6/2016 expand not work correctly 
            TreePath treePath1 = new TreePath(treePath.getPath());
          //  jcgTree.setSelectionPath(treePath1);
              //      jcgTree.scrollPathToVisible(treePath1);
     //       TreePath t =jcgTree.getSelectionPath();
       //     jcgTree.expandPath(new TreePath(treePath.getPath()));
            jcgTree.expandRow(1);
            jcgTree.expandRow(2);
//            SimpleNavigatorTreeUtil.expandOrCollapsToLevel(jcgTree, treePath, 3, true);
     //           SimpleNavigatorTreeUtil.expandOrCollapsToLevel(jcgTree, treePath1, 3, true);

        }
    }

   // public Tree getJcgTree() {
   //     return jcgTree;
  //  }
//    private final Icon rootIcon = IconLoader.findIcon("/icons/zookeeper_small.png");

    private JComponent createToolBar() {
        ActionGroup actionGroup = (ActionGroup) ActionManager.getInstance().getAction("JCG.TreeToolbar");
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

        Model<Entity> entityModel = jcgProject.getEntitiesModel();
        DefaultMutableTreeNode entityModelNode = new DefaultMutableTreeNode(entityModel);
        for (ir.sk.jcg.jcgengine.model.project.Package<Entity> entityPackage : entityModel.getPackages()) {
            DefaultMutableTreeNode packageNode = new DefaultMutableTreeNode(entityPackage);
            entityModelNode.add(packageNode);
            loadPackages(entityPackage, packageNode, true);
        }




        Model<View> viewModel = jcgProject.getViewsModel();
        DefaultMutableTreeNode viewModelNode = new DefaultMutableTreeNode(viewModel);
        for (ir.sk.jcg.jcgengine.model.project.Package<View> viewPackage : viewModel.getPackages()) {
            DefaultMutableTreeNode packageNode = new DefaultMutableTreeNode(viewPackage);
            viewModelNode.add(packageNode);
            loadPackages(viewPackage, packageNode, false);
        }

        projectNode.add(entityModelNode);
        projectNode.add(viewModelNode);

        jcgTree = new Tree(projectNode);

    }

    /**
     * Load packages and entities recursive
     * */
    private <T extends ModelElement> void loadPackages(Package<T> aPackage, DefaultMutableTreeNode parentNode, boolean hasEntity) { // TODO: 4/29/2016 must go to a util class
        for (T t : aPackage.getElements()) {
            DefaultMutableTreeNode tNode = new DefaultMutableTreeNode(t);
            parentNode.add(tNode);
            if (hasEntity)
                loadProperties((Entity) t, tNode);
        }
        for (Package<T> bPackage : aPackage.getPackages()) {
            DefaultMutableTreeNode packageNode = new DefaultMutableTreeNode(bPackage);
            parentNode.add(packageNode);
            loadPackages(bPackage, packageNode, hasEntity);
        }
    }

    private void loadProperties(Entity entity, DefaultMutableTreeNode parentNode) {
        for (Property property : entity.getProperties()) {
            DefaultMutableTreeNode tNode = new DefaultMutableTreeNode(property);
            parentNode.add(tNode);
        }
    }

    /**
     * Return user object from current selected node
     * */
    public Object currentSelectedNodeUserObject() {
        TreePath treePath = jcgTree.getSelectionPath();
        DefaultMutableTreeNode currentNode = (DefaultMutableTreeNode) treePath.getLastPathComponent();
        return currentNode.getUserObject();
    }

    public TreePath getSelectionPath() {
        return jcgTree.getSelectionPath();
    }

}