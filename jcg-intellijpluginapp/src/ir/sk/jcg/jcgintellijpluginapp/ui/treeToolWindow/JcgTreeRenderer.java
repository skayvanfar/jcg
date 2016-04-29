package ir.sk.jcg.jcgintellijpluginapp.ui.treeToolWindow;

import com.intellij.openapi.fileTypes.FileTypes;
import org.jdesktop.swingx.renderer.DefaultTreeRenderer;
import org.jdesktop.swingx.renderer.WrappingIconPanel;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 4/25/2016
 */
public class JcgTreeRenderer extends DefaultTreeRenderer {

    public JcgTreeRenderer() {

    }

    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus) {
        Component component = super.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);
        if (value instanceof DefaultMutableTreeNode && component instanceof WrappingIconPanel) {
            DefaultMutableTreeNode treeNode = (DefaultMutableTreeNode) value;
            WrappingIconPanel wrappingPanel = (WrappingIconPanel) component;
            Icon icon = null;
            if (treeNode.isRoot()) {
                icon = FileTypes.ARCHIVE.getIcon(); // TODO: 4/28/2016 must create icons
            } else if (treeNode.toString().equals("Entities")) {
                icon = FileTypes.ARCHIVE.getIcon();
            } else if (treeNode.toString().equals("Views")) {
                icon = FileTypes.PLAIN_TEXT.getIcon();
            } else if (treeNode.getParent().toString().equals("Entites")) {
                icon = FileTypes.ARCHIVE.getIcon();
            } else if (treeNode.getParent().toString().equals("Views")) {
                icon = FileTypes.UNKNOWN.getIcon();
            }
            wrappingPanel.setIcon(icon);
        }
        return component;
    }
}
