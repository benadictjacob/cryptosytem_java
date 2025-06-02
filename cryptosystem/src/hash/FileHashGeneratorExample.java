package hash;

import java.io.File;

/**
 * Test generating hash values from File.
 * @author www.codejava.net
 *
 */
public class FileHashGeneratorExample {
    
    
    
    public String hashvalue(String filePath) throws HashGenerationException{
        String sha1Hash=null;
        
//			filePath = "C:\\Users\\user\\Documents\\mat.txt";
			System.out.println("File Path: " + filePath);
			File file = new File(filePath);
			
//			String md5Hash = HashGeneratorUtils.generateMD5(file);
//			System.out.println("MD5 Hash: " + md5Hash);
			
			 sha1Hash = HashGeneratorUtils.generateSHA1(file);
			System.out.println("SHA-1 Hash: " + sha1Hash);

//			String sha256Hash = HashGeneratorUtils.generateSHA256(file);
//			System.out.println("SHA-256 Hash: " + sha256Hash);			
                  
		
        return sha1Hash;
    }

	public static void main(String[] args) {
		
	}

}
