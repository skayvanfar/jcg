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
    private Long testId;
	/* GENERATED CODE END */

    /* GENERATED CODE SECTION TYPE(GetterSetter) ENABLED START */
    public Long getTestId() {
        return testId;
    }

    public void setTestId(Long testId) {
        this.testId = testId;
    }
    /* GENERATED CODE END */

    /* GENERATED CODE SECTION TYPE(Controller) ENABLED START */
    @RequestMapping(value="/", method = RequestMethod.GET)
    public void addUser(String User) {
        dao.saveUser(user);
    }
    /* GENERATED CODE END */
}