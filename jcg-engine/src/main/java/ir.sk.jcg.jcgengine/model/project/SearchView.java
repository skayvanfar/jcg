package ir.sk.jcg.jcgengine.model.project;

import ir.sk.jcg.jcgcommon.PropertyView.annotation.Editable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.io.Serializable;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 7/7/2016
 */
@XmlAccessorType(XmlAccessType.NONE)
@Editable
public class SearchView extends View implements Serializable {

    private DataGrid dataGrid;

    public DataGrid getDataGrid() {
        return dataGrid;
    }

    @XmlElement(name = "dataGrid")
    public void setDataGrid(DataGrid dataGrid) {
        this.dataGrid = dataGrid;
    }
}
