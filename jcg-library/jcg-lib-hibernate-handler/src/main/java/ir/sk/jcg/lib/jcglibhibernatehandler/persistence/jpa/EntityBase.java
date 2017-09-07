package ir.sk.jcg.lib.jcglibhibernatehandler.persistence.jpa;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 1/5/2017.
 */
public abstract class EntityBase {
    public abstract long getId();

    @Deprecated
    protected String getInValidProperty() {
        return null;
    }

}
