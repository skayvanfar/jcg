package ir.sk.jcg.jcgengine.model.platform.architecture;

import ir.sk.jcg.jcgcommon.enums.EnumBase;

import java.util.Objects;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 4/19/2016
 */
public enum ArchitectureType implements EnumBase {

    THREE_LAYER(1, "Three Layer"), // sample
    TWO_LAYER(0, "Two Layer"); // sample

    private Integer value;
    private String description;

    ArchitectureType(Integer value, String description) {
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
                architecture = new SpringWebArchitecture();
                break;
        }
        return architecture;
    }

    public static ArchitectureType valueOf(Integer type) {
        for (ArchitectureType code : ArchitectureType.values()) {
            if (Objects.equals(type, code.getValue())) {
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
