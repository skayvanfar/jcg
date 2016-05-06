package ir.sk.jcg.jcgengine.model.platform.architecture;

import ir.sk.jcg.jcgcommon.enums.EnumBase;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 4/19/2016
 */
public enum ArchitectureType implements EnumBase {

    TWO_LAYER(0, "Two Layer"), // sample
    THREE_LAYER(1, "Three Layer"); // sample

    private Integer value;
    private String description;

    private ArchitectureType(Integer value, String description) {
        this.value = value;
        this.description = description;
    }

    @Override
    public Integer getValue() {
        return value;
    }

    @Override
    public String getDescription() {
        return description;
    }

    public Architecture architectureBuilder() {
        Architecture architecture = null;
        switch (value) {
            case 0:
           //     architecture = new TwoLayerArchitecture();
                break;
            case 1:
                architecture = new ThreeLayerArchitecture();
                break;
        }
        return architecture;
    }

    public static ArchitectureType valueOf(Integer type) {
        for (ArchitectureType code : ArchitectureType.values()) {
            if (type == code.getValue()) {
                return code;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return description;
    }

}
