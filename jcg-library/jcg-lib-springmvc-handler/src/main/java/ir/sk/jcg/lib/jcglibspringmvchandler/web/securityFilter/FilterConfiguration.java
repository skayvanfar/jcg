package ir.sk.jcg.lib.jcglibspringmvchandler.web.securityFilter;

import ir.sk.jcg.jcglibcommon.util.RandomUtils;
import ir.sk.jcg.jcglibcommon.util.SpringUtils;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 1/7/2017
 */
public class FilterConfiguration {

  //  static final Logger logger = Logger.getLogger(FilterConfiguration.class); // TODO: 1/7/2017
    private static FilterConfiguration instance;

    public static FilterConfiguration i() {
        if (instance == null)
            instance = new FilterConfiguration();
        return instance;
    }

    private String paramCSRF;
    private String sessionCSRF;
    private String sessionHashLongBias = "hash_long_bias";

    public String getParamCSRF() {
        return paramCSRF;
    }

    public void setParamCSRF(String paramCSRF) {
        this.paramCSRF = paramCSRF;
    }

    public String getSessionCSRF() {
        return sessionCSRF;
    }

    public void setSessionCSRF(String sessionCSRF) {
        this.sessionCSRF = sessionCSRF;
    }

    public String getSessionHashLongBias() {
        return sessionHashLongBias;
    }

    public void setSessionHashLongBias(String sessionHashLongBias) {
        this.sessionHashLongBias = sessionHashLongBias;
    }

    public int createOrGetSessionHashLongBias() {
        final Object sessionObj = SpringUtils.session(sessionHashLongBias);
        if(sessionObj!=null)
            return (Integer) sessionObj;
        final Integer sessionValue = RandomUtils.randomGenerator.nextInt(1000) * 1000 + RandomUtils.randomGenerator.nextInt(1000);
        SpringUtils.session(sessionHashLongBias, sessionValue);
        return sessionValue;
    }
}
