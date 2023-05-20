
import java.math.BigInteger;

public class App {
    public static void main(String[] args) throws Exception {
        RSA rsa = new RSA();
        KeyPair key_pair = rsa.generate_key_pair();

        // encryption / decryption example
		String message = "Encryption test example";
		// Convert string to numbers using a cipher
		BigInteger cipherMessage = rsa.stringCipher(message);
		// Encrypt the ciphered message
		BigInteger encrypted = rsa.cipher(cipherMessage, key_pair.public_key);
		// Decrypt the encrypted message
		BigInteger decrypted = rsa.cipher(encrypted, key_pair.private_key);
		// Uncipher the decrypted message to text
		String restoredMessage = rsa.cipherToString(decrypted);

		System.out.println("Original message: " + message);
		System.out.println("Ciphered: " + cipherMessage);
		System.out.println("Encrypted: " + encrypted);
		System.out.println("Decrypted: " + decrypted);
		System.out.println("Restored: " + restoredMessage);
    }

}
