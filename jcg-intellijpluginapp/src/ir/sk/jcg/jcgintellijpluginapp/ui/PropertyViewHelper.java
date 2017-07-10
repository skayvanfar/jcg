package ir.sk.jcg.jcgintellijpluginapp.ui;

import com.intellij.openapi.ui.ComboBox;
import ir.sk.jcg.jcgcommon.PropertyView.PropertyInfo;

import javax.swing.*;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 5/10/2016
 */
public class PropertyViewHelper {

    public static JComponent getJComponentByPropertyField(PropertyInfo propertyInfo) {
        JComponent propertyFieldComponent;
        switch (propertyInfo.getComponentType()) {
            case NON_EDITABLE_COMBO:
                Class<? extends Enum> classType = (Class<? extends Enum>) propertyInfo.getTypeClass();
                Enum[] possibleValues = classType.getEnumConstants();
                ComboBox<Enum<?>> comboBox = new ComboBox<>();
                for (Enum<?> e : possibleValues) {
                    comboBox.addItem(e);
                }
                propertyFieldComponent = comboBox;
                break;
            case EDITABLE_COMBO:
                String[] values = propertyInfo.getValues();
                ComboBox<String> editableComboBox = new ComboBox<>();
                editableComboBox.setEditable(true);
                for (String value : values) {
                    editableComboBox.addItem(value);
                }
                propertyFieldComponent = editableComboBox;
                break;
            case BOOLEAN_CHECKBOX:
                propertyFieldComponent = new JCheckBox();
                break;
            case DEFAULT:
                JTextField textField = new JTextField();
                Object value = propertyInfo.getValue();
                if (value != null)
                    textField.setText((String) value);
                propertyFieldComponent = (textField);
                break;
            default:
                propertyFieldComponent = new JTextField();
        }
        if (!propertyInfo.isEditableInWizard())
            propertyFieldComponent.setEnabled(false);
        return propertyFieldComponent;
    }
}
