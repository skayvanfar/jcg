package ir.sk.jcg.jcgintellijpluginapp.ui.action;

import com.intellij.openapi.ui.ComboBox;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import ir.sk.jcg.jcgengine.model.project.Entity;
import ir.sk.jcg.jcgengine.model.project.enums.CardinalityType;
import ir.sk.jcg.jcgengine.model.project.enums.CollectionType;
import ir.sk.jcg.jcgengine.model.project.enums.DirectionalityType;
import ir.sk.jcg.jcgintellijpluginapp.ui.dto.RelationShipDto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 5/12/2016
 */
public class RelationshipPanel extends JPanel { // TODO: 5/12/2016 may better extends CreateNewNodePanel

    private String operationName;

    private RelationShipDto relationShipDto;

    private final JLabel relationNameLabel;
    private JTextField  relationNameTextField;
    private final JLabel CardinalityLabel;
    private ComboBox cardinalityComboBox;
    private final JLabel directionalityLabel;
    private ComboBox directionalityComboBox;
    private final JLabel targetEntityLabel;
    private ComboBox targetEntityComboBox;
    private final JLabel collectionLabel;
    private ComboBox collectionComboBox;

    public RelationshipPanel(java.util.List<Entity> entities) {
        relationShipDto = new RelationShipDto();
        setLayout(new GridLayoutManager(5, 2, new Insets(0, 0, 0, 0), -1, -1));

        relationNameLabel = new JLabel("Name :");
        add(relationNameLabel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        relationNameTextField = new JTextField();
        relationNameTextField.setEnabled(false); // TODO: 5/27/16
        add(relationNameTextField, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));

        CardinalityLabel = new JLabel("Cardinality Type :");
        add(CardinalityLabel, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        cardinalityComboBox = new ComboBox();
        for (CardinalityType type : CardinalityType.values())
            cardinalityComboBox.addItem(type);
        add(cardinalityComboBox, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        cardinalityComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                CardinalityType cardinalityType = (CardinalityType) e.getItem();
                if (cardinalityType.equals(CardinalityType.MANY_TO_MANY) ||
                        cardinalityType.equals(CardinalityType.ONE_TO_MANY) ||
                        (cardinalityType.equals(CardinalityType.Many_TO_ONE))) {
                    setStateOfCollectionComboBox(true);
                } else {
                    setStateOfCollectionComboBox(false);
                }
            }
        });

        directionalityLabel = new JLabel("Directionality :");
        add(directionalityLabel, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        directionalityComboBox = new ComboBox();
        for (DirectionalityType directionalityType : DirectionalityType.values())
            directionalityComboBox.addItem(directionalityType);
        add(directionalityComboBox, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));

        targetEntityLabel = new JLabel("Target Entity :");
        add(targetEntityLabel, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        targetEntityComboBox = new ComboBox();
        for (Entity entity : entities)
            targetEntityComboBox.addItem(entity);
        add(targetEntityComboBox, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));

        collectionLabel = new JLabel("Collection Type :");
        add(collectionLabel, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        collectionComboBox = new ComboBox();
        setStateOfCollectionComboBox(false);
//        for (CollectionType collectionType : CollectionType.values())
//            collectionTypeComboBox.addItem(collectionType);
        add(collectionComboBox, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
    }

    private void setStateOfCollectionComboBox(boolean state) {
        collectionComboBox.removeAllItems();
        if (state) {
            for (CollectionType collectionType : CollectionType.values())
                if (collectionType != CollectionType.NOTHING)
                    collectionComboBox.addItem(collectionType);
            collectionComboBox.setEnabled(true);
        } else {
            collectionComboBox.addItem(CollectionType.NOTHING);
            collectionComboBox.setEnabled(false);
        }
    }

//    public String getRelationshipName() {
//        return relationNameTextField.getText();
//    }
//
//    public void setRelationshipName(String relationshipName) {
//        relationNameTextField.setText(relationshipName);
//    }

//    public CardinalityType getCardinalityType() {
//        return (CardinalityType) cardinalityComboBox.getSelectedItem();
//    }
//
//    public void setCardinalityType(CardinalityType cardinalityType) {
//        cardinalityComboBox.setSelectedItem(cardinalityType);
//    }
//
//    public DirectionalityType getDirectionalityType() {
//        return (DirectionalityType) directionalityComboBox.getSelectedItem();
//    }
//
//    public void setDirectionalityType(DirectionalityType directionalityType) {
//        directionalityComboBox.setSelectedItem(directionalityType);
//    }
//
//    public Entity getTargetEntity() {
//        return (Entity) targetEntityComboBox.getSelectedItem();
//    }
//
//    public void setTargetEntity(Entity targetEntity) {
//        targetEntityComboBox.setSelectedItem(targetEntity);
//    }
//
//    public CollectionType getCollectionType() {
//        return (CollectionType) collectionComboBox.getSelectedItem();
//    }
//
//    public void setCollectionType(CollectionType collectionType) {
//        collectionComboBox.setSelectedItem(collectionType);
//    }

    public RelationShipDto getRelationShipDto() {
        relationShipDto.setCardinalityType((CardinalityType) cardinalityComboBox.getSelectedItem());
        relationShipDto.setDirectionalityType((DirectionalityType) directionalityComboBox.getSelectedItem());
        relationShipDto.setTargetEntity((Entity) targetEntityComboBox.getSelectedItem());
        relationShipDto.setCollectionType((CollectionType) collectionComboBox.getSelectedItem());
        return relationShipDto;
    }

    public void setRelationShipDto(RelationShipDto relationShipDto) {
        cardinalityComboBox.setSelectedItem(relationShipDto.getCardinalityType());
        directionalityComboBox.setSelectedItem(relationShipDto.getDirectionalityType());
        targetEntityComboBox.setSelectedItem(relationShipDto.getTargetEntity());
        collectionComboBox.setSelectedItem(relationShipDto.getCollectionType());

        this.relationShipDto = relationShipDto;
    }
}
