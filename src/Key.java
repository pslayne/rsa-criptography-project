
import java.math.BigInteger;

public class Key {
    public BigInteger exp;
    public BigInteger n;

    public Key(BigInteger exp, BigInteger n) {
        this.exp = exp;
        this.n = n;
    }
}