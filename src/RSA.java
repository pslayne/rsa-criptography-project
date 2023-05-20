
import java.math.BigInteger;
import java.util.Random;

public class RSA {

    public KeyPair generate_key_pair() {
		// escolher primos p e q
		BigInteger p = largePrime(512);
		BigInteger q = largePrime(512);

		// n = p * q
		// n bit length = key length
		BigInteger n = n(p, q);

		// calcular phi(n)
		BigInteger phi = getPhi(p, q);

		// 1 < e < phi(n) e mdc(e, phi) = 1
		BigInteger e = genE(phi);

		// d ≡ e^(-1) (mod phi(n))
		BigInteger d = extEuclid(e, phi)[1];

        return new KeyPair(new Key(e, n), new Key(d, n)); // public, private
    }

	public BigInteger stringCipher(String message) {
		message = message.toUpperCase();
		String cipherString = "";
		int i = 0;
		while (i < message.length()) {
			int ch = (int) message.charAt(i);
			cipherString = cipherString + ch;
			i++;
		}
		BigInteger cipherBig = new BigInteger(String.valueOf(cipherString));
		return cipherBig;
	}

	public String cipherToString(BigInteger message) {
		String cipherString = message.toString();
		String output = "";
		int i = 0;
		while (i < cipherString.length()) {
			int temp = Integer.parseInt(cipherString.substring(i, i + 2));
			char ch = (char) temp;
			output = output + ch;
			i = i + 2;
		}
		return output;
	}

	public BigInteger getPhi(BigInteger p, BigInteger q) {
		BigInteger phi = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));
		return phi;
	}

	//nº primo alto aleatório
	public BigInteger largePrime(int bits) {
		Random randomInteger = new Random();
		BigInteger largePrime = BigInteger.probablePrime(bits, randomInteger);
		return largePrime;
	}

	//greatest common denominator
	public BigInteger gcd(BigInteger a, BigInteger b) {
		if (b.equals(BigInteger.ZERO)) {
			return a;
		} else {
			return gcd(b, a.mod(b));
		}
	}

	public BigInteger[] extEuclid(BigInteger a, BigInteger b) {
		if (b.equals(BigInteger.ZERO)) return new BigInteger[] {
			a, BigInteger.ONE, BigInteger.ZERO
		}; // { a, 1, 0 }
		BigInteger[] vals = extEuclid(b, a.mod(b));
		BigInteger d = vals[0];
		BigInteger p = vals[2];
		BigInteger q = vals[1].subtract(a.divide(b).multiply(vals[2]));
		return new BigInteger[] {
			d, p, q
		};
	}

	public BigInteger genE(BigInteger phi) {
		Random rand = new Random();
		BigInteger e = new BigInteger(1024, rand);
		do {
			e = new BigInteger(1024, rand);
			while (e.min(phi).equals(phi)) { // while phi is smaller than e, look for a new e
				e = new BigInteger(1024, rand);
			}
		} while (!gcd(e, phi).equals(BigInteger.ONE)); // if gcd(e,phi) isnt 1 then stay in loop
		return e;
	}

    //cifragem e decifragem
	public BigInteger cipher(BigInteger message, Key key) {
		return message.modPow(key.exp, key.n);
	}

	public BigInteger n(BigInteger p, BigInteger q) {
		return p.multiply(q);

	}
}


