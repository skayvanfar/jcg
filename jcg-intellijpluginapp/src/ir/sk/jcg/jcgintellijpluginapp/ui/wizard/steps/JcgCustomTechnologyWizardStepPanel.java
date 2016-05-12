package ir.sk.jcg.jcgintellijpluginapp.ui.wizard.steps;

import com.intellij.openapi.ui.ComboBox;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import ir.sk.jcg.jcgcommon.PropertyView.PropertyInfo;
import ir.sk.jcg.jcgintellijpluginapp.ui.PropertyViewHelper;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 5/10/2016
 */
public class JcgCustomTechnologyWizardStepPanel extends JPanel {

    private final java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("/messages/messages"); // NOI18N

    private Map<PropertyInfo, JComponent> componentsMap = new HashMap<>();
    private List<PropertyInfo> propertyInfos;

    public JcgCustomTechnologyWizardStepPanel() {
        setLayout(new GridLayoutManager(4, 3, new Insets(0, 0, 0, 0), -1, -1));
    }

    public List<PropertyInfo> getPropertyInfos() {
        return propertyInfos;
    }

    public void setPropertyInfos(List<PropertyInfo> propertyInfos) {
        this.propertyInfos = propertyInfos;
    }

    public void initComponents(List<PropertyInfo> propertyInfos) {
        this.propertyInfos = propertyInfos;
        int x = 0;
        for (PropertyInfo propertyInfo : propertyInfos) {
            final JLabel propertyFieldLabel = new JLabel(propertyInfo.getName());
            add(propertyFieldLabel, new GridConstraints(x, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
            JComponent propertyFieldComponent = PropertyViewHelper.getJComponentByPropertyField(propertyInfo);

            componentsMap.put(propertyInfo, propertyFieldComponent);
            add(propertyFieldComponent, new GridConstraints(x, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
            x++;
        }
    }

    public void setComponents() {
        for (PropertyInfo propertyInfo : componentsMap.keySet()) {
            JComponent component =componentsMap.get(propertyInfo);
            if (component instanceof JTextField)
                propertyInfo.setValue(((JTextField) component).getText());
            else if (component instanceof ComboBox)
                propertyInfo.setValue(((ComboBox) component).getSelectedItem().toString()); /// TODO: 5/10/2016
            else if (component instanceof JCheckBox)
                propertyInfo.setValue(((JCheckBox) component).getText()); /// TODO: 5/10/2016
            else
                propertyInfo.setValue(((JTextField) component).getText());
        }
    }

    @Override
    public String getName() {
        return "Jcg Technology Config";
    }
}
