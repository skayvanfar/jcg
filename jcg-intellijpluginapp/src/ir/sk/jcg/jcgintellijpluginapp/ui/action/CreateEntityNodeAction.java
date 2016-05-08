package ir.sk.jcg.jcgintellijpluginapp.ui.action;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.ui.DialogBuilder;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.openapi.util.text.StringUtil;
import ir.sk.jcg.jcgcommon.util.Utils;
import ir.sk.jcg.jcgengine.CodeGenerator;
import ir.sk.jcg.jcgengine.model.project.*;
import ir.sk.jcg.jcgengine.model.project.Package;
import ir.sk.jcg.jcgintellijpluginapp.ui.toolwindow.JcgProjectComponent;

import javax.xml.bind.JAXBException;
import java.util.Arrays;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 4/28/2016
 */
public class CreateEntityNodeAction extends CreateNodeAction {

    public CreateEntityNodeAction() {
        super("Entity");
    }


    @Override
    public void actionPerformed(AnActionEvent e) {
        super.actionPerformed(e);
        builder.setOkOperation(new Runnable() {
            @Override
            public void run() {
                String entityName = addNodePanel.getNodeName();
                if (StringUtil.isNotEmpty(entityName)) {
                    entityName = correctName(entityName);

                    JcgProjectComponent jcgProjectComponent = JcgProjectComponent.getInstance(e.getProject());
                    Package<Entity> entityPackage = (Package<Entity>) jcgProjectComponent.currentSelectedNodeUserObject();

                    CodeGenerator codeGenerator = jcgProjectComponent.getCodeGenerator();

                    Entity entity = new Entity(); // TODO: 5/3/2016 create new method
                    entity.setName(entityName);

                    // add to project
                    entityPackage.addElement(entity);

                    Object[] pathArray = jcgProjectComponent.getSelectionPath().getPath();

                    String[] packagePathArray = Arrays.copyOfRange(Utils.convertObjectArrayToStringArray(pathArray), 2, pathArray.length );
                    codeGenerator.addEntity(entity, Utils.covertStringArrayToString(packagePathArray, '.'));

                    try {
                        codeGenerator.marshalling(); // TODO: 5/3/2016
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
