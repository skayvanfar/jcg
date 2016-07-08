package ir.sk.jcg.jcgintellijpluginapp.ui.action;

import com.intellij.openapi.ui.ComboBox;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import ir.sk.jcg.jcgengine.model.project.Entity;
import ir.sk.jcg.jcgengine.model.project.Property;
import ir.sk.jcg.jcgengine.model.project.enums.CardinalityType;
import ir.sk.jcg.jcgengine.model.project.enums.ComponentType;
import ir.sk.jcg.jcgengine.model.project.enums.ViewType;
import ir.sk.jcg.jcgintellijpluginapp.ui.dto.ComponentDto;
import ir.sk.jcg.jcgintellijpluginapp.ui.dto.ViewDto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 7/8/2016
 */
public class ComponentPanel extends JPanel {

    private String operationName;

    private ComponentDto componentDto;

    private final JLabel componentNameLabel;
    private JTextField  componentNameTextField;
    private final JLabel targetPropertyLabel;
    private ComboBox targetPropertyComboBox;
    private final JLabel componentTypeLabel;
    private ComboBox componentTypeComboBox;

    public ComponentPanel(java.util.List<Property> properties) {
        componentDto = new ComponentDto();
        setLayout(new GridLayoutManager(3, 2, new Insets(0, 0, 0, 0), -1, -1));

        componentNameLabel = new JLabel("Name :");
        add(componentNameLabel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        componentNameTextField = new JTextField();
        add(componentNameTextField, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));

        targetPropertyLabel = new JLabel("Target Property :");
        add(targetPropertyLabel, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        targetPropertyComboBox = new ComboBox();
        for (Property property : properties)
            targetPropertyComboBox.addItem(property);
        add(targetPropertyComboBox, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        targetPropertyComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                Property property = (Property) e.getItem();
                setStateOfCollectionComboBox();
            }
        });

        setStateOfCollectionComboBox();

        componentTypeLabel = new JLabel("Component Type :");
        add(componentTypeLabel, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        componentTypeComboBox = new ComboBox();
        for (ComponentType componentType : ComponentType.values())
            componentTypeComboBox.addItem(componentType);
        add(componentTypeComboBox, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
    }

    private void setStateOfCollectionComboBox() {
        Property property = (Property) targetPropertyComboBox.getSelectedItem();
        if (property.getLabelName() != null || !property.getLabelName().equals(""))
            componentNameTextField.setText(property.getLabelName());
        else if (property.getName() != null || !property.getName().equals(""))
            componentNameTextField.setText(property.getName());
    }

    public ComponentDto getComponentDto() {
        componentDto.setName(componentNameTextField.getName());
        componentDto.setTargetProperty((Property) targetPropertyComboBox.getSelectedItem());
        componentDto.setComponentType((ComponentType) componentTypeComboBox.getSelectedItem());
        return componentDto;
    }

    public void setComponentDto(ComponentDto componentDto) {
        componentNameTextField.setText(componentDto.getName());
        targetPropertyComboBox.setSelectedItem(componentDto.getTargetProperty());
        targetPropertyComboBox.setSelectedItem(componentDto.getComponentType());

        this.componentDto = componentDto;
    }
}
