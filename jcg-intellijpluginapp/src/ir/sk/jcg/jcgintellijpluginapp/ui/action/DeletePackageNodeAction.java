package ir.sk.jcg.jcgintellijpluginapp.ui.action;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.ui.DialogBuilder;
import com.intellij.openapi.ui.DialogWrapper;
import ir.sk.jcg.jcgengine.Generator;
import ir.sk.jcg.jcgengine.model.project.ModelElement;
import ir.sk.jcg.jcgengine.model.project.Package;
import ir.sk.jcg.jcgengine.model.project.Packageable;
import ir.sk.jcg.jcgintellijpluginapp.ui.toolwindow.JcgProjectComponent;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 5/3/2016
 */
public class DeletePackageNodeAction extends NodeAction {
    @Override
    public void actionPerformed(AnActionEvent anActionEvent) {
        final DialogBuilder builder = new DialogBuilder(anActionEvent.getProject());
        builder.setTitle("Create Package");

        //   CreatePackagePanel panel = new CreatePackagePanel();
        //  builder.setPreferredFocusComponent(panel);
        //  builder.setCenterPanel(panel);
        builder.setOkOperation(new Runnable() {
            @Override
            public void run() {


                JcgProjectComponent jcgProjectComponent = JcgProjectComponent.getInstance(anActionEvent.getProject());

                TreePath treePath = jcgProjectComponent.getTreePath();

                Generator generator = jcgProjectComponent.getGenerator();



                DefaultMutableTreeNode currentNode = (DefaultMutableTreeNode) treePath.getLastPathComponent();
                DefaultMutableTreeNode parentNode = (DefaultMutableTreeNode) treePath.getParentPath().getLastPathComponent();

                Package<ModelElement> currentPackage = (Package<ModelElement>) currentNode.getUserObject();
                Packageable<ModelElement> parentPackageable = (Packageable<ModelElement>) parentNode.getUserObject();

                if (currentPackage.getPackages().size() == 0) {
                    try {
                        parentPackageable.removePackage(currentPackage);

                        jcgProjectComponent.getGenerator().marshalling();
                        jcgProjectComponent.reloadJcgTree();
                    } catch (Exception e) {
                        e.printStackTrace(); // TODO: 5/2/2016
                    }
                } else { // when package has child
                    System.out.println("Selected package has child."); // TODO: 5/3/2016 must show a dialog window 
                }
                
                builder.getDialogWrapper().close(DialogWrapper.OK_EXIT_CODE);
            }

            private String validateAndCorrection(String packageName) { // TODO: 5/4/2016 may go to upper class 
                if (packageName.startsWith(".")) {
                    packageName = packageName.substring(1);
                }
                if (packageName.endsWith(".")) {
                    packageName = packageName.substring(0, packageName.length() - 1);
                }
                if (packageName.contains(".")) {
                    packageName = packageName.replaceAll("[.]", ""); // TODO: 5/2/2016 must be complex
                }
                return packageName;
            }
        });
        builder.showModal(true);
    }
}
