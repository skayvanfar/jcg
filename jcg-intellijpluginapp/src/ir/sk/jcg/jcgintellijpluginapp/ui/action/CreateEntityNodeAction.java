package ir.sk.jcg.jcgintellijpluginapp.ui.action;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.openapi.util.text.StringUtil;
import ir.sk.jcg.jcgcommon.util.StringUtils;
import ir.sk.jcg.jcgengine.model.project.Entity;
import ir.sk.jcg.jcgengine.model.project.Id;
import ir.sk.jcg.jcgengine.model.project.Package;
import ir.sk.jcg.jcgengine.model.project.enums.IdGeneratorType;
import ir.sk.jcg.jcgintellijpluginapp.ui.toolwindow.JcgProjectComponent;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 4/28/2016
 */
public class CreateEntityNodeAction extends CreateNodeAction {

    public CreateEntityNodeAction() {
        super("Create Entity");
    }

    @Override
    public void actionPerformed(AnActionEvent e) {
        super.actionPerformed(e);
        builder.setOkOperation(() -> {
            String entityName = addNodePanel.getNodeName();
            if (StringUtil.isNotEmpty(entityName)) {
                entityName = correctName(entityName);

                JcgProjectComponent jcgProjectComponent1 = JcgProjectComponent.getInstance(e.getProject());
                Package<Entity> entityPackage = (Package<Entity>) jcgProjectComponent1.currentSelectedNodeUserObject();

                Entity entity = new Entity(); // TODO: 5/3/2016 create new method
                entity.setName(entityName);
                String tableNamePattern = jcgProjectComponent1.getCodeGenerator().getJcgProject().getTableNamePattern();
                entity.setTableName(tableNamePattern + entityName);
                entity.setLabelName(entityName);
                // Id property that auto generated
                Id id = new Id();
                // convert String to camelcase like: "PersonInfo" --> "personInfo"
            //    id.setName(StringUtils.toCamelCase(entity.getName()) + "Id");
                id.setName("id");
                id.setLabelName(id.getName());
                id.setIdGeneratorType(IdGeneratorType.AUTO);
                id.setType("Long");
                id.setColumnName(id.getName());
                id.setEntity(entity);
                entity.setId(id);

                // add to project
                entityPackage.addElement(entity);

                jcgProjectComponent.addNeededNodes(entity);

                marshalingAndReloadTree(false);
            }
            builder.getDialogWrapper().close(DialogWrapper.OK_EXIT_CODE);
        });


        builder.showModal(true);
    }

}
