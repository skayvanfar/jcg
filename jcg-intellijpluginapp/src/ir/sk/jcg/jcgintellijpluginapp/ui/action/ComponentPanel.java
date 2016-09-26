package ir.sk.jcg.jcgintellijpluginapp.ui.action;

import com.intellij.openapi.ui.ComboBox;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.util.ui.JBUI;
import ir.sk.jcg.jcgengine.model.project.Property;
import ir.sk.jcg.jcgengine.model.project.enums.InputComponentType;
import ir.sk.jcg.jcgengine.model.project.enums.OutputComponentType;
import ir.sk.jcg.jcgintellijpluginapp.ui.dto.ComponentDto;

import javax.swing.*;
import java.awt.*;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 7/8/2016
 */
class ComponentPanel extends JPanel {

    private String operationName;

    private ComponentDto componentDto;

    private JTextField  componentNameTextField;
    private ComboBox<Property> targetPropertyComboBox;
    private ComboBox<Enum> componentTypeComboBox;

    private boolean isInput;

    ComponentPanel(java.util.List<Property> properties, boolean isInput) {
        componentDto = new ComponentDto();
        setLayout(new GridLayoutManager(3, 2, JBUI.emptyInsets(), -1, -1));

        JLabel componentNameLabel = new JLabel("Name :");
        add(componentNameLabel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        componentNameTextField = new JTextField();
        add(componentNameTextField, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));

        JLabel targetPropertyLabel = new JLabel("Target Property :");
        add(targetPropertyLabel, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        targetPropertyComboBox = new ComboBox<>();
        for (Property property : properties)
            targetPropertyComboBox.addItem(property);
        add(targetPropertyComboBox, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        targetPropertyComboBox.addItemListener(e -> setStateOfCollectionComboBox());

        setStateOfCollectionComboBox();

        JLabel componentTypeLabel = new JLabel("Component Type :");
        add(componentTypeLabel, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        componentTypeComboBox = new ComboBox<>();
        this.isInput = isInput;
        if (isInput)
            for (InputComponentType inputComponentType : InputComponentType.values())
                componentTypeComboBox.addItem(inputComponentType);
        else
            for (OutputComponentType outputComponentType : OutputComponentType.values())
                componentTypeComboBox.addItem(outputComponentType);
        add(componentTypeComboBox, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
    }

    private void setStateOfCollectionComboBox() {
        Property property = (Property) targetPropertyComboBox.getSelectedItem();
        if (property.getLabelName() != null && !property.getLabelName().equals(""))
            componentNameTextField.setText(property.getLabelName());
        else if (property.getName() != null && !property.getName().equals(""))
            componentNameTextField.setText(property.getName());
    }

    ComponentDto getComponentDto() {
        componentDto.setName(componentNameTextField.getText());
        componentDto.setTargetProperty((Property) targetPropertyComboBox.getSelectedItem());
        if (isInput)
            componentDto.setInputComponentType((InputComponentType) componentTypeComboBox.getSelectedItem());
        else
            componentDto.setOutputComponentType((OutputComponentType) componentTypeComboBox.getSelectedItem());
        return componentDto;
    }

    void setComponentDto(ComponentDto componentDto) {
        componentNameTextField.setText(componentDto.getName());
        targetPropertyComboBox.setSelectedItem(componentDto.getTargetProperty());
        targetPropertyComboBox.setSelectedItem(componentDto.getInputComponentType());

        this.componentDto = componentDto;
    }
}
