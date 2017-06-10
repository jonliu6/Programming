package org.freecode.demo;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.xml.bind.DatatypeConverter;

public class ChecksumHelper {
	private final static String DEFAULT_ALGORITHM_TYPE = "MD5"; // default algorithm type; or SHA1, SHA-256
	
	public static void main(String[] args) {
		if (args == null || args.length < 1) {
		    System.out.println("Usage: ChecksumHelper <plain text> <algorithm type>");
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

	public String getHash(String plainText, String algorithmType) {
		MessageDigest md = null;
		String hashString = null;
		try {
			md = MessageDigest.getInstance(algorithmType);
			byte[] checksum = md.digest(plainText.getBytes("UTF-8"));
		    // String output = String.format("%032X", new BigInteger(1, checksum));
			hashString = DatatypeConverter.printHexBinary(checksum);
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
