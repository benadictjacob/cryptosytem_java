package hash;

/**
 * Test generating hash values from String.
 * @author www.codejava.net
 *
 */
public class StringHashGeneratorExample {
    
    
    
    
    
    public String hashget(String string) throws HashGenerationException{
        String sha1Hash = HashGeneratorUtils.generateSHA1(string);
			System.out.println("SHA-1 Hash: " + sha1Hash);
        return sha1Hash;
    }

	public static void main(String[] args) {
            
            
            
            
//		try {
////			String inputString = "jasmi";
////			System.out.println("Input String: " + inputString);
////
////			String md5Hash = HashGeneratorUtils.generateMD5(inputString);
////			System.out.println("MD5 Hash: " + md5Hash);
////
////			String sha1Hash = HashGeneratorUtils.generateSHA1(inputString);
////			System.out.println("SHA-1 Hash: " + sha1Hash);
////
////			String sha256Hash = HashGeneratorUtils.generateSHA256(inputString);
////			System.out.println("SHA-256 Hash: " + sha256Hash);
//		} catch (HashGenerationException ex) {
//			ex.printStackTrace();
//		}
	}

}
