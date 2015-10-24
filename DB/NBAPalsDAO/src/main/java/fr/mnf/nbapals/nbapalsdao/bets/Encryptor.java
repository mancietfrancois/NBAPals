/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.mnf.nbapals.nbapalsdao.bets;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

public class Encryptor {
    
    public static final int KEY_SIZE = 16;
    public static final String DEFAULT_KEY = "ThisIsASecretKey";
    public static final String KEY_2 = "fr.mnf.nbapals.=";
    
    public static String getKey1(String key1) {
        if (key1.length() == 0) {
            return DEFAULT_KEY;
        }
        if (key1.length() > KEY_SIZE) {
            return key1.substring(0, KEY_SIZE);
        } else {
            while(key1.length() < KEY_SIZE) {
                key1 = key1 + key1;
            }
            return key1.substring(0, KEY_SIZE);
        }
    }   
    
    public static String encrypt(String key1, String value) {
        try {
            IvParameterSpec iv = new IvParameterSpec(KEY_2.getBytes("UTF-8"));

            SecretKeySpec skeySpec = new SecretKeySpec(key1.getBytes("UTF-8"),
                    "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
            byte[] encrypted = cipher.doFinal(value.getBytes());
            System.out.println("encrypted string:"
                    + Base64.encodeBase64String(encrypted));
            return Base64.encodeBase64String(encrypted);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static String decrypt(String key1, String encrypted) {
        try {
            IvParameterSpec iv = new IvParameterSpec(KEY_2.getBytes("UTF-8"));

            SecretKeySpec skeySpec = new SecretKeySpec(key1.getBytes("UTF-8"),
                    "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
            byte[] original = cipher.doFinal(Base64.decodeBase64(encrypted));

            return new String(original);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

//    public static void main(String[] args) {
//
//        String key1 = "Bar12345Bar12345"; // 128 bit key
//        String key2 = "ThisIsASecretKet";
//        System.out.println(decrypt(key1, key2,
//                encrypt(key1, key2, "Hello World")));
//    }
}
