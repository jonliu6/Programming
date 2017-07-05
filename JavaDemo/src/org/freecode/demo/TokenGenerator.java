package org.freecode.demo;

import org.apache.commons.codec.binary.Base64;

public class TokenGenerator {
	
	public static void main(String[] args) {
		String uid = "jon";
		String dtStr = "2017-07-01 00:00:00";
		String orig = "192.168.1.1";
		
		String srcStr = uid + ":" + dtStr + ":" + orig;
		byte[] encStr = Base64.encodeBase64(srcStr.getBytes());
		String decStr = new String(Base64.decodeBase64(encStr));
		
		if (srcStr.equalsIgnoreCase(decStr)) {
			System.out.println("Successfully encoded and decoded.");
			System.out.println("Your information: " + decStr);
			System.out.println("Encoded: " + new String(encStr));
		}
		else {
			System.out.println("Something wrong happened when encoding and decoding...");
			System.out.println("Origin: " + srcStr);
			System.out.println("Destination: " + decStr);
		}
		System.out.println();
	}

}
