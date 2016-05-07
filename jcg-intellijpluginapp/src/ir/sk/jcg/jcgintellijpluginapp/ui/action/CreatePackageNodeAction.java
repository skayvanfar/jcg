package ir.sk.jcg.jcgintellijpluginapp.ui.action;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.ui.DialogBuilder;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.openapi.util.text.StringUtil;
import ir.sk.jcg.jcgengine.model.project.*;
import ir.sk.jcg.jcgengine.model.project.Package;
import ir.sk.jcg.jcgintellijpluginapp.ui.toolwindow.JcgProjectComponent;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 4/30/2016
 */
public class CreatePackageNodeAction extends NodeAction {
    @Override
    public void actionPerformed(AnActionEvent anActionEvent) {
        final DialogBuilder builder = new DialogBuilder(anActionEvent.getProject());
        builder.setTitle("Create Package");

        CreatePackagePanel panel = new CreatePackagePanel();
        builder.setPreferredFocusComponent(panel);
        builder.setCenterPanel(panel);
        builder.setOkOperation(new Runnable() {
            @Override
            public void run() {
                String packageName = panel.getPackageName();
                if (StringUtil.isNotEmpty(packageName)) {

                    // Validate and Correction
                    packageName = validateAndCorrection(packageName);

                    JcgProjectComponent jcgProjectComponent = JcgProjectComponent.getInstance(anActionEvent.getProject());
                    Packageable<ModelElement> packageable  = (Packageable<ModelElement>) jcgProjectComponent.currentSelectedNodeUserObject();
                    try {
                        //create recursively support
//                        String[] parts = packageName.split("."); // TODO: 5/2/2016 for many package create
//                        for (int i = 0; i < parts.length ; i++) {
//                            if (aPackage.getName().equals(parts[i]))
//                                continue;
//                            else {
//                                Package<ModelElement> aPackage1 = new Package<ModelElement>();
//                                aPackage1.setName(parts[i]);
//                                
//                            }
//                                
//                        }
                        // a flag for see current packageable has a package with same name of new package
                        boolean isPackageExist = false;
                        if(packageable.getPackages().size() != 0) {
                            for (Packageable<ModelElement> elementPackage : packageable.getPackages()) {
                                Element element = (Element) elementPackage;
                                if (element.getName().equals(packageName)) {
                                    isPackageExist = true;
                                    break;
                                }
                            }
                        }
                        if (!isPackageExist) {
                            Package<ModelElement> elementPackage = new Package<>();
                            elementPackage.setName(packageName);
                            packageable.addPackage(elementPackage);
                        }

                        jcgProjectComponent.getGenerator().marshalling();
                        jcgProjectComponent.reloadJcgTree(jcgProjectComponent.getSelectionPath());
                    } catch (Exception e) {
                        e.printStackTrace(); // TODO: 5/2/2016  
                    }
                }
                builder.getDialogWrapper().close(DialogWrapper.OK_EXIT_CODE);
            }

            private String validateAndCorrection(String packageName) {
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
