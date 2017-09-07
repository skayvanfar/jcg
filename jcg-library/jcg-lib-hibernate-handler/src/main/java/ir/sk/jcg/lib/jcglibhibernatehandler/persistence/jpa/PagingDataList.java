package ir.sk.jcg.lib.jcglibhibernatehandler.persistence.jpa;

import java.util.List;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 1/6/2017.
 */
public class PagingDataList<T> extends SortableDataList<T> {

    // start page
    private int page;

    // count of pages
    private int pageCount;

    // count of records in a page
    private int pageSize;

    // count of all rerods = data.size()
    private int rowCount;

    protected PagingDataList() {
    }

    public PagingDataList(List<T> data, int rowCount, int page, int pageSize) {
        super(data);
        this.page = page;
        this.rowCount = rowCount;
        if (pageSize > 0) {
            this.pageCount = rowCount / pageSize;
            if (rowCount % pageSize != 0)
                this.pageCount++;
        } else
            this.pageCount = 0;
        this.pageSize = pageSize;
    }

    protected void update(List<T> data, int rowCount, int page, int pageSize) {
        update(data);
        this.page = page;
        this.rowCount = rowCount;
        if (pageSize > 0) {
            this.pageCount = rowCount / pageSize;
            if (rowCount % pageSize != 0)
                this.pageCount++;
        } else
            this.pageCount = 0;
        this.pageSize = pageSize;
    }

    public int getPage() {
        return page;
    }

    public int getPageCount() {
        return pageCount;
    }

    public int getPageSize() {
        return pageSize;
    }

    public int getRowCount() {
        return rowCount;
    }

    @Override
    public List<T> load(String sortField, SortOrder sortOrder) {
        return load(0, pageSize, sortField, sortOrder);
    }

    public List<T> load(int page, String sortField, SortOrder sortOrder) {
        return load(page, pageSize, sortField, sortOrder);
    }

    public List<T> load(int page, int pageSize, String sortField,
                        SortOrder sortOrder) {
        throw new UnsupportedOperationException(
                "Lazy loading is not implemented.");
    }
}
