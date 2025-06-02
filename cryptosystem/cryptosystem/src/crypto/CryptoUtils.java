package crypto;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * A utility class that encrypts or decrypts a file. 
 * @author www.codejava.net
 *
 */
public class CryptoUtils {
	private static final String ALGORITHM = "AES";
	private static final String TRANSFORMATION = "AES";

         static String IV = "AAAAAAAAAAAAAAAA";
	public static void encrypt(String key, File inputFile, File outputFile)
			throws CryptoException {
            System.out.println("datass"+key);
            key = key.trim();
		doCrypto(Cipher.ENCRYPT_MODE, key, inputFile, outputFile);
	}

	public static void decrypt(String key, File inputFile, File outputFile)
			throws CryptoException {
            System.out.println("datass"+key);
            System.out.println(""+inputFile.getAbsolutePath());
            System.out.println(""+outputFile.getAbsolutePath());
            key = key.trim();
		doCrypto(Cipher.DECRYPT_MODE, key, inputFile, outputFile);
           
	}

	private static void doCrypto(int cipherMode, String key, File inputFile,
			File outputFile) throws CryptoException {
		try {
			Key secretKey = new SecretKeySpec(key.getBytes(), ALGORITHM);
			Cipher cipher = Cipher.getInstance(TRANSFORMATION);
			cipher.init(cipherMode, secretKey);
			
			FileInputStream inputStream = new FileInputStream(inputFile);
			byte[] inputBytes = new byte[(int) inputFile.length()];
			inputStream.read(inputBytes);
			
			byte[] outputBytes = cipher.doFinal(inputBytes);
			
			FileOutputStream outputStream = new FileOutputStream(outputFile);
			outputStream.write(outputBytes);
			
			inputStream.close();
			outputStream.close();
			
		} catch (Exception ex) {
			//throw new CryptoException("Error encrypting/decrypting file", ex);
                        ex.printStackTrace();
		}
	}
        
        
           public static byte[] encryptText(String plainText, String encryptionKey) throws Exception {
    Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding", "SunJCE");
    SecretKeySpec key = new SecretKeySpec(encryptionKey.getBytes("UTF-8"), "AES");
    cipher.init(Cipher.ENCRYPT_MODE, key,new IvParameterSpec(IV.getBytes("UTF-8")));
    return cipher.doFinal(plainText.getBytes("UTF-8"));
  }
     
       public static String decryptText(byte[] cipherText, String encryptionKey) throws Exception{
    Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding", "SunJCE");
    SecretKeySpec key = new SecretKeySpec(encryptionKey.getBytes("UTF-8"), "AES");
    cipher.init(Cipher.DECRYPT_MODE, key,new IvParameterSpec(IV.getBytes("UTF-8")));
    return new String(cipher.doFinal(cipherText),"UTF-8");
  }   
        
           public static String encryptdata(String data,String kkkm) {
        String encrypted = null;
        String keys = "abcdefghijklmnop";//kkkm;System.out.println("Encryption started with key sk "+keys);
        String appen = "0";
         /*if(keys.length()<16) {
        for(int p =0;p<16-keys.length();p++){
       
        keys = new StringBuilder(appen).append(keys).toString();
        }}else*/ keys = keys.substring(0,16);System.out.println("Substring "+keys);
               System.out.println("key going to encrypt"+keys);
        byte[] key =new byte[16];
          key =keys.getBytes() ;//... secret sequence of bytes
         byte[] dataToSend = data.getBytes();
         Cipher c;
         byte[] encryptedData= null;
    try {
        c = Cipher.getInstance("AES");
        SecretKeySpec k = new SecretKeySpec(key, "AES");
        c.init(Cipher.ENCRYPT_MODE, k);
         encryptedData = c.doFinal(dataToSend);
         System.out.println("Encrypted data is"+encryptedData );
    } catch (NoSuchAlgorithmException ex) {
        Logger.getLogger(CryptoUtils.class.getName()).log(Level.SEVERE, null, ex);
    } catch (NoSuchPaddingException ex) {
        Logger.getLogger(CryptoUtils.class.getName()).log(Level.SEVERE, null, ex);
    } catch (InvalidKeyException ex) {
        Logger.getLogger(CryptoUtils.class.getName()).log(Level.SEVERE, null, ex);
    } catch (IllegalBlockSizeException ex) {
        Logger.getLogger(CryptoUtils.class.getName()).log(Level.SEVERE, null, ex);
    } catch (BadPaddingException ex) {
        Logger.getLogger(CryptoUtils.class.getName()).log(Level.SEVERE, null, ex);
    }
        encrypted = new String(encryptedData);
        System.out.println("in string "+encrypted);
        return encrypted;
        
    }
           
           
     public static String decryptSk(String sk, String prvtkeysub) throws UnsupportedEncodingException {
         
        System.out.print("Key fro "+sk+" df"+prvtkeysub);
      String Random_keysk = null;
       String keys = prvtkeysub;
        String appen = "0";
        /*if(keys.length()<16) {
        for(int p =0;p<16-keys.length();p++) {
        
        keys = new StringBuilder(appen).append(keys).toString();
        }}else {*/
            keys = "abcdefghijklmnop";//keys.substring(0,16);
        keys = keys.substring(0,16);
         System.out.println("JKey going to decrypt"+keys);
         byte[] key =new byte[16];
         key = keys.getBytes();
         byte[] encryptedData = sk.getBytes();System.out.println("  Encrypted  "+encryptedData);//... received from Alice

Cipher c;
    try {
        c = Cipher.getInstance("AES");
        SecretKeySpec k = new SecretKeySpec(key, "AES");
         c.init(Cipher.DECRYPT_MODE, k);
         byte[] data = c.doFinal(encryptedData);
         Random_keysk = new String(data);
    } catch (NoSuchAlgorithmException ex) {
        Logger.getLogger(CryptoUtils.class.getName()).log(Level.SEVERE, null, ex);
    } catch (NoSuchPaddingException ex) {
        Logger.getLogger(CryptoUtils.class.getName()).log(Level.SEVERE, null, ex);
    } catch (InvalidKeyException ex) {
        Logger.getLogger(CryptoUtils.class.getName()).log(Level.SEVERE, null, ex);
    } catch (IllegalBlockSizeException ex) {
        Logger.getLogger(CryptoUtils.class.getName()).log(Level.SEVERE, null, ex);
    } catch (BadPaddingException ex) {
        Logger.getLogger(CryptoUtils.class.getName()).log(Level.SEVERE, null, ex);
    }

              
      return Random_keysk;
    }
    
}
