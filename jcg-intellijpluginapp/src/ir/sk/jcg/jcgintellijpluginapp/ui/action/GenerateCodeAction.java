package ir.sk.jcg.jcgintellijpluginapp.ui.action;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.ui.DialogBuilder;
import com.intellij.openapi.ui.DialogWrapper;
import ir.sk.jcg.jcgcommon.util.Utils;
import ir.sk.jcg.jcgengine.CodeGenerator;
import ir.sk.jcg.jcgengine.model.project.*;
import ir.sk.jcg.jcgengine.model.project.Package;
import ir.sk.jcg.jcgintellijpluginapp.ui.toolwindow.JcgProjectComponent;

import java.util.Arrays;
import java.util.List;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 5/6/2016
 */
public class GenerateCodeAction extends NodeAction {

    private DialogBuilder builder;
    protected QuestionPanel questionPanel;

    public GenerateCodeAction() {
        questionPanel = new QuestionPanel("Generate Code");
    }

    @Override
    public void actionPerformed(AnActionEvent e) {
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
                Element element = (Element) jcgProjectComponent.currentSelectedNodeUserObject();
                
                Object[] pathArray = jcgProjectComponent.getSelectionPath().getPath();

                String[] packagePathArray = Arrays.copyOfRange(Utils.convertObjectArrayToStringArray(pathArray), 2, pathArray.length );
                List<EntityElement> entityElements = generate(element, packagePathArray);
             //   element.addAll(entityElements); // TODO: 5/8/2016 Elementmust have this method
                builder.getDialogWrapper().close(DialogWrapper.OK_EXIT_CODE);
            }
            
            private List<EntityElement> generate(Element element, String[] packagePathArray) {
                List<EntityElement> entityElements = null;
                if (element instanceof Entity) {
                    entityElements = codeGenerator.addEntity((Entity) element, Utils.covertStringArrayToString(packagePathArray, '.'));
                } else if (element instanceof View) {
                    
                } else {
                    
                }
                return entityElements;
            }
        });
        builder.showModal(true);
    }
}
