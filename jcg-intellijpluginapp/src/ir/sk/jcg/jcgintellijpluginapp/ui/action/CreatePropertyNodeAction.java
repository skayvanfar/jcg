package ir.sk.jcg.jcgintellijpluginapp.ui.action;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.openapi.util.text.StringUtil;
import ir.sk.jcg.jcgengine.model.project.Entity;
import ir.sk.jcg.jcgengine.model.project.Property;
import ir.sk.jcg.jcgintellijpluginapp.ui.toolwindow.JcgProjectComponent;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 5/6/2016
 */
public class CreatePropertyNodeAction extends CreateNodeAction {


    public CreatePropertyNodeAction() {
        super("Property");
    }

    @Override
    public void actionPerformed(AnActionEvent e) { // TODO: 5/6/2016 repeated code in actions
        super.actionPerformed(e);
        builder.setOkOperation(() -> {
            String propertyName = addNodePanel.getNodeName();
            if (StringUtil.isNotEmpty(propertyName)) {
                propertyName = correctName(propertyName);

                JcgProjectComponent jcgProjectComponent1 = JcgProjectComponent.getInstance(e.getProject());
                Entity entity = (Entity) jcgProjectComponent1.currentSelectedNodeUserObject();

                Property property = new Property(propertyName, propertyName, "String", propertyName); // TODO: 5/3/2016 create new method
                // add to project
                entity.addProperty(property);

                marshalingAndReloadTree();
            }
            builder.getDialogWrapper().close(DialogWrapper.OK_EXIT_CODE);
        });


        builder.showModal(true);
    }
}
