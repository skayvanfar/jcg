package ir.sk.jcg.jcgintellijpluginapp.ui.wizard.steps;

import com.intellij.openapi.ui.ComboBox;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import ir.sk.jcg.jcgengine.model.platform.architecture.Architecture;
import ir.sk.jcg.jcgengine.model.platform.architecture.ArchitectureType;
import ir.sk.jcg.jcgengine.model.platform.technology.TechnologyEnumBase;
import ir.sk.jcg.jcgengine.model.platform.technology.TechnologyType;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 4/19/2016
 */
public class JcgTechnologyWizardStepPanel extends JPanel {

    private final java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("/messages/messages"); // NOI18N

    private java.util.List<JComboBox<TechnologyEnumBase>> comboBoxList = new ArrayList<>();

    public JcgTechnologyWizardStepPanel() {
   //     initComponents(technologyTypes);
    }

    public List<JComboBox<TechnologyEnumBase>> getComboBoxList() {
        return comboBoxList;
    }

    public void initComponents(TechnologyType[] technologyTypes) {
        setLayout(new GridLayoutManager(4, 3, new Insets(0, 0, 0, 0), -1, -1));

        int x = 0;

        for (TechnologyType technologyType : technologyTypes) {
            final JLabel technologyTypeLabel = new JLabel();
            technologyTypeLabel.setText(technologyType.getDescription());
            add(technologyTypeLabel, new GridConstraints(x, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));

            DefaultComboBoxModel<TechnologyEnumBase> comboBoxModel = new DefaultComboBoxModel<>();

         //   for ( subTechnologies : technologyType.getSubTechnologyTypes())

            for (TechnologyEnumBase technologyEnumBase : technologyType.getSubTechnologyTypes())
                comboBoxModel.addElement(technologyEnumBase);
            JComboBox<TechnologyEnumBase> technologyComboBox = new ComboBox();
            technologyComboBox.setModel(comboBoxModel);
            add(technologyComboBox, new GridConstraints(x, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
            comboBoxList.add(technologyComboBox);
            x++;
        }
    }



    @Override
    public String getName() {
        return "Jcg Technology Project";
    }

}
