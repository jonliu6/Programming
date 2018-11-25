package org.freecode.demo;

import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.xml.bind.DatatypeConverter;

public class ChecksumHelper {
	private final static String DEFAULT_ALGORITHM_TYPE = "MD5"; // default algorithm type; or SHA, SHA-1, SHA-256, SHA-512
	
	public static void main(String[] args) {
		if (args == null || args.length < 1) {
		    System.out.println("Usage: ChecksumHelper <plain text or a full file name with path> <algorithm type>");
		}
		else {
			String plainText = args[0];
			String algorithmType = DEFAULT_ALGORITHM_TYPE;
			if ( args.length > 1 ) {
				algorithmType = args[1];
			}
			
			System.out.println("Source: " + plainText );
			System.out.println("Algorithm: " + algorithmType );
			ChecksumHelper checksum = new ChecksumHelper();
			System.out.println("Hash: " + checksum.getHash(plainText, algorithmType) );
		}
	}

	/**
	 * the srcText can be plain text or a file name, so the hash is based on either plain text or the file content
	 * @param srcText
	 * @param algorithmType
	 * @return
	 */
	public String getHash(String srcText, String algorithmType) {
		MessageDigest md = null;
		String hashString = null;
		byte[] input = null;
		try {
			md = MessageDigest.getInstance(algorithmType);
			if (srcText.indexOf(".") > -1 ) { // likely a file name
				Path fileName = Paths.get(srcText);
				if (Files.exists(fileName)) {
					input = Files.readAllBytes(fileName);
				}
				
			}
			else { // treat as plain text content
				input = srcText.getBytes("UTF-8");
			}
			byte[] checksum = md.digest(input);
		    // String output = String.format("%032X", new BigInteger(1, checksum));
			hashString = DatatypeConverter.printHexBinary(checksum);
			if (hashString != null) {
				hashString = hashString.toLowerCase();
			}
		}
		catch (NoSuchAlgorithmException nsae) {
			nsae.printStackTrace();
		}
		catch (UnsupportedEncodingException uee) {
			uee.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	    
		return hashString;
	}
}
