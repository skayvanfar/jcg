package ir.sk.jcg.lib.jcglibhibernatehandler.persistence.jpa;

import java.util.List;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 1/6/2017.
 */
public class SortableDataList<T> {

    private List<T> data;

    protected SortableDataList() {
    }

    public SortableDataList(List<T> data) {
        this.data = data;
    }

    public List<T> getData() {
        return data;
    }

    protected void update(List<T> data) {
        this.data = data;
    }

    public List<T> load(String sortField, SortOrder sortOrder) {
        throw new UnsupportedOperationException(
                "Lazy loading is not implemented.");
    }
}
