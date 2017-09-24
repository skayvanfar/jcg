package ir.sk.jcg.jcgintellijpluginapp.ui.action;

import com.intellij.openapi.ui.ComboBox;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.util.ui.JBUI;
import ir.sk.jcg.jcgengine.model.project.EntityElement;
import ir.sk.jcg.jcgengine.model.project.Relationship;
import ir.sk.jcg.jcgengine.model.project.SearchView;
import ir.sk.jcg.jcgengine.model.project.View;
import ir.sk.jcg.jcgengine.model.project.component.Link;
import ir.sk.jcg.jcgengine.model.project.component.LinkList;
import ir.sk.jcg.jcgengine.model.project.enums.InputComponentType;
import ir.sk.jcg.jcgengine.model.project.enums.OutputComponentType;
import ir.sk.jcg.jcgintellijpluginapp.ui.dto.ComponentDto;

import javax.swing.*;
import java.awt.*;
import java.util.Set;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 7/8/2016
 */
class ComponentPanel extends JPanel {

    private String operationName;

    private ComponentDto componentDto;

    private JTextField componentNameTextField;
    private ComboBox<EntityElement> targetEntityElementComboBox;
    private ComboBox<Enum> componentTypeComboBox;
    private ComboBox<View> targetViewComboBox;

    private boolean isInput;

    ComponentPanel(Set<EntityElement> entityElements, boolean isInput) {
        componentDto = new ComponentDto();
        setLayout(new GridLayoutManager(4, 2, JBUI.emptyInsets(), -1, -1));

        JLabel componentNameLabel = new JLabel("Name :");
        add(componentNameLabel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        componentNameTextField = new JTextField();
        add(componentNameTextField, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));

        JLabel targetPropertyLabel = new JLabel("Target :");
        add(targetPropertyLabel, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        targetEntityElementComboBox = new ComboBox<>();
        for (EntityElement entityElement : entityElements)
            targetEntityElementComboBox.addItem(entityElement);
        add(targetEntityElementComboBox, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        targetEntityElementComboBox.addItemListener(e -> setStateOfCollectionComboBox());

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

        componentTypeComboBox.addItemListener(e -> {
            if (e.getItem() == OutputComponentType.LINK || e.getItem() == OutputComponentType.LINK_LIST) {
                EntityElement entityElement = (EntityElement) targetEntityElementComboBox.getSelectedItem();
                if (entityElement instanceof Relationship) {
                    Set<View> views = ((Relationship)entityElement).getTargetEntity().getDisplayViews();
                    targetViewComboBox.removeAllItems();
                    views.stream().forEach(view -> targetViewComboBox.addItem(view));
                } else {
                    throw new IllegalStateException("For a Link Component, EntityElement must be Relationship.");
                }
                setStateOfTargetViewComboBox(true);
            } else {
                targetViewComboBox.removeAllItems();
                setStateOfTargetViewComboBox(false);
            }
        });

        add(componentTypeComboBox, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));

        JLabel TargetViewLabel = new JLabel("Target View :");
        add(TargetViewLabel, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        targetViewComboBox = new ComboBox<>();
        setStateOfTargetViewComboBox(false);
        add(targetViewComboBox, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
    }

    private void setStateOfCollectionComboBox() {
        EntityElement entityElement = (EntityElement) targetEntityElementComboBox.getSelectedItem();
        if (entityElement.getLabelName() != null && !entityElement.getLabelName().equals(""))
            componentNameTextField.setText(entityElement.getLabelName());
        else if (entityElement.getName() != null && !entityElement.getName().equals(""))
            componentNameTextField.setText(entityElement.getName());
    }

    private void setStateOfTargetViewComboBox(boolean state) { // TODO: 9/24/2017 Hidden is better
       /* if (state) {
            for (CollectionType collectionType : CollectionType.values())
                if (collectionType != CollectionType.NOTHING)
                    collectionComboBox.addItem(collectionType);
            collectionComboBox.setEnabled(true);
        } else {
            collectionComboBox.addItem(CollectionType.NOTHING);
            collectionComboBox.setEnabled(false);
        }*/
        targetViewComboBox.setEnabled(state);
    }

    ComponentDto getComponentDto() {
        componentDto.setName(componentNameTextField.getText());
        componentDto.setTargetEntityElement((EntityElement) targetEntityElementComboBox.getSelectedItem());
        if (isInput)
            componentDto.setInputComponentType((InputComponentType) componentTypeComboBox.getSelectedItem());
        else
            componentDto.setOutputComponentType((OutputComponentType) componentTypeComboBox.getSelectedItem());
        return componentDto;
    }

    void setComponentDto(ComponentDto componentDto) {
        componentNameTextField.setText(componentDto.getName());
        targetEntityElementComboBox.setSelectedItem(componentDto.getTargetEntityElement());
        targetEntityElementComboBox.setSelectedItem(componentDto.getInputComponentType());

        this.componentDto = componentDto;
    }
}
