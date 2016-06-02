package ir.sk.jcg.jcgcommon.util;

import java.math.BigInteger;
import java.security.SecureRandom;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 6/2/2016.
 */
public class SessionIdentifierGenerator {
    private SecureRandom random = new SecureRandom();

    public String nextSessionId() {
        return new BigInteger(130, random).toString(32);
    }

}
