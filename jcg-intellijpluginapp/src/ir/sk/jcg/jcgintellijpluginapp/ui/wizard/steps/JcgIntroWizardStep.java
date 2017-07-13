package ir.sk.jcg.jcgintellijpluginapp.ui.wizard.steps;

import com.intellij.ide.util.projectWizard.ModuleWizardStep;
import com.intellij.ide.wizard.CommitStepException;
import com.intellij.openapi.Disposable;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.ResourceBundle;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 4/18/2016
 */
public class JcgIntroWizardStep extends ModuleWizardStep implements Disposable {

    private final ResourceBundle bundle = ResourceBundle.getBundle("/messages/pluginMessages");

    private JLabel jcgIntroLabel;
    private JComponent myMainPanel;

    @Override
    public JComponent getPreferredFocusedComponent() {
        return myMainPanel;
    }

    @Override
    public void onWizardFinished() throws CommitStepException {

    }

    @Override
    public JComponent getComponent() {
        if (myMainPanel == null) {
            myMainPanel = new JPanel();
            {
                myMainPanel.setBorder(new TitledBorder(bundle.getString("jcgWizardPanel.myMainPanel.border.title")));
                myMainPanel.setPreferredSize(new Dimension(333, 364));


                jcgIntroLabel = new JLabel();
                jcgIntroLabel.setText(bundle.getString("jcgWizardPanel.myMainPanel.description"));
                Font labelFont = jcgIntroLabel.getFont();

                jcgIntroLabel.setFont(new Font(labelFont.getName(), Font.PLAIN, 14));

                GroupLayout jPanel4Layout = new GroupLayout(myMainPanel);
                myMainPanel.setLayout(jPanel4Layout);
                jPanel4Layout.setHorizontalGroup(
                        jPanel4Layout.createParallelGroup()
                                .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(jcgIntroLabel)
                                        .addGap(0, 0, Short.MAX_VALUE))
                );
                jPanel4Layout.setVerticalGroup(
                        jPanel4Layout.createParallelGroup()
                                .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(jcgIntroLabel)
                                        .addContainerGap(0, Short.MAX_VALUE))
                );
            }
        }
        return myMainPanel;
    }

    @Override
    public void updateDataModel() {

    }

    @Override
    public void dispose() {

    }
}
