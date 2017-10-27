package org.freecode.demo;

import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

import javax.crypto.KeyGenerator;

import org.apache.commons.codec.binary.Base64;

public class TokenGenerator {
	
	public static void main(String[] args) {
		String uid = "jon";
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dtStr = df.format(cal.getTime()); // "2017-07-01 00:00:00";
		String appID = "PowerOn";
		
		String srcStr = uid + "|" + dtStr + "|" + appID;
		// int saltLen = uid.length();
		int saltLen = appID.length();
//		byte[] encStr = Base64.encodeBase64(srcStr.getBytes());
//		String decStr = new String(Base64.decodeBase64(encStr));
		// double encoded
		String encStr = encodeString(encodeString(srcStr, saltLen));
		String decStr = decodeString(decodeString(encStr), saltLen);
		
		if (srcStr.equalsIgnoreCase(decStr)) {
			System.out.println("Successfully encoded and decoded.");
			System.out.println("Your information: " + decStr);
			System.out.println("Encoded: " + new String(encStr));
			System.out.println("Decoded: " + new String(decStr));
		}
		else {
			System.out.println("Something wrong happened when encoding and decoding...");
			System.out.println("Origin: " + srcStr);
			System.out.println("Destination: " + decStr);
		}
		System.out.println("AES BASE 64 Encoded Sample: " + generateAESKey(appID + "Remote"));
		// System.out.println("Salt: " + createSalt(6));
	}
	
	public static String generateAESKey(String inputString) {
		KeyGenerator keyGen = null;
		try {
			keyGen = KeyGenerator.getInstance("AES");
		}
		catch (NoSuchAlgorithmException nsae) {
			nsae.printStackTrace();
		}
		keyGen.init(128); // due to US export law, JDK limits to 128 bits
		String key = Base64.encodeBase64String(keyGen.generateKey().getEncoded());
		return key; 
	}

	public static String createSalt(int len) {
		StringBuilder sb = new StringBuilder();
		Random rnd = new Random();
		for (int i=0; i<len; i++) {
			int num = rnd.nextInt(25);
			if (num % 2 == 0) {
				num += 65;
			}
			else {
				num += 97;
			}
			char c = (char) num;
			sb.append(c);
		}
		return sb.toString();	
	}
	
	public static String encodeString(String str) {
		String retStr = null;
		if (str != null) {
			byte[] encStr = Base64.encodeBase64(str.getBytes());
			retStr = new String(encStr);
		}
		
		return retStr;
	}
	
	public static String encodeString(String str, int saltLength) {
		StringBuilder retStr = new StringBuilder();
		if (str != null) {
			byte[] encStr = Base64.encodeBase64(str.getBytes());
			retStr.append(createSalt(saltLength));
			retStr.append(new String(encStr));
		}
		
		return retStr.toString();
	}
	
	public static String decodeString(String encStr) {
		return new String(Base64.decodeBase64(encStr));
	}
	
	public static String decodeString(String encStr, int saltLength) {
		String decStr = null;
		if (encStr != null && encStr.length() > saltLength) {
			String encStrWithoutSalt = encStr.substring(saltLength);
			if (encStrWithoutSalt != null) {
				decStr = new String(Base64.decodeBase64(encStrWithoutSalt));
			}
		}
		
		return decStr;
	}
}
