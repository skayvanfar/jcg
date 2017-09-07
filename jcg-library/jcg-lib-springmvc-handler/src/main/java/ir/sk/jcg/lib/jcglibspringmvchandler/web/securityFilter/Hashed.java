package ir.sk.jcg.lib.jcglibspringmvchandler.web.securityFilter;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 1/7/2017
 */
public abstract class Hashed<T> {

    private T value;
    private String encrypted;

    protected Hashed() {
    }

    protected Hashed(T value) {
        setValue(value);
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
        this.encrypted = encrypt(value);
    }

    public String getEncrypted() {
        return encrypted;
    }

    public void setEncrypted(String encrypted) {
        this.encrypted = encrypted;
        this.value = decrypt(encrypted);
    }

    protected abstract String encrypt(T value);

    protected abstract T decrypt(String value);
}
