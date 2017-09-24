package ir.sk.jcg.jcgintellijpluginapp.ui.dto;

import ir.sk.jcg.jcgengine.model.project.EntityElement;
import ir.sk.jcg.jcgengine.model.project.Property;
import ir.sk.jcg.jcgengine.model.project.enums.InputComponentType;
import ir.sk.jcg.jcgengine.model.project.enums.OutputComponentType;

import java.io.Serializable;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 7/8/2016
 */
public class ComponentDto implements Serializable {

    private String name;
    private EntityElement targetEntityElement;
    private InputComponentType inputComponentType;
    private OutputComponentType outputComponentType;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public EntityElement getTargetEntityElement() {
        return targetEntityElement;
    }

    public void setTargetEntityElement(EntityElement targetEntityElement) {
        this.targetEntityElement = targetEntityElement;
    }

    public InputComponentType getInputComponentType() {
        return inputComponentType;
    }

    public void setInputComponentType(InputComponentType inputComponentType) {
        this.inputComponentType = inputComponentType;
    }

    public OutputComponentType getOutputComponentType() {
        return outputComponentType;
    }

    public void setOutputComponentType(OutputComponentType outputComponentType) {
        this.outputComponentType = outputComponentType;
    }
}
