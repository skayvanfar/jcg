package ir.sk.jcg.jcgintellijpluginapp.ui.treeToolWindow;

import javax.swing.event.TreeModelListener;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import java.beans.ConstructorProperties;
import java.io.File;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 4/29/2016
 */
public class JcgTreeModel extends DefaultTreeModel {

    public JcgTreeModel(TreeNode root) {
        super(root);

    }

//
//    @Override
//    public Object getRoot() {
//        return root;
//    }

    @Override
    public Object getChild(Object parent, int index) {
        File f = (File) parent;
        return f.listFiles()[index];
    }

    @Override
    public int getChildCount(Object parent) {
        File f = (File) parent;
        if (!f.isDirectory()) {
            return 0;
        } else {
            return f.list().length;
        }
    }

    @Override
    public boolean isLeaf(Object node) {
        File f = (File) node;
        return !f.isDirectory();
    }

    @Override
    public void valueForPathChanged(TreePath path, Object newValue) {
        // TODO: 4/29/2016  
    }

    @Override
    public int getIndexOfChild(Object parent, Object child) {
        return 0;
    }

    @Override
    public void addTreeModelListener(TreeModelListener l) {

    }

    @Override
    public void removeTreeModelListener(TreeModelListener l) {

    }
}
