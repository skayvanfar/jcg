package ir.sk.jcg.jcgintellijpluginapp.ui.action;

import javax.swing.*;
import java.awt.*;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 5/8/2016
 */
public class QuestionPanel extends JPanel {

    private String operationName;

    private JLabel nodeNameLabel;

    public QuestionPanel(String operationName) {

        this.operationName = operationName;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        nodeNameLabel = new JLabel("Are You sure to " + operationName);

        add(nodeNameLabel);
    }

    @Override
    public Insets getInsets() {
        return new Insets(20,20,20,20);
    }
}
