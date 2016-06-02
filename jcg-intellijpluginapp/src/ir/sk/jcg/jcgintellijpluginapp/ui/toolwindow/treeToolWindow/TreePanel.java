package ir.sk.jcg.jcgintellijpluginapp.ui.toolwindow.treeToolWindow;

import com.intellij.ide.ui.customization.CustomizationUtil;
import com.intellij.openapi.actionSystem.ActionGroup;
import com.intellij.openapi.actionSystem.ActionManager;
import com.intellij.openapi.actionSystem.ActionPlaces;
import com.intellij.openapi.ui.SimpleToolWindowPanel;
import com.intellij.ui.components.JBScrollPane;
import com.intellij.ui.treeStructure.Tree;
import ir.sk.jcg.jcgengine.model.platform.architecture.Architecture;
import ir.sk.jcg.jcgengine.model.platform.technology.TechnologyHandler;
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
    private Architecture architecture;

    private TreeSelectionChangedListener treeSelectionChangedListener;

    public TreePanel(Project jcgProject, Architecture architecture) {
        super(true);
        this.jcgProject = jcgProject;
        this.architecture = architecture;

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
                } else if (treeNode.getUserObject() instanceof Schema) {
                    CustomizationUtil.installPopupHandler(jcgTree, "JCG.ModelOperationMenu", ActionPlaces.UNKNOWN);
                } else if (treeNode.getUserObject() instanceof Package) {
                    CustomizationUtil.installPopupHandler(jcgTree, "JCG.PackageOperationMenu", ActionPlaces.UNKNOWN);
                } else if (treeNode.getUserObject() instanceof Entity) {
                    CustomizationUtil.installPopupHandler(jcgTree, "JCG.EntityOperationMenu", ActionPlaces.UNKNOWN);
                } else if (treeNode.getUserObject() instanceof Relationship) {
                    CustomizationUtil.installPopupHandler(jcgTree, "JCG.RelationshipOperationMenu", ActionPlaces.UNKNOWN);
                } else if (treeNode.getUserObject() instanceof Property) {
                    CustomizationUtil.installPopupHandler(jcgTree, "JCG.PropertiesOperationMenu", ActionPlaces.UNKNOWN);
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
    public void initJcgTree() { // TODO: 5/12/2016 auto create tree
        DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode();

        DefaultMutableTreeNode projectNode = new DefaultMutableTreeNode(jcgProject);
        loadImplElements(jcgProject, projectNode);

        Schema<Entity> entitySchema = jcgProject.getEntitiesSchema();
        DefaultMutableTreeNode entityModelNode = new DefaultMutableTreeNode(entitySchema);
        loadImplElements(entitySchema, entityModelNode);
        for (ir.sk.jcg.jcgengine.model.project.Package<Entity> entityPackage : entitySchema.getPackages()) {
            DefaultMutableTreeNode packageNode = new DefaultMutableTreeNode(entityPackage);
            entityModelNode.add(packageNode);
            loadImplElements(entityPackage, packageNode);
            loadPackages(entityPackage, packageNode, true);
        }

        Schema<View> viewSchema = jcgProject.getViewsSchema();
        DefaultMutableTreeNode viewModelNode = new DefaultMutableTreeNode(viewSchema);
        loadImplElements(viewSchema, viewModelNode);
        for (ir.sk.jcg.jcgengine.model.project.Package<View> viewPackage : viewSchema.getPackages()) {
            DefaultMutableTreeNode packageNode = new DefaultMutableTreeNode(viewPackage);
            viewModelNode.add(packageNode);
            loadImplElements(viewPackage, entityModelNode);
            loadPackages(viewPackage, entityModelNode, false);
        }

        projectNode.add(entityModelNode);
        projectNode.add(viewModelNode);

        /// for architecture
        DefaultMutableTreeNode architectureNode = new DefaultMutableTreeNode(architecture);

        loadSpringTechnologyHandler(architecture, architectureNode);

        loadTechnologyHandlers(architecture, architectureNode);

        rootNode.add(projectNode);
        rootNode.add(architectureNode);

        jcgTree = new Tree(rootNode);
        jcgTree.setRootVisible(false);
    }

    /**
     * Load packages and entities recursive
     * */
    private <T extends SchemaItem> void loadPackages(Package<T> aPackage, DefaultMutableTreeNode parentNode, boolean hasEntity) { // TODO: 4/29/2016 must go to a util class
        for (T t : aPackage.getElements()) {
            DefaultMutableTreeNode tNode = new DefaultMutableTreeNode(t);
            parentNode.add(tNode);
            loadImplElements(t, tNode);
            if (hasEntity) {
                loadId((Entity) t, tNode);
                loadProperties((Entity) t, tNode);
                loadRelations((Entity) t, tNode);
            }
        }
        for (Package<T> bPackage : aPackage.getPackages()) {
            DefaultMutableTreeNode packageNode = new DefaultMutableTreeNode(bPackage);
            parentNode.add(packageNode);
            loadImplElements(bPackage, packageNode);
            loadPackages(bPackage, packageNode, hasEntity);
        }
    }

    private void loadImplElements(ModelElement modelElement, DefaultMutableTreeNode parentNode) {
        for (ImplElement implElement : modelElement.getImplElements()) {
            DefaultMutableTreeNode tNode = new DefaultMutableTreeNode(implElement);
            parentNode.add(tNode);
        }
    }

    private void loadId(Entity entity, DefaultMutableTreeNode parentNode) {
        DefaultMutableTreeNode nodeId = new DefaultMutableTreeNode(entity.getId());
        parentNode.add(nodeId);
    }

    private void loadProperties(Entity entity, DefaultMutableTreeNode parentNode) {
        for (Property property : entity.getProperties()) {
            DefaultMutableTreeNode tNode = new DefaultMutableTreeNode(property);
            parentNode.add(tNode);
        }
    }

    private void loadRelations(Entity entity, DefaultMutableTreeNode parentNode) {
        for (Relationship relationship : entity.getRelationships()) {
            DefaultMutableTreeNode tNode = new DefaultMutableTreeNode(relationship);
            DefaultMutableTreeNode targetEntityNode = new DefaultMutableTreeNode(relationship.getTargetEntity());
            tNode.add(targetEntityNode);
            parentNode.add(tNode);
        }
    }

    private void loadSpringTechnologyHandler(Architecture architecture, DefaultMutableTreeNode parentNode) {
        DefaultMutableTreeNode springTechnologyHandlerNode = new DefaultMutableTreeNode(architecture.getSpringHandler());
        parentNode.add(springTechnologyHandlerNode);
    }

    private void loadTechnologyHandlers(Architecture architecture, DefaultMutableTreeNode parentNode) {
        for (TechnologyHandler technologyHandler : architecture.getTechnologies()) {
            DefaultMutableTreeNode technologyHandlerNode = new DefaultMutableTreeNode(technologyHandler);
            parentNode.add(technologyHandlerNode);
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
