package ir.sk.jcg.jcgintellijpluginapp.ui.action;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.ui.DialogBuilder;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.openapi.util.text.StringUtil;
import ir.sk.jcg.jcgcommon.util.Utils;
import ir.sk.jcg.jcgengine.Generator;
import ir.sk.jcg.jcgengine.model.project.Entity;
import ir.sk.jcg.jcgengine.model.project.Package;
import ir.sk.jcg.jcgengine.model.project.Property;
import ir.sk.jcg.jcgintellijpluginapp.ui.toolwindow.JcgProjectComponent;

import javax.swing.*;
import javax.xml.bind.JAXBException;
import java.util.Arrays;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 5/6/2016
 */
public class CreatePropertyNodeAction extends NodeAction {
    @Override
    public void actionPerformed(AnActionEvent e) { // TODO: 5/6/2016 repeated code in actions
        final DialogBuilder builder = new DialogBuilder(e.getProject());
        builder.setTitle("Create Property");
        final JTextField textField = new JTextField();
        builder.setPreferredFocusComponent(textField);
        builder.setCenterPanel(textField);

        builder.setOkOperation(new Runnable() {
            @Override
            public void run() {
                String entityName = textField.getText();
                if (StringUtil.isNotEmpty(entityName)) {
                    entityName = correctName(entityName);

                    JcgProjectComponent jcgProjectComponent = JcgProjectComponent.getInstance(e.getProject());
                    Entity entity = (Entity) jcgProjectComponent.currentSelectedNodeUserObject();

                    Generator generator = jcgProjectComponent.getGenerator();

                    Property property = new Property(); // TODO: 5/3/2016 create new method
                    property.setName(entityName);

                    // add to project
                    entity.addProperty(property);

                    try {
                        generator.marshalling(); // TODO: 5/3/2016
                    } catch (JAXBException e) {
                        e.printStackTrace();
                    }
                    jcgProjectComponent.reloadJcgTree(jcgProjectComponent.getSelectionPath());
                }
                builder.getDialogWrapper().close(DialogWrapper.OK_EXIT_CODE);
            }
        });


        builder.showModal(true);
    }
}
