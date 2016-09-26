package ir.sk.jcg.jcgintellijpluginapp.ui.action;

import com.intellij.util.ui.JBUI;

import javax.swing.*;
import java.awt.*;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 4/30/2016
 */
class CreateNewNodePanel extends JPanel {

    private String nodeType;

    private JTextField  nodeNameTextField;


    CreateNewNodePanel(String nodeType) {

        this.nodeType = nodeType;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel nodeNameLabel = new JLabel("Enter New " + nodeType + " Name:");
        nodeNameTextField = new JTextField();

        add(nodeNameLabel);
        add(nodeNameTextField);
    }

    @Override
    public Insets getInsets() {
        return JBUI.insets(20);
    }

    String getNodeName() {
        return nodeNameTextField.getText();
    }

    void clearNodeName() {
        nodeNameTextField.setText("");
    }
}
