package ir.sk.jcg.jcgintellijpluginapp.ui.action;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.ui.DialogBuilder;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.openapi.util.text.StringUtil;
import ir.sk.jcg.jcgcommon.util.StringUtils;
import ir.sk.jcg.jcgcommon.util.Utils;
import ir.sk.jcg.jcgengine.CodeGenerator;
import ir.sk.jcg.jcgengine.model.project.*;
import ir.sk.jcg.jcgengine.model.project.Package;
import ir.sk.jcg.jcgengine.model.project.enums.IdGeneratorType;
import ir.sk.jcg.jcgintellijpluginapp.ui.toolwindow.JcgProjectComponent;

import javax.xml.bind.JAXBException;
import java.util.Arrays;
import java.util.List;

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
                    String tableNamePattern = jcgProjectComponent.getCodeGenerator().getJcgProject().getTableNamePattern();
                    entity.setTableName(tableNamePattern + entityName);
                    entity.setLabelName(entityName);
                    // Id property that auto generated
                    Id id = new Id();
                    // convert String to camelcase like: "PersonInfo" --> "personInfo"
                    id.setName(StringUtils.toCamelCase(entity.getName()) + "Id");
                    id.setIdGeneratorType(IdGeneratorType.AUTO);
                    id.setType("Long");
                    id.setColumnName(id.getName());
                    entity.setId(id);

                    // add to project
                    entityPackage.addElement(entity);

                    marshalingAndReloadTree();
                }
                builder.getDialogWrapper().close(DialogWrapper.OK_EXIT_CODE);
            }
        });


        builder.showModal(true);
    }

}
