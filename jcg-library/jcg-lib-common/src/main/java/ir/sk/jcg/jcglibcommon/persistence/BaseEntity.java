package ir.sk.jcg.jcglibcommon.persistence;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 1/5/2017.
 */
public abstract class BaseEntity {
    public abstract long getId();

    @Deprecated
    public String getInValidProperty() {
        return null;
    }

}
