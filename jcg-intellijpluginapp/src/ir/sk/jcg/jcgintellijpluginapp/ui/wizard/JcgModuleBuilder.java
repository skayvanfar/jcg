package ir.sk.jcg.jcgintellijpluginapp.ui.wizard;

import com.intellij.ide.util.projectWizard.ModuleBuilder;
import com.intellij.ide.util.projectWizard.ModuleWizardStep;
import com.intellij.ide.util.projectWizard.SettingsStep;
import com.intellij.ide.util.projectWizard.WizardContext;
import com.intellij.openapi.Disposable;
import com.intellij.openapi.module.JavaModuleType;
import com.intellij.openapi.module.ModuleType;
import com.intellij.openapi.module.StdModuleTypes;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.projectRoots.JavaSdkType;
import com.intellij.openapi.projectRoots.SdkTypeId;
import com.intellij.openapi.roots.ModifiableRootModel;
import com.intellij.openapi.roots.ui.configuration.ModulesProvider;
import com.intellij.openapi.util.Disposer;
import com.intellij.openapi.util.io.FileUtil;
import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.openapi.vfs.VirtualFile;
import ir.sk.jcg.jcgengine.CodeGenerator;
import ir.sk.jcg.jcgintellijpluginapp.ui.icon.JcgIcons;
import ir.sk.jcg.jcgintellijpluginapp.ui.wizard.steps.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.io.File;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 4/18/2016
 */
public class JcgModuleBuilder extends ModuleBuilder {

    private final java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("/messages/messages"); // NOI18N

    private Project intellijProject;

  //  private CodeGenerator codeGenerator = new JavaCodeGenerator(); // TODO: 4/26/2016 must go to constructor may be

    private CodeGenerator codeGenerator;

    public Project getIntellijProject() {
        return intellijProject;
    }

    public void setIntellijProject(Project intellijProject) {
        this.intellijProject = intellijProject;
    }

    public CodeGenerator getCodeGenerator() {
        return codeGenerator;
    }

    public void setCodeGenerator(CodeGenerator codeGenerator) {
        this.codeGenerator = codeGenerator;
    }

    @Override
    public String getBuilderId() {
        return getClass().getName();
    }

    @Override
    public Icon getBigIcon() {
        return JcgIcons.JcgMedium;
    }

    @Override
    public Icon getNodeIcon() {
        return JcgIcons.JcgLogo;
    }

    @Override
    public ModuleWizardStep[] createWizardSteps(@NotNull WizardContext wizardContext, @NotNull ModulesProvider modulesProvider) {
        return new ModuleWizardStep[]{
                new JcgBaseInfoWizardStep(this, wizardContext), new JcgTechnologyWizardStep(this, wizardContext),
                new JcgBuildTechnologyWizardStep(this, wizardContext), new JcgOrmTechnologyWizardStep(this, wizardContext), new JcgMvcTechnologyWizardStep(this, wizardContext)
        };
    }

    private VirtualFile createAndGetContentEntry() {
        String path = FileUtil.toSystemIndependentName(getContentEntryPath());
        new File(path).mkdirs();
        return LocalFileSystem.getInstance().refreshAndFindFileByPath(path);
    }

    /**
     * Call after project created (After onWizardFinished() on steps)
     * */
    @Override
    public void setupRootModel(ModifiableRootModel modifiableRootModel) throws ConfigurationException {
        final Project project = modifiableRootModel.getProject();
        intellijProject = modifiableRootModel.getProject();
        final VirtualFile root = createAndGetContentEntry();
        modifiableRootModel.addContentEntry(root);

        modifiableRootModel.inheritSdk();


        JcgModuleBuilderHelper jcgModuleBuilderHelper = new JcgModuleBuilderHelper(codeGenerator, "Create new Jcg module", project, root);
        jcgModuleBuilderHelper.configure();

//        RunnableHelper.runWhenInitialized(intellijProject, new Runnable() {
//            public void run() {
//
//                JcgModuleBuilderHelper jcgModuleBuilderHelper = new JcgModuleBuilderHelper(codeGenerator, "Create new Jcg module", project, root);
//                jcgModuleBuilderHelper.configure();
//
//            }
//        });
    }


    @Override
    public String getGroupName() {
        return JavaModuleType.JAVA_GROUP;
    }

    @Nullable
    public ModuleWizardStep getCustomOptionsStep(WizardContext context, Disposable parentDisposable) {
        JcgIntroWizardStep jcgIntroWizardStep = new JcgIntroWizardStep();
        Disposer.register(parentDisposable, jcgIntroWizardStep);
        return jcgIntroWizardStep;
    }

    @Override
    public ModuleType getModuleType() {
        return StdModuleTypes.JAVA;
    }

    @Override
    public boolean isSuitableSdkType(SdkTypeId sdkType) {
        return sdkType instanceof JavaSdkType;
    }

    @Nullable
    @Override
    public ModuleWizardStep modifySettingsStep(@NotNull SettingsStep settingsStep) {
        return StdModuleTypes.JAVA.modifySettingsStep(settingsStep, this);
    }

    @Nullable
    protected static String getPathForOutputPathStep() {
        return null;
    }

    @Override
    public String getPresentableName() {
        return bundle.getString("jcgModuleBuilder.presentableName");
    }

    @Override
    public String getDescription() {
         return bundle.getString("jcgModuleBuilder.mainPanel.description");
    }

}
