
public class KeyPair {
    public Key public_key;
    public Key private_key;

    public KeyPair(Key public_key, Key private_key) {
        this.public_key = public_key;
        this.private_key = private_key;
    }

    public String toString() {
        String r = "Kpub = {" + public_key.exp + ", " + public_key.n + "}\n";
        r += "Kpri = {" + private_key.exp + ", " + private_key.n + "}\n";

        return r;
    }
}
