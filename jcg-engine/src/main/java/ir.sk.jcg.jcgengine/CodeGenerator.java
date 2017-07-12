package ir.sk.jcg.jcgengine;

import ir.sk.jcg.jcgengine.model.platform.architecture.Architecture;
import ir.sk.jcg.jcgengine.model.project.*;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 4/13/2016
 */
public interface CodeGenerator {

    String JCG_CONFIG_DIR = "jcg";
    String JCG_CONFIG_PROJECT_FILE_NAME = "project.xml";
    String JCG_CONFIG_ARCHITECTURE_FILE_NAME = "architecture.xml";

    Project getJcgProject();

    void setJcgProject(Project jcgProject);

    Architecture getArchitecture();

    void setArchitecture(Architecture architecture);

    String getBaseDir();

    void setBaseDir(String baseDir);

    File getBaseXmlDir();

    void setBaseXmlDir(String baseXmlDir);

    boolean createBaseProject();

    boolean marshalling() throws JAXBException;

    boolean unmarshalling() throws JAXBException;

    boolean marshallingProject() throws JAXBException;

    boolean unmarshallingProject() throws JAXBException;

    boolean marshallingArchitecture() throws JAXBException;

    boolean unmarshallingArchitecture() throws JAXBException;

    /**
     * Add Entity to project and generate implementation
     */
    void addEntity(Entity entity, String packagePath); // TODO: 5/3/2016 use boolean parameter for implements dao

    void addAllModelElements(Map<String, Set<? extends ModelElement>> allModelElements);

    void addModelElement(ModelElement modelElement, String packagePath);

    void addView(View view, String packagePath); // TODO: 6/30/2017 better change to addBusiness // TODO: 6/30/2017 maybe packagePath not needed.
}
