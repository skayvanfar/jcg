package ir.sk.jcg.jcgintellijpluginapp.ui.action;

import com.intellij.openapi.ui.ComboBox;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import ir.sk.jcg.jcgengine.model.project.Entity;

import javax.swing.*;
import java.awt.*;
import java.util.*;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 5/12/2016
 */
public class NewRelationPanel extends JPanel { // TODO: 5/12/2016 may better extends CreateNewNodePanel

    private String operationName;

    private final JLabel relationNameLabel;
    private JTextField  relationNameTextField;
    private final JLabel targetEntityLabel;
    private ComboBox targetEntityComboBox;

    public NewRelationPanel(java.util.List<Entity> entities) {
        setLayout(new GridLayoutManager(2, 2, new Insets(0, 0, 0, 0), -1, -1));

        relationNameLabel = new JLabel("Relation Name :");
        add(relationNameLabel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        relationNameTextField = new JTextField();
        add(relationNameTextField, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        targetEntityLabel = new JLabel("Target Entity :");
        add(targetEntityLabel, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        targetEntityComboBox = new ComboBox();
        for (Entity entity : entities)
            targetEntityComboBox.addItem(entity);
        add(targetEntityComboBox, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));

    }
    public String getNodeName() {
        return relationNameTextField.getText();
    }

    public Entity getTargetEntitySelected() {
        return (Entity) targetEntityComboBox.getSelectedItem();
    }
}
