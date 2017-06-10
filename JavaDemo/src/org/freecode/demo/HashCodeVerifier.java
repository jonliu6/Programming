package org.freecode.demo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import javax.xml.bind.DatatypeConverter;

public class HashCodeVerifier {

	public static void main(String[] args) {
		
		String filePath = "c:/Temp/commons-fileupload-1.3.2-bin.zip";
		System.out.println("File path: " + filePath);
		System.out.println("MD5: " + generateMD5Hash(filePath, "MD5"));
		System.out.println("SHA1: " + generateMD5Hash(filePath, "SHA1"));
	}
	
	public static String generateMD5Hash(String filePath, String algorithm) {
		MessageDigest md = null;
		byte[] digestArray = null;
		String hashString = null;
		try {
			md = MessageDigest.getInstance(algorithm);
			md.update(Files.readAllBytes(Paths.get(filePath)));
			digestArray = md.digest();
		}
		catch (NoSuchAlgorithmException nsae) {
			nsae.printStackTrace();
		}
		catch (IOException ioe) {
			ioe.printStackTrace();
		}
		
		if ( digestArray != null ) {
			hashString = DatatypeConverter.printHexBinary(digestArray).toLowerCase();
		}
		
		return hashString;
	}

}
