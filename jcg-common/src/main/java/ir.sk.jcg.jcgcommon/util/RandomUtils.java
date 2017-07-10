package ir.sk.jcg.jcgcommon.util;

import org.apache.commons.lang.ArrayUtils;

import java.util.Random;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 4/13/2016.
 */
public class RandomUtils {

    private static final int ChecksumLength = 4;

    public static final Random randomGenerator = new Random(System
            .currentTimeMillis());
    ;
    private static char[] chars = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i',
            'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
            'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8',
            '9'};

    static {
        //Change char position at startup
        for (int i = 0; i < 100; i++) {
            int m = randomGenerator.nextInt(chars.length);
            int n = randomGenerator.nextInt(chars.length);
            char t = chars[m];
            chars[m] = chars[n];
            chars[n] = t;
        }
    }

    public static String generateRandomCode(int length) {
        final StringBuilder stringBuilder = new StringBuilder(length);
        for (int i = 0; i < length; i++)
            stringBuilder.append(chars[randomGenerator.nextInt(chars.length)]);
        return stringBuilder.toString();
    }

    public static String encrypt(Long id, long bias) {
        if (id == null) return null;
        long key = ((id ^ bias) + (57 ^ bias));
        String result = key > 0 ? "" : "-";
        if (key < 0)
            key = -key;
        while (key > 0) {
            result = result + chars[((int) (key % chars.length))];
            key = (key - key % chars.length) / chars.length;
        }
        int[] checksum = new int[ChecksumLength];
        for (int i = 0; i < result.length(); i++)
            checksum[i % ChecksumLength] = checksum[i % ChecksumLength]
                    ^ (int) result.charAt(i);
        String checksumStr = "";
        for (int aChecksum : checksum) checksumStr += chars[aChecksum % chars.length];
        return result + checksumStr;
    }

    public static Long decrypt(String str, long bias) {
        if (str == null || str.length() < ChecksumLength)
            return null;
        boolean negative = str.startsWith("-");
        String checksumStr = str.substring(str.length() - ChecksumLength);
        String result = str.substring(0, str.length() - ChecksumLength);
        int[] checksum = new int[ChecksumLength];
        for (int i = 0; i < result.length(); i++)
            checksum[i % ChecksumLength] = checksum[i % ChecksumLength]
                    ^ (int) result.charAt(i);
        String checksumStr2 = "";
        for (int aChecksum : checksum) checksumStr2 += chars[aChecksum % chars.length];
        if (!checksumStr.equals(checksumStr2))
            return null;

        str = negative ? str.substring(1, str.length() - ChecksumLength) : str
                .substring(0, str.length() - ChecksumLength);
        long key = 0;
        for (int i = 0; i < str.length(); i++)
            key += (long) Math.pow((long) chars.length, i)
                    * ArrayUtils.indexOf(chars, str.charAt(i));
        if (negative)
            key = -key;
        return (key - (57 ^ bias)) ^ bias;
    }

}
