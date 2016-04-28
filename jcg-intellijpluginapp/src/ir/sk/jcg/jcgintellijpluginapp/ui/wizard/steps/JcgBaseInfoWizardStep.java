package ir.sk.jcg.jcgintellijpluginapp.ui.wizard.steps;

import com.intellij.ide.util.projectWizard.ModuleWizardStep;
import com.intellij.ide.util.projectWizard.WizardContext;
import com.intellij.ide.wizard.CommitStepException;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.util.text.StringUtil;
import ir.sk.jcg.jcgengine.Generator;
import ir.sk.jcg.jcgengine.JavaGenerator;
import ir.sk.jcg.jcgengine.model.platform.architecture.Architecture;
import ir.sk.jcg.jcgengine.model.platform.architecture.ArchitectureType;
import ir.sk.jcg.jcgengine.model.project.Project;
import ir.sk.jcg.jcgintellijpluginapp.ui.wizard.JcgModuleBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 4/18/2016
 */
public class JcgBaseInfoWizardStep extends ModuleWizardStep {

    private static final Logger logger = LoggerFactory.getLogger(JcgBaseInfoWizardStep.class);

    private final JcgModuleBuilder jcgModuleBuilder;
    private final WizardContext wizardContext;
    private JcgBaseInfoWizardStepPanel jcgBaseInfoWizardStepPanel;

    public JcgBaseInfoWizardStep(JcgModuleBuilder jcgModuleBuilder, WizardContext wizardContext) {
        this.jcgModuleBuilder = jcgModuleBuilder;
        this.wizardContext = wizardContext;
    }

    @Override
    public JComponent getPreferredFocusedComponent() {
        return jcgBaseInfoWizardStepPanel;
    }

    /**
     * Call After wizard complete.
     * */
    @Override
    public void onWizardFinished() throws CommitStepException {
        logger.info("================ Finish (JcgBaseInfoWizardStep) ================");

      //  new Project();
     //   updateComponentsSelectionList();

    }

    @Override
    public void disposeUIResources() {
        super.disposeUIResources();
    }


    @Override
    public JcgBaseInfoWizardStepPanel getComponent() {
        if (jcgBaseInfoWizardStepPanel == null) {
            jcgBaseInfoWizardStepPanel = new JcgBaseInfoWizardStepPanel();
        }
        return jcgBaseInfoWizardStepPanel;
    }

    /**
     * Validate input fields.
     * */
    @Override
    public boolean validate() throws ConfigurationException {
        if (StringUtil.isEmptyOrSpaces(getComponent().getProjectNameField().getText())) {
            throw new ConfigurationException("Please, specify project name");
        }

        if (StringUtil.isEmptyOrSpaces(getComponent().getProjectPersianNameField().getText())) {
            throw new ConfigurationException("Please, specify project persian name");
        }

        if (StringUtil.isEmptyOrSpaces(getComponent().getPackagePrefixField().getText())) {
            throw new ConfigurationException("Please, specify package prefix");
        }

//        if (getComponent().getArchitectureComboBox().getSelectedIndex() != 0) { // todo:may need change (!= 0)
//            throw new ConfigurationException("Please, specify architecture");
//        }

        return true;
    }

    /**
    * Call After step complete.
    * */
    @Override
    public void updateDataModel() {
        wizardContext.setProjectBuilder(jcgModuleBuilder);

        Generator generator  = jcgModuleBuilder.getGenerator();
        Project jcgProject= generator.getJcgProject();
        jcgProject.setName(getComponent().getProjectNameField().getText());
        jcgProject.setPersianName(getComponent().getProjectPersianNameField().getText());
        jcgProject.setPackagePrefix(getComponent().getPackagePrefixField().getText());

        // build architecture that user selected
        ArchitectureType architectureType = (ArchitectureType) getComponent().getArchitectureComboBox().getSelectedItem();
        generator.setArchitecture(architectureType.architectureBuilder());
    }

}
