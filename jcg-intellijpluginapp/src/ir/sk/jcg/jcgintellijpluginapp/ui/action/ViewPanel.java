package ir.sk.jcg.jcgintellijpluginapp.ui.action;

import com.intellij.openapi.ui.ComboBox;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.util.ui.JBUI;
import ir.sk.jcg.jcgengine.model.project.Entity;
import ir.sk.jcg.jcgengine.model.project.enums.ViewType;
import ir.sk.jcg.jcgintellijpluginapp.ui.dto.ViewDto;

import javax.swing.*;
import java.awt.*;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 7/7/2016
 */
class ViewPanel extends JPanel {
    private String operationName;

    private ViewDto viewDto;

    private JTextField viewNameTextField;
    private ComboBox<Entity> targetEntityComboBox;
    private ComboBox<ViewType> viewTypeComboBox;

    ViewPanel(java.util.List<Entity> entities) {
        viewDto = new ViewDto();
        setLayout(new GridLayoutManager(3, 2, JBUI.emptyInsets(), -1, -1));

        JLabel viewNameLabel = new JLabel("Name :");
        add(viewNameLabel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        viewNameTextField = new JTextField();
        add(viewNameTextField, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));

        JLabel targetEntityLabel = new JLabel("Target Entity :");
        add(targetEntityLabel, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        targetEntityComboBox = new ComboBox<>();
        for (Entity entity : entities)
            targetEntityComboBox.addItem(entity);
        add(targetEntityComboBox, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));

        JLabel viewTypeLabel = new JLabel("View Type :");
        add(viewTypeLabel, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        viewTypeComboBox = new ComboBox<>();
        for (ViewType viewType : ViewType.values())
            viewTypeComboBox.addItem(viewType);
        add(viewTypeComboBox, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
    }

    ViewDto getViewDto() {
        viewDto.setName(viewNameTextField.getText());
        viewDto.setTargetEntity((Entity) targetEntityComboBox.getSelectedItem());
        viewDto.setViewType((ViewType) viewTypeComboBox.getSelectedItem());
        return viewDto;
    }

    void setViewDto(ViewDto viewDto) {
        viewNameTextField.setText(viewDto.getName());
        targetEntityComboBox.setSelectedItem(viewDto.getTargetEntity());
        viewTypeComboBox.setSelectedItem(viewDto.getViewType());

        this.viewDto = viewDto;
    }
}
