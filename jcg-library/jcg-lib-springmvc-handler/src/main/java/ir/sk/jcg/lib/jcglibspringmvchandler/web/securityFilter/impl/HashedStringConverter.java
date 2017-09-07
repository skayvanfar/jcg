package ir.sk.jcg.lib.jcglibspringmvchandler.web.securityFilter.impl;

import ir.sk.jcg.lib.jcglibspringmvchandler.web.securityFilter.HashedConverter;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 1/7/2017
 */
public class HashedStringConverter extends HashedConverter<HashedString> {
    public HashedStringConverter() {
        super(HashedString.class);
    }
}
