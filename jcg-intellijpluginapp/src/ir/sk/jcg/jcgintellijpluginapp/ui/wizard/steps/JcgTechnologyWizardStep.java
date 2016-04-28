package ir.sk.jcg.jcgintellijpluginapp.ui.wizard.steps;

import com.intellij.ide.util.projectWizard.ModuleWizardStep;
import com.intellij.ide.util.projectWizard.WizardContext;
import com.intellij.ide.wizard.CommitStepException;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.application.ModalityState;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.progress.ProgressManager;
import ir.sk.jcg.jcgengine.Generator;
import ir.sk.jcg.jcgengine.model.platform.architecture.Architecture;
import ir.sk.jcg.jcgengine.model.platform.technology.Technology;
import ir.sk.jcg.jcgengine.model.platform.technology.TechnologyEnumBase;
import ir.sk.jcg.jcgengine.model.project.Project;
import ir.sk.jcg.jcgintellijpluginapp.ui.util.execution.ModalTaskImpl;
import ir.sk.jcg.jcgintellijpluginapp.ui.wizard.JcgAPIManagerCallback;
import ir.sk.jcg.jcgintellijpluginapp.ui.wizard.JcgModuleBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 4/19/2016
 */
public class JcgTechnologyWizardStep extends ModuleWizardStep {

    private static final Logger logger = LoggerFactory.getLogger(JcgTechnologyWizardStep.class);

    private final JcgModuleBuilder jcgModuleBuilder;
    private final WizardContext wizardContext;
    private JcgTechnologyWizardStepPanel jcgTechnologyWizardStepPanel;

    public JcgTechnologyWizardStep(JcgModuleBuilder jcgModuleBuilder, WizardContext wizardContext) {
        this.jcgModuleBuilder = jcgModuleBuilder;
        this.wizardContext = wizardContext;
    }

    @Override
    public JComponent getPreferredFocusedComponent() {
        return jcgTechnologyWizardStepPanel;
    }

    /**
     * Call After wizard complete.
     * */
    @Override
    public void onWizardFinished() throws CommitStepException {
      //  File baseDir = new File(jcgModuleBuilder.getContentEntryPath());
        Architecture architecture = jcgModuleBuilder.getGenerator().getArchitecture();
  //      architecture.setBaseDir(baseDir);

        Generator generator = jcgModuleBuilder.getGenerator();
        Project jcgProject =jcgModuleBuilder.getGenerator().getJcgProject();
        generator.setBaseDir(jcgModuleBuilder.getContentEntryPath());
        jcgProject.setPackagePrefix("ir.sk"); // TODO: 4/28/2016 must get from user

   //     architecture.setProject(jcgProject);

        architecture.initialize(generator.getBaseDir(), jcgProject.getPackagePrefix()); // TODO: 4/27/2016 may beter go to JcgModuleBuilderHelper.configure()
        
        JcgAPIManagerCallback jcgAPIManagerCallback = new JcgAPIManagerCallback();

        final ModalTaskImpl modalTask = new ModalTaskImpl(null, "other works after create base project.", jcgAPIManagerCallback); // todo: must set Project instead null
        ApplicationManager.getApplication().invokeAndWait(new Runnable() {
            @Override
            public void run() {
                ProgressManager.getInstance().run(modalTask);
            }
        }, ModalityState.defaultModalityState());

        if (!modalTask.isDone()) {
            throw new CommitStepException("Operation Fail");  // todo: must use Constant
        }
    }

    @Override
    public void disposeUIResources() {
        super.disposeUIResources();
    }


    @Override
    public JcgTechnologyWizardStepPanel getComponent() {
        if (jcgTechnologyWizardStepPanel == null) {
            jcgTechnologyWizardStepPanel = new JcgTechnologyWizardStepPanel();
        }
        return jcgTechnologyWizardStepPanel;
    }

    /**
     * Validate input fields.
     * */
    @Override
    public boolean validate() throws ConfigurationException {
//        if (StringUtil.isEmptyOrSpaces(getComponent().getProjectNameField().getText())) {
//            throw new ConfigurationException("Please, specify project name");
//        }
//
//        if (StringUtil.isEmptyOrSpaces(getComponent().getProjectPersianNameField().getText())) {
//            throw new ConfigurationException("Please, specify project persian name");
//        }
//
//        if (StringUtil.isEmptyOrSpaces(getComponent().getPackagePrefixField().getText())) {
//            throw new ConfigurationException("Please, specify package prefix");
//        }
//
//        if (getComponent().getArchitectureComboBox().getSelectedIndex() != 0) { // todo:may need change (!= 0)
//            throw new ConfigurationException("Please, specify architecture");
//        }

        return true;
    }

    /**
     * Call After enter to this step.
     * */
    @Override
    public void updateStep() {
        Architecture architecture = jcgModuleBuilder.getGenerator().getArchitecture();
        jcgTechnologyWizardStepPanel.initComponents(architecture.getTechnologyTypes());

        updateComponents();
    }

    private void updateComponents() {
//        myGroupIdField.setEnabled(true);
//        myVersionField.setEnabled(true);
    }

    /**
     * Call After step complete.
     * */
    @Override
    public void updateDataModel() {
        wizardContext.setProjectBuilder(jcgModuleBuilder);
        List<Technology> technologies = new ArrayList<>();
        Architecture architecture = jcgModuleBuilder.getGenerator().getArchitecture();

        for (JComboBox comboBox : getComponent().getComboBoxList()) {
            Technology technology = ((TechnologyEnumBase) comboBox.getSelectedItem()).technologyBuilder();
            technologies.add(technology);
        }

        architecture.setTechnologies(technologies);
    }
}
