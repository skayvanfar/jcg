package ir.sk.jcg.jcgengine;


import ir.sk.jcg.jcgcommon.util.XMLParser;
import ir.sk.jcg.jcgengine.model.project.Entity;
import ir.sk.jcg.jcgengine.model.project.Package;
import ir.sk.jcg.jcgengine.model.project.Project;
import ir.sk.jcg.jcgengine.model.project.Schema;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.HashSet;
import java.util.Set;


/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 4/21/2016
 */
public class JavaCodeGeneratorTest {
    @Test
    public void marshallingProject() throws Exception {
        Project project = new Project();
        project.setName("Project");
        project.setPackagePrefix("ir.sk");

        Schema<Entity> entitySchema = new Schema<>();
        entitySchema.setName("Entity Schema");

        Package<Entity> entityPackage = new Package<>();
        entityPackage.setName("com");

        Set<Entity> entities = new HashSet<>();
        Entity entity = new Entity();
        entity.setName("Person");
        entities.add(entity);

        entityPackage.setElements(entities);
        Set<Package<Entity>> packageList = new HashSet<>();
        packageList.add(entityPackage);
        entitySchema.setPackages(packageList);
        project.setEntitiesSchema(entitySchema);

       // XMLParser.marshaling(new File("e:/cropper.xml"), project);

    }

    @Test
    public void unmarshallingProject() throws Exception {

    }

    @Test
    public void marshallingArchitecture() throws Exception {
        //  codeGenerator.marshallingArchitecture();
    }

    @Test
    public void unmarshallingArchitecture() throws Exception {

    }

    @Test
    public void getProject() throws Exception {

    }

    @Test
    public void setProject() throws Exception {

    }

    @Test
    public void getArchitecture() throws Exception {

    }

    @Test
    public void setArchitecture() throws Exception {

    }

    private CodeGenerator codeGenerator;

    @Before
    public void setUp() throws Exception {
//        Project proejct = new Project();
//        proejct.setName("project");
//        proejct.setPackagePrefix("ir.sk");
//        proejct.setPersianName("پروژ]");
//
//        codeGenerator = new JavaCodeGenerator();
//
//        Architecture architecture = new SpringWebArchitecture();
//        BuildTechnologyHandler buildTechnology = new MavenHandler();
//        MVCTechnologyHandler mvcTechnology = new SpringMVCHandler();
//
//
//        TechnologyHandler technology = new HibernateHandler();
//        List<TechnologyHandler> technologies = new ArrayList<>();
//        technologies.add(technology);
//        technologies.add(buildTechnology);
//        technologies.add(mvcTechnology);
//        architecture.setTechnologies(technologies);
//
//        architecture.setPackagePrefixName("ir.sk");
//        architecture.setBaseProjectPath(new File("E:/"));
//
//        codeGenerator.setArchitecture(architecture);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void createBaseProject() throws Exception {

    }

    @Test
    public void addEntity() throws Exception {
        //     codeGenerator.addEntity(null);
    }

}