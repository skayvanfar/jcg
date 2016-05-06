package ir.sk.jcg.jcgintellijpluginapp.ui.action;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.ui.DialogBuilder;
import ir.sk.jcg.jcgcommon.util.Utils;
import ir.sk.jcg.jcgengine.Generator;
import ir.sk.jcg.jcgengine.model.project.*;
import ir.sk.jcg.jcgengine.model.project.Package;
import ir.sk.jcg.jcgintellijpluginapp.ui.toolwindow.JcgProjectComponent;
import ir.sk.jcg.jcgintellijpluginapp.ui.toolwindow.treeToolWindow.TreePanel;

import javax.xml.bind.JAXBException;
import java.util.Arrays;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 4/28/2016
 */
public class CreateEntityNodeAction extends NodeAction {
    @Override
    public void actionPerformed(AnActionEvent anActionEvent) {
        final DialogBuilder builder = new DialogBuilder(anActionEvent.getProject());
        builder.setTitle("Create Entity");


        builder.setOkOperation(new Runnable() {
            @Override
            public void run() {

                JcgProjectComponent jcgProjectComponent = JcgProjectComponent.getInstance(anActionEvent.getProject());
                Package<Entity> entityPackage = (Package<Entity>) jcgProjectComponent.currentSelectedNodeUserObject();

                Generator generator = jcgProjectComponent.getGenerator();

                Entity entity = new Entity(); // TODO: 5/3/2016 create new method

                // add to project
                entityPackage.addElement(entity);

                Object[] pathArray = jcgProjectComponent.getTreePath().getPath();
                String[] packagePathArray = Arrays.copyOfRange(Utils.convertObjectArrayToStringArray(pathArray), 2, pathArray.length );
                generator.addEntity(entity, Utils.covertStringArrayToString(packagePathArray, '.'));

                try {
                    generator.marshalling(); // TODO: 5/3/2016
                } catch (JAXBException e) {
                    e.printStackTrace();
                }
                jcgProjectComponent.reloadJcgTree();
            }
        });

        builder.showModal(true);
    }

}
