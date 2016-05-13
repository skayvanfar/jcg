package ir.sk.jcg.jcgintellijpluginapp.ui.action;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.ui.DialogBuilder;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.uiDesigner.core.GridLayoutManager;
import ir.sk.jcg.jcgcommon.util.Utils;
import ir.sk.jcg.jcgengine.CodeGenerator;
import ir.sk.jcg.jcgengine.model.project.*;
import ir.sk.jcg.jcgengine.model.project.Package;
import ir.sk.jcg.jcgintellijpluginapp.ui.toolwindow.JcgProjectComponent;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.xml.bind.JAXBException;
import java.awt.*;
import java.awt.List;
import java.util.*;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 5/12/2016
 */
public class CreateRelationNodeAction extends NodeAction {

    private DialogBuilder builder;
    protected NewRelationPanel newRelationPanel;

    public CreateRelationNodeAction() {
    }

    @Override
    public void actionPerformed(AnActionEvent e) {
        JcgProjectComponent jcgProjectComponent = JcgProjectComponent.getInstance(e.getProject());
        Package<Entity> entityPackage = (Package<Entity>) ((DefaultMutableTreeNode) jcgProjectComponent.getSelectionPath().getParentPath().getLastPathComponent()).getUserObject();


        newRelationPanel = new NewRelationPanel(entityPackage.getElements());
        builder = new DialogBuilder(e.getProject());
        builder.setTitle("New Relation");
        builder.setPreferredFocusComponent(newRelationPanel);
        builder.setCenterPanel(newRelationPanel);
        builder.setOkOperation(new Runnable() {

            private CodeGenerator codeGenerator;

            @Override
            public void run() {
                String relationName = newRelationPanel.getNodeName();
                if (StringUtil.isNotEmpty(relationName)) {
               //     entityName = correctName(entityName); // TODO: 5/12/2016
                }

                JcgProjectComponent jcgProjectComponent = JcgProjectComponent.getInstance(e.getProject());
                Entity entity = (Entity) jcgProjectComponent.currentSelectedNodeUserObject();
                codeGenerator = jcgProjectComponent.getCodeGenerator();

                Relation relation = new Relation();
                relation.setName(relationName);
                relation.setTargetEntity(newRelationPanel.getTargetEntitySelected());
                entity.addRelation(relation);

                try {
                    codeGenerator.marshalling();
                } catch (JAXBException e1) {
                    e1.printStackTrace();
                }

                jcgProjectComponent.reloadJcgTree(jcgProjectComponent.getSelectionPath());

                builder.getDialogWrapper().close(DialogWrapper.OK_EXIT_CODE);
            }
        });
        builder.showModal(true);
    }
}
