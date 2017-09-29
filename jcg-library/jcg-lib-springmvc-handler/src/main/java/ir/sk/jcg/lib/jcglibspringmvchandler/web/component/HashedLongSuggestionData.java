package ir.sk.jcg.lib.jcglibspringmvchandler.web.component;

import ir.sk.jcg.jcglibcommon.web.SuggestionData;
import ir.sk.jcg.lib.jcglibspringmvchandler.web.securityFilter.impl.HashedLong;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 9/28/2017.
 */
public class HashedLongSuggestionData implements SuggestionData {
    private String title;
    private HashedLong value;

    public HashedLongSuggestionData() {
    }

    public HashedLongSuggestionData(String title, long id) {
        this.title = title;
        this.value = new HashedLong(id);
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getValue() {
        return value.getEncrypted();
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setValue(HashedLong value) {
        this.value = value;
    }

    public Long getId() {
        return value.getValue();
    }

}
