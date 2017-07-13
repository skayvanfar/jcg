import javax.jws.WebService;
import java.io.Serializable;

/**
 * Simple test class
 */
@WebService
public class FirstTest extends Testt {
    /**
     * An example static value
     */
    public final static     int staticValue = 5;

    /**
     * Value A
     */
            int aValue;

    /**
     * Value B
     */
    int bValue;

            /**
        * A simple static method that prints two integer value
     *
     * @param a The first value
     * @param b The second value
     * @return None
     */
    public static void static_method1(int a, int b)
    {
        System.out.println(a);
        System.out.println(b);
    }

    /**
     * The main entry point
     *
     * @param argv Command line arguments
     * @return None
     */
    public static void main(String argv[])          {
        static_method1(10, 20);
    }
}



 package tt.iii.model.hth;
         import java.io.Serializable;

         import java.util.*;

         import javax.persistence.*;



@Entity
@Table(name = "tbl_hhh", schema = "", catalog = "${databaseSchemaName}")
public class hhh implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.            AUTO
    )
    @Column(name = "hhhId", unique = false, nullable = false)
    private Long hhhId;




    public Long getHhhId() {
        return hhhId;
    }

    public void setHhhId(Long hhhId) {
        this.hhhId = hhhId;
    }
    ;



}