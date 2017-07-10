package ir.sk.jcg.jcgintellijpluginapp.ui.wizard.steps;

import com.intellij.ide.util.projectWizard.ModuleWizardStep;
import com.intellij.ide.util.projectWizard.WizardContext;
import com.intellij.ide.wizard.CommitStepException;
import com.intellij.openapi.options.ConfigurationException;
import ir.sk.jcg.jcgcommon.PropertyView.PropertyInfo;
import ir.sk.jcg.jcgcommon.PropertyView.annotation.Prop;
import ir.sk.jcg.jcgcommon.util.ReflectionUtil;
import ir.sk.jcg.jcgengine.model.platform.architecture.Architecture;
import ir.sk.jcg.jcgengine.model.platform.technology.TechnologyHandler;
import ir.sk.jcg.jcgengine.model.platform.technology.TechnologyHandlerType;
import ir.sk.jcg.jcgintellijpluginapp.ui.wizard.JcgModuleBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 5/10/2016
 */
public class JcgMvcTechnologyWizardStep extends ModuleWizardStep {

    private static final Logger logger = LoggerFactory.getLogger(JcgTechnologyWizardStep.class);

    private final JcgModuleBuilder jcgModuleBuilder;
    private final WizardContext wizardContext;

    private JcgCustomTechnologyWizardStepPanel jcgCustomTechnologyWizardStepPanel;

    public JcgMvcTechnologyWizardStep(JcgModuleBuilder jcgModuleBuilder, WizardContext wizardContext) {
        this.jcgModuleBuilder = jcgModuleBuilder;
        this.wizardContext = wizardContext;
    }

    @Override
    public JcgCustomTechnologyWizardStepPanel getComponent() {
        if (jcgCustomTechnologyWizardStepPanel == null) {
            jcgCustomTechnologyWizardStepPanel = new JcgCustomTechnologyWizardStepPanel();
        }
        return jcgCustomTechnologyWizardStepPanel;
    }

    /**
     * Validate input fields.
     */
    @Override
    public boolean validate() throws ConfigurationException {
        jcgCustomTechnologyWizardStepPanel.setComponents();
        for (PropertyInfo propertyInfo : jcgCustomTechnologyWizardStepPanel.getPropertyInfos()) {
            if (propertyInfo.isRequired() && (propertyInfo.getValue() == null || propertyInfo.getValue().equals("")))
                throw new ConfigurationException("Please, specify " + propertyInfo.getName());
        }
        return true;
    }

    /**
     * Call After enter to this step.
     */
    @Override
    public void updateStep() {
        Architecture architecture = jcgModuleBuilder.getCodeGenerator().getArchitecture();

        TechnologyHandler technologyHandler = architecture.getTechnologyByType(TechnologyHandlerType.MVC_TECHNOLOGY);

        List<PropertyInfo> propertyInfos = new ArrayList<>();
        List<Field> fields = ReflectionUtil.findFields(technologyHandler.getClass(), Prop.class);

        PropertyInfo propertyInfo = null;
        for (Field field : fields) {
            try {
                propertyInfo = new PropertyInfo(field, technologyHandler);
            } catch (IllegalAccessException e) {
                e.printStackTrace(); // TODO: 5/5/2016
            }
            propertyInfos.add(propertyInfo);
        }
        jcgCustomTechnologyWizardStepPanel.initComponents(propertyInfos);

        updateComponents();
    }

    private void updateComponents() {
    }

    /**
     * Call After wizard complete on all type of modules.
     */
    @Override
    public void onWizardFinished() throws CommitStepException {

    }

    /**
     * Call After step complete.
     */
    @Override
    public void updateDataModel() {
        wizardContext.setProjectBuilder(jcgModuleBuilder);
        for (PropertyInfo propertyInfo : jcgCustomTechnologyWizardStepPanel.getPropertyInfos()) {

            try {
                Field field = ReflectionUtil.getFieldByName(propertyInfo.getName(), propertyInfo.getObject());
                assert field != null;
                field.setAccessible(true);
                field.set(propertyInfo.getObject(), propertyInfo.getValue());
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
}
