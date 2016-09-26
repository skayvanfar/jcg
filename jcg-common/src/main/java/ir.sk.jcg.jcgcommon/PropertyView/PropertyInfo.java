package ir.sk.jcg.jcgcommon.PropertyView;

import ir.sk.jcg.jcgcommon.PropertyView.annotation.Prop;

import java.lang.reflect.Field;

/**
 * contain information about a property of object
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 5/10/2016
 */
public class PropertyInfo {

    public PropertyInfo(String name, Object value, boolean required, ComponentType componentType, Class<?> type) {
    }

    // Builder Pattern
    private static class Builder {
        // Required parameters
        private String name;
        private String label;
        private Object value;
        private boolean editable;
        private boolean editableInWizard;
        private boolean required;

        // Optional parameters - initialized to default values
        private ComponentType componentType;
        private String[] values;
        private Object object;
        private Class<?> typeClass;

        public Builder(String name, Object value,boolean editable, boolean editableInWizard, boolean required) {
            this.name = name;
            this.value = value;
            this.editable = editable;
            this.editableInWizard = editableInWizard;
            this.required = required;
        }

        public Builder label(String val) {
            label = val;
            return this;
        }
        public Builder componentType(ComponentType val) {
            componentType = val;
            return this;
        }
        public Builder values(String[] val) {
            values = val;
            return this;
        }
        public Builder object(Object val) {
            object = val;
            return this;
        }
        public Builder typeClass(Class<?> val) {
            typeClass = val;
            return this;
        }

        public PropertyInfo build() {
            return new PropertyInfo(this);
        }
    }

    private String name;
    private String label;
    private Object value;
    private boolean editable;
    private boolean editableInWizard;
    private boolean required;
    private ComponentType componentType;
    private String[] values;
    private Object object;
    private Class<?> typeClass;

    public PropertyInfo(Builder builder) {
        this.name = builder.name;
        this.label = builder.label;
        this.value = builder.value;
        this.editable = builder.editable;
        this.required = builder.required;
        this.componentType = builder.componentType;
        this.values = builder.values;
        this.object = builder.object;
        this.typeClass = builder.typeClass;
    }

    public PropertyInfo(String name, String label,Object value, boolean editable, boolean required, ComponentType componentType, String[] values, Object object, Class<?> typeClass) {
        this.name = name;
        this.label = label;
        this.value = value;
        this.editable = editable;
        this.required = required;
        this.componentType = componentType;
        this.values = values;
        this.object = object;
        this.typeClass = typeClass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public boolean isEditable() {
        return editable;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
    }

    public boolean isEditableInWizard() {
        return editableInWizard;
    }

    public void setEditableInWizard(boolean editableInWizard) {
        this.editableInWizard = editableInWizard;
    }

    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

    public ComponentType getComponentType() {
        return componentType;
    }

    public void setComponentType(ComponentType componentType) {
        this.componentType = componentType;
    }

    public String[] getValues() {
        return values;
    }

    public void setValues(String[] values) {
        this.values = values;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public Class<?> getTypeClass() {
        return typeClass;
    }

    public void setTypeClass(Class<?> typeClass) {
        this.typeClass = typeClass;
    }


    /**
     * @param field of property
     * @param o owner of property
     * @throws IllegalAccessException if o not have that property
     */
    public PropertyInfo(Field field, Object o) throws IllegalAccessException {
        field.setAccessible(true);
        Object value =  field.get(o);
        Prop prop = field.getAnnotation(Prop.class);

        this.name = field.getName();
        this.label = prop.label();
        this.value = value;
        this.editable = prop.editable();
        this.editableInWizard = prop.editableInWizard();
        this.required = prop.required();
        this.componentType = prop.componentType();
        this.values = prop.values();
        this.object = o;
        this.typeClass = field.getType();

    }
}
