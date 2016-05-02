package ir.sk.jcg.jcgintellijpluginapp.ui.action;

import javax.swing.*;
import java.awt.*;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 4/30/2016
 */
public class CreatePackagePanel extends JPanel {

    private JLabel packageNameLabel;
    private JTextField  packageNameTextField;


    public CreatePackagePanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        packageNameLabel = new JLabel("Enter New package Name:");
        packageNameTextField = new JTextField();

        add(packageNameLabel);
        add(packageNameTextField);
    }

    @Override
    public Insets getInsets() {
        return new Insets(20,20,20,20);
    }

    public String getPackageName() {
        return packageNameTextField.getText();
    }
}
