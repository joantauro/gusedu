package com.gusedu.util;

import java.security.MessageDigest;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

public class CypherUtil {

	private static String phrase = "Kangen Water, phrase to encrypt";

	public static String encrypt(String plainString){
		String encryptedString = "";
		MessageDigest digest;
		try{
			digest = MessageDigest.getInstance("SHA");
			digest.update(phrase.getBytes());
			
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			SecretKeySpec secretKey = new SecretKeySpec(digest.digest(), 0, 16, "AES");
			
			cipher.init(Cipher.ENCRYPT_MODE, secretKey);
			encryptedString = Base64.encodeBase64String(cipher.doFinal(plainString.getBytes()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return encryptedString;
	}
	/*
	public static String decrypt(String encryptedString){
		String plainString = "";
		MessageDigest digest;
		try{
			digest = MessageDigest.getInstance("SHA");
			digest.update(phrase.getBytes());
			
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			SecretKeySpec secretKey = new SecretKeySpec(digest.digest(), 0, 16, "AES");
			
			cipher.init(Cipher.DECRYPT_MODE, secretKey);
			plainString = new String(cipher.doFinal(Base64.decodeBase64(encryptedString)));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return encryptedString;
	}*/
}
