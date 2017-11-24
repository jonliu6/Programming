package org.freecode.demo;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.core.token.Sha512DigestUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        // System.out.println( "Hello World!" );
    	Md5PasswordEncoder encoder = new Md5PasswordEncoder();
    	BCryptPasswordEncoder encoder1 = new BCryptPasswordEncoder();
    	String userName = "jon";
    	System.out.println("MD5: " + encoder.encodePassword(userName, null));
    	System.out.println("BCrypt: " + encoder1.encode(userName));
        System.out.println("SHA: " + Sha512DigestUtils.shaHex(userName));    	
    }
}
