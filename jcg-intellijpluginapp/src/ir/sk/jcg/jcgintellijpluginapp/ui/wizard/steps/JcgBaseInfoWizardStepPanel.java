package ir.sk.jcg.jcgintellijpluginapp.ui.wizard.steps;

import com.intellij.openapi.ui.ComboBox;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.util.ui.JBUI;
import ir.sk.jcg.jcgengine.model.platform.architecture.ArchitectureType;

import javax.swing.*;
import java.awt.*;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 4/18/2016
 */
class JcgBaseInfoWizardStepPanel extends JPanel {

    private final java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("/messages/messages"); // NOI18N

    private JTextField projectNameField;
    private JTextField projectPersianNameField;
    private JTextField packagePrefixField;
    private JTextField tableNamePatternField;
    private JTextField configPackageField;
    private JComboBox<ArchitectureType> architectureComboBox;

    public JTextField getProjectNameField() {
        return projectNameField;
    }

    public void setProjectNameField(JTextField projectNameField) {
        this.projectNameField = projectNameField;
    }

    public JTextField getProjectPersianNameField() {
        return projectPersianNameField;
    }

    public void setProjectPersianNameField(JTextField projectPersianNameField) {
        this.projectPersianNameField = projectPersianNameField;
    }

    JTextField getPackagePrefixField() {
        return packagePrefixField;
    }

    public void setPackagePrefixField(JTextField packagePrefixField) {
        this.packagePrefixField = packagePrefixField;
    }

    public JTextField getTableNamePatternField() {
        return tableNamePatternField;
    }

    public void setTableNamePatternField(JTextField tableNamePatternField) {
        this.tableNamePatternField = tableNamePatternField;
    }

    JTextField getConfigPackageField() {
        return configPackageField;
    }

    public void setConfigPackageField(JTextField configPackageField) {
        this.configPackageField = configPackageField;
    }

    JComboBox<ArchitectureType> getArchitectureComboBox() {
        return architectureComboBox;
    }

    public void setArchitectureComboBox(JComboBox<ArchitectureType> architectureComboBox) {
        this.architectureComboBox = architectureComboBox;
    }

    //  private JPanel myArchetypesPanel;

    JcgBaseInfoWizardStepPanel() {
        initComponents();
    }

    private void initComponents() {
        setLayout(new GridLayoutManager(6, 3, JBUI.emptyInsets(), -1, -1));

        final JLabel projectNameLabel = new JLabel();
        projectNameLabel.setText("Project Name");
        add(projectNameLabel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel projectPersianNameLabel = new JLabel();
        projectPersianNameLabel.setText("Project Persian Name");
        add(projectPersianNameLabel, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel packagePrefixLabel = new JLabel();
        packagePrefixLabel.setText("Package Prefix");
        add(packagePrefixLabel, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));

        final JLabel tableNamePatternLabel = new JLabel();
        tableNamePatternLabel.setText("Table Name Pattern");
        add(tableNamePatternLabel, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));


        final JLabel configPackageLabel = new JLabel();
        configPackageLabel.setText("Config Package");
        add(configPackageLabel, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel architectureLabel = new JLabel();
        architectureLabel.setText("Architecture");
        add(architectureLabel, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));

        projectNameField = new JTextField();
        add(projectNameField, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        projectPersianNameField = new JTextField();
        add(projectPersianNameField, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        packagePrefixField = new JTextField("ir.sk");
        add(packagePrefixField, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));

        tableNamePatternField = new JTextField("tbl_");
        add(tableNamePatternField, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));

        configPackageField = new JTextField("configuration");
        add(configPackageField, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));


        DefaultComboBoxModel<ArchitectureType> comboBoxModel = new DefaultComboBoxModel<>();
        for (ArchitectureType architectureType : ArchitectureType.values())
            comboBoxModel.addElement(architectureType);
        architectureComboBox = new ComboBox<>();
        architectureComboBox.setModel(comboBoxModel);
        add(architectureComboBox, new GridConstraints(5, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));

        architectureComboBox.setEnabled(false); // TODO: 5/11/2016 for now but future must enabled
//        myArchetypesPanel = new JPanel();
//        myArchetypesPanel.setLayout(new BorderLayout(0, 0));
//        add(myArchetypesPanel, new GridConstraints(3, 0, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
    }

    @Override
    public String getName() {
        return "Jcg Project";
    }
}
