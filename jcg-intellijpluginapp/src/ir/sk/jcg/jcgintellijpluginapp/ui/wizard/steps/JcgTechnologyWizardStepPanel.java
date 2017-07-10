package ir.sk.jcg.jcgintellijpluginapp.ui.wizard.steps;

import com.intellij.openapi.ui.ComboBox;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.util.ui.JBUI;
import ir.sk.jcg.jcgengine.model.platform.technology.TechnologyHandlerEnumBase;
import ir.sk.jcg.jcgengine.model.platform.technology.TechnologyHandlerType;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 4/19/2016
 */
class JcgTechnologyWizardStepPanel extends JPanel {

    private final java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("/messages/messages"); // NOI18N

    private java.util.List<JComboBox<TechnologyHandlerEnumBase>> comboBoxList = new ArrayList<>();

    JcgTechnologyWizardStepPanel() {
    }

    List<JComboBox<TechnologyHandlerEnumBase>> getComboBoxList() {
        return comboBoxList;
    }

    void initComponents(TechnologyHandlerType[] technologyHandlerTypes) {
        setLayout(new GridLayoutManager(4, 3, JBUI.emptyInsets(), -1, -1));

        int x = 0;

        for (TechnologyHandlerType technologyHandlerType : technologyHandlerTypes) {
            final JLabel technologyTypeLabel = new JLabel();
            technologyTypeLabel.setText(technologyHandlerType.getDescription());
            add(technologyTypeLabel, new GridConstraints(x, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));

            DefaultComboBoxModel<TechnologyHandlerEnumBase> comboBoxModel = new DefaultComboBoxModel<>();

            for (TechnologyHandlerEnumBase technologyHandlerEnumBase : technologyHandlerType.getSubTechnologyTypes())
                comboBoxModel.addElement(technologyHandlerEnumBase);
            ComboBox<TechnologyHandlerEnumBase> technologyComboBox = new ComboBox<>();
            technologyComboBox.setModel(comboBoxModel);
            add(technologyComboBox, new GridConstraints(x, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
            comboBoxList.add(technologyComboBox);
            x++;
        }
    }


    @Override
    public String getName() {
        return "Jcg TechnologyHandler Project";
    }

}
