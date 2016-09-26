package ir.sk.jcg.jcgintellijpluginapp.ui.action;

import com.intellij.util.ui.JBUI;

import javax.swing.*;
import java.awt.*;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 5/8/2016
 */
class QuestionPanel extends JPanel {

    private String operationName;

    QuestionPanel(String operationName) {

        this.operationName = operationName;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel nodeNameLabel = new JLabel("Are You sure to " + operationName);

        add(nodeNameLabel);
    }

    @Override
    public Insets getInsets() {
        return JBUI.insets(20);
    }
}
