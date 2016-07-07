package ir.sk.jcg.jcgintellijpluginapp.ui.action;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.ui.DialogBuilder;
import com.intellij.openapi.ui.DialogWrapper;
import ir.sk.jcg.jcgcommon.util.Utils;
import ir.sk.jcg.jcgengine.CodeGenerator;
import ir.sk.jcg.jcgengine.model.platform.technology.ormTechnology.hibernate.element.EntityClass;
import ir.sk.jcg.jcgengine.model.project.*;
import ir.sk.jcg.jcgengine.model.project.Package;
import ir.sk.jcg.jcgintellijpluginapp.ui.toolwindow.JcgProjectComponent;

import javax.xml.bind.JAXBException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 5/6/2016
 */
public class GenerateCodeAction extends NodeAction {

    private DialogBuilder builder;
    protected QuestionPanel questionPanel;

    public GenerateCodeAction() {
        super("Generate Code");
        questionPanel = new QuestionPanel("Generate Code");
    }

    @Override
    public void actionPerformed(AnActionEvent e) {
        super.actionPerformed(e);
        builder = new DialogBuilder(e.getProject());
        builder.setTitle("Code Generate");
        builder.setPreferredFocusComponent(questionPanel);
        builder.setCenterPanel(questionPanel);
        builder.setOkOperation(new Runnable() {

            private CodeGenerator codeGenerator;

            @Override
            public void run() {

                JcgProjectComponent jcgProjectComponent = JcgProjectComponent.getInstance(e.getProject());
                codeGenerator = jcgProjectComponent.getCodeGenerator();
                ModelElement modelElement = (ModelElement) jcgProjectComponent.currentSelectedNodeUserObject();

                Object[] pathArray = jcgProjectComponent.getSelectionPath().getPath();

                String[] packagePathArray = Arrays.copyOfRange(Utils.convertObjectArrayToStringArray(pathArray), 2, pathArray.length ); // TODO: 5/9/2016  on project throws ArrayIndexOutOfBoundException fo 2
                List<? extends ImplElement> implElements = generate(modelElement, packagePathArray);

                modelElement.addAllImplElements((List<ImplElement>) implElements);

                marshalingAndReloadTree();

                builder.getDialogWrapper().close(DialogWrapper.OK_EXIT_CODE);
            }

            private List<? extends ImplElement> generate(ModelElement modelElement, String[] packagePathArray) {
                List<? extends ImplElement> implElements = new ArrayList<>();
                if (modelElement instanceof Entity) {
                    implElements = codeGenerator.addEntity((Entity) modelElement, Utils.covertStringArrayToString(packagePathArray, '.'));
                } else if (modelElement instanceof View) {

                } else {

                }
                return implElements;
            }
        });
        builder.showModal(true);
    }
}
