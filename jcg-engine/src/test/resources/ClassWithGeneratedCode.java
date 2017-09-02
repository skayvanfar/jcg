package tt.iii.model.hth;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tbl_hhh", schema = "", catalog = "${databaseSchemaName}")
public class ClassWithGeneratedCode implements Serializable {

    /* GENERATED CODE SECTION TYPE(Property) ENABLED START */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "hhhId", unique = false, nullable = false)
    private Long hhhId;

	/* GENERATED CODE END */

	/* GENERATED CODE SECTION TYPE(GetterSetter) ENABLED START */

    public Long getHhhId() {
        return hhhId;
    }

    public void setHhhId(Long hhhId) {
        this.hhhId = hhhId;
    };

	/* GENERATED CODE END */
}