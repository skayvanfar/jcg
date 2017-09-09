package ir.sk.jcg.jcgintellijpluginapp.ui.dto;

import ir.sk.jcg.jcgengine.model.project.Entity;
import ir.sk.jcg.jcgengine.model.project.enums.ViewType;

import java.io.Serializable;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 7/7/2016
 */
public class ViewDto implements Serializable {

    private String name;
    private String viewFileName;
    private Entity targetEntity;
    private ViewType viewType;

    public ViewDto() {
    }

    public ViewDto(String name, String viewFileName, Entity targetEntity, ViewType viewType) {
        this.name = name;
        this.viewFileName = viewFileName;
        this.targetEntity = targetEntity;
        this.viewType = viewType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getViewFileName() {
        return viewFileName;
    }

    public void setViewFileName(String viewFileName) {
        this.viewFileName = viewFileName;
    }

    public Entity getTargetEntity() {
        return targetEntity;
    }

    public void setTargetEntity(Entity targetEntity) {
        this.targetEntity = targetEntity;
    }

    public ViewType getViewType() {
        return viewType;
    }

    public void setViewType(ViewType viewType) {
        this.viewType = viewType;
    }

}
