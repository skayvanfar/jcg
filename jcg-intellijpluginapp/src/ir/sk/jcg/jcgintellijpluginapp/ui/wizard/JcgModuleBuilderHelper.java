package ir.sk.jcg.jcgintellijpluginapp.ui.wizard;

import com.intellij.openapi.vfs.VirtualFile;
import ir.sk.jcg.jcgengine.CodeGenerator;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBException;


/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 4/20/2016
 */
class JcgModuleBuilderHelper {

    private static final Logger logger = LoggerFactory.getLogger(JcgModuleBuilderHelper.class);

    // private final Project jcgProject;
    private final CodeGenerator codeGenerator;

    private final String commandName;
    private com.intellij.openapi.project.Project intellijProject;
    private VirtualFile root;

    JcgModuleBuilderHelper(@NotNull CodeGenerator codeGenerator, String commaneName, com.intellij.openapi.project.Project intellijProject, VirtualFile root) {
        this.codeGenerator = codeGenerator;
        this.intellijProject = intellijProject;
        this.root = root;
        this.commandName = commaneName;
    }

    /**
     * Call Engine to make base Architecture
     */
    void configure() {
        codeGenerator.getArchitecture().createBaseArchitecture();

        try {
            boolean result = codeGenerator.marshalling();
            if (result)
                logger.info("Marshaling finished correctly.");
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}
