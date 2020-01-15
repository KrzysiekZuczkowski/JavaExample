import java.io.*;
import java.math.BigInteger;
import java.net.*;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.swing.*;
import javax.xml.bind.DatatypeConverter;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;

public class TCPclient {

	public BigInteger losuj(BigInteger n) {
		SecureRandom secureRandom = new SecureRandom();
		BigInteger result = new BigInteger(n.bitLength(), secureRandom);
		BigInteger n1 = new BigInteger("2");
		while (result.compareTo(n) >= 0) { // jezeli jest rowna badz wieksza to  zwraca 0, 1
										
			result = new BigInteger(n.bitLength(), secureRandom).add(n1);
		}
		return result;
	}

	public PrivateKey tworzenieKlucza(byte[] k) {
		PrivateKey alicePrivKey = null;
		try {
			PKCS8EncodedKeySpec pkC8EKeySpec = new PKCS8EncodedKeySpec(k);
			KeyFactory aliceKeyFac = KeyFactory.getInstance("DSA");
			alicePrivKey = aliceKeyFac.generatePrivate(pkC8EKeySpec);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			e.printStackTrace();
		}
		return alicePrivKey;
	}

	public static void main(String[] args) throws Exception {

		// BigInteger p = new
		// BigInteger("115258481741813675423209375660109193517328583790037036964788906320492445756911468825226294715705245733596520593212");
		boolean value = true;
		SecureRandom secureRandom = new SecureRandom();
		BigInteger q = new BigInteger(1024, secureRandom);
		BigInteger p = new BigInteger("0");
		while (value) {
			q = new BigInteger(1024, secureRandom);
			p = q.multiply(new BigInteger("2")).add(new BigInteger("1"));
			value = !p.isProbablePrime(20);
		}

		BigInteger g = new TCPclient().losuj(p);
		while ((g.pow(2).mod(p).equals(1)) || (g.modPow(q, p).equals(1))) {
			g = new TCPclient().losuj(p);
		}

		System.out.println("p : " + p + p.bitLength());
		System.out.println("g : " + g + g.bitLength());

		BigInteger aliceSecValue = new BigInteger("0");
		BigInteger aliceValue = new BigInteger("0");

		try {
			Socket clientSocket = new Socket("150.254.79.161", 9996);

			DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
			BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

			// DataOutputStream out = new DataOutputStream(new BufferedOutputStream(clientSocket.getOutputStream()));
			// DataInputStream in = new DataInputStream(new BufferedInputStream(clientSocket.getInputStream()));

			aliceSecValue = new TCPclient().losuj(p);
			aliceValue = g.modPow(aliceSecValue, p);

			String strp = p.toString();
			String strg = g.toString();
			String strAliceValue = aliceValue.toString();

			String wiadomosc = strp + strg + strAliceValue;

			Signature s = Signature.getInstance("SHA1WithDSA");
			byte[] key = Files.readAllBytes(Paths.get("privkey"));

			s.initSign(new TCPclient().tworzenieKlucza(key));

			byte[] message = wiadomosc.getBytes(StandardCharsets.UTF_8);
			s.update(message);
			byte[] sigBytes = s.sign();

			outToServer.writeBytes(strp + '\n' + strg +  '\n' + strAliceValue + '\n');

			System.out.println("Poddpis Alice : " + DatatypeConverter.printHexBinary(sigBytes));

			outToServer.writeBytes(DatatypeConverter.printHexBinary(sigBytes) + '\n');

			// outToServer.flush();
			String strBobValue = inFromServer.readLine();

			

			if (strBobValue.equals("Brak weryfikacji podpisu")) {
					System.out.println("Bob vaulue : " + strBobValue);
			} 
			else {
				String wiadomosc1 = JOptionPane.showInputDialog("Podaj wiadomsc");
				System.out.println(
						"public value Alice : " + aliceValue + '\n' + "public value Bob :   " + strBobValue + '\n');

				BigInteger secret = new BigInteger(strBobValue).modPow(aliceSecValue, p);

				secureRandom = new SecureRandom();
				byte[] iv = new byte[16];
				secureRandom.nextBytes(iv);
				IvParameterSpec ivSpec = new IvParameterSpec(iv);
				String siv = DatatypeConverter.printBase64Binary(iv) + '\n';

				MessageDigest md = MessageDigest.getInstance("SHA");
				String data = secret.toString();

				byte[] dataDigest = md.digest(data.getBytes());
				byte[] keybytes = new byte[16];
				System.arraycopy(ByteBuffer.allocate(21).put(dataDigest).array(), 1, keybytes, 0, 16);
				// dataDigest = new byte[16];

				SecretKey sKey = new SecretKeySpec(keybytes, "AES");
				System.out.println("Wspolny sekret Alice : " + secret);
				// byte[] key = Files.readAllBytes(Paths.get("klucz1"));

				// byte[] readBytes = new byte[10000];
				// int num = sockIn.read(readBytes);
				// String resp = new String(readBytes, 0, num);

				Cipher ci = Cipher.getInstance("AES/CBC/PKCS5Padding");
				ci.init(Cipher.ENCRYPT_MODE, sKey, ivSpec);
				byte[] input = (wiadomosc1).getBytes("UTF-8");
				byte[] encrypted = ci.doFinal(input);
				String strencrypted = DatatypeConverter.printBase64Binary(encrypted) + '\n';

				outToServer.writeBytes(siv + strencrypted);
				String wiadomoscServer;
				while ((wiadomoscServer = inFromServer.readLine()) != null) {
					System.out.println(wiadomoscServer);
				}

			}
			// in.close();
			// out.close();
			outToServer.close();
			inFromServer.close();
			clientSocket.close();
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (InvalidAlgorithmParameterException e) {
			e.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
	}
}
// MessageDigest md = MessageDigest.getInstance("SHA");
// String data = "String I wish to calculate digest for";
// byte[] dataDigest = md.digest(data.getBytes());
//
//
//
//
// BigInteger p = new BigInteger("23");
// BigInteger g = new BigInteger("5");
// DHParameterSpec dhp = new DHParameterSpec(p, g);
//
//// Bob tworzy losow¹ liczbê
// KeyPairGenerator bobkeyGen = KeyPairGenerator.getInstance("DH", "BC");
// bobkeyGen.initialize(dhp, new SecureRandom());
//
//// Bobce oblicza u = g^a mod p i wysyl³a do Alice
// KeyAgreement bobkeyAgree = KeyAgreement.getInstance("DH", "BC");
// KeyPair bobPar = bobkeyGen.generateKeyPair();
//
// bobkeyAgree.init(bobPar.getPrivate());
// Key bobKey = bobkeyAgree.doPhase(alicePar.getPublic(), true);
//
//// Alice tworzy losow¹ liczbê klucz prywatny
// KeyPairGenerator alikeyGen = KeyPairGenerator.getInstance("DH", "BC");
// alikeyGen.initialize(dhp, new SecureRandom());
//
//// Alice oblicza u = g^a mod p i wysyl³a do Boba klucz publiczny
// KeyAgreement alikeyAgree = KeyAgreement.getInstance("DH", "BC");
// KeyPair alicePar = alikeyGen.generateKeyPair();
//
// alikeyAgree.init(alicePar.getPrivate());
// Key aliceKey = alikeyAgree.doPhase(bobPar.getPublic(), true);
//
//
// MessageDigest hash = MessageDigest.getInstance("SHA1", "BC");
// byte[] aliSecret = hash.digest(alikeyAgree.generateSecret());
// byte[] bobSecret = hash.digest(bobkeyAgree.generateSecret());
