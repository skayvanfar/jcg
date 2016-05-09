package ir.sk.jcg.jcgintellijpluginapp.ui.action;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.openapi.util.text.StringUtil;
import ir.sk.jcg.jcgengine.model.project.*;
import ir.sk.jcg.jcgengine.model.project.Package;
import ir.sk.jcg.jcgintellijpluginapp.ui.toolwindow.JcgProjectComponent;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 4/30/2016
 */
public class CreatePackageNodeAction extends CreateNodeAction {

    public CreatePackageNodeAction() {
        super("Package");
    }

    @Override
    public void actionPerformed(AnActionEvent e) {
        super.actionPerformed(e);
        builder.setOkOperation(new Runnable() {
            @Override
            public void run() {
                String packageName = addNodePanel.getNodeName();
                if (StringUtil.isNotEmpty(packageName)) {

                    // Validate and Correction
                    packageName = validateAndCorrection(packageName);

                    JcgProjectComponent jcgProjectComponent = JcgProjectComponent.getInstance(e.getProject());
                    Packageable<SubModelElement> packageable  = (Packageable<SubModelElement>) jcgProjectComponent.currentSelectedNodeUserObject();
                    try {
                        //create recursively support
//                        String[] parts = packageName.split("."); // TODO: 5/2/2016 for many package create
//                        for (int i = 0; i < parts.length ; i++) {
//                            if (aPackage.getName().equals(parts[i]))
//                                continue;
//                            else {
//                                Package<SubModelElement> aPackage1 = new Package<SubModelElement>();
//                                aPackage1.setName(parts[i]);
//                                
//                            }
//                                
//                        }
                        // a flag for see current packageable has a package with same name of new package
                        boolean isPackageExist = false;
                        if(packageable.getPackages().size() != 0) {
                            for (Packageable<SubModelElement> elementPackage : packageable.getPackages()) {
                                Element element = (Element) elementPackage;
                                if (element.getName().equals(packageName)) {
                                    isPackageExist = true;
                                    break;
                                }
                            }
                        }
                        if (!isPackageExist) {
                            Package<SubModelElement> elementPackage = new Package<>();
                            elementPackage.setName(packageName);
                            packageable.addPackage(elementPackage);
                        }

                        jcgProjectComponent.getCodeGenerator().marshalling();
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
