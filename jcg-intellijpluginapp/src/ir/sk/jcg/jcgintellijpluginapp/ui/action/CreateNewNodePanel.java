package ir.sk.jcg.jcgintellijpluginapp.ui.action;

import javax.swing.*;
import java.awt.*;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 4/30/2016
 */
public class CreateNewNodePanel extends JPanel {

    private String nodeType;

    private JLabel nodeNameLabel;
    private JTextField  nodeNameTextField;


    public CreateNewNodePanel(String nodeType) {

        this.nodeType = nodeType;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        nodeNameLabel = new JLabel("Enter New " + nodeType + " Name:");
        nodeNameTextField = new JTextField();

        add(nodeNameLabel);
        add(nodeNameTextField);
    }

    @Override
    public Insets getInsets() {
        return new Insets(20,20,20,20);
    }

    public String getNodeName() {
        return nodeNameTextField.getText();
    }

    public void clearNodeName() {
        nodeNameTextField.setText("");
    }
}
