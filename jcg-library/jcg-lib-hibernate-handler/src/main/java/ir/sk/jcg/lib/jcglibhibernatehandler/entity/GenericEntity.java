package ir.sk.jcg.lib.jcglibhibernatehandler.entity;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 9/28/2017.
 */
public class GenericEntity<T> {

    protected T id;

    public T getId() {
        return id;
    }

    public void setId(T id) {
        this.id = id;
    }
}
