package ir.sk.jcg.jcgengine.model.project.enums;

import ir.sk.jcg.jcgcommon.enums.EnumBase;
import ir.sk.jcg.jcgengine.model.project.*;
import ir.sk.jcg.jcgengine.model.project.component.TextFieldComponent;

import java.util.Objects;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 7/7/2016
 */
public enum ViewType implements EnumBase {

    // type 1 view: Separated views
    Display(0, "Display"),
    SEARCH(1, "Search"),
    CREATE_EDIT(2, "Create-Edit"),
    // type 2 view: All in one
    Editable_Display(3, "Display-Search-Edit");

    private Integer value;
    private String desc;

    ViewType(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    @Override
    public Integer getValue() {
        return value;
    }

    @Override
    public String getDescription() {
        return desc;
    }

    public static ViewType valueOf(Integer type) {
        for (ViewType code : ViewType.values()) {
            if (Objects.equals(type, code.getValue())) {
                return code;
            }
        }
        return null;
    }

    public View createView() {
        View view = null;
        switch (this) {
            case Display:
                view = new DisplayView();
                break;
            case SEARCH:
                SearchView searchView = new SearchView();
                DataGrid dataGrid = new DataGrid();
                searchView.setDataGrid(dataGrid);
                view = searchView;
                break;
            case CREATE_EDIT:
                view = new CreateEditView();
                break;
            case Editable_Display:
                view = new CreateEditView(); // TODO: 6/30/2017 must replaced
        }
        return view;
    }

    @Override
    public String toString() {
        return desc;
    }
}
