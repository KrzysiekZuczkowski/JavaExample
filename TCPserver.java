import java.io.*;
import java.math.BigInteger;
import java.net.*;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import javax.crypto.spec.IvParameterSpec;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;

public class TCPserver {

	public BigInteger losuj(BigInteger n) {
		SecureRandom secureRandom = new SecureRandom();
		BigInteger result = new BigInteger(n.bitLength(), secureRandom);
		BigInteger n1 = new BigInteger("1");
		while (result.compareTo(n) >= 0) { // jezeli jest rowna badz wieksza to
											// zwraca 0, 1
			result = new BigInteger(n.bitLength(), secureRandom).add(n1);
		}
		return result;
	}

	public PublicKey tworzenieKlucza(byte[] k) {
		PublicKey alicePubKey = null;
		try {
			X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(k);
			KeyFactory aliceKeyFac = KeyFactory.getInstance("DSA");
			alicePubKey = aliceKeyFac.generatePublic(x509KeySpec);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			e.printStackTrace();
		}
		return alicePubKey;
	}
	
	static void copy(InputStream in, OutputStream  out) throws IOException {
        int c;
        while ((c = in.read()) != -1) 
        	out.write(c);
      }


	public static void main(String[] args) throws Exception {

		// BigInteger p = new
		// BigInteger("115258481741813675423209375660109193517328583790037036964788906320492445756911468825226294715705245733596520593212");
		// BigInteger g = new BigInteger("2");

		BigInteger bobSecValue = new BigInteger("0");
		BigInteger bobValue = new BigInteger("0");
 
		ServerSocket welcomSocket = new ServerSocket(9996);
		try { 
			// byte[] key = Files.readAllBytes(Paths.get("klucz1"));
			while (true) {
				Socket conectionSocket = welcomSocket.accept();
				try {
					BufferedReader inFromClient = new BufferedReader(
							new InputStreamReader(conectionSocket.getInputStream()));
					DataOutputStream outToClient = new DataOutputStream(conectionSocket.getOutputStream());

//					DataOutputStream out = new DataOutputStream(new BufferedOutputStream(conectionSocket.getOutputStream()));
//					DataInputStream in = new DataInputStream((conectionSocket.getInputStream()));
					    
					String strp = inFromClient.readLine();
					String strg = inFromClient.readLine();
					String strAliceValue = inFromClient.readLine(); 

//					FileOutputStream out1 = new FileOutputStream("podpis");
//					
//					   byte[] data = new byte[4096];
//					   int bytesRead, totalBytes = 0;
//					   while( (bytesRead = in.read(data)) > 0) {
//					     out1.write(data, 0, bytesRead);
//					     totalBytes += bytesRead;
//					   }
//					   out1.close();
					String strsig = inFromClient.readLine();
					byte[] sigBytes = DatatypeConverter.parseHexBinary(strsig);
					//System.arraycopy(ByteBuffer.allocate(count).put(buffer).array(), 0, sigBytes, 0, count);
					
					String wiadomosc = strp + strg + strAliceValue;
					byte[] message = wiadomosc.getBytes(StandardCharsets.UTF_8);

					Signature s = Signature.getInstance("SHA1WithDSA");
					byte[] key = Files.readAllBytes(Paths.get("publickey"));
					s.initVerify(new TCPserver().tworzenieKlucza(key));
					s.update(message);

					if (s.verify(sigBytes)) {
						BigInteger p = new BigInteger(strp);
						BigInteger g = new BigInteger(strg);

						bobSecValue = new TCPserver().losuj(p);
						bobValue = g.modPow(bobSecValue, p);
						String strBobValue = bobValue.toString() + '\n';
						outToClient.writeBytes(strBobValue);
						BigInteger secret = new BigInteger(strAliceValue).modPow(bobSecValue, p);

						String siv = inFromClient.readLine();
						byte[] iv = DatatypeConverter.parseBase64Binary(siv); // byte[] iv = Base64.getDecoder().decode(striv);
						IvParameterSpec ivSpec = new IvParameterSpec(iv);

						String data = secret.toString();
						MessageDigest md = MessageDigest.getInstance("SHA");
						byte[] dataDigest = md.digest(data.getBytes());
						byte[] keybytes = new byte[16];
						System.arraycopy(ByteBuffer.allocate(21).put(dataDigest).array(), 1, keybytes, 0, 16);

						SecretKey sKey = new SecretKeySpec(keybytes, "AES");

						Cipher ci = Cipher.getInstance("AES/CBC/PKCS5Padding");
						ci.init(Cipher.DECRYPT_MODE, sKey, ivSpec);

						String encrypted = inFromClient.readLine();
						byte[] enco = DatatypeConverter.parseBase64Binary(encrypted);
						byte[] decrypted = ci.doFinal(enco);

						String decryptedm = new String(decrypted, "UTF-8");
						String fromServer = "Server at port: " + conectionSocket.getLocalPort() + '\n'
								+ "Klient adress: " + conectionSocket.getInetAddress() + '\n'  
								+ "Wiadomosc: "+ decryptedm + '\n' 
								+ "Wspolny sekret Bob :   " + secret.toString() + '\n';
						outToClient.writeBytes(fromServer);
					} 
					else {
						outToClient.writeBytes("Brak weryfikacji podpisu");
					}
//					in.close();
//					out.close();
					inFromClient.close();
					outToClient.close();
				} catch (NoSuchAlgorithmException e) {
					e.printStackTrace();
				} catch (NoSuchPaddingException e) {
					e.printStackTrace();
				} catch (InvalidKeyException e) {
					e.printStackTrace();
				} catch (InvalidAlgorithmParameterException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} catch (IllegalBlockSizeException e) {
					e.printStackTrace();
				} catch (BadPaddingException e) {
					e.printStackTrace();
				} finally {
					conectionSocket.close();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			welcomSocket.close();
		}
	}
}

// zdanieClient = inFromClient.readLine();
// zdanieServer = zdanieClient.replace('j', 'k') + '\n' + "jak cos" + '\n' +
// "to"
// + '\n';
// outToClient.writeBytes(zdanieServer);
// inFromClient.close();
// outToClient.close();
// } finally {
// conectionSocket.close();
// }
//
// }
// } finally {
// welcomSocket.close();
// }
// }
// }
