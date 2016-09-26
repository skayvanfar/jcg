package ir.sk.jcg.jcgintellijpluginapp.ui.util.execution;

import com.intellij.openapi.progress.ProgressIndicator;
import org.jetbrains.annotations.NotNull;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 4/19/2016
 */
public interface CallBackHandler {
    boolean executeTask(@NotNull ProgressIndicator progressIndicator);
}
