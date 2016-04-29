package ir.sk.jcg.jcgengine.model.project;

import ir.sk.jcg.jcgengine.model.platform.architecture.Architecture;

import javax.xml.bind.annotation.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 4/13/2016
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public class Project {

    // base info
    private String name;
    private String persianName;
    private String packagePrefix;
    private String tableNamePattern;

//    private List<Entity> entities = new ArrayList<>();
//    private List<View> views = new ArrayList<>();
    private Model<Entity> entitiesModel = new Model<>();
    private Model<View> viewsModel = new Model<>();

    public Project() {
        entitiesModel.setName("Entity Model");
        viewsModel.setName("View Model");
    }

    public String getName() {
        return name;
    }

    @XmlAttribute(required = true)
    public void setName(String name) {
        this.name = name;
    }

    public String getPersianName() {
        return persianName;
    }

    @XmlAttribute
    public void setPersianName(String persianName) {
        this.persianName = persianName;
    }

    public String getPackagePrefix() {
        return packagePrefix;
    }

    @XmlAttribute
    public void setPackagePrefix(String packagePrefix) {
        this.packagePrefix = packagePrefix;
    }

    public String getTableNamePattern() {
        return tableNamePattern;
    }

    @XmlAttribute
    public void setTableNamePattern(String tableNamePattern) {
        this.tableNamePattern = tableNamePattern;
    }

    public Model<Entity> getEntitiesModel() {
        return entitiesModel;
    }

    @XmlElement(name = "entityModel")
    public void setEntitiesModel(Model<Entity> entitiesModel) {
        this.entitiesModel = entitiesModel;
    }

    public Model<View> getViewsModel() {
        return viewsModel;
    }

    @XmlElement(name = "viewModel")
    public void setViewsModel(Model<View> viewsModel) {
        this.viewsModel = viewsModel;
    }

//    public List<Entity> getEntities() {
//        return entities;
//    }
//
//    @XmlElement(name = "entiry")
//    public void setEntities(List<Entity> entities) {
//        this.entities = entities;
//    }
//
//    public List<View> getViews() {
//        return views;
//    }
//
//    @XmlElement(name = "view")
//    public void setViews(List<View> views) {
//        this.views = views;
//    }

    @Override
    public String toString() {
        return name;
    }
}
