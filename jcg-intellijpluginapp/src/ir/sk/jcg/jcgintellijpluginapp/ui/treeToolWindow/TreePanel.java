package ir.sk.jcg.jcgintellijpluginapp.ui.treeToolWindow;

import com.intellij.openapi.actionSystem.ActionGroup;
import com.intellij.openapi.actionSystem.ActionManager;
import com.intellij.openapi.actionSystem.ActionPlaces;
import com.intellij.openapi.ui.SimpleToolWindowPanel;
import com.intellij.ui.components.JBScrollPane;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentManager;
import com.intellij.ui.treeStructure.Tree;
import ir.sk.jcg.jcgengine.model.project.Project;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
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
        JBScrollPane jbScrollPane = new JBScrollPane(jcgTree);
        add(jbScrollPane);
        setToolbar(createToolBar());

    }

//    private final Icon rootIcon = IconLoader.findIcon("/icons/zookeeper_small.png");

    private JComponent createToolBar() {
        ActionGroup actionGroup = (ActionGroup) ActionManager.getInstance().getAction("ZK.Toolbar");
        String place = ActionPlaces.EDITOR_TOOLBAR;
        JPanel toolBarPanel = new JPanel(new GridLayout());
        toolBarPanel.add(ActionManager.getInstance().createActionToolbar(place, actionGroup, true).getComponent());
        return toolBarPanel;
    }

    public void initJcgTree() {

        DefaultMutableTreeNode top = new DefaultMutableTreeNode(jcgProject);


        //test
        DefaultMutableTreeNode top2 = new DefaultMutableTreeNode("project");
        top2.add(new DefaultMutableTreeNode("Entiry"));

        jcgTree = new Tree(top2);

//        if (this.curator != null) {
//            this.curator.close();
//        }
//        ZkConfigPersistence config = ZkConfigPersistence.getInstance(project);
//        if (config.isAvailable() && ruok(config.getFirstServer())) {
//            this.curator = CuratorFrameworkFactory.newClient(config.getZkUrl(), new ExponentialBackoffRetry(1000, 0, 1000));
//            curator.start();
//            this.fileSystem = new ZkVirtualFileSystem(curator, ZkConfigPersistence.getInstance(project).charset);
//        }
    }

}
