/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Random;
import org.apache.tomcat.util.codec.binary.Base64;

/**
 *
 * @author sony
 */
public class Hashpassword {
    
    public static String hashPassword(String password) {
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("SHA-256");
		} catch (Exception e) {
		}
		md.update(password.getBytes());
		byte[] mdArray = md.digest();
		StringBuilder sb = new StringBuilder(mdArray.length * 2);
		for (byte b : mdArray) {
			int v = b & 0xff;
			if (v < 16) {
				sb.append('0');
			}
			sb.append(Integer.toHexString(v));
		}
		return sb.toString();
	}
        
        public static String getSalt() {
        Random r = new SecureRandom();
        byte[] saltBytes = new byte[32];
        r.nextBytes(saltBytes);
        return Base64.encodeBase64String(saltBytes);
    }
    
    public static String hashAndSaltPassword(String password,String salt)
             {
        
     //   return hashPassword(password + salt);
        String pwd= hashPassword(password + salt);
       System.out.println("Pwd:" +pwd.length());
       return pwd;
             }
    
	public static void main(String args[])
	{
		String s="harsh";
		String a=hashPassword(s);
		System.out.println(a);
		String x=hashPassword(s);
		
		if(x.equals(a))
		{
			System.out.println("test");
		}
			
	}
    
}
