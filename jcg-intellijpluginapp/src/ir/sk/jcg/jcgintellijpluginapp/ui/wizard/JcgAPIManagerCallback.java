package ir.sk.jcg.jcgintellijpluginapp.ui.wizard;

import com.intellij.openapi.progress.ProgressIndicator;
import ir.sk.jcg.jcgintellijpluginapp.ui.util.execution.CallBackHandler;
import org.jetbrains.annotations.NotNull;

/**
 * Call after create project
 *
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 4/19/2016
 */
public class JcgAPIManagerCallback implements CallBackHandler {

    @Override
    public boolean executeTask(@NotNull ProgressIndicator progressIndicator) {
        progressIndicator.setIndeterminate(true);
        progressIndicator.setText("Creating Base Project");


        //  MavenDependenciesManager comManager = new MavenDependenciesManager();

        //    return comManager.retrieveAsposeMavenDependencies(progressIndicator);
        return true; //todo: must define

    }
}
