package ir.sk.jcg.lib.jcglibspringmvchandler.web.securityFilter.impl;

import ir.sk.jcg.jcglibcommon.util.RandomUtils;
import ir.sk.jcg.lib.jcglibspringmvchandler.web.securityFilter.FilterConfiguration;
import ir.sk.jcg.lib.jcglibspringmvchandler.web.securityFilter.Hashed;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 1/7/2017
 */
public class HashedLong extends Hashed<Long> {

    public HashedLong() {
    }

    public HashedLong(Long value) {
        super(value);
    }

    @Override
    protected String encrypt(Long value) {
        final int bias = FilterConfiguration.i().createOrGetSessionHashLongBias();
        return RandomUtils.encrypt(value, bias);
    }

    @Override
    protected Long decrypt(String value) {
        final int bias = FilterConfiguration.i().createOrGetSessionHashLongBias();
        return RandomUtils.decrypt(value, bias);
    }
}
