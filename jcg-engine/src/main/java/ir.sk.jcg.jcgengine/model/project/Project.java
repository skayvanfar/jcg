package ir.sk.jcg.jcgengine.model.project;

import ir.sk.jcg.jcgengine.model.project.annotation.Editable;
import ir.sk.jcg.jcgengine.model.project.annotation.Prop;
import ir.sk.jcg.jcgengine.model.project.enums.ModelInfoType;

import javax.xml.bind.annotation.*;
import java.io.Serializable;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 4/13/2016
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
@Editable
public class Project extends Element implements Serializable {

    // base info
    @Prop
    private String persianName;
    @Prop(isRequired = true)
    private String packagePrefix;
    @Prop(isRequired = true)
    private String tableNamePattern;
    @Prop(editor = CellType.NON_EDITABLE_COMBO, isRequired = true)
    private ModelInfoType modelInfoType;

    private Model<Entity> entitiesModel = new Model<>();
    private Model<View> viewsModel = new Model<>();

    public Project() {
        super();
        entitiesModel.setName("Entity Model");
        viewsModel.setName("View Model");
    }

    /**
     * Copy constructor
     * */
    public Project(Project anotherProject) {
        super(anotherProject);
        this.persianName = anotherProject.getPersianName();
        this.packagePrefix = anotherProject.getPackagePrefix();
        this.tableNamePattern = anotherProject.getTableNamePattern();
        this.entitiesModel = anotherProject.getEntitiesModel();
        this.viewsModel = anotherProject.getViewsModel();
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

    public ModelInfoType getModelInfoType() {
        return modelInfoType;
    }

    @XmlAttribute
    public void setModelInfoType(ModelInfoType modelInfoType) {
        this.modelInfoType = modelInfoType;
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

}
