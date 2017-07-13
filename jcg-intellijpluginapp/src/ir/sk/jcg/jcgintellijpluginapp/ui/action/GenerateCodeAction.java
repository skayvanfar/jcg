package ir.sk.jcg.jcgintellijpluginapp.ui.action;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.ui.DialogBuilder;
import com.intellij.openapi.ui.DialogWrapper;
import ir.sk.jcg.jcgcommon.util.Utils;
import ir.sk.jcg.jcgengine.CodeGenerator;
import ir.sk.jcg.jcgengine.model.project.*;
import ir.sk.jcg.jcgengine.model.project.Package;
import ir.sk.jcg.jcgintellijpluginapp.ui.toolwindow.JcgProjectComponent;

import javax.swing.tree.DefaultMutableTreeNode;
import java.util.*;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 5/6/2016
 */
public class GenerateCodeAction extends NodeAction {

    private DialogBuilder builder;
    private QuestionPanel questionPanel;

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

                Object[] nodeObjects =  jcgProjectComponent.getSelectionPath().getPath();

                String packagePatch = getPackagePath(nodeObjects);

                Map<String, Set<? extends ModelElement>> allModelElements = new HashMap<>();

                if (modelElement instanceof Packageable) {
                    getAllModelElements(packagePatch, (Packageable) modelElement, allModelElements);
                } else {
                    Set<ModelElement> modelSet = new HashSet<>();
                    modelSet.add(modelElement);
                    allModelElements.put(packagePatch, modelSet);
                }

                codeGenerator.addAllModelElements(allModelElements);

                marshalingAndReloadTree();

                builder.getDialogWrapper().close(DialogWrapper.OK_EXIT_CODE);
            }

            private String getPackagePath(Object[] nodeObjects) {
                StringBuilder stringPackage = new StringBuilder();
                for (int i = 0; i < nodeObjects.length; i++) {
                    if (((DefaultMutableTreeNode) nodeObjects[i]).getUserObject()instanceof Package)
                        stringPackage.append('.').append(nodeObjects[i].toString());
                }
                return stringPackage.toString().contains(".") ? stringPackage.toString().substring(1): stringPackage.toString();
            }

            private void generate(ModelElement modelElement, String[] packagePathArray) {
                if (modelElement instanceof Entity) {
                    codeGenerator.addEntity((Entity) modelElement, Utils.covertStringArrayToString(packagePathArray, '.'));
                } else if (modelElement instanceof View) {
                    codeGenerator.addView((View) modelElement, Utils.covertStringArrayToString(packagePathArray, '.'));
                }
            }
        });
        builder.showModal(true);
    }

    private void getAllModelElements(String path, Packageable packageable, Map<String, Set<? extends ModelElement>> allModelElements) {
        if (packageable instanceof Package) {
            allModelElements.put(path, ((Package) packageable).getElements());
        }
        if (packageable.getPackages().size() > 0) {
            packageable.getPackages().forEach(aPackage ->  getAllModelElements(path.equals("") ? aPackage.toString() : (path + '.' + aPackage), (Packageable) aPackage, allModelElements));
        }
    }
}
