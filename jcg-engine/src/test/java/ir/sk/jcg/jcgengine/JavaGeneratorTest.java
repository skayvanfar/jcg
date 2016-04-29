package ir.sk.jcg.jcgengine;


import ir.sk.jcg.jcgcommon.util.XMLParser;
import ir.sk.jcg.jcgengine.model.platform.architecture.Architecture;
import ir.sk.jcg.jcgengine.model.platform.architecture.ThreeLayerArchitecture;
import ir.sk.jcg.jcgengine.model.platform.technology.*;
import ir.sk.jcg.jcgengine.model.platform.technology.Maven.Maven;
import ir.sk.jcg.jcgengine.model.project.Entity;
import ir.sk.jcg.jcgengine.model.project.Model;
import ir.sk.jcg.jcgengine.model.project.Package;
import ir.sk.jcg.jcgengine.model.project.Project;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 4/21/2016
 */
public class JavaGeneratorTest {
    @Test
    public void marshallingProject() throws Exception {
        Project project = new Project();
        project.setName("Project");
        project.setPackagePrefix("ir.sk");

        Model<Entity> entityModel = new Model<>();
        entityModel.setName("Entity Model");

        Package<Entity> entityPackage = new Package<>();
        entityPackage.setName("com");

        List<Entity> entities = new ArrayList<>();
        Entity entity = new Entity();
        entity.setName("Person");
        entities.add(entity);

        entityPackage.setElements(entities);
        List<Package<Entity>> packageList = new ArrayList<>();
        packageList.add(entityPackage);
        entityModel.setPackages(packageList);
        project.setEntitiesModel(entityModel);

        XMLParser.marshaling(new File("e:/h.xml"), project);

    }

    @Test
    public void unmarshallingProject() throws Exception {

    }

    @Test
    public void marshallingArchitecture() throws Exception {
      //  generator.marshallingArchitecture();
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

    private Generator generator;

    @Before
    public void setUp() throws Exception {
//        Project proejct = new Project();
//        proejct.setName("project");
//        proejct.setPackagePrefix("ir.sk");
//        proejct.setPersianName("پروژ]");
//
//        generator = new JavaGenerator();
//
//        Architecture architecture = new ThreeLayerArchitecture();
//        BuildTechnology buildTechnology = new Maven();
//        MVCTechnology mvcTechnology = new SpringMVC();
//
//
//        Technology technology = new Hibernate();
//        List<Technology> technologies = new ArrayList<>();
//        technologies.add(technology);
//        technologies.add(buildTechnology);
//        technologies.add(mvcTechnology);
//        architecture.setTechnologies(technologies);
//
//        architecture.setBasePackageName("ir.sk");
//        architecture.setBaseDir(new File("E:/"));
//
//        generator.setArchitecture(architecture);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void createBaseProject() throws Exception {

    }

    @Test
    public void addEntity() throws Exception {
   //     generator.addEntity(null);
    }

}